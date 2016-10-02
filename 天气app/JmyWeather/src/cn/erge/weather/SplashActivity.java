package cn.erge.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import cn.erge.weather.app.MyApp;
import cn.erge.weather.min.VpAdapter;

public class SplashActivity extends FragmentActivity {

	ViewPager vp;
	ImageView iv1,iv2,iv3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences setting = getSharedPreferences("FIRST_LOGIN", 0);
		Boolean user_first = setting.getBoolean("FIRST", true);
		if (user_first) {// µÚÒ»´Î
			firstLoad();
			setting.edit().putBoolean("FIRST", false).commit();
		} else {
			load();
		}

	}
	private void load() {
		setContentView(R.layout.activity_welcome);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							startActivity(new Intent(SplashActivity.this,MainActivity.class));
							SplashActivity.this.finish();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private void firstLoad() {
		setContentView(R.layout.activity_splash);
		vp=(ViewPager) findViewById(R.id.splash_vp);
		iv1=(ImageView) findViewById(R.id.header_inditor_iv1);
		iv2=(ImageView) findViewById(R.id.header_inditor_iv2);
		iv3=(ImageView) findViewById(R.id.header_inditor_iv3);
		vp.setAdapter(new VpAdapter(getSupportFragmentManager()));
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				switch (arg0%4){
				case 0:
					iv1.setImageResource(R.drawable.zhusediaodiandian);
					iv2.setImageResource(R.drawable.baisediandian);
					iv3.setImageResource(R.drawable.baisediandian);
					break;
				case 1:
					iv1.setImageResource(R.drawable.baisediandian);
					iv2.setImageResource(R.drawable.zhusediaodiandian);
					iv3.setImageResource(R.drawable.baisediandian);
					break;
				case 2:
					iv1.setImageResource(R.drawable.baisediandian);
					iv2.setImageResource(R.drawable.baisediandian);
					iv3.setImageResource(R.drawable.zhusediaodiandian);
					break;
				}
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

}
