package cn.erge.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONException;

import cn.erge.jmyweather.Process;
import cn.erge.weather.app.MyApp;
import cn.erge.weather.location.MyLocationListener;
import cn.erge.weather.util.Constant;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.natasa.progressviews.CircleSegmentBar;
import com.natasa.progressviews.utils.ProgressStartPoint;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/**
 * @author k 以下代码在书写时，请记住不要删除super方法调用
 * onCreate()
 * showWeatherBack()
 * wordWhite()
 * worldBlack()
 * 
 */
public class MainActivity extends BMainActivity {
	private TextView tv_title_update;
	// 当前天气显示 控件
	private View weatherView;
	private TextView tv_weather_update_time;
	private CircleSegmentBar segmentbar_weather;
	private TextView tv_weather_weekday;
	private TextView tv_weather_condition;
	// 左右抽屉菜单
	private ListView main_left_drawer_list;
	private ListView main_right_drawer_list;
	// 未来几天天气展示
	private TextView nextDay11, nextDay12, nextDay13, nextDay21, nextDay22,
			nextDay23, nextDay31, nextDay32, nextDay33, nextDay41, nextDay42,
			nextDay43;
	private ImageView next_day_image1, next_day_image2, next_day_image3,
			next_day_image4;

	// 解析工具
	private Process process = null;
	// 定位
	private MyApp app;
	// 主界面下拉刷新
	private PullToRefreshListView ptrListView;
	// 其他
	private ListView listView;//下拉刷新对应的listView
	
	private FrameLayout chartContainer;//图标需要用到的FrameLayout
	private TextView select_city;//天气选择
	
	private DrawerLayout drawerLayout;//抽屉布局
	// 主handler控制天气显示，在转圈的时候发送延迟更新显示
	private Handler handlerMain = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MAIN_ACT_GET_WEATHER_SUCCESS:// 更新UI显示，使用Process
				if (process != null) {
					setStartWeather(
							"更新时间：" + process.realTime.getTime(),
							"星期"
									+ weekNumber(Integer
											.parseInt(process.realTime
													.getWeek())),
							process.realTime.getWeather().getInfo() + "|空气质量：良",
							process.realTime.getWeather().getTemperature());
					for (int i = 1; i <= 4; i++) {
						setNextWeather(weatherIcon(process.nextWeathers.get(i)
								.getWeather()), process.nextWeathers.get(i)
								.getWeather(), process.nextWeathers.get(i)
								.getTempeare(), process.nextWeathers.get(i)
								.getWeek(), i);
					}
					tv_title_update.setText(""
							+ process.realTime.getCity_name());
					showWeatherBack(process.realTime.getWeather().getInfo());
				}

