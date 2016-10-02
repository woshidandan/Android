package cn.erge.weather.bean;

/***
 * ��ǰ����
 * 
 * @author tarena
 * 
 */
public class RealTime {
	private String city_code;
	/** ������ **/
	private String city_name;
	/** ���� **/
	private String date;
	/** ����ʱ�� **/
	private String time;
	private int week;
	private String moon;
	private int dataUptime;
	private String temperature;
	/** ʪ�� **/
	private String humidity;
	/** ����״�� **/
	private String info;
	private String img;
	/** ���� **/
	private String direct;
	/** ���� **/
	private String power;

	public RealTime() {
		super();
	}

	public RealTime(String city_code, String city_name, String date,
			String time, int week, String moon, int dataUptime,
			String temperature, String humidity, String info, String img,
			String direct, String power) {
		super();
		this.city_code = city_code;
		this.city_name = city_name;
		this.date = date;
		this.time = time;
		this.week = week;
		this.moon = moon;
		this.dataUptime = dataUptime;
		this.temperature = temperature;
		this.humidity = humidity;
		this.info = info;
		this.img = img;
		this.direct = direct;
		this.power = power;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getMoon() {
		return moon;
	}

	public void setMoon(String moon) {
		this.moon = moon;
	}

	public int getDataUptime() {
		return dataUptime;
	}

	public void setDataUptime(int dataUptime) {
		this.dataUptime = dataUptime;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	@Override
	public String toString() {
		return "realtime [city_code=" + city_code + ", city_name=" + city_name
				+ ", date=" + date + ", time=" + time + ", week=" + week
				+ ", moon=" + moon + ", dataUptime=" + dataUptime
				+ ", temperature=" + temperature + ", humidity=" + humidity
				+ ", info=" + info + ", img=" + img + ", direct=" + direct
				+ ", power=" + power + "]";
	}

}
