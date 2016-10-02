package cn.erge.weather.util;

import android.widget.ImageView;
import android.widget.TextView;
import cn.erge.weather.R;

public class StaticUtils {
/**
 * 用于改变pm25得天气图标
 * @param curPmNumber
 * @param ivPmPicture
 */
	public static void ChangePM25Picture(int curPmNumber, ImageView ivPmPicture) {
		if (curPmNumber >= 0 && curPmNumber <= 50) {
			ivPmPicture.setImageResource(R.drawable.biz_plugin_weather_0_50);
		}
		if (curPmNumber >= 51 && curPmNumber <= 100) {
			ivPmPicture.setImageResource(R.drawable.biz_plugin_weather_51_100);
		}
		if (curPmNumber >= 101 && curPmNumber <= 150) {
			ivPmPicture.setImageResource(R.drawable.biz_plugin_weather_101_150);
		}
		if (curPmNumber >= 151 && curPmNumber <= 200) {
			ivPmPicture.setImageResource(R.drawable.biz_plugin_weather_151_200);
		}
		if (curPmNumber >= 201 && curPmNumber < 300) {
			ivPmPicture.setImageResource(R.drawable.biz_plugin_weather_201_300);
		}
		if (curPmNumber >= 300) {
			ivPmPicture
					.setImageResource(R.drawable.biz_plugin_weather_greater_300);
		}

	}

	/**
	 * 用于改变天气图片
	 * 
	 * @param climate
	 * @param ivWeather
	 */
	public static void ChangeWeatherPicture(String climate, ImageView ivWeather) {
		if (climate.equals("晴")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_qing);
		}
		if (climate.equals("霾")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_mai);
		}
		if (climate.equals("雾")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_wu);
		}
		if (climate.equals("暴雪")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_baoxue);
		}
		if (climate.equals("暴雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_baoyu);
		}
		if (climate.equals("大雪")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_daxue);
		}

		if (climate.equals("大雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_dayu);
		}
		if (climate.equals("大暴雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
		}
		if (climate.equals("多云")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_duoyun);
		}
		if (climate.equals("雷阵雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
		}
		if (climate.equals("雷阵雨冰雹")) {
			ivWeather
					.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
		}

		if (climate.equals("沙尘暴")) {
			ivWeather
					.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
		}
		if (climate.equals("特大暴雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
		}
		if (climate.equals("小雪")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
		}
		if (climate.equals("小雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
		}
		if (climate.equals("阴")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_yin);
		}

		if (climate.equals("雨夹雪")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
		}
		if (climate.equals("阵雪")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
		}
		if (climate.equals("阵雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
		}
		if (climate.equals("中雪")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
		}
		if (climate.equals("中雨")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
		}
	}

	/**
	 * 将显示星期的数字转换为汉字的方法
	 */
	public static void ChangeHanzi(int number, TextView tvWeekToday) {
		if (number == 0) {
			tvWeekToday.setText("今天  星期日");
		} else if (number == 1) {
			tvWeekToday.setText("今天  星期一");
		} else if (number == 2) {
			tvWeekToday.setText("今天  星期二");
		} else if (number == 3) {
			tvWeekToday.setText("今天  星期三");
		} else if (number == 4) {
			tvWeekToday.setText("今天  星期四");
		} else if (number == 5) {
			tvWeekToday.setText("今天  星期五");
		} else if (number == 6) {
			tvWeekToday.setText("今天  星期六");
		}
	}

}
