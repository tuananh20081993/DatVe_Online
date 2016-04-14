package com.datve.fragment.chonghe;


import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import com.datve.data.parse.tuyenxe.DiemDonObject;
import com.datve.data.parse.tuyenxe.ThoiGianObject;
import com.datve.data.parse.tuyenxe.TuyenXeObject;
import com.datve_online.request.Request;
import com.datve_online.request.ThongTinChiTiet;
import com.datve_online.request.TuyenXeAtivity;
import com.example.datve_online.R;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class FragmentGheTangtren extends Fragment implements OnClickListener{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View View=inflater.inflate(R.layout.layout_fragment_ghetangtren, container,
				false);
	    

           Log.d("day la tang tren", "--------------------");
		
		Intent intent = this.getActivity().getIntent();
		JSONObject jsontime=null, jsontuyen = null, jsondate = null;
		String json = (String)intent.getStringExtra("chonghe");
		try {
			jsondate = new JSONObject(json);
			Log.d("jsondate", json);
			jsontime = new JSONObject(intent.getStringExtra("Timer"));
			ThoiGianObject timeObject = new ThoiGianObject(jsontime);
			Log.d("jsontime", timeObject.getStringJson());
			jsontuyen = new JSONObject(intent.getStringExtra("tuyen"));
			TuyenXeObject tuyenObject = new TuyenXeObject(jsontuyen);
			Log.d("CHONGHE", tuyenObject.getStringJson());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	Request req = new Request("getseat", "POST");
    try {
		req.execute("RouteId="+jsontuyen.getString("Id")+"&"+"DepartureDate="+jsondate.getString("DepartureDate")+"&"+"DepartureTime="+
         jsontime.getString("Time")+"&"+"Kind="+jsontime.getString("Kind")+"&"+"CarBooking="+jsontime.getString("Id"));
	
		GridView gridview = (GridView) View.findViewById(R.id.gridview);
	    //gridview.setNumColumns();
	    //ImageAdapter adapter = new ImageAdapter(this.getActivity());
	    //gridview.setAdapter(adapter);


//	    gridview.setOnItemClickListener(new OnItemClickListener() {
//	        public void onItemClick1(AdapterView<?> parent, View v,
//	                int position, long id) {
////	            Toast.makeText(FragmentGheTangtren.this, "" + position,
////	                    Toast.LENGTH_SHORT).show();
//	        }
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
//				// TODO Auto-generated method stub
//				
//			}
//	    });
    } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
    String arr[]={"Ipad","Iphone","New Ipad",
    		 "SamSung","Nokia","Sony Ericson",
    		 "LG","Q-Mobile","HTC","Blackberry",
    		 "G Phone","FPT - Phone","HK Phone"
    		 };
		
    
		
		    Button tieptuc = (Button) View.findViewById(R.id.button1);
			tieptuc.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent myintent = new Intent(getActivity(),ThongTinChiTiet.class);
					startActivity(myintent);
					
				}
			});
//				
			Button doichuyen = (Button) View.findViewById(R.id.button2);
			doichuyen.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent myintent = new Intent(getActivity(),TuyenXeAtivity.class);
					startActivity(myintent);
				}
			});

		return View;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
