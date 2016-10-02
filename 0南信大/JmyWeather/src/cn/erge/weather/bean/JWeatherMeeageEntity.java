package cn.erge.weather.bean;

public class JWeatherMeeageEntity extends JBaseEntity{
	public JWeatherMeeageEntity(String code,String cmd, String msg){
		super(code,cmd,  msg);
	}
	public JWeatherMeeageEntity(){
		
	}
	private String id;
	private String site;
	private String location;
	private String lat;
	private String lon;
	private String city;
	private String state;
	private String parent;
	private String tem;
	private String humid;
	private String speed;
	private String created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getTem() {
		return tem;
	}

	public void setTem(String tem) {
		this.tem = tem;
	}

	public String getHumid() {
		return humid;
	}

	public void setHumid(String humid) {
		this.humid = humid;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return super.toString()+":JWeatherMeeageEntity [id=" + id + ", site=" + site
				+ ", location=" + location + ", lat=" + lat + ", lon=" + lon
				+ ", city=" + city + ", state=" + state + ", parent=" + parent
				+ ", tem=" + tem + ", humid=" + humid + ", speed=" + speed
				+ ", created=" + created + "]";
	}
	
}
