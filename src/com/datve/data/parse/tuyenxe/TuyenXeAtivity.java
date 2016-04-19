package com.datve.data.parse.tuyenxe;


import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.datve.fragment.chonghe.FragmentTabManager;
import com.datve_online.request.Request;
import com.example.datve_online.R;
import android.view.View.OnClickListener;
import android.widget.Button;



public class TuyenXeAtivity extends Activity implements  OnItemSelectedListener 
{
	private Intent myData;
	private TuyenXeObject tx, datatrans;
	private ThoiGianObject timer;
	private DiemDonObject diemdon,datadiemdon;
	private JSONObject dataTuyen = null;
	private JSONObject time = null;
	private String json,sove;
	private String ApiGetTime = " ";
	private String ApiGetboardingpoint = " ";
	private Spinner spintake, spinroute,spintime;
	private Button buttonPrev, buttonNext;
	private ThoiGianObject dataSend;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.chontuyenxe);

		buttonNext = (Button) findViewById(R.id.button1);
		buttonNext.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) 
			{
				Intent myintent = new Intent(getApplicationContext(),FragmentTabManager.class);
				
				
				if(dataSend == null) {
					
				}else{
					Log.d("BEFORE_SEND", dataSend.getStringJson());
					myintent.putExtra("chonghe", json);	
					myintent.putExtra("sove", sove);
					Log.d("json", json);
					myintent.putExtra( "Timer", dataSend.getStringJson());
					Log.d("SEND", dataSend.getStringJson());
					myintent.putExtra("tuyen", datatrans.getStringJson());
					Log.d("After_SEND", datatrans.getStringJson());
                    myintent.putExtra("diemdon",datadiemdon.getStringJson());
					startActivity(myintent);					
				}
			}
		});


		buttonPrev = (Button) findViewById(R.id.button2);
		buttonPrev.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) 
			{
				finish();
			}
		});

		TextView tentuyen = (TextView) findViewById(R.id.texttentuyen);
		spinroute=(Spinner) findViewById(R.id.spchontuyen);
		String ApiGetRoutePrice = " "; 
		myData= this.getIntent();
		json = (String) myData.getStringExtra("thongtin");
		sove = (String) myData.getStringExtra("sove");
		Log.d("jsontuyenxe", json);
		
		
		try {
			dataTuyen = new JSONObject(json);
			ApiGetRoutePrice = this.ConnectDataApiGetRoutePrice(dataTuyen.getString("OriginCode"),dataTuyen.getString("DestCode"), dataTuyen.getString("DepartureDate"));
			tentuyen.setText(dataTuyen.getString("OriginName")+" - " + dataTuyen.getString("DestName"));
			Log.d("fromApI", ApiGetRoutePrice);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray tuyenxearr = new JSONArray();
		ArrayList<TuyenXeObject> arrAdapter = new ArrayList<TuyenXeObject>();


		try {
			JSONObject tuyenxe = new JSONObject(ApiGetRoutePrice);
			tuyenxearr= tuyenxe.getJSONArray("Data");
			for (int i = 0; i < tuyenxearr.length(); i++) {
				tx = new TuyenXeObject(tuyenxearr.getJSONObject(i));
				arrAdapter.add(tx);
			}

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		


		ArrayAdapter<TuyenXeObject> arrayAdapter = new ArrayAdapter<TuyenXeObject>(TuyenXeAtivity.this,android.R.layout.simple_spinner_item,arrAdapter.subList(0, arrAdapter.size()));
		ArrayAdapter adapterroute=arrayAdapter;

		
		spinroute.setOnItemSelectedListener(this);
		adapterroute.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinroute.setAdapter(adapterroute);
		spinroute.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				
				tx = (TuyenXeObject)parent.getItemAtPosition(position);
				datatrans =  (TuyenXeObject)parent.getItemAtPosition(position); 
				
				  ArrayList<ThoiGianObject> list = new ArrayList<ThoiGianObject>();
				try {
					
					ApiGetTime = ConnectDataGetTime(tx.getString("Id"), dataTuyen.getString("DepartureDate"));
					Log.d("datagio", ApiGetTime);
					time = new JSONObject(ApiGetTime);
					JSONArray timearr = new JSONArray();
					
					timearr = time.getJSONArray("Data");
					for (int i = 0; i <timearr.length(); i++) {
						timer= new ThoiGianObject(timearr.getJSONObject(i));
						list.add(timer);
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ArrayAdapter<ThoiGianObject> arrayadapter = new ArrayAdapter<ThoiGianObject>(TuyenXeAtivity.this,android.R.layout.simple_spinner_item,list.subList(0, list.size()));
				ArrayAdapter adaptime = arrayadapter;
				spintime.setAdapter(adaptime);
				  
				Toast.makeText(TuyenXeAtivity.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
			}
			
			
			
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		
		//Log.d("datagio", ApiGetTime);
		spintime=(Spinner) findViewById(R.id.spchongio);
		spintake=(Spinner) findViewById(R.id.spchondiemdon);
		spintime.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				timer =(ThoiGianObject)parent.getItemAtPosition(position);
				dataSend = (ThoiGianObject)parent.getItemAtPosition(position);
				ArrayList<DiemDonObject> listdd = new ArrayList<DiemDonObject>();
				try {
					ApiGetboardingpoint = ConnectDatatGetBoardingPoint(tx.getString("Id"),timer.getString("Time"),dataTuyen.getString("DepartureDate"));
					//Log.d("datadiem", ApiGetboardingpoint);
					JSONObject point = new JSONObject(ApiGetboardingpoint);
					JSONArray ptarr = new JSONArray();
					ptarr = point.getJSONArray("Data");
					for (int i = 0; i < ptarr.length(); i++) {
	                   	diemdon = new DiemDonObject(ptarr.getJSONObject(i));
	                   	listdd.add(diemdon);
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayAdapter<DiemDonObject> arrayadapter = new ArrayAdapter<DiemDonObject>(TuyenXeAtivity.this,android.R.layout.simple_spinner_item,listdd.subList(0,listdd.size()));
				ArrayAdapter arrdiemdon = arrayadapter;
				spintake.setAdapter(arrdiemdon);
				arrdiemdon.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

				Toast.makeText(TuyenXeAtivity.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		spintake.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				diemdon =(DiemDonObject)parent.getItemAtPosition(position);
				datadiemdon=(DiemDonObject)parent.getItemAtPosition(position);
				
				Toast.makeText(TuyenXeAtivity.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}


	public String ConnectDataApiGetRoutePrice(String OrignCode,String DestCode, String DepartureDate)
	{

		Request req = new Request("getrouteprice", "POST");

		req.execute("OriginCode="+ OrignCode+"&"+"DestCode="+ DestCode+"&"+"DepartureDate="+DepartureDate);
		try {

			return req.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String ConnectDataGetTime(String Id, String DepartureDate)
	{
		Request req = new Request("gettime", "POST");
		req.execute("RouteId="+Id+"&"+"DepartureDate="+DepartureDate);
		try {

			return req.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}


	public String ConnectDatatGetBoardingPoint(String Id, String DepartureTime, String DepartureDate)
	{
		Request req = new Request("getboardingpoint", "POST");
		req.execute("RouteId="+Id+"&"+"DepartureTime="+DepartureTime+"&"+"DepartureDate="+DepartureDate);

		try {

			return req.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  "";
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}


	


}
