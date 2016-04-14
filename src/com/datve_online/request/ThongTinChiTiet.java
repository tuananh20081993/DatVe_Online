package com.datve_online.request;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.datve.data.parse.tuyenxe.DiemDonObject;
import com.datve.data.parse.tuyenxe.ThoiGianObject;
import com.datve.data.parse.tuyenxe.TuyenXeObject;
import com.datve.fragment.chonghe.FragmentChonGhe;
import com.datve.fragment.chonghe.FragmentTabManager;
import com.datve.fragment.chonghe.TabFactory;
import com.datve.fragment.chonghe.ImageAdapter;
import com.datve.sqlite.SqliteConnector;
import com.datve_online.MainActivity;
import com.example.datve_online.R;
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
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;
public class ThongTinChiTiet extends Activity
{
	private String json;
	private String thoigianobject;
	private String tuyenobject;
	private String diemdonobject;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_thongtinchitiet);
		
		Intent intent = this.getIntent();
		json =(String)intent.getStringExtra("tuyen");
		thoigianobject=(String)intent.getStringExtra("thoigian");
		tuyenobject = (String)intent.getStringExtra("tuyenxe");
		diemdonobject = (String)intent.getStringExtra("diemdon");

		Log.d("json", diemdonobject);
		JSONObject thoigian=null;
		JSONObject tuyenxe =null;
		JSONObject diemdon =null;
		JSONObject chontuyen = null;
		try {
			chontuyen = new JSONObject(json);
            thoigian = new JSONObject(thoigianobject);
            tuyenxe = new JSONObject(tuyenobject);
            diemdon= new JSONObject(diemdonobject);
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
			giodi.setText(thoigian.getString("Time"));
			phone.setText(diemdon.getString("Phone"));
			ngaydi.setText(chontuyen.getString("DepartureDate"));
			ngayden.setText(chontuyen.getString("DepartureDate"));
			noidon.setText(diemdon.getString("Address"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




