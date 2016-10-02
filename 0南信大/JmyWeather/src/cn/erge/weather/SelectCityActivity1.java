package cn.erge.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import cn.erge.weather.bean.JWeatherMeeageEntity;
import cn.erge.weather.dao.JWeatherMessageDao;
import cn.erge.weather.util.Constant;
import cn.erge.weather.util.UrlUtil;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SelectCityActivity1 extends Activity {
	private ListView listView;
	private TextView tv_text;
	private ArrayAdapter<String> adapter;
	private List<JWeatherMeeageEntity> cityAreas;
	private List<String> cityAreaNames;
	private ImageView iv_title;
	private TextView editText1;
	public static String city;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constant.SEL2_ACT_GET_CITY_ERROR:
				listView.setVisibility(View.GONE);
				tv_text.setVisibility(View.VISIBLE);
				break;
			case Constant.SEL2_ACT_GET_CITY_SUCCESS:
				tv_text.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);

				for (JWeatherMeeageEntity cityArea : cityAreas) {
					cityAreaNames.add(cityArea.getLocation());
				}
				adapter.notifyDataSetChanged();
				listView.setAdapter(adapter);
				break;
			default:
				break;
			}
		}
	};
	private String JCityEntityName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectcity);
		initViews();
		initDatas();
		initListener();
		JCityEntityName = getIntent().getStringExtra("JCityEntityName");
		BMainActivity.cityTest=JCityEntityName;
		Log.i("jmy", JCityEntityName);
		getCityAreas(JCityEntityName);
	}

	private void initListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent in=new Intent(SelectCityActivity1.this,
						BMainActivity.class);
				Log.i("jmy", JCityEntityName);
				in.putExtra("JCityEntityName", JCityEntityName);
				startActivity(in);
				finish();
			}

		});
		iv_title.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SelectCityActivity1.this.finish();
			}
		});

		editText1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s != null && s.toString() != "") {
					List<String> cityNamess = new ArrayList<String>();
					for (String cityName : cityAreaNames) {
						if (cityName.contains(s)) {
							cityNamess.add(cityName);
						}
					}
					adapter = new ArrayAdapter<String>(SelectCityActivity1.this,
							R.layout.listview_item, cityNamess);
					listView.setAdapter(adapter);
				} else {
					adapter = new ArrayAdapter<String>(SelectCityActivity1.this,
							R.layout.listview_item, cityAreaNames);
					listView.setAdapter(adapter);
				}
			}
		});
	}
	private void getCityAreas(final String jCityEntityName) {
		HttpUtils http = new HttpUtils(3000);
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("city", jCityEntityName);
		http.send(HttpMethod.POST, UrlUtil.BaseUrl1 + "2020",
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {

						try {
							cityAreas = new JWeatherMessageDao(response.result).weathers;
							if (null == cityAreas || cityAreas.size() <= 0) {
								handler.sendEmptyMessage(Constant.SEL2_ACT_GET_CITY_ERROR);
							} else {
								handler.sendEmptyMessage(Constant.SEL2_ACT_GET_CITY_SUCCESS);
							}
						} catch (XmlPullParserException e) {
							handler.sendEmptyMessage(Constant.SEL2_ACT_GET_CITY_ERROR);
							e.printStackTrace();
						} catch (IOException e) {
							handler.sendEmptyMessage(Constant.SEL2_ACT_GET_CITY_ERROR);
							e.printStackTrace();
						}
					}
				});
	}
	private void initDatas() {
		cityAreas = new ArrayList<JWeatherMeeageEntity>();
		cityAreaNames = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(SelectCityActivity1.this,
				R.layout.listview_item, cityAreaNames);
	}
	private void initViews() {
		listView = (ListView) findViewById(R.id.lv);
		iv_title = (ImageView) findViewById(R.id.iv_title);
		tv_text = (TextView) findViewById(R.id.tv_text);
		editText1 = (EditText) findViewById(R.id.editText1);
	}
}
