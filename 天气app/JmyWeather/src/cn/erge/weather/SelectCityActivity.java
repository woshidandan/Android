package cn.erge.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import cn.erge.weather.bean.JCityEntity;
import cn.erge.weather.bean.JWeatherMeeageEntity;
import cn.erge.weather.dao.JCityDao;
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
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectCityActivity extends Activity {
	private ListView listView;
	private TextView tv_text;
	private List<JCityEntity> citys;
	private List<String> cityNames;
	private ArrayAdapter<String> adapter;
	private ImageView iv_title;
	private EditText editText1;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constant.SEL1_ACT_GET_CITY_ERROR1:
				listView.setVisibility(View.GONE);
				tv_text.setVisibility(View.VISIBLE);
				break;
			case Constant.SEL1_ACT_GET_CITY_ERROR:
				Toast.makeText(getApplicationContext(), "服务器异常", 0).show();
			case Constant.SEL1_ACT_GET_CITY_SUCCESS:
				tv_text.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);
				if (citys != null && citys.size() > 0) {
					for (JCityEntity city : citys) {
						cityNames.add(city.getName());
					}
					cityNames.add("其他城市");
				}
				adapter = new ArrayAdapter<String>(SelectCityActivity.this,
						R.layout.listview_item, cityNames);
				listView.setAdapter(adapter);
				break;
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectcity);
		initViews();
		initDatas();
		initListener();
		getCitys();
	}

	private void initListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(cityNames.get(position).equals("其他城市")){
					Toast.makeText(SelectCityActivity.this, "暂时不支持其他城市", 0).show();
			    }else{
				Intent intent = new Intent(SelectCityActivity.this,
						SelectCityActivity1.class);
				intent.putExtra("JCityEntityName", citys.get(position)
						.getName());
				startActivity(intent);
				}
			}
		});
		iv_title.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SelectCityActivity.this.finish();
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
					for (String cityName : cityNames) {
						if (cityName.contains(s)) {
							cityNamess.add(cityName);
						}
					}
					adapter = new ArrayAdapter<String>(SelectCityActivity.this,
							R.layout.listview_item, cityNamess);
					listView.setAdapter(adapter);
				} else {
					adapter = new ArrayAdapter<String>(SelectCityActivity.this,
							R.layout.listview_item, cityNames);
					listView.setAdapter(adapter);
				}
			}
		});

	}

	private void getCitys() {
		HttpUtils http = new HttpUtils(3000);
		String url = UrlUtil.BaseUrl1 + "2070";
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Message msg = new Message();
				msg.what = Constant.SEL1_ACT_GET_CITY_ERROR;
				msg.obj = arg1;
				handler.sendMessage(msg);

			}
			@Override
			public void onSuccess(ResponseInfo<String> response) {
				try {
					citys = new JCityDao(response.result).citys;
					if (null == citys || citys.size() < 0) {
						handler.sendEmptyMessage(Constant.SEL1_ACT_GET_CITY_ERROR1);
					} else {
						handler.sendEmptyMessage(Constant.SEL1_ACT_GET_CITY_SUCCESS);
					}
				} catch (XmlPullParserException e) {
					handler.sendEmptyMessage(2);
					e.printStackTrace();
				} catch (Exception e) {
					handler.sendEmptyMessage(2);
					e.printStackTrace();
				}
			}
		});
	}
	private void initDatas() {
		cityNames = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(SelectCityActivity.this,
				R.layout.listview_item, cityNames);
	}
	private void initViews() {
		listView = (ListView) findViewById(R.id.lv);
		editText1 = (EditText) findViewById(R.id.editText1);
		iv_title = (ImageView) findViewById(R.id.iv_title);
		tv_text = (TextView) findViewById(R.id.tv_text);
	}
}
