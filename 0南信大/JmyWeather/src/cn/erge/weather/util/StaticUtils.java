package cn.erge.weather.util;

import android.widget.ImageView;
import android.widget.TextView;
import cn.erge.weather.R;

public class StaticUtils {
/**
 * ���ڸı�pm25������ͼ��
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
	 * ���ڸı�����ͼƬ
	 * 
	 * @param climate
	 * @param ivWeather
	 */
	public static void ChangeWeatherPicture(String climate, ImageView ivWeather) {
		if (climate.equals("��")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_qing);
		}
		if (climate.equals("��")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_mai);
		}
		if (climate.equals("��")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_wu);
		}
		if (climate.equals("��ѩ")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_baoxue);
		}
		if (climate.equals("����")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_baoyu);
		}
		if (climate.equals("��ѩ")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_daxue);
		}

		if (climate.equals("����")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_dayu);
		}
		if (climate.equals("����")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
		}
		if (climate.equals("����")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_duoyun);
		}
		if (climate.equals("������")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
		}
		if (climate.equals("���������")) {
			ivWeather
					.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
		}

		if (climate.equals("ɳ����")) {
			ivWeather
					.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
		}
		if (climate.equals("�ش���")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
		}
		if (climate.equals("Сѩ")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
		}
		if (climate.equals("С��")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
		}
		if (climate.equals("��")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_yin);
		}

		if (climate.equals("���ѩ")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
		}
		if (climate.equals("��ѩ")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
		}
		if (climate.equals("����")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
		}
		if (climate.equals("��ѩ")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
		}
		if (climate.equals("����")) {
			ivWeather.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
		}
	}

	/**
	 * ����ʾ���ڵ�����ת��Ϊ���ֵķ���
	 */
	public static void ChangeHanzi(int number, TextView tvWeekToday) {
		if (number == 0) {
			tvWeekToday.setText("����  ������");
		} else if (number == 1) {
			tvWeekToday.setText("����  ����һ");
		} else if (number == 2) {
			tvWeekToday.setText("����  ���ڶ�");
		} else if (number == 3) {
			tvWeekToday.setText("����  ������");
		} else if (number == 4) {
			tvWeekToday.setText("����  ������");
		} else if (number == 5) {
			tvWeekToday.setText("����  ������");
		} else if (number == 6) {
			tvWeekToday.setText("����  ������");
		}
	}

}
