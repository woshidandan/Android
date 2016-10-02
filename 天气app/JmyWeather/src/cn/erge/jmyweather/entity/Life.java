package cn.erge.jmyweather.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Life implements Serializable{
private String[] kongtiao;
private String[] yundong;
private String[] ziwaixian;
private String[] ganmao;
private String[] xiche;
private String[] wuran;
private String[] chuanyi;
public String[] getKongtiao() {
	return kongtiao;
}
public void setKongtiao(String[] kongtiao) {
	this.kongtiao = kongtiao;
}
public String[] getYundong() {
	return yundong;
}
public void setYundong(String[] yundong) {
	this.yundong = yundong;
}
public String[] getZiwaixian() {
	return ziwaixian;
}
public void setZiwaixian(String[] ziwaixian) {
	this.ziwaixian = ziwaixian;
}
public String[] getGanmao() {
	return ganmao;
}
public void setGanmao(String[] ganmao) {
	this.ganmao = ganmao;
}
public String[] getXiche() {
	return xiche;
}
public void setXiche(String[] xiche) {
	this.xiche = xiche;
}
public String[] getWuran() {
	return wuran;
}
public void setWuran(String[] wuran) {
	this.wuran = wuran;
}
public String[] getChuanyi() {
	return chuanyi;
}
public void setChuanyi(String[] chuanyi) {
	this.chuanyi = chuanyi;
}
public Life(String[] kongtiao, String[] yundong, String[] ziwaixian,
		String[] ganmao, String[] xiche, String[] wuran, String[] chuanyi) {
	super();
	this.kongtiao = kongtiao;
	this.yundong = yundong;
	this.ziwaixian = ziwaixian;
	this.ganmao = ganmao;
	this.xiche = xiche;
	this.wuran = wuran;
	this.chuanyi = chuanyi;
}
@Override
public String toString() {
	return "Life [kongtiao=" + Arrays.toString(kongtiao) + ", yundong="
			+ Arrays.toString(yundong) + ", ziwaixian="
			+ Arrays.toString(ziwaixian) + ", ganmao="
			+ Arrays.toString(ganmao) + ", xiche=" + Arrays.toString(xiche)
			+ ", wuran=" + Arrays.toString(wuran) + ", chuanyi="
			+ Arrays.toString(chuanyi) + "]";
}

}
