package cn.erge.weather.bean;

public class Pm25 {
	private String key;
	private int show_desc;
	private String curPm;
	private String pm25;
	private String pm10;
	private int level;
	private String quality;
	private String des;
	public Pm25() {
		super();
	}
	public Pm25(String key, int show_desc, String curPm, String pm25,
			String pm10, int level, String quality, String des) {
		super();
		this.key = key;
		this.show_desc = show_desc;
		this.curPm = curPm;
		this.pm25 = pm25;
		this.pm10 = pm10;
		this.level = level;
		this.quality = quality;
		this.des = des;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getShow_desc() {
		return show_desc;
	}
	public void setShow_desc(int show_desc) {
		this.show_desc = show_desc;
	}
	public String getCurPm() {
		return curPm;
	}
	public void setCurPm(String curPm) {
		this.curPm = curPm;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public String getPm10() {
		return pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "pm25 [key=" + key + ", show_desc=" + show_desc + ", curPm="
				+ curPm + ", pm25=" + pm25 + ", pm10=" + pm10 + ", level="
				+ level + ", quality=" + quality + ", des=" + des + "]";
	}
	
}
