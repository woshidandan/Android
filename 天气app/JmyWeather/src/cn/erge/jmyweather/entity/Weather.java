package cn.erge.jmyweather.entity;

public class Weather {
private String humidity;
private String img;
private String info;
private String temperature;
public String getHumidity() {
	return humidity;
}
public void setHumidity(String humidity) {
	this.humidity = humidity;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info;
}
public String getTemperature() {
	return temperature;
}
public void setTemperature(String temperature) {
	this.temperature = temperature;
}
public Weather(String humidity, String img, String info, String temperature) {
	super();
	this.humidity = humidity;
	this.img = img;
	this.info = info;
	this.temperature = temperature;
}
@Override
public String toString() {
	return "Weather [humidity=" + humidity + ", img=" + img + ", info=" + info
			+ ", temperature=" + temperature + "]";
}

}