				break;
			case Constant.MAIN_ACT_GET_WEATHER_ERROR:
				setFirstOpen();
				break;
			default:
				break;
			}

		};
	};
	// 数据初始化工作，逻辑处理
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载跟布局
		loadLayout(R.layout.activity_main);
		//初始化显示控件
		initview();
		//初始化下拉刷新
		initPreToRefresh();
		//设置事件
		setListener();
		//设置第一次显示效果
		setFirstOpen();
		// 打开，自动定位到本地
		app = (MyApp) getApplication();
		//注册监听函数
		app.mLocationClient.registerLocationListener(new MyLocationListener() {
			@Override
			public void stopLocation(String city) {
				//定位结束监听
				app.mLocationClient.stop();
				//定位结果展示
				tv_title_update.setText(city);
				//如果定位失败，则city为null
				if (city == null) {
					getWeather("南京");
				} else {
					getWeather(city);
				}
			}
		});
		//开始定位
		app.mLocationClient.start();
	}
	private void setFirstOpen() {
		//显示默认背景效果
		showWeatherBack("风沙");
		//设置默认显示数据
		setStartWeather("更新时间：09:00", "星期二", "晴|空气质量：良", "32");
		//设置未来几天天气
		setNextWeather(weatherIcon("晴天"), "晴天", "32°/21°", "周三", 1);
		setNextWeather(weatherIcon("晴天"), "晴天", "32°/21°", "周四", 2);
		setNextWeather(weatherIcon("晴天"), "晴天", "32°/21°", "周五", 3);
		setNextWeather(weatherIcon("晴天"), "晴天", "32°/21°", "周六", 4);
	}

	private void initPreToRefresh() {
		//加载主天气界面
		weatherView = getLayoutInflater()
				.inflate(R.layout.weather_layout, null);
		tv_weather_update_time = (TextView) weatherView
				.findViewById(R.id.tv_weather_update_time);
		segmentbar_weather = (CircleSegmentBar)weatherView
				.findViewById(R.id.segmentbar_weather);
		tv_weather_weekday = (TextView) weatherView
				.findViewById(R.id.tv_weather_weekday);
		tv_weather_condition = (TextView) weatherView
				.findViewById(R.id.tv_weather_condition);
		//获得ptrListView提供的listview
		listView = ptrListView.getRefreshableView();
		//设置头View显示
		listView.addHeaderView(weatherView);
		//设置滑动缓存颜色
		listView.setCacheColorHint(R.color.transparent);		
	}

	// 加载界面
	public void initview() {
		//下拉刷新list
		ptrListView = (PullToRefreshListView) findViewById(R.id.pulltorefresh_listview);
		// 添加一个刷新的方式，此方法仅允许用户从开始位置下拉
		ptrListView.setMode(Mode.PULL_FROM_START);
		//加载未来几天的天气
		nextDay11 = (TextView) findViewById(R.id.nextDay11);
		nextDay12 = (TextView) findViewById(R.id.nextDay12);
		nextDay13 = (TextView) findViewById(R.id.nextDay13);
		nextDay21 = (TextView) findViewById(R.id.nextDay21);
		nextDay22 = (TextView) findViewById(R.id.nextDay22);
		nextDay23 = (TextView) findViewById(R.id.nextDay23);
		nextDay31 = (TextView) findViewById(R.id.nextDay31);
		nextDay32 = (TextView) findViewById(R.id.nextDay32);
		nextDay33 = (TextView) findViewById(R.id.nextDay33);
		nextDay41 = (TextView) findViewById(R.id.nextDay41);
		nextDay42 = (TextView) findViewById(R.id.nextDay42);
		nextDay43 = (TextView) findViewById(R.id.nextDay43);
		next_day_image1 = (ImageView) findViewById(R.id.next_day_image1);
		next_day_image2 = (ImageView) findViewById(R.id.next_day_image2);
		next_day_image3 = (ImageView) findViewById(R.id.next_day_image3);
		next_day_image4 = (ImageView) findViewById(R.id.next_day_image4);
		// 初始化抽屉数据
		main_left_drawer_list = (ListView) findViewById(R.id.main_left_drawer_list);
		main_right_drawer_list = (ListView) findViewById(R.id.main_right_drawer_list);
		main_left_drawer_list.setCacheColorHint(R.color.transparent);
		main_right_drawer_list.setCacheColorHint(R.color.transparent);
		main_left_drawer_list.setAdapter(new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				new String[] { "空调", "运动", "紫外线", "感冒", "洗车", "污染", "穿衣" }));
		main_right_drawer_list.setAdapter(new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				new String[] { "天气展示", "北京", "上海", "重庆", "雷电", "雨雪" }));
		// 标题更新
		tv_title_update = (TextView) findViewById(R.id.tv_title_update);
		// 其他
		chartContainer = (FrameLayout) findViewById(R.id.chartcontainer);
		select_city = (TextView) findViewById(R.id.select_city);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
	}

	public void setListener() {
		// 查看天气试例
		ptrListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				handlerMain.removeCallbacksAndMessages(null);
				String label = DateUtils.formatDateTime(
						getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				getWeather(tv_title_update.getText().toString());
			}

		});
		tv_title_update.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				app = (MyApp) getApplication();
				app.mLocationClient
						.registerLocationListener(new MyLocationListener() {

							@Override
							public void stopLocation(String city) {
								app.mLocationClient.stop();
								tv_title_update.setText(city);
								if (city == null) {
									getWeather("南京");
								} else {
									getWeather(city);
								}
							}

						}); // 注册监听函数
				app.mLocationClient.start();

			}
		});
		select_city.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						SelectCityActivity.class));
			}
		});
		ImageView ivRight = (ImageView) findViewById(R.id.iv_headerview_right);
		// ivLeft.setImageResource(resId);
		// 设置ivLeft图片颜色从灰色--->白色
		ivRight.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		ivRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (chartContainer.getVisibility() != View.VISIBLE) {
					chartContainer.setVisibility(View.VISIBLE);
					// 把之前的天气走势图清除掉
					chartContainer.removeAllViews();
					initChart(chartContainer);
				} else {
					chartContainer.setVisibility(View.GONE);
				}

			}
		});
		main_right_drawer_list
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						switch (position) {
						case 1:
							getWeather("北京");
							break;
						case 2:
							getWeather("上海");
							break;
						case 3:
							getWeather("重庆");
							break;
						case 4:
							showWeatherBack("雷电");
							break;
						case 5:
							showWeatherBack("雨雪");
							break;
						}
						openRightLayout();
					}
				});
		main_left_drawer_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent in = new Intent(MainActivity.this,
						MainLeftActivity.class);
				in.putExtra("position", position);
				in.putExtra("life", process.life);
				startActivity(in);
				openLeftLayout();
			}
		});
	}

	@Override
	public void showWeatherBack(String weather) {
		super.showWeatherBack(weather);
	}

	@Override
	public void wordBlack() {
		super.wordBlack();
		int color = this.getResources().getColor(R.color.Myblack);
		tv_title_update.setTextColor(color);
		tv_weather_update_time.setTextColor(color);
		tv_weather_weekday.setTextColor(color);
		tv_weather_condition.setTextColor(color);
		segmentbar_weather.setTextColor(color);
		nextDay11.setTextColor(color);
		nextDay12.setTextColor(color);
		nextDay13.setTextColor(color);
		nextDay21.setTextColor(color);
		nextDay22.setTextColor(color);
		nextDay23.setTextColor(color);
		nextDay31.setTextColor(color);
		nextDay32.setTextColor(color);
		nextDay33.setTextColor(color);
		nextDay41.setTextColor(color);
		nextDay42.setTextColor(color);
		nextDay43.setTextColor(color);
	}

	@Override
	public void wordWhite() {
		super.wordWhite();
		int color = this.getResources().getColor(R.color.MyWhite);
		tv_title_update.setTextColor(color);
		tv_weather_update_time.setTextColor(color);
		tv_weather_weekday.setTextColor(color);
		tv_weather_condition.setTextColor(color);
		segmentbar_weather.setTextColor(color);
		nextDay11.setTextColor(color);
		nextDay12.setTextColor(color);
		nextDay13.setTextColor(color);
		nextDay21.setTextColor(color);
		nextDay22.setTextColor(color);
		nextDay23.setTextColor(color);
		nextDay31.setTextColor(color);
		nextDay32.setTextColor(color);
		nextDay33.setTextColor(color);
		nextDay41.setTextColor(color);
		nextDay42.setTextColor(color);
		nextDay43.setTextColor(color);
	}


	// 获取某个城市天气
	private void getWeather(String city) {
		//采用Xutils框架
		HttpUtils http = new HttpUtils(3000);
		String url = "http://op.juhe.cn/onebox/weather/query?cityname=" + city
				+ "&key=15b3860417a0875de210d562b0be2ce3";
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String msg) {
				Toast.makeText(MainActivity.this, "获取天气失败", 1).show();
				handlerMain.sendEmptyMessage(Constant.MAIN_ACT_GET_WEATHER_ERROR);
				//FIXME
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				process = new Process();
				try {
					process.processData(responseInfo.result);
					handlerMain.sendEmptyMessage(Constant.MAIN_ACT_GET_WEATHER_SUCCESS);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 设置开始展示天气
	private void setStartWeather(String updateTime, String xinqi,
			String weatherCondition, String tem) {
		ptrListView.onRefreshComplete();
		listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, new String[] {}));
		tv_weather_update_time.setText(updateTime);
		tv_weather_weekday.setText(xinqi);
		tv_weather_condition.setText(weatherCondition);

		segmentbar_weather.setCircleViewPadding(2);
		// segmentbar_weather.setWidth(250);
		segmentbar_weather.setWidthProgressBackground(25);
		segmentbar_weather.setWidthProgressBarLine(25);
		// you can set start position for progress
		segmentbar_weather.setStartPositionInDegrees(ProgressStartPoint.BOTTOM);

		// you can set linear gradient with default colors or to set yours
		// colors, or sweep gradient
		segmentbar_weather.setLinearGradientProgress(true);
		setTemperature(tem);
	}

	// 设置未来几天天气
	public void setNextWeather(int weatherResId, String s1, String s2,
			String s3, int nextday) {
		switch (nextday) {
		case 1:
			next_day_image1.setImageResource(weatherResId);
			nextDay11.setText(s1);
			nextDay12.setText(s2);
			nextDay13.setText(s3);
			break;
		case 2:
			next_day_image2.setImageResource(weatherResId);
			nextDay21.setText(s1);
			nextDay22.setText(s2);
			nextDay23.setText(s3);
			break;
		case 3:
			next_day_image3.setImageResource(weatherResId);
			nextDay31.setText(s1);
			nextDay32.setText(s2);
			nextDay33.setText(s3);
			break;
		case 4:
			next_day_image4.setImageResource(weatherResId);
			nextDay41.setText(s1);
			nextDay42.setText(s2);
			nextDay43.setText(s3);
			break;
		}
	}

	// 设置温度
	protected void setTemperature(final String temperature) {
		handlerMain.removeCallbacksAndMessages(null);
		// ptrListView.setMode(Mode.DISABLED);
		handlerMain.postDelayed(new Runnable() {
			int progress = 0;
			@Override
			public void run() {
				progress += 2;
				if (progress <= (Integer.parseInt(temperature) * 100) / 50) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							segmentbar_weather.setProgress(progress);
							segmentbar_weather.setText("" + (progress * 50)
									/ 100 + "°C");

						}
					});
				} else {
					// ptrListView.setMode(Mode.PULL_FROM_START);
				}
				handlerMain.postDelayed(this, 100);
			}
		}, 500);
	}

	// 左边抽屉开关事件

	public void openLeftLayout() {

		if (drawerLayout.isDrawerOpen(main_left_drawer_list)) {

			drawerLayout.closeDrawer(main_left_drawer_list);

		} else {

			drawerLayout.openDrawer(main_left_drawer_list);

		}

	}

	// 右边抽屉开关事件

	public void openRightLayout() {

		if (drawerLayout.isDrawerOpen(main_right_drawer_list)) {

			drawerLayout.closeDrawer(main_right_drawer_list);

		} else {

			drawerLayout.openDrawer(main_right_drawer_list);
		}
	}
