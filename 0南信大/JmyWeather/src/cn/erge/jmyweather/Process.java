package cn.erge.jmyweather;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.erge.jmyweather.entity.Life;
import cn.erge.jmyweather.entity.NextWeather;
import cn.erge.jmyweather.entity.RealTime;
import cn.erge.jmyweather.entity.Result;
import cn.erge.jmyweather.entity.Weather;
import cn.erge.jmyweather.entity.Wind;

/**
 * 
 * @author k
 * 解析工具类
 * 用来解析用到的天气信息
 * 全部采用手动解析法
 *
 */

public class Process {
	public Result result;
	public RealTime realTime;
	public ArrayList<NextWeather> nextWeathers;
	public Life life;
		
	public  int  processData(String xml) throws JSONException{
		JSONObject resultJO=new JSONObject(xml);
		//构建result对象
		result=new Result(resultJO.getString("reason"),resultJO.getString("error_code"));
		
		if(!result.getReason().equals("successed!")){
			return 1;//请求数据失败
		}
		
	    //构建RealTime对象
		JSONObject realtimeJO=resultJO.getJSONObject("result").getJSONObject("data").getJSONObject("realtime");
		JSONObject windJO=realtimeJO.getJSONObject("wind");
		Wind wind=new Wind(windJO.getString("windspeed"),windJO.getString("direct") , windJO.getString("power"), windJO.getString("offset"));
		JSONObject weatherJO=realtimeJO.getJSONObject("weather");
		Weather weather=new Weather(weatherJO.getString("humidity"), weatherJO.getString("img"), weatherJO.getString("info"), weatherJO.getString("temperature"));
		realTime=new RealTime(wind, realtimeJO.getString("time"), weather,  realtimeJO.getString("date"), realtimeJO.getString("city_name"), realtimeJO.getString("week"));
		//构建nextWeathers对象
		JSONArray nextWeatherJO=resultJO.getJSONObject("result").getJSONObject("data").getJSONArray("weather");
		nextWeathers=new ArrayList<NextWeather>();
		for(int i=0;i<nextWeatherJO.length();i++){
			JSONObject jobj=nextWeatherJO.getJSONObject(i);
			String wea=jobj.getJSONObject("info").getJSONArray("day").getString(1);
			String tem=jobj.getJSONObject("info").getJSONArray("day").getString(2)+"/"+jobj.getJSONObject("info").getJSONArray("night").getString(2);
			String we=jobj.getString("week");
			NextWeather nextWeather=new NextWeather(wea, tem, we);
			nextWeathers.add(nextWeather);
		}
		
		JSONObject lifeJO=resultJO.getJSONObject("result").getJSONObject("data").getJSONObject("life");
		JSONObject lifeJ=lifeJO.getJSONObject("info");
		life=new Life(
				new String[]{lifeJ.getJSONArray("kongtiao").getString(0),lifeJ.getJSONArray("kongtiao").getString(1)},
				new String[]{lifeJ.getJSONArray("yundong").getString(0),lifeJ.getJSONArray("yundong").getString(1)}, 
				new String[]{lifeJ.getJSONArray("ziwaixian").getString(0),lifeJ.getJSONArray("ziwaixian").getString(1)}, 
				new String[]{lifeJ.getJSONArray("ganmao").getString(0),lifeJ.getJSONArray("ganmao").getString(1)}, 
				new String[]{lifeJ.getJSONArray("xiche").getString(0),lifeJ.getJSONArray("xiche").getString(1)}, 
				new String[]{lifeJ.getJSONArray("wuran").getString(0),lifeJ.getJSONArray("wuran").getString(1)}, 
				new String[]{lifeJ.getJSONArray("chuanyi").getString(0),lifeJ.getJSONArray("chuanyi").getString(1)}
				);
		return -1;
	}
}
