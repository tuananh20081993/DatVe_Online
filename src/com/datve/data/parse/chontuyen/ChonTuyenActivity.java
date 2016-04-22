package com.datve.data.parse.chontuyen;


import java.util.Iterator;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import com.datve.sqlite.SqliteConnector;
import com.datve_online.request.ThongTinKhachHang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.datve_online.R;
public class ChonTuyenActivity extends Activity implements OnClickListener, OnItemSelectedListener, OnItemClickListener


{

	private TextView txtDate, txtsove;
	private AutoCompleteTextView diemdi;
	private AutoCompleteTextView diemden;
	private SQLiteDatabase database;
	private String datadiemdi, datadiemden;
	private String tendiemdi, tendiemden;


	/**
	 * @throws JSONException
	 */
	private void initdatabase() throws JSONException
	{
        
		database = openOrCreateDatabase(
				"datveonline.db",
				MODE_PRIVATE,
				null);

		SqliteConnector sql = new SqliteConnector(database);
		sql.DanhSachDiemDiTable();
		sql.RouteTable();
		sql.RouteStopTable();
		String ret="",read="";

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/data/data/com.example.datve_online/routes.json")), "UtF-8"));
			while((read = in.readLine() )!= null){
				ret+=read;
			}
			in.close();		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Router routes = new Router(ret,true);
		JSONArray poinss =new JSONArray(ret);
		for (int i = 0; i < poinss.length(); i++) {
			DiemDiObject diemDiObject = new DiemDiObject(poinss.getJSONObject(i));
			sql.save("route", "", diemDiObject.getColumns(), diemDiObject.getValues(), "id = ?");
			//TODO implement stub getColumns, getValues
			JSONArray r = diemDiObject.getJSONArray("RouteStops");
			for (int j = 0; j < r.length(); j++) {
				DiemDenObject rst = new DiemDenObject(r.getJSONObject(j));
				rst.setRouteId(diemDiObject.getId());
				Log.d("routeStop", rst.getRouteId());
				sql.save("routestop", "", rst.getColumns(), rst.getValues(), "Address = ?");
			}	
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//cpheck file exits db file; 
		File db = new File("/data/data/com.example.datve_online/databases/datveonline.db");
		Log.d("FILE_PATH", db.getAbsolutePath());
		if(!db.exists())
		{
			//run script;
			try {
				this.initdatabase();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setContentView(R.layout.layout_chontuyen);
		database = openOrCreateDatabase(
				"datveonline.db",
				MODE_PRIVATE,
				null);
		SqliteConnector conn = new SqliteConnector(database);
		String cols[] = {"id","name","origincode","originname","destcode","destname","distance","duration","kind","totalschedule"};
		String selectionArgs[] = {};

		ArrayList<ArrayList<String>> reslt = conn.find("route", cols, "", selectionArgs, "", "", "", null);
		ArrayList<DiemDiObject> listItem = getAdapter(reslt,true);

		diemdi=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		diemden=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
		txtsove = (TextView)findViewById(R.id.sove);


		
		ArrayAdapter myAdapterdiemdi = new ArrayAdapter( this, android.R.layout.simple_dropdown_item_1line,listItem.subList(0, listItem.size()));
		diemdi.setAdapter(myAdapterdiemdi);
		diemdi.setThreshold(1);
		diemdi.setOnItemSelectedListener(this);
		diemdi.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				// TODO Auto-generated method stub
				DiemDiObject src = (DiemDiObject)parent.getItemAtPosition(position);
				try {
					database = openOrCreateDatabase(
							"datveonline.db",
							MODE_PRIVATE,
							null);
					SqliteConnector conn = new SqliteConnector(database);
					String cols[] = {"id","name","origincode","originname","destcode","destname","distance","duration","kind","totalschedule"};
					String selectionArgs[] = {src.getOrigincode()};
					datadiemdi=src.getOrigincode();
					tendiemdi=src.getOriginname();
					

					ArrayList<ArrayList<String>> reslt = conn.find("route", cols, "origincode =?", selectionArgs, "", "", "", null);
					ArrayList<DiemDiObject> diemDiObject = getAdapter(reslt,false);

					ArrayAdapter myAdapterdiemden = new ArrayAdapter( ChonTuyenActivity.this, android.R.layout.simple_dropdown_item_1line,diemDiObject.subList(0, diemDiObject.size()));
					diemden.setAdapter(myAdapterdiemden);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		ArrayAdapter myAdapterdiemden = new ArrayAdapter( ChonTuyenActivity.this, android.R.layout.simple_dropdown_item_1line,listItem.subList(0, listItem.size()));
		diemden.setAdapter(myAdapterdiemden);
		diemden.setThreshold(1);
		diemden.setOnItemSelectedListener(this);
		diemden.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				DiemDiObject src = (DiemDiObject)parent.getItemAtPosition(position);
				datadiemden = src.getDestcode();
				tendiemden=src.getDestname();
			}

		});

		Button tieptuc = (Button) findViewById(R.id.btnthanhtoan);
		tieptuc.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChonTuyenActivity.this,ThongTinKhachHang.class);
				String data = "{\"OriginCode\":\""+datadiemdi+"\",\"DestCode\":\""+datadiemden+"\",\"DepartureDate\":\""+txtDate.getText()+"\",\"OriginName\":\""+tendiemdi+"\","
						+ "\"DestName\":\""+tendiemden+"\"}";
				String sove = txtsove.getText()+"";
				intent.putExtra("sove", sove);
				intent.putExtra("tuyen", data);
				
				Log.d("data", data);
				startActivity(intent);

			}


		});


		Button huy = (Button) findViewById(R.id.button2);
		huy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		txtDate = (TextView) findViewById(R.id.txtdate);
		getDefaultInfor();
		Button date = (Button) findViewById(R.id.btndate);
		date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub		
				showDatePickerDialog();
			}

			private void showDatePickerDialog() 
			{
				// TODO Auto-generated method stub

				OnDateSetListener callback=new OnDateSetListener() 
				{
					public void onDateSet(DatePicker view, int year,int monthOfYear,int dayOfMonth) 
					{
						//Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
						CharSequence strDate = null;
                        Time chosenDate = new Time();        
                        chosenDate.set(dayOfMonth, monthOfYear, year);
                        long dtDob = chosenDate.toMillis(true);

                        strDate = DateFormat.format("dd-MM-yyyy", dtDob);

                        txtDate.setText(strDate);
					}
				};

				// hiện datepickerdialog
				String s=txtDate.getText()+"";
				String strArrtmp[]=s.split("-");
				int ngay=Integer.parseInt(strArrtmp[0]);
				int thang=Integer.parseInt(strArrtmp[1])-1;
				int nam=Integer.parseInt(strArrtmp[2]);
				DatePickerDialog pic=new DatePickerDialog(
						ChonTuyenActivity.this,
						callback, nam, thang, ngay);
				pic.setTitle("Chọn ngày khởi hành");
				pic.show();
			}
		});
	}
	
	 public void getDefaultInfor()
	 {
	 //lấy ngày hiện tại của hệ thống
	Calendar cal=Calendar.getInstance();
	 SimpleDateFormat dft=null;
	 //Định dạng ngày / tháng /năm
	 dft=new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
	 String strDate=dft.format(cal.getTime());
	 //hiển thị lên giao diện
	 txtDate.setText(strDate);
	 }



	private ArrayList<DiemDiObject> getAdapter(ArrayList<ArrayList<String>> reslt,boolean fromSrc) {
		ArrayList<DiemDiObject>listItem = new ArrayList<DiemDiObject>();
		String existed = "";
		for (Iterator iterator = reslt.iterator(); iterator.hasNext();) {
			ArrayList<String> arrayList = (ArrayList<String>) iterator.next();
			DiemDiObject r;
			try {
				if(existed.contains(arrayList.get((fromSrc?3:5)))){
					continue;
				}
				existed+=arrayList.get((fromSrc?3:5))+",";
				r = new DiemDiObject(arrayList.get(0), arrayList.get(1),arrayList.get(2),arrayList.get(3),
						arrayList.get(4),arrayList.get(5),arrayList.get(6),arrayList.get(7),
						arrayList.get(8),arrayList.get(9));
				r.setFromSrc(fromSrc);
				listItem.add(r);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
		return listItem;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}




	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getBaseContext(), "Position:"+position+" Month:"+parent.getItemAtPosition(position),
				Toast.LENGTH_LONG).show();

		Log.d("AutocompleteContacts", "Position:"+position+" Month:"+parent.getItemAtPosition(position));
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		//		if(parent.getId() == diemdi.getId()){
		//			
		//		}
		InputMethodManager imm = (InputMethodManager) getSystemService(
				INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}



}
