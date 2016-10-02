package cn.erge.weather.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class WeatherData {
	/**
	 * 根据城市 ，连接到网络获得原始的JSON字符串
	 * 
	 * @param name
	 *            城市
	 * @return JOSN
	 */
	public static JSONObject getJsonData(final String name) {
		JSONObject data = null;
		try {
			String uri = UrlUtil.BaseUrl+"/weather/query?cityname="
					+ name + "&key="+UrlUtil.appkey;
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(uri);
			HttpResponse respon = client.execute(get);
			HttpEntity entity = respon.getEntity();
			String content = EntityUtils.toString(entity);
			JSONObject jsonobject = new JSONObject(content);
			JSONObject result = jsonobject.getJSONObject("result");
			data = result.getJSONObject("data");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	};

	public static JSONArray getJsonXiaohua(final int page) {
		JSONArray data = null;
		try {
			String uri = "http://japi.juhe.cn/joke/content/list.from?key=" +
					"22ed3efc62b305a0bc75da7b4bd50c93" +
					"&page=" +page+
					"&pagesize=10&sort=asc&time=1418745237";
			Log.d("", uri);
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(uri);
			HttpResponse respon = client.execute(get);
			HttpEntity entity = respon.getEntity();
			String content = EntityUtils.toString(entity);
			JSONObject jsonobject = new JSONObject(content);
			JSONObject result = jsonobject.getJSONObject("result");
			Log.d("", result.toString());
			data = result.getJSONArray("data");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	};

}
