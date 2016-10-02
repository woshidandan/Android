package cn.erge.weather.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.erge.weather.R;
import cn.erge.weather.bean.City;


public class WeatherAdapter extends BaseAdapter {
	private List<City> citys;
	private Context context;

	public WeatherAdapter(List<City> citys, Context context) {
		super();
		if (citys == null) {
			citys = new ArrayList<City>();
		}
		this.citys = citys;
		this.context = context;
	}

	@Override
	public int getCount() {
		return citys.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.adapter_item, null);

		}
		TextView tvPrivince = (TextView) convertView.findViewById(R.id.city_1);
		TextView tvcity = (TextView) convertView.findViewById(R.id.city_2);
		City city = citys.get(position);
		tvPrivince.setText(city.getProvince());
		tvcity.setText(city.getCity());
		return convertView;
	}
	
	public void add(List<City>list){
		citys.clear();
		citys.addAll(list);
		notifyDataSetChanged();
	}
	

}

