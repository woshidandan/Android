����jar��
��������
��application��ǩ������service���,ÿ��appӵ���Լ������Ķ�λservice
<service android:name="com.baidu.location.f" android:enabled="true" android:process=":remote">
</service>
���Ȩ�ޣ�
<!-- ���Ȩ�����ڽ������綨λ-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
<!-- ���Ȩ�����ڷ���GPS��λ-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
<!-- ���ڷ���wifi������Ϣ��wifi��Ϣ�����ڽ������綨λ-->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<!-- ��ȡ��Ӫ����Ϣ������֧���ṩ��Ӫ����Ϣ��صĽӿ�-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
<!-- ���Ȩ�����ڻ�ȡwifi�Ļ�ȡȨ�ޣ�wifi��Ϣ�������������綨λ-->
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
<!-- ���ڶ�ȡ�ֻ���ǰ��״̬-->
<uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
<!-- д����չ�洢������չ��д�����ݣ�����д�����߶�λ����-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
<!-- �������磬���綨λ��Ҫ����-->
<uses-permission android:name="android.permission.INTERNET" />
<!-- SD����ȡȨ�ޣ��û�д�����߶�λ����-->
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"></uses-permission>
application�����
<meta-data
            android:name="com.baidu.lbsapi.API_KEY"
            android:value="key" />       //key:�����������key
��ʼ�����ܣ�
public LocationClient mLocationClient = null;

public BDLocationListener myListener = new MyLocationListener();
 
public void onCreate() {
    mLocationClient = new LocationClient(getApplicationContext());     //����LocationClient��
    mLocationClient.registerLocationListener( myListener );    //ע���������
}
���ö�λ����
private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy
);//��ѡ��Ĭ�ϸ߾��ȣ����ö�λģʽ���߾��ȣ��͹��ģ����豸
        option.setCoorType("bd09ll");//��ѡ��Ĭ��gcj02�����÷��صĶ�λ�������ϵ
        int span=1000;
        option.setScanSpan(span);//��ѡ��Ĭ��0��������λһ�Σ����÷���λ����ļ����Ҫ���ڵ���1000ms������Ч��
        option.setIsNeedAddress(true);//��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ
        option.setOpenGps(true);//��ѡ��Ĭ��false,�����Ƿ�ʹ��gps
        option.setLocationNotify(true);//��ѡ��Ĭ��false�������Ƿ�gps��Чʱ����1S1��Ƶ�����GPS���
        option.setIsNeedLocationDescribe(true);//��ѡ��Ĭ��false�������Ƿ���Ҫλ�����廯�����������BDLocation.getLocationDescribe��õ�����������ڡ��ڱ����찲�Ÿ�����
        option.setIsNeedLocationPoiList(true);//��ѡ��Ĭ��false�������Ƿ���ҪPOI�����������BDLocation.getPoiList��õ�
option.setIgnoreKillProcess(false);//��ѡ��Ĭ��true����λSDK�ڲ���һ��SERVICE�����ŵ��˶������̣������Ƿ���stop��ʱ��ɱ��������̣�Ĭ�ϲ�ɱ��  
        option.SetIgnoreCacheException(false);//��ѡ��Ĭ��false�������Ƿ��ռ�CRASH��Ϣ��Ĭ���ռ�
option.setEnableSimulateGps(false);//��ѡ��Ĭ��false�������Ƿ���Ҫ����gps��������Ĭ����Ҫ
        mLocationClient.setLocOption(option);
    }
����MyLocationListener
��ʼ��λ
mLocationClient.start();

ֹͣ�ص����ص�stopLocation(String city)

ֹͣ��λapp.mLocationClient.stop();
ִ�з���