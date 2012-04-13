package com.billdlabs.dMinion;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InitAdapter extends BaseAdapter {
	private static ArrayList<character> searchArrayList;
	private LayoutInflater mInflater;
	
	public InitAdapter(Context context, ArrayList<character> results){
		searchArrayList = results;
		mInflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return searchArrayList.size();
	}

	public Object getItem(int position) {
		return searchArrayList.size();
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null){
			convertView = mInflater.inflate(R.layout.custom_row_view, null);
			holder = new ViewHolder();
			holder.dispName = (TextView) convertView.findViewById(R.id.dispName);
			holder.dispHP = (TextView) convertView.findViewById(R.id.dispHP);
			holder.dispTempHP = (TextView) convertView.findViewById(R.id.dispTempHP);
			holder.dispAC = (TextView) convertView.findViewById(R.id.dispAC);
			holder.dispFort = (TextView) convertView.findViewById(R.id.dispFort);
			holder.dispWill = (TextView) convertView.findViewById(R.id.dispWill);
			holder.dispRef = (TextView) convertView.findViewById(R.id.dispRef);
			
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		holder.dispName.setText(searchArrayList.get(position).getCharName());
		holder.dispHP.setText(Integer.toString(searchArrayList.get(position).getHp()));
		holder.dispTempHP.setText(Integer.toString(searchArrayList.get(position).getTempHp()));
		holder.dispAC.setText(Integer.toString(searchArrayList.get(position).getAc()));
		holder.dispFort.setText(Integer.toString(searchArrayList.get(position).getFortitude()));
		holder.dispWill.setText(Integer.toString(searchArrayList.get(position).getWill()));
		holder.dispRef.setText(Integer.toString(searchArrayList.get(position).getReflex()));
		
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView dispName;
		TextView dispHP;
		TextView dispTempHP;
		TextView dispAC;
		TextView dispFort;
		TextView dispWill;
		TextView dispRef;
	}
}
