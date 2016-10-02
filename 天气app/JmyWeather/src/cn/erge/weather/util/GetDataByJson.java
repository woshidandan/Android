package cn.erge.weather.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.erge.weather.bean.FutureWeather;
import cn.erge.weather.bean.Life;
import cn.erge.weather.bean.Pm25;
import cn.erge.weather.bean.RealTime;

/**
 * 根据json解析出所有的单条数据
 * 
 * @author tarena
 * 
 */
public class GetDataByJson {
	private JSONObject json;

	public GetDataByJson(JSONObject json) {
		super();
		this.json = json;
	}
	/**
	 * 根据json解析出realtime
	 * 
	 * @return
	 */
	public RealTime getRealTime() {
		RealTime realtime = null;
		try {
			JSONObject jsonObj = json.getJSONObject("realtime");
			String city_code = jsonObj.getString("city_code");
			String city_name = jsonObj.getString("city_name");
			String date = jsonObj.getString("date");
			String time = jsonObj.getString("time");
			String moon = jsonObj.getString("moon");
			int dataUptime = jsonObj.getInt("dataUptime");
			int week = jsonObj.getInt("week");

			JSONObject wind = jsonObj.getJSONObject("wind");
			String direct = wind.getString("direct");
			String power = wind.getString("power");

			JSONObject weather = jsonObj.getJSONObject("weather");
			String info = weather.getString("info");
			String img = weather.getString("img");
			String temperature = weather.getString("temperature");
			String humidity = weather.getString("humidity");

			realtime = new RealTime(city_code, city_name, date, time, week,
					moon, dataUptime, temperature, humidity, info, img, direct,
					power);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return realtime;
	}

	/**
	 * 根据json获取Pm25的所有的信息
	 * 
	 * @return
	 */
	public Pm25 getpm25() {
		Pm25 pm = null;
		try {
			String str = json.getString("pm25");
			if (str.length() < 3) {
				return null;
			}
			JSONObject jsonObj = json.getJSONObject("pm25");
			String key = jsonObj.getString("key");
			int show_desc = jsonObj.getInt("show_desc");
			JSONObject pm250 = jsonObj.getJSONObject("pm25");
			String curPm = pm250.getString("curPm");
			String pm25 = pm250.getString("pm25");
			String pm10 = pm250.getString("pm10");
			int level = pm250.getInt("level");
			String quality = pm250.getString("quality");
			String des = pm250.getString("des");
			pm = new Pm25(key, show_desc, curPm, pm25, pm10, level, quality,
					des);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pm;
	}

	/**
	 * 根据json字符串获取FutureWeather信息
	 * 
	 * @param json
	 * @return
	 */
	public List<FutureWeather> getFurure() {
		List<FutureWeather> futures = new ArrayList<FutureWeather>();
		FutureWeather weather;
		try {
			JSONArray jsonweather = json.getJSONArray("weather");
			for (int i = 0; i < jsonweather.length(); i++) {
				String s = jsonweather.getString(i);
				JSONObject info = new JSONObject(s);
				String date = info.getString("date");
				String week = info.getString("week");
				String nongli = info.getString("nongli");
				JSONObject infos = info.getJSONObject("info");
				JSONArray json = infos.getJSONArray("day");
				String day = json.getString(0) + "/" + json.getString(1) + "/"
						+ json.getString(2) + "/" + json.getString(3) + "/"
						+ json.getString(4) + "/" + json.getString(5);
				JSONArray d = infos.getJSONArray("night");
				String night = d.getString(0) + "/" + d.getString(1) + "/"
						+ d.getString(2) + "/" + d.getString(3) + "/"
						+ d.getString(4) + "/" + d.getString(5);
				weather = new FutureWeather(date, week, nongli, day, night);
				futures.add(weather);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return futures;
	}

	/**
	 * 根据json字符串获取life信息
	 * 
	 * @param json
	 * @return
	 */
	public Life getLife() {
		Life life = null;
		try {
			JSONObject jsonlife = json.getJSONObject("life");
			JSONObject info = jsonlife.getJSONObject("info");
			JSONArray yi = info.getJSONArray("chuanyi");
			String chuanyi = yi.getString(0) + "/" + yi.getString(1);
			JSONArray gan = info.getJSONArray("ganmao");
			String ganmao = gan.getString(0) + "/" + gan.getString(1);
			JSONArray kong = info.getJSONArray("kongtiao");
			String kongtiao = kong.getString(0) + "/" + kong.getString(1);
			JSONArray wu = info.getJSONArray("wuran");
			String wuran = wu.getString(0) + "/" + wu.getString(1);
			JSONArray xi = info.getJSONArray("xiche");
			String xiche = xi.getString(0) + "/" + xi.getString(1);
			JSONArray yun = info.getJSONArray("yundong");
			String yundong = yun.getString(0) + "/" + yun.getString(1);
			JSONArray zi = info.getJSONArray("ziwaixian");
			String ziwaixian = zi.getString(0) + "/" + zi.getString(1);
			life = new Life(kongtiao, ziwaixian, yundong, ganmao, xiche, wuran,
					chuanyi);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return life;
	}

}
