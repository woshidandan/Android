package cn.erge.weather.min;

import cn.erge.weather.BMainActivity;
import cn.erge.weather.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Fragment3 extends Fragment{
	private Activity context;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v=inflater.inflate(R.layout.fragment, container,false);
		ImageView iv=(ImageView) v.findViewById(R.id.fragm_iv);
		iv.setImageResource(R.drawable.ming2);
		Button btn=(Button) v.findViewById(R.id.fragm_btn);
		btn.setVisibility(View.VISIBLE);
		context=getActivity();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context,BMainActivity.class));
				getActivity().finish();
				
			}
		});
		return v;
	}
}
