package finals;

public class Wind {
	private String windspeed;

	private String direct;

	private String power;

	private String offset;

	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}

	public String getWindspeed() {
		return this.windspeed;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public String getDirect() {
		return this.direct;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getPower() {
		return this.power;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getOffset() {
		return this.offset;
	}

	@Override
	public String toString() {
		return "Wind [windspeed=" + windspeed + ", direct=" + direct
				+ ", power=" + power + ", offset=" + offset + "]";
	}
	

}