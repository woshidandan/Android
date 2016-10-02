package finals;

public class Pm25 {
	private String key;

	private int show_desc;

	private Pm251 pm25;

	private String dateTime;

	private String cityName;

	public void setKey(String key){
	this.key = key;
	}
	public String getKey(){
	return this.key;
	}
	public void setShow_desc(int show_desc){
	this.show_desc = show_desc;
	}
	public int getShow_desc(){
	return this.show_desc;
	}
	public void setPm25(Pm251 pm25){
	this.pm25 = pm25;
	}
	public Pm251 getPm25(){
	return this.pm25;
	}
	public void setDateTime(String dateTime){
	this.dateTime = dateTime;
	}
	public String getDateTime(){
	return this.dateTime;
	}
	public void setCityName(String cityName){
	this.cityName = cityName;
	}
	public String getCityName(){
	return this.cityName;
	}
}
