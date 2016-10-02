package cn.erge.weather;

import cn.erge.jmyweather.entity.Life;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainLeftActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainleft);
		TextView tv1=(TextView) findViewById(R.id.textView1);
		TextView tv2=(TextView) findViewById(R.id.textView2);
		
		
		Intent intent=getIntent();
		int position=intent.getIntExtra("position", 0);
		Life life=(Life) intent.getSerializableExtra("life");
		switch (position) {
		case 0:
			tv1.setText(life.getKongtiao()[0]);
			tv2.setText(life.getKongtiao()[1]);
			break;
		case 1:
			tv1.setText(life.getYundong()[0]);
			tv2.setText(life.getYundong()[1]);
			break;
		case 2:
			tv1.setText(life.getZiwaixian()[0]);
			tv2.setText(life.getZiwaixian()[1]);
			break;
		case 3:
			tv1.setText(life.getGanmao()[0]);
			tv2.setText(life.getGanmao()[1]);
			break;
		case 4:
			tv1.setText(life.getXiche()[0]);
			tv2.setText(life.getXiche()[1]);
			break;
		case 5:
			tv1.setText(life.getWuran()[0]);
			tv2.setText(life.getWuran()[1]);
			break;
		case 6:
			tv1.setText(life.getChuanyi()[0]);
			tv2.setText(life.getChuanyi()[1]);
			break;
		}
		
		findViewById(R.id.backMainActivity).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainLeftActivity.this.finish();
			}
		});
	}

}
