package cn.erge.weather.bean;


public class FutureWeather {
	private String date;
	private String week;
	private String nongli;
	private String day;
	private String night;
	
	public String getNight() {
		return night;
	}
	public void setNight(String night) {
		this.night = night;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getNongli() {
		return nongli;
	}
	public void setNongli(String nongli) {
		this.nongli = nongli;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "futureweather [date=" + date + ", week=" + week + ", nongli="
				+ nongli + ", day=" + day + "]";
	}
	public FutureWeather(String date, String week, String nongli, String day,
			String night) {
		super();
		this.date = date;
		this.week = week;
		this.nongli = nongli;
		this.day = day;
		this.night = night;
	}
	public FutureWeather() {
		super();
	}
	
}