//根据天气状况得到对应的图片显示
	private int weatherIcon(String weather) {
		int res = 0;
		if (weather.equals("晴天")) {
			res = R.drawable.a323;
		} else if (weather.equals("多云")) {
			res = R.drawable.a321;
		} else if (weather.equals("阴天")) {
			res = R.drawable.a305;
		} else if (weather.equals("夜阴")) {
			res = R.drawable.a305;
		} else if (weather.equals("大雾")) {
			res = R.drawable.a309;
		} else if (weather.equals("雾霾")) {
			res = R.drawable.a313;
		} else if (weather.equals("风沙")) {
			res = R.drawable.a300;
		} else if (weather.equals("下雪")) {
			res = R.drawable.a307;
		} else if (weather.equals("雨雪")) {
			res = R.drawable.a303;
		} else if (weather.equals("雷电")) {
			res = R.drawable.a301;
		} else if (weather.equals("夜晴")) {
			res = R.drawable.a311;
		} else {// 下雨
			res = R.drawable.a306;
		}
		return res;
	}
	//日期转换，对应的数字转化为对应的字符串
	private String weekNumber(int number) {
		String week = "";
		switch (number) {
		case 1:
			week = "一";
			break;
		case 2:
			week = "二";
			break;
		case 3:
			week = "三";
			break;
		case 4:
			week = "四";
			break;
		case 5:
			week = "五";
			break;
		case 6:
			week = "六";
			break;
		case 7:
			week = "日";
			break;
		}
		return week;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
		}
		return false;
	}
	private static Boolean isExit = false;
	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
			finish();
			System.exit(0);
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		if (cityTest != null && !cityTest.equals("")) {
			getWeather(cityTest);
		}
	}
	/**
	 * 绘制未来天气走势图，添加到容器中
	 * FIXME
	 * @param chartContainer2
	 */
	private void initChart(FrameLayout chartContainer2) {

		String[] titles = new String[] { "最高温度", "最低温度" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7 });
		}
		List<double[]> values = new ArrayList<double[]>();

		double[] days = new double[] { 32, 35, 28, 32, 32, 34, 27 };// 最高温度
		double[] nights = new double[] { 24, 26, 18, 20, 24, 26, 20 };// 最低温度

		/*
		 * List<Weather> list = weather.getResult().getData().getWeather();
		 * for(int i=0;i<list.size();i++){ days[i] =
		 * Double.parseDouble(list.get(i).getInfo().getDay().get(2)); nights[i]
		 * = Double.parseDouble(list.get(i).getInfo().getNight().get(2)); }
		 */
		values.add(days);
		values.add(nights);
		int[] colors = new int[] { Color.GREEN, Color.YELLOW };
		PointStyle[] styles = new PointStyle[] { PointStyle.DIAMOND,
				PointStyle.SQUARE };

		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);

		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			// 设置每条折线的“点”是实心的
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}

		setChartSettings(renderer, "温度走势", "未来七天", "温度", 0.5, 7.5, -10, 40,
				Color.LTGRAY, Color.LTGRAY);

		// x轴上面放多少个Label
		renderer.setXLabels(7);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		// 右下角设置放大缩小的
		// renderer.setZoomButtonsVisible(true);
		// renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		// renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		// XYSeries series = dataset.getSeriesAt(0);
		// series.addAnnotation("Vacation", 6, 30);
		View chart = ChartFactory.getLineChartView(this, dataset, renderer);
		chartContainer2.addView(chart);
	}

	private XYMultipleSeriesDataset buildDataset(String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;
	}

	private void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
			List<double[]> xValues, List<double[]> yValues, int scale) {

		int length = titles.length;
		for (int i = 0; i < length; i++) {
			XYSeries series = new XYSeries(titles[i], scale);
			double[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
	}

	private void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}

	private XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}

	private void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
			PointStyle[] styles) {
		// 设置X Y 轴标题的字体的大小
		renderer.setAxisTitleTextSize(16);
		// 图表的标题文字大小
		renderer.setChartTitleTextSize(20);
		// x y 轴上数字的大小
		renderer.setLabelsTextSize(15);
		// 图例的文字大小
		renderer.setLegendTextSize(15);
		// 折线图“点”的大小
		renderer.setPointSize(5f);
		// 设置图表的边距
		renderer.setMargins(new int[] { 20, 30, 15, 20 });

		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			r.setDisplayChartValues(true);
			renderer.addSeriesRenderer(r);
		}
	}
}
