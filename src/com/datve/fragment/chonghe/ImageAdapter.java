package com.datve.fragment.chonghe;

import java.util.ArrayList;

import org.json.JSONArray;

import com.example.datve_online.R;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Activity mContext;
	private ArrayList<ChonGheObject> data;
	private boolean isTwoFloor,isFirstFloor;
	private int size;
	private int getItem;
	public ImageAdapter(Activity c) {
		mContext = c;
		if(c == null)
			Log.d("NULL", "ko co du lieu ");
	}
	
	public ImageAdapter(Activity c,ArrayList<ChonGheObject> data,boolean isTwoFloor,boolean isFirstFloor,int size){
		this.data = new ArrayList<ChonGheObject>(data);
		this.mContext = c;
		this.isTwoFloor = isTwoFloor;
		this.isFirstFloor = isFirstFloor;
		this.size = size*5;
		this.getItem = 0;
	}
	public int getCount() {
		return this.size;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getViewImage(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			// if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext.getApplicationContext());
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}

	// references to our images
	private Integer[] mThumbIds = {
			R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7
	};

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = mContext.getLayoutInflater();
		View row;
		row = inflater.inflate(R.layout.layout_image, parent, false);
		ImageView i1 =(ImageView)row.findViewById(R.id.imageLogo);
		TextView t = (TextView)row.findViewById(R.id.textImageChair);
		i1.setImageResource(R.drawable.sample_7);
		t.setText("ABC");
		try{
			
			if(!this.isTwoFloor && ((position - 2) % 5 !=0) && position < this.data.size() -1){
				ChonGheObject ghe = this.data.get(position);
				t.setText(ghe.getChair());
				Log.d("IS_TOW", "is floor - "+this.isTwoFloor+" pos: "+position+" chair: "+ghe.getChair());
				if(ghe.getBookstatus().equalsIgnoreCase("1")){
					i1.setImageResource(R.drawable.ghe_blue);
				}else{
					i1.setImageResource(R.drawable.ghe_nau_02);
				}
			}
			
		}catch(Exception e){
			//TODO nothing
			i1.setImageResource(R.drawable.sample_7);
			e.printStackTrace();
		}
		//i1.setImageResource(R.drawable.sample_7);
		return row;
	}
}