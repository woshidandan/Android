package cn.erge.jmyweather.entity;

public class RealTime {
private Wind wind;//风
private String time;//更新时间
private Weather weather;//天气
private String date;//日期
private String city_name;//城市名称
private String week;//周几
public Wind getWind() {
	return wind;
}
public void setWind(Wind wind) {
	this.wind = wind;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public Weather getWeather() {
	return weather;
}
public void setWeather(Weather weather) {
	this.weather = weather;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getCity_name() {
	return city_name;
}
public void setCity_name(String city_name) {
	this.city_name = city_name;
}
public String getWeek() {
	return week;
}
public void setWeek(String week) {
	this.week = week;
}
public RealTime(Wind wind, String time, Weather weather, String date,
		String city_name, String week) {
	this.wind = wind;
	this.time = time;
	this.weather = weather;
	this.date = date;
	this.city_name = city_name;
	this.week = week;
}
@Override
public String toString() {
	return "RealTime [wind=" + wind + ", time=" + time + ", weather=" + weather
			+ ", date=" + date + ", city_name=" + city_name + ", week=" + week
			+ "]";
}

}
