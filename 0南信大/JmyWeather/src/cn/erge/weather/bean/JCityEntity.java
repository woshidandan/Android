package cn.erge.weather.bean;

public class JCityEntity extends JBaseEntity {
	private String name;
	private String lat;
	private String lon;
	public JCityEntity(String code,String cmd, String msg){
		super(code,cmd,  msg);
	}
	public JCityEntity(){
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return super.toString()+":JCityEntity [name=" + name + ", lat=" + lat + ", lon=" + lon
				+ "]";
	}

}
