package cn.erge.jmyweather.entity;

public class RealTime {
private Wind wind;//��
private String time;//����ʱ��
private Weather weather;//����
private String date;//����
private String city_name;//��������
private String week;//�ܼ�
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
