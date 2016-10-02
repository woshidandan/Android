package cn.erge.weather.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.util.Xml;
import cn.erge.weather.bean.JBaseEntity;
import cn.erge.weather.bean.JCityEntity;
import cn.erge.weather.bean.JWeatherDetailEntity;
import cn.erge.weather.util.UrlUtil;

public class JCityDao extends JBaseDao {
	public List<JCityEntity> citys;
	private String xml;
	private JCityEntity city;
	private JBaseEntity jBaseEntity;

	public JCityDao(String xml) throws XmlPullParserException, IOException {
		citys = new ArrayList<JCityEntity>();
		this.xml = xml;
		processS();
	}

	/**
	 * 
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public void processS() throws XmlPullParserException, IOException {
		XmlPullParser xpp = Xml.newPullParser(); // ��android.util.Xml����һ��XmlPullParserʵ��
		xpp.setInput(getStringStream(xml), "UTF-8");
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			// �жϵ�ǰ�¼��Ƿ�Ϊ�ĵ���ʼ�¼�
			case XmlPullParser.START_DOCUMENT: //
				break;
			// �жϵ�ǰ�¼��Ƿ�Ϊ��ǩԪ�ؿ�ʼ�¼�
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
				} else if (xpp.getName().equals("city")) {
					city = new JCityEntity(jBaseEntity.getCode(),
							jBaseEntity.getCmd(), jBaseEntity.getMsg());
				} else if (xpp.getName().equals("name")) {
					eventType = xpp.next();
					city.setName(xpp.getText());
				} else if (xpp.getName().equals("lat")) {
					eventType = xpp.next();
					city.setLat(xpp.getText());
					;
				} else if (xpp.getName().equals("lon")) {
					eventType = xpp.next();
					city.setLon(xpp.getText());
				}
				break;

			// �жϵ�ǰ�¼��Ƿ�Ϊ��ǩԪ�ؽ����¼�
			case XmlPullParser.END_TAG:
				if (xpp.getName().equals("city")) {
					citys.add(city);
				}
				break;
			}
			// ������һ��Ԫ�ز�������Ӧ�¼�
			eventType = xpp.next();
		}
	}
}
