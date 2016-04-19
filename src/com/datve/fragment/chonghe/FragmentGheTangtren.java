package com.datve.fragment.chonghe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.datve.data.parse.tuyenxe.DiemDonObject;
import com.datve.data.parse.tuyenxe.ThoiGianObject;
import com.datve.data.parse.tuyenxe.TuyenXeAtivity;
import com.datve.data.parse.tuyenxe.TuyenXeObject;
import com.datve.sqlite.SqliteConnector;
import com.datve_online.request.Request;
import com.datve_online.request.ThongTinChiTiet;
import com.example.datve_online.R;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.SQLException;


public class FragmentGheTangTren extends Fragment implements OnClickListener{
	private ChonGheObject chonghe;
	private ThoiGianObject timeObject;
	private DiemDonObject diemdonObject;
	private TuyenXeObject tuyenObject;
	private String  json;	
	private boolean [] flag;
	private int countsove =0;
	private String soghe, sove;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) 
	{
		View View=inflater.inflate(R.layout.layout_fragment_ghetangtren, container,
				false);

		//		database = context.openOrCreateDatabase("datveonline.db", 
		//				Context.MODE_PRIVATE, null);
		//
		//		SqliteConnector sql = new SqliteConnector(database);
		//		sql.ChonGheTable();
		Log.d("day la tang duoi", "--------------------");	
		Intent intent = this.getActivity().getIntent();
		JSONObject jsontime=null, jsontuyen = null, jsondate = null, jsondiemdon = null;
		json = (String)intent.getStringExtra("chonghe");
		sove = (String)intent.getStringExtra("sove");
		try {
			jsondate = new JSONObject(json);
			Log.d("jsondate", json);
			jsontime = new JSONObject(intent.getStringExtra("Timer"));
			timeObject = new ThoiGianObject(jsontime);
			Log.d("jsontime", timeObject.getStringJson());
			jsontuyen = new JSONObject(intent.getStringExtra("tuyen"));
			tuyenObject = new TuyenXeObject(jsontuyen);
			Log.d("CHONGHE", tuyenObject.getStringJson());
			jsondiemdon = new JSONObject(intent.getStringExtra("diemdon"));
			diemdonObject = new DiemDonObject(jsondiemdon);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		JSONArray getseatarray = new JSONArray();
		ArrayList<ChonGheObject> listseat = new ArrayList<ChonGheObject>();
		ArrayList<ChonGheObject> listseattangtren = new ArrayList<ChonGheObject>();
		String GetApiSeat = " ";
		int count = 0;
		int row = 0;
		int five = 0;
		try {
			GetApiSeat = ConnectGetSeat(jsontuyen.getString("Id"),jsondate.getString("DepartureDate"),jsontime.getString("Time"),jsontime.getString("Kind"),jsontime.getString("Id"));
			Log.d("du lieu day nay", GetApiSeat);
			JSONObject getseat = new JSONObject(GetApiSeat);
			getseatarray = getseat.getJSONArray("Data");

			for (int i = 0; i < getseatarray.length(); i++) {

				chonghe = new ChonGheObject(getseatarray.getJSONObject(i));
				if(Integer.parseInt(chonghe.getRowno())> row)
					row = Integer.parseInt(chonghe.getRowno());
				//int index = (Integer.parseInt(chonghe.getRowno())-1)*4+Integer.parseInt(chonghe.getColumnno());
				//sql.save("ChonGheObject", "", chonghe.getColumns(), chonghe.getValues(), "id = ?");
			}
			boolean isTwo = !jsontime.getString("Kind").equalsIgnoreCase("Ghế");
			int ignore = (isTwo)?1:2;
			row = (isTwo)?row*2:row;
			boolean isFive = false;
			Log.d("IGNORE", ignore+"");
			int getItem = 0;
			for (int i = 0; i < row*5; i++) {

				//TODO for one floor
				chonghe = new ChonGheObject(getseatarray.getJSONObject(getItem));
				if(i == 2 || i ==3)
					Log.d("ITEM_2_3", getseatarray.getJSONObject(getItem).toString());
				Log.d("ADD_ITEM", "ITEM: "+getItem+" - "+" size list: "+listseat.size()+" -  seat: "+chonghe.getChair()+" - I: "+i+ " - igonre: "+ignore);
				if (chonghe.getFloorno().equalsIgnoreCase("1"))
				{
					listseat.add(chonghe);

				}else{
					listseattangtren.add(chonghe);
				}
				if((i != ignore || i >= row*5 -5) && getItem < getseatarray.length()-1){
					//listseat.add(chonghe);
					if(i>=row*5-5){
						if(chonghe.getRowno().equalsIgnoreCase(row+"")){
							five++;
						}
					}
					getItem++;
					if(isTwo){
						if(i % 5 == 0 || (i -2)%5 ==0){
							Log.d("tangne","I "+i);
							ignore = i+1;
						}
					}
				}else{
					if(i>=2 && !isTwo){
						ignore+=5;						
					}else{
						if(i % 5 == 0 || (i -2)%5 ==0){
							ignore = i+1;
						}
					}
				}
			}
			this.flag = new boolean[listseat.size()];
			Arrays.fill(flag, false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Log.d("FIVE", five+"");

		GridView gridview = (GridView) View.findViewById(R.id.gridview);
		ImageAdapter adapter;
		try {
			adapter = new ImageAdapter(this.getActivity(),listseattangtren,(jsontime.getString("Kind").equalsIgnoreCase("Ghế")?false:true),true,row,(five == 4)?true:false);
			gridview.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


  gridview.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
		// TODO Auto-generated method stub
		
		ChonGheObject src = (ChonGheObject)parent.getItemAtPosition(position);
		soghe = src.getChair();
		Log.d("số ghế",soghe );
		ImageView image = (ImageView) view.findViewById(R.id.imageLogo);
		//				if(image.getDrawable().get)
		if(src.getBookstatus().equalsIgnoreCase("1")){
			Toast.makeText(getActivity(), "Ghế Đã được chọn", Toast.LENGTH_SHORT).show();
		}else
		{
			if(flag[position])
			{
				image.setImageResource(R.drawable.ghe_nau_02);
				flag[position] = false;
				countsove=(countsove<=0)?0:countsove-1;
				Toast.makeText(getActivity(), "Bạn Đã bỏ chọn ghế: " +soghe, Toast.LENGTH_SHORT).show();
			}else{
				if(countsove >= Integer.parseInt(sove)){
					Toast.makeText(getActivity(), "Bạn Đã vượt quá số vé ", Toast.LENGTH_SHORT).show();
				}
				else{
					image.setImageResource(R.drawable.ghe_red);
					flag[position] = true;
					countsove++;
					Toast.makeText(getActivity(), "Bạn Đang chọn ghế: " +soghe, Toast.LENGTH_SHORT).show();
				}
			}
				
			
			

		}
		
	}
});



		Button tieptuc = (Button) View.findViewById(R.id.button1);
		tieptuc.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				String thoigian = null;
				String tuyenxe = null;
				String diemdon = null;
				Intent myintent = new Intent(getActivity(),ThongTinChiTiet.class);
				myintent.putExtra("tuyen", json);
				myintent.putExtra("sove", sove);
				//	Log.d("json", json);
				try {
					thoigian = "{\"Time\":\""+timeObject.getString("Time")+"\"}";	
					tuyenxe = "{\"Name\":\""+tuyenObject.getString("Name")+"\",\"Price\":\""+tuyenObject.getString("Price")+"\"}";
					diemdon = "{\"Address\":\""+diemdonObject.getString("Address")+"\",\"Phone\":\""+diemdonObject.getString("Phone")+"\"}";
					Log.d("thoi gian", thoigian);
					Log.d("thoi gian", tuyenxe);
					Log.d("thoi gian", diemdon);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//gui thuoc tinh trong cac doi tuong json object
				myintent.putExtra( "thoigian", thoigian);
				myintent.putExtra( "tuyenxe", tuyenxe);
				myintent.putExtra("diemdon", diemdon);

				//gui doi tuong json object
				myintent.putExtra("time", timeObject.getStringJson());
				myintent.putExtra("route", tuyenObject.getStringJson());
				//	Log.d("After_SEND", tuyenObject.getStringJson());
				myintent.putExtra("takepoint",diemdonObject.getStringJson());
				//    Log.d("Send_final", diemdonObject.getStringJson());

				startActivity(myintent);

			}
		});

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



	public String ConnectGetSeat(String id, String departuredate, String Departuretime,String kind, String carbooking)
	{

		Request req = new Request("getseat", "POST");
		req.execute("RouteId="+id+"&"+"DepartureDate="+departuredate+"&"+"DepartureTime="+
				Departuretime+"&"+"Kind="+kind+"&"+"CarBooking="+carbooking);
		try {
			return req.get();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return " ";
	}

}
