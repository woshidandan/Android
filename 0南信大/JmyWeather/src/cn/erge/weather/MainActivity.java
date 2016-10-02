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
 * @author k ���´�������дʱ�����ס��Ҫɾ��super��������
 * onCreate()
 * showWeatherBack()
 * wordWhite()
 * worldBlack()
 * 
 */
public class MainActivity extends BMainActivity {
	private TextView tv_title_update;
	// ��ǰ������ʾ �ؼ�
	private View weatherView;
	private TextView tv_weather_update_time;
	private CircleSegmentBar segmentbar_weather;
	private TextView tv_weather_weekday;
	private TextView tv_weather_condition;
	// ���ҳ���˵�
	private ListView main_left_drawer_list;
	private ListView main_right_drawer_list;
	// δ����������չʾ
	private TextView nextDay11, nextDay12, nextDay13, nextDay21, nextDay22,
			nextDay23, nextDay31, nextDay32, nextDay33, nextDay41, nextDay42,
			nextDay43;
	private ImageView next_day_image1, next_day_image2, next_day_image3,
			next_day_image4;

	// ��������
	private Process process = null;
	// ��λ
	private MyApp app;
	// ����������ˢ��
	private PullToRefreshListView ptrListView;
	// ����
	private ListView listView;//����ˢ�¶�Ӧ��listView
	
	private FrameLayout chartContainer;//ͼ����Ҫ�õ���FrameLayout
	private TextView select_city;//����ѡ��
	
	private DrawerLayout drawerLayout;//���벼��
	// ��handler����������ʾ����תȦ��ʱ�����ӳٸ�����ʾ
	private Handler handlerMain = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MAIN_ACT_GET_WEATHER_SUCCESS:// ����UI��ʾ��ʹ��Process
				if (process != null) {
					setStartWeather(
							"����ʱ�䣺" + process.realTime.getTime(),
							"����"
									+ weekNumber(Integer
											.parseInt(process.realTime
													.getWeek())),
							process.realTime.getWeather().getInfo() + "|������������",
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
	// ���ݳ�ʼ���������߼�����
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���ظ�����
		loadLayout(R.layout.activity_main);
		//��ʼ����ʾ�ؼ�
		initview();
		//��ʼ������ˢ��
		initPreToRefresh();
		//�����¼�
		setListener();
		//���õ�һ����ʾЧ��
		setFirstOpen();
		// �򿪣��Զ���λ������
		app = (MyApp) getApplication();
		//ע���������
		app.mLocationClient.registerLocationListener(new MyLocationListener() {
			@Override
			public void stopLocation(String city) {
				//��λ��������
				app.mLocationClient.stop();
				//��λ���չʾ
				tv_title_update.setText(city);
				//�����λʧ�ܣ���cityΪnull
				if (city == null) {
					getWeather("�Ͼ�");
				} else {
					getWeather(city);
				}
			}
		});
		//��ʼ��λ
		app.mLocationClient.start();
	}
	private void setFirstOpen() {
		//��ʾĬ�ϱ���Ч��
		showWeatherBack("��ɳ");
		//����Ĭ����ʾ����
		setStartWeather("����ʱ�䣺09:00", "���ڶ�", "��|������������", "32");
		//����δ����������
		setNextWeather(weatherIcon("����"), "����", "32��/21��", "����", 1);
		setNextWeather(weatherIcon("����"), "����", "32��/21��", "����", 2);
		setNextWeather(weatherIcon("����"), "����", "32��/21��", "����", 3);
		setNextWeather(weatherIcon("����"), "����", "32��/21��", "����", 4);
	}

	private void initPreToRefresh() {
		//��������������
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
		//���ptrListView�ṩ��listview
		listView = ptrListView.getRefreshableView();
		//����ͷView��ʾ
		listView.addHeaderView(weatherView);
		//���û���������ɫ
		listView.setCacheColorHint(R.color.transparent);		
	}

	// ���ؽ���
	public void initview() {
		//����ˢ��list
		ptrListView = (PullToRefreshListView) findViewById(R.id.pulltorefresh_listview);
		// ���һ��ˢ�µķ�ʽ���˷����������û��ӿ�ʼλ������
		ptrListView.setMode(Mode.PULL_FROM_START);
		//����δ�����������
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
		// ��ʼ����������
		main_left_drawer_list = (ListView) findViewById(R.id.main_left_drawer_list);
		main_right_drawer_list = (ListView) findViewById(R.id.main_right_drawer_list);
		main_left_drawer_list.setCacheColorHint(R.color.transparent);
		main_right_drawer_list.setCacheColorHint(R.color.transparent);
		main_left_drawer_list.setAdapter(new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				new String[] { "�յ�", "�˶�", "������", "��ð", "ϴ��", "��Ⱦ", "����" }));
		main_right_drawer_list.setAdapter(new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				new String[] { "����չʾ", "����", "�Ϻ�", "����", "�׵�", "��ѩ" }));
		// �������
		tv_title_update = (TextView) findViewById(R.id.tv_title_update);
		// ����
		chartContainer = (FrameLayout) findViewById(R.id.chartcontainer);
		select_city = (TextView) findViewById(R.id.select_city);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
	}

	public void setListener() {
		// �鿴��������
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
									getWeather("�Ͼ�");
								} else {
									getWeather(city);
								}
							}

						}); // ע���������
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
		// ����ivLeftͼƬ��ɫ�ӻ�ɫ--->��ɫ
		ivRight.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		ivRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (chartContainer.getVisibility() != View.VISIBLE) {
					chartContainer.setVisibility(View.VISIBLE);
					// ��֮ǰ����������ͼ�����
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
							getWeather("����");
							break;
						case 2:
							getWeather("�Ϻ�");
							break;
						case 3:
							getWeather("����");
							break;
						case 4:
							showWeatherBack("�׵�");
							break;
						case 5:
							showWeatherBack("��ѩ");
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


	// ��ȡĳ����������
	private void getWeather(String city) {
		//����Xutils���
		HttpUtils http = new HttpUtils(3000);
		String url = "http://op.juhe.cn/onebox/weather/query?cityname=" + city
				+ "&key=15b3860417a0875de210d562b0be2ce3";
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String msg) {
				Toast.makeText(MainActivity.this, "��ȡ����ʧ��", 1).show();
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

	// ���ÿ�ʼչʾ����
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

	// ����δ����������
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

	// �����¶�
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
									/ 100 + "��C");

						}
					});
				} else {
					// ptrListView.setMode(Mode.PULL_FROM_START);
				}
				handlerMain.postDelayed(this, 100);
			}
		}, 500);
	}

	// ��߳��뿪���¼�

	public void openLeftLayout() {

		if (drawerLayout.isDrawerOpen(main_left_drawer_list)) {

			drawerLayout.closeDrawer(main_left_drawer_list);

		} else {

			drawerLayout.openDrawer(main_left_drawer_list);

		}

	}

	// �ұ߳��뿪���¼�

	public void openRightLayout() {

		if (drawerLayout.isDrawerOpen(main_right_drawer_list)) {

			drawerLayout.closeDrawer(main_right_drawer_list);

		} else {

			drawerLayout.openDrawer(main_right_drawer_list);
		}
	}
