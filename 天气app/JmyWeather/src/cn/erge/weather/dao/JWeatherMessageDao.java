package cn.erge.weather.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;
import cn.erge.weather.bean.JBaseEntity;
import cn.erge.weather.bean.JCityEntity;
import cn.erge.weather.bean.JWeatherMeeageEntity;

public class JWeatherMessageDao extends JBaseDao{
	public List<JWeatherMeeageEntity > weathers;
	private String xml;
	private JWeatherMeeageEntity weather;  
	private JBaseEntity jBaseEntity;
	public JWeatherMessageDao(String xml) throws XmlPullParserException, IOException {
		super(xml);
		this.xml=xml;
		weathers=new ArrayList<JWeatherMeeageEntity>();
		processS();
	}
	public void processS() throws XmlPullParserException, IOException {
		 XmlPullParser xpp = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例  
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
				} else 
				if (xpp.getName().equals("node")) {
					weather = new JWeatherMeeageEntity(jBaseEntity.getCode(),jBaseEntity.getCmd(),jBaseEntity.getMsg());
				} else if (xpp.getName().equals("id")) {
					eventType = xpp.next();
					weather.setId(xpp.getText());
				} else if (xpp.getName().equals("site")) {
					eventType = xpp.next();
					weather.setSite(xpp.getText());
				} else if (xpp.getName().equals("location")) {
					eventType = xpp.next();
					weather.setLocation(xpp.getText());
					;
				} else if (xpp.getName().equals("lat")) {
					eventType = xpp.next();
					weather.setLat(xpp.getText());
				} else if (xpp.getName().equals("lon")) {
					eventType = xpp.next();
					weather.setLon(xpp.getText());
				} else if (xpp.getName().equals("city")) {
					eventType = xpp.next();
					weather.setCity(xpp.getText());
				} else if (xpp.getName().equals("state")) {
					eventType = xpp.next();
					weather.setState(xpp.getText());
				} else if (xpp.getName().equals("parent")) {
					eventType = xpp.next();
					weather.setParent(xpp.getText());
				} else if (xpp.getName().equals("tem")) {
					eventType = xpp.next();
					weather.setTem(xpp.getText());
				} else if (xpp.getName().equals("humid")) {
					eventType = xpp.next();
					weather.setHumid(xpp.getText());
				} else if (xpp.getName().equals("speed")) {
					eventType = xpp.next();
					weather.setSpeed(xpp.getText());
				} else if (xpp.getName().equals("created")) {
					eventType = xpp.next();
					weather.setCreated(xpp.getText());
				}
				break;

			// 判断当前事件是否为标签元素结束事件
			case XmlPullParser.END_TAG:
             if (xpp.getName().equals("city")) {   
	                            	 weathers.add(weather);
	                             }  
	                             break;  
	                         }  
	                         // 进入下一个元素并触发相应事件  
	                         eventType = xpp.next();  
	                     }  
	}
}
