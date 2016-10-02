package finals;

import java.util.List;

public class Info1 {
	private List<Night> night;

	private List<Day> day;

	public void setNight(List<Night> night) {
		this.night = night;
	}

	public List<Night> getNight() {
		return this.night;
	}

	public void setDay(List<Day> day) {
		this.day = day;
	}

	public List<Day> getDay() {
		return this.day;
	}

}