package cn.erge.weather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {
	/**
	 * 判断当前设备的网络连接情况
	 * 
	 * @param context
	 *            上下文对象
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
	 * 判断当前的网络类型，是不是mobile
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
	 * 判断当前的网络类型，是不是wifi
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
	 * 根据上下文返回 网络信息
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
