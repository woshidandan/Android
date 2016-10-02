package cn.erge.weather.min;

import cn.erge.weather.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment2 extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v=inflater.inflate(R.layout.fragment, container,false);
		ImageView iv=(ImageView) v.findViewById(R.id.fragm_iv);
		iv.setImageResource(R.drawable.ming1);
		return v;
	}
}