//��������״���õ���Ӧ��ͼƬ��ʾ
	private int weatherIcon(String weather) {
		int res = 0;
		if (weather.equals("����")) {
			res = R.drawable.a323;
		} else if (weather.equals("����")) {
			res = R.drawable.a321;
		} else if (weather.equals("����")) {
			res = R.drawable.a305;
		} else if (weather.equals("ҹ��")) {
			res = R.drawable.a305;
		} else if (weather.equals("����")) {
			res = R.drawable.a309;
		} else if (weather.equals("����")) {
			res = R.drawable.a313;
		} else if (weather.equals("��ɳ")) {
			res = R.drawable.a300;
		} else if (weather.equals("��ѩ")) {
			res = R.drawable.a307;
		} else if (weather.equals("��ѩ")) {
			res = R.drawable.a303;
		} else if (weather.equals("�׵�")) {
			res = R.drawable.a301;
		} else if (weather.equals("ҹ��")) {
			res = R.drawable.a311;
		} else {// ����
			res = R.drawable.a306;
		}
		return res;
	}
	//����ת������Ӧ������ת��Ϊ��Ӧ���ַ���
	private String weekNumber(int number) {
		String week = "";
		switch (number) {
		case 1:
			week = "һ";
			break;
		case 2:
			week = "��";
			break;
		case 3:
			week = "��";
			break;
		case 4:
			week = "��";
			break;
		case 5:
			week = "��";
			break;
		case 6:
			week = "��";
			break;
		case 7:
			week = "��";
			break;
		}
		return week;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // ����˫���˳�����
		}
		return false;
	}
	private static Boolean isExit = false;
	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // ׼���˳�
			Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // ȡ���˳�
				}
			}, 2000); // ���2������û�а��·��ؼ�����������ʱ��ȡ�����ղ�ִ�е�����
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
	 * ����δ����������ͼ����ӵ�������
	 * FIXME
	 * @param chartContainer2
	 */
	private void initChart(FrameLayout chartContainer2) {

		String[] titles = new String[] { "����¶�", "����¶�" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7 });
		}
		List<double[]> values = new ArrayList<double[]>();

		double[] days = new double[] { 32, 35, 28, 32, 32, 34, 27 };// ����¶�
		double[] nights = new double[] { 24, 26, 18, 20, 24, 26, 20 };// ����¶�

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
			// ����ÿ�����ߵġ��㡱��ʵ�ĵ�
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}

		setChartSettings(renderer, "�¶�����", "δ������", "�¶�", 0.5, 7.5, -10, 40,
				Color.LTGRAY, Color.LTGRAY);

		// x������Ŷ��ٸ�Label
		renderer.setXLabels(7);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		// ���½����÷Ŵ���С��
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
		// ����X Y ����������Ĵ�С
		renderer.setAxisTitleTextSize(16);
		// ͼ��ı������ִ�С
		renderer.setChartTitleTextSize(20);
		// x y �������ֵĴ�С
		renderer.setLabelsTextSize(15);
		// ͼ�������ִ�С
		renderer.setLegendTextSize(15);
		// ����ͼ���㡱�Ĵ�С
		renderer.setPointSize(5f);
		// ����ͼ��ı߾�
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
