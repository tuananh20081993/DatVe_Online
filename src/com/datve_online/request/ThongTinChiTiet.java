package com.datve_online.request;


import org.json.JSONException;
import org.json.JSONObject;



import com.example.datve_online.R;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ThongTinChiTiet extends Activity
{
	private String json;
	private String thoigianobject;
	private String tuyenobject;
	private String diemdonobject;
	private String sogheobject;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_thongtinchitiet);
		
		Intent intent = this.getIntent();
		json =(String)intent.getStringExtra("tuyen");
		sogheobject=(String)intent.getStringExtra("soghe");
		thoigianobject=(String)intent.getStringExtra("thoigian");
		tuyenobject = (String)intent.getStringExtra("tuyenxe");
		diemdonobject = (String)intent.getStringExtra("diemdon");

		Log.d("json", diemdonobject);
		JSONObject thoigian=null;
		JSONObject tuyenxe =null;
		JSONObject diemdon =null;
		JSONObject chontuyen = null;
		JSONObject chonghe =null;
		try {
			chontuyen = new JSONObject(json);
            thoigian = new JSONObject(thoigianobject);
            tuyenxe = new JSONObject(tuyenobject);
            diemdon= new JSONObject(diemdonobject);
            chonghe = new JSONObject(sogheobject);
//			Log.d("timeobject", timeobject.getStringJson());
//			tuyenxe = new JSONObject(intent.getStringExtra("tuyenxe"));
//			tuyenobject = new TuyenXeObject(tuyenxe);
//			Log.d("tuyenobject", tuyenobject.getStringJson());
//			diemdon = new JSONObject(intent.getStringExtra("diemdon"));
//			diemdonobject= new DiemDonObject(diemdon);
//			Log.d("diemdon", diemdonobject.getStringJson());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
		
		TextView tuyenduong = (TextView) findViewById(R.id.tuyenduong);
		TextView gia = (TextView) findViewById(R.id.gia);
		TextView soghe = (TextView) findViewById(R.id.soghe);
		TextView giodi = (TextView) findViewById(R.id.xuatphat);
		TextView phone = (TextView) findViewById(R.id.gioden);
		TextView ngaydi = (TextView) findViewById(R.id.ngaydi);
		TextView ngayden = (TextView) findViewById(R.id.ngayden);
		TextView noidon = (TextView) findViewById(R.id.diemdon);
		
		try {
			tuyenduong.setText(tuyenxe.getString("Name"));
         	gia.setText(tuyenxe.getString("Price")+" "+"VND");
         	soghe.setText(chonghe.getString("Chair"));
			giodi.setText(thoigian.getString("Time"));
			phone.setText(diemdon.getString("Phone"));
			ngaydi.setText(chontuyen.getString("DepartureDate"));
			ngayden.setText(chontuyen.getString("DepartureDate"));
			noidon.setText(diemdon.getString("Address"));
			//soghe.setText(20);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




