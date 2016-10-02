package cn.erge.weather.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;
import cn.erge.weather.bean.JBaseEntity;
import cn.erge.weather.bean.JWeatherDetailEntity;
import cn.erge.weather.bean.JWeatherMeeageEntity;

public class JWeatherDetailDao extends JBaseDao {
	private String xml;
	public JWeatherDetailEntity weatherDetail;
	private JBaseEntity jBaseEntity;
	public JWeatherDetailDao(String xml) throws XmlPullParserException,
			IOException {
		super(xml);
		this.xml = xml;
		processS();
	}

	public void processS() throws XmlPullParserException, IOException {
		XmlPullParser xpp = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		xpp.setInput(getStringStream(xml), "UTF-8");
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			// 判断当前事件是否为文档开始事件
			case XmlPullParser.START_DOCUMENT:
				break;
			// 判断当前事件是否为标签元素开始事件
			case XmlPullParser.START_TAG:
				if (xpp.getName().equals("result")) { 
					 jBaseEntity = new JBaseEntity();
				} else if (xpp.getName().equals("code")) {
					eventType = xpp.next();
					jBaseEntity.setCode(xpp.getText());
				} else if (xpp.getName().equals("cmd")) {
					eventType = xpp.next();
					jBaseEntity.setCmd(xpp.getText());
				} else if (xpp.getName().equals("msg")) {
					eventType = xpp.next();
					jBaseEntity.setMsg(xpp.getText());
				} else if (xpp.getName().equals("data")) {
					weatherDetail=new JWeatherDetailEntity(jBaseEntity.getCode(),jBaseEntity.getCmd(),jBaseEntity.getMsg());
					for (int i = 0; i < xpp.getAttributeCount(); i++) {
						if (xpp.getAttributeName(i).equals("id")) {
							weatherDetail.setId(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("parent")) {
							weatherDetail.setParent(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("epoch")) {
							weatherDetail.setEpoch(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("state")) {
							weatherDetail.setState(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc0")) {
							weatherDetail.setAdc0(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc1")) {
							weatherDetail.setAdc1(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc2")) {
							weatherDetail.setAdc2(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc3")) {
							weatherDetail.setAdc3(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc4")) {
							weatherDetail.setAdc4(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc5")) {
							weatherDetail.setAdc5(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc6")) {
							weatherDetail.setAdc6(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("adc7")) {
							weatherDetail.setAdc7(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("voltag")) {
							weatherDetail.setVoltag(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("humid")) {
							weatherDetail.setHumid(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("tem")) {
							weatherDetail.setTem(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("speed")) {
							weatherDetail.setSpeed(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("dir")) {
							weatherDetail.setDir(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("rain")) {
							weatherDetail.setRain(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("pressure")) {
							weatherDetail.setPressure(xpp.getAttributeValue(i));
						} else if (xpp.getAttributeName(i).equals("created")) {
							weatherDetail.setCreated(xpp.getAttributeValue(i));
						}
					}
				}
				break;

			// 判断当前事件是否为标签元素结束事件
			case XmlPullParser.END_TAG:
				break;
			}
			// 进入下一个元素并触发相应事件
			eventType = xpp.next();
		}
	}
}
