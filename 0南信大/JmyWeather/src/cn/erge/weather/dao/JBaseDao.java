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
		 XmlPullParser xpp = Xml.newPullParser(); //��android.util.Xml����һ��XmlPullParserʵ��  
	      xpp.setInput(getStringStream(xml), "UTF-8");   
	      int eventType = xpp.getEventType(); 
	      while (eventType != XmlPullParser.END_DOCUMENT){  
	             switch (eventType) {  
	                         // �жϵ�ǰ�¼��Ƿ�Ϊ�ĵ���ʼ�¼�  
	                         case XmlPullParser.START_DOCUMENT:  
	                             break;  
	                         // �жϵ�ǰ�¼��Ƿ�Ϊ��ǩԪ�ؿ�ʼ�¼�  
	                         case XmlPullParser.START_TAG:  
	                             if (xpp.getName().equals("head")) { // �жϿ�ʼ��ǩԪ���Ƿ���book  
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
	                
	                         // �жϵ�ǰ�¼��Ƿ�Ϊ��ǩԪ�ؽ����¼�  
	                         case XmlPullParser.END_TAG:  
	                             if (xpp.getName().equals("head")) {   
	                            	 return;
	                             }  
	                             break;  
	                         }  
	                         // ������һ��Ԫ�ز�������Ӧ�¼�  
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
	 * ��һ��������ת��Ϊ�ַ���
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
