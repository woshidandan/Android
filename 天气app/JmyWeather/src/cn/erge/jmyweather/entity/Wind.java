package cn.erge.jmyweather.entity;

public class Wind {
private String windspeed;
private String direct;
private String power;
private String offset;
public String getWindspeed() {
	return windspeed;
}
public void setWindspeed(String windspeed) {
	this.windspeed = windspeed;
}
public String getDirect() {
	return direct;
}
public void setDirect(String direct) {
	this.direct = direct;
}
public String getPower() {
	return power;
}
public void setPower(String power) {
	this.power = power;
}
public String getOffset() {
	return offset;
}
public void setOffset(String offset) {
	this.offset = offset;
}
public Wind(String windspeed, String direct, String power, String offset) {
	super();
	this.windspeed = windspeed;
	this.direct = direct;
	this.power = power;
	this.offset = offset;
}

}
