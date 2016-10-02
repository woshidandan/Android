package cn.erge.jmyweather.entity;
/**
 * 从集合下表为1的访问，才是明天
 * @author k
 *
 */
public class NextWeather {
	private String weather;
	private String tempeare;
	private String week;
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTempeare() {
		return tempeare;
	}
	public void setTempeare(String tempeare) {
		this.tempeare = tempeare;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public NextWeather(String weather, String tempeare, String week) {
		super();
		this.weather = weather;
		this.tempeare = tempeare;
		this.week = week;
	}
	@Override
	public String toString() {
		return "NextWeather [weather=" + weather + ", tempeare=" + tempeare
				+ ", week=" + week + "]";
	}

}
