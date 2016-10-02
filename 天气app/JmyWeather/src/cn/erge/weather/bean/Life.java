package cn.erge.weather.bean;

public class Life {
	private String kongtiao;
	private String ziwaixian;
	private String yundong;
	private String ganmao;
	private String xiche;
	private String wuran;
	private String chuanyi;

	public String getKongtiao() {
		return kongtiao;
	}

	public void setKongtiao(String kongtiao) {
		this.kongtiao = kongtiao;
	}

	public String getZiwaixian() {
		return ziwaixian;
	}

	public void setZiwaixian(String ziwaixian) {
		this.ziwaixian = ziwaixian;
	}

	public String getYundong() {
		return yundong;
	}

	public void setYundong(String yundong) {
		this.yundong = yundong;
	}

	public String getGanmao() {
		return ganmao;
	}

	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}

	public String getXiche() {
		return xiche;
	}

	public void setXiche(String xiche) {
		this.xiche = xiche;
	}

	public String getWuran() {
		return wuran;
	}

	public void setWuran(String wuran) {
		this.wuran = wuran;
	}

	public String getChuanyi() {
		return chuanyi;
	}

	public void setChuanyi(String chuanyi) {
		this.chuanyi = chuanyi;
	}

	@Override
	public String toString() {
		return "Life [kongtiao=" + kongtiao + ", ziwaixian=" + ziwaixian
				+ ", yundong=" + yundong + ", ganmao=" + ganmao + ", xiche="
				+ xiche + ", wuran=" + wuran + ", chuanyi=" + chuanyi + "]";
	}
	public Life(String kongtiao, String ziwaixian, String yundong,
			String ganmao, String xiche, String wuran, String chuanyi) {
		super();
		this.kongtiao = kongtiao;
		this.ziwaixian = ziwaixian;
		this.yundong = yundong;
		this.ganmao = ganmao;
		this.xiche = xiche;
		this.wuran = wuran;
		this.chuanyi = chuanyi;
	}

	public Life() {
		super();
	}

}
