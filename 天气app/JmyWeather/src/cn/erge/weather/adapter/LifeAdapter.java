package cn.erge.weather.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.erge.weather.R;
import cn.erge.weather.bean.LifeMessage;

public class LifeAdapter extends BaseAdapter {

	private Context context;
	private List<LifeMessage> data;

	public LifeAdapter(Context context, List<LifeMessage> data) {
		super();
		this.context = context;
		this.data = data;

	}

	public void setMessage(List<LifeMessage> data) {
		if (data == null) {
			data = new ArrayList<LifeMessage>();
		}
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.life_item, null);
			holder.ivPiture = (ImageView) convertView
					.findViewById(R.id.iv_life_picture);
			holder.tvMessage = (TextView) convertView
					.findViewById(R.id.tv_life_message);
			holder.tvAdvice = (TextView) convertView
					.findViewById(R.id.tv_life_advice);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		LifeMessage message = data.get(position);

		holder.ivPiture.setImageResource(message.getPictureId());
		holder.tvMessage.setText(message.getPrompMessage());
		holder.tvAdvice.setText(message.getPrompAdvice());
		return convertView;
	}

	private class ViewHolder {
		ImageView ivPiture;
		TextView tvMessage;
		TextView tvAdvice;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
