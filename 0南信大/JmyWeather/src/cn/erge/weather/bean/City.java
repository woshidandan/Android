package cn.erge.weather.bean;

public class City {
	private String province;
	private String city;
	private String number;
	private String allpy;
	private String allfirstpy;
	private String firstpy;

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAllpy() {
		return allpy;
	}

	public void setAllpy(String allpy) {
		this.allpy = allpy;
	}

	public String getAllfirstpy() {
		return allfirstpy;
	}

	public void setAllfirstpy(String allfirstpy) {
		this.allfirstpy = allfirstpy;
	}

	public String getFirstpy() {
		return firstpy;
	}

	public void setFirstpy(String firstpy) {
		this.firstpy = firstpy;
	}

	@Override
	public String toString() {
		return "City [province=" + province + ", city=" + city + ", number="
				+ number + ", allpy=" + allpy + ", allfirstpy=" + allfirstpy
				+ ", firstpy=" + firstpy + "]";
	}


}

