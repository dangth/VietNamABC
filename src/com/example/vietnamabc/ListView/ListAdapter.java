package com.example.vietnamabc.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.vietnamabc.Constants;
import com.example.vietnamabc.R;
import com.example.vietnamabc.R.drawable;
import com.example.vietnamabc.R.id;
import com.example.vietnamabc.R.layout;
import com.example.vietnamabc.R.raw;
import com.example.vietnamabc.R.string;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private Activity activity;
	private static LayoutInflater inflater = null;


	public ListAdapter(Activity activity) {
		super();
		this.activity = activity;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Constants.TITLE.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.list_row, null, true);

		TextView title = (TextView) vi.findViewById(R.id.title);
		ImageView thum_image = (ImageView) vi.findViewById(R.id.list_image);
		TextView artist = (TextView) vi.findViewById(R.id.artist);
		title.setText(Constants.TITLE[position]);
		artist.setText(Constants.ARTIST[position]);
		thum_image.setImageResource(Constants.ICON_ITEM[position]);
		return vi;
	}

}
