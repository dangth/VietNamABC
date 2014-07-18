package com.example.vietnamabc.Grid;

import com.example.vietnamabc.Constants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridBoardAdapter extends BaseAdapter {
	private Context context;

	public GridBoardAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GridBoardAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Constants.THUMB_IMAGES.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Constants.THUMB_IMAGES[position];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		if(convertView==null){
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(110,110));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5, 5, 5, 5);
		}else{
			imageView = (ImageView)convertView;
		}
		imageView.setImageResource(Constants.THUMB_IMAGES[position]);
		return imageView;
	}
}