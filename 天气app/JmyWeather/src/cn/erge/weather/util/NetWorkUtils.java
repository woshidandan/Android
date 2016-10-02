package cn.erge.weather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {
	/**
	 * �жϵ�ǰ�豸�������������
	 * 
	 * @param context
	 *            �����Ķ���
	 * @return
	 */
	public static boolean hasNetWork(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			return info.isAvailable();
		} else {
			return false;
		}
	}

	/**
	 * �жϵ�ǰ���������ͣ��ǲ���mobile
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobile(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			return info.getType() == ConnectivityManager.TYPE_MOBILE;
		} else {
			return false;
		}
	}

	/**
	 * �жϵ�ǰ���������ͣ��ǲ���wifi
	 * 
	 * @param context
	 * @return
	 */

	public static boolean isWifi(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			return info.getType() == ConnectivityManager.TYPE_WIFI;
		} else {
			return false;
		}
	}

	/**
	 * ���������ķ��� ������Ϣ
	 * 
	 * @param context
	 * @return networkInfo
	 */
	private static NetworkInfo getNetworkInfo(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();

		return activeNetworkInfo;
	}
}
