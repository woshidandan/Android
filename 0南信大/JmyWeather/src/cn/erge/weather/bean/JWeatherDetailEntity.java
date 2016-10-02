package cn.erge.weather.bean;


public class JWeatherDetailEntity extends JBaseEntity{
	@Override
	public String toString() {
		return  super.toString()+":JWeatherDetailEntity [id=" + id + ", parent=" + parent
				+ ", epoch=" + epoch + ", state=" + state + ", adc0=" + adc0
				+ ", adc1=" + adc1 + ", adc2=" + adc2 + ", adc3=" + adc3
				+ ", adc4=" + adc4 + ", adc5=" + adc5 + ", adc6=" + adc6
				+ ", adc7=" + adc7 + ", voltag=" + voltag + ", humid=" + humid
				+ ", tem=" + tem + ", speed=" + speed + ", dir=" + dir
				+ ", rain=" + rain + ", pressure=" + pressure + ", created="
				+ created + "]";
	}
	public JWeatherDetailEntity(String code,String cmd, String msg){
		super(code,cmd,  msg);
	}
public JWeatherDetailEntity(){
		
	}
	private String id;
	private String parent;
	private String epoch;
	private String state;
	private String adc0;
	private String adc1;
	private String adc2;
	private String adc3;
	private String adc4;
	private String adc5;
	private String adc6;
	private String adc7;
	private String voltag;
	private String humid;
	private String tem;
	private String speed;
	private String dir;
	private String rain;
	private String pressure;
	private String created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getEpoch() {
		return epoch;
	}

	public void setEpoch(String epoch) {
		this.epoch = epoch;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdc0() {
		return adc0;
	}

	public void setAdc0(String adc0) {
		this.adc0 = adc0;
	}

	public String getAdc1() {
		return adc1;
	}

	public void setAdc1(String adc1) {
		this.adc1 = adc1;
	}

	public String getAdc2() {
		return adc2;
	}

	public void setAdc2(String adc2) {
		this.adc2 = adc2;
	}

	public String getAdc3() {
		return adc3;
	}

	public void setAdc3(String adc3) {
		this.adc3 = adc3;
	}

	public String getAdc4() {
		return adc4;
	}

	public void setAdc4(String adc4) {
		this.adc4 = adc4;
	}

	public String getAdc5() {
		return adc5;
	}

	public void setAdc5(String adc5) {
		this.adc5 = adc5;
	}

	public String getAdc6() {
		return adc6;
	}

	public void setAdc6(String adc6) {
		this.adc6 = adc6;
	}

	public String getAdc7() {
		return adc7;
	}

	public void setAdc7(String adc7) {
		this.adc7 = adc7;
	}

	public String getVoltag() {
		return voltag;
	}

	public void setVoltag(String voltag) {
		this.voltag = voltag;
	}

	public String getHumid() {
		return humid;
	}

	public void setHumid(String humid) {
		this.humid = humid;
	}

	public String getTem() {
		return tem;
	}

	public void setTem(String tem) {
		this.tem = tem;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getRain() {
		return rain;
	}

	public void setRain(String rain) {
		this.rain = rain;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

}
