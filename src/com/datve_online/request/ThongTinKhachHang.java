package com.datve_online.request;

import java.util.ArrayList;


import com.datve.sqlite.SqliteConnector;
import com.example.datve_online.R;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;


import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;
public class ThongTinKhachHang extends Activity implements OnMenuItemClickListener,OnItemSelectedListener, OnItemClickListener, OnClickListener{
	private SQLiteDatabase database=null;
	private AutoCompleteTextView sdt;
	private SqliteConnector dbHelper;
	private EditText ten,email,ngaysinh,tinh,quanhuyen;
	private Intent myData;
	private String json;
	Button tieptuc;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		myData = this.getIntent();
		json = (String) myData.getStringExtra("tuyen");
		Log.d("jsonthongtinkhachhang", json);
		setContentView(R.layout.layout_thongtinkhachhang);
		database = openOrCreateDatabase(
				"datveonline.db",
				MODE_PRIVATE,
				null);
		dbHelper = new SqliteConnector(database);
		dbHelper.CreateTable();
		dbHelper.doInsertRecord();
		sdt = (AutoCompleteTextView)findViewById(R.id.sdt);
		ten = (EditText)findViewById(R.id.edittextten);
		email = (EditText)findViewById(R.id.edittextemail);
		ngaysinh = (EditText)findViewById(R.id.edittextngaysinh);
		tinh = (EditText) findViewById(R.id.edittextinh);
		quanhuyen = (EditText) findViewById(R.id.edittexhuyen);
		String[] cols = {"soDt"};
		String[] selectionArgs = {"NULL"};
		ArrayList<ArrayList<String>> datas = dbHelper.find("thongtinkhachhang",cols, "soDt is not ?", selectionArgs, "soDt", "", "soDt", "10");
		String[]data = new String[datas.size()];
		for (int i = 0; i < datas.size(); i++) {
			data[i] = new String(datas.get(i).get(0));
		}
		ArrayAdapter<String> sdtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,data);
		sdt.setAdapter(sdtAdapter);
		sdt.setThreshold(1);
		sdt.setOnItemSelectedListener(this);
		sdt.setOnItemClickListener(this);
		
		Button huy = (Button) findViewById(R.id.button2);
		huy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	    tieptuc = (Button) findViewById(R.id.button1);
		tieptuc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				myData.setClass(getApplicationContext(),TuyenXeAtivity.class);
				//String soDt,ten,email,ngaysinh,tinh,quanhuyen;
				String[]cols = {"soDt","ten","email","ngaysinh","tinh","quanhuyen"};
				String[]vls = {sdt.getText().toString(),ten.getText().toString(),email.getText().toString(),
						ngaysinh.getText().toString(),tinh.getText().toString(),quanhuyen.getText().toString()};
				dbHelper.save("thongtinkhachhang", "", cols, vls,"soDt=?");
				
				
				myData.putExtra("thongtin",json);
		
				
				startActivity(myData);
			    }		});
		

		
		
	
	}
	


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		String sdt = parent.getItemAtPosition(position).toString();
		String[] cols = {"ten","email","ngaysinh","tinh","quanhuyen"};
		String[] args = {sdt};
		
		ArrayList<ArrayList<String>> data = this.dbHelper.find("thongtinkhachhang", cols, "soDt = ?1", args, "", "", "", "1");
		ten = (EditText)findViewById(R.id.edittextten);
		email = (EditText)findViewById(R.id.edittextemail);
		ngaysinh = (EditText)findViewById(R.id.edittextngaysinh);
		tinh = (EditText) findViewById(R.id.edittextinh);
		quanhuyen = (EditText) findViewById(R.id.edittexhuyen);
		
		if(data.size() > 0) {
			ten.setText(data.get(0).get(0));
			email.setText(data.get(0).get(1));
			ngaysinh.setText(data.get(0).get(2));
			tinh.setText(data.get(0).get(3));
			quanhuyen.setText(data.get(0).get(4));
			
		}
		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
		
}
