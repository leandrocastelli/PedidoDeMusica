package com.lcsmobileapps.pedidodemusicas.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcsmobileapps.pedidodemusicas.R;
import com.lcsmobileapps.pedidodemusicas.util.ImageHelper;


//
public class CustomListAdapter extends BaseAdapter {

	private Context ctx;
	  
	private class ViewHolder {
		ImageView imageView;
		TextView txtName;
		
	}
	public CustomListAdapter (Context ctx) {
		super();
		this.ctx = ctx;
		
		
	}
	@Override
	public int getCount() {
		String[] array = ctx.getResources().getStringArray(R.array.musicas_list);
		return array.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
		
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null) {
			convertView = layoutInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.txtName = (TextView)convertView.findViewById(R.id.sound_name);
			
			holder.imageView = (ImageView) convertView.findViewById(R.id.radio_icon);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txtName.setText(ctx.getResources().getStringArray(R.array.musicas_list)[position]);
//		ImageHelper.loadImage(holder.imageView, R.id.radio_icon, ctx);
		holder.imageView.setImageResource(R.drawable.ic_radio);
		return convertView;
	}
	
	

}
