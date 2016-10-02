package cn.erge.weather.dao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import cn.erge.weather.bean.JBaseEntity;
import android.content.Context;
import android.util.Xml;

public class JBaseDao {
	private String xml;
	private Context context;
	public JBaseEntity base;
	public JBaseDao(String xml){
		this.xml=xml;
	}
	public JBaseDao(){}
	public void process(JBaseEntity base) throws XmlPullParserException, IOException {
		 XmlPullParser xpp = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例  
	      xpp.setInput(getStringStream(xml), "UTF-8");   
	      int eventType = xpp.getEventType(); 
	      while (eventType != XmlPullParser.END_DOCUMENT){  
	             switch (eventType) {  
	                         // 判断当前事件是否为文档开始事件  
	                         case XmlPullParser.START_DOCUMENT:  
	                             break;  
	                         // 判断当前事件是否为标签元素开始事件  
	                         case XmlPullParser.START_TAG:  
	                             if (xpp.getName().equals("head")) { // 判断开始标签元素是否是book  
	                            	 //base=new JBaseEntity();
	                             } else if (xpp.getName().equals("code")) {  
	                                 eventType = xpp.next();
	                                 base.setCode(xpp.getText());
	                             }else if(xpp.getName().equals("cmd")){
	                            	   eventType = xpp.next();
		                               base.setCmd(xpp.getText());
	                             }else if(xpp.getName().equals("msg")){
	                            	 eventType = xpp.next();
		                               base.setMsg(xpp.getText());
	                             }
	                             break;  
	                
	                         // 判断当前事件是否为标签元素结束事件  
	                         case XmlPullParser.END_TAG:  
	                             if (xpp.getName().equals("head")) {   
	                            	 return;
	                             }  
	                             break;  
	                         }  
	                         // 进入下一个元素并触发相应事件  
	                         eventType = xpp.next();  
	                     }  
	      this.base=base;
	}
	public InputStream getStringStream(String sInputString) {
		if (sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
						sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将一个输入流转化为字符串
	 */
	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
