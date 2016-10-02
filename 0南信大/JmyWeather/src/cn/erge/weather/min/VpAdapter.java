package cn.erge.weather.min;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class VpAdapter extends FragmentPagerAdapter{
	public VpAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		list=new ArrayList<Fragment>();
		list.add(new Fragment1());
		list.add(new Fragment2());
		list.add(new Fragment3());
	}

	List<Fragment> list;

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}
}
