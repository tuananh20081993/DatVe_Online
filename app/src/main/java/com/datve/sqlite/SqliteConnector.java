package com.datve.sqlite;
import java.util.ArrayList;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SqliteConnector {

	private SQLiteDatabase database=null;

	public SqliteConnector(SQLiteDatabase database) {
		// TODO Auto-generated constructor stub
		this.database = database;
	}

	public void CreateTable()
	{

		ThongTinKhachHangTable();
	}


	public void DanhSachDiemDiTable()
	{
		String sql ="CREATE TABLE IF NOT EXISTS danhsachdiemdi (";
		sql+="origincode varchar(100) primary key, ";
		sql+="originname varchar(100))";
		database.execSQL(sql);

	}

	public void RouteTable()
	{
		String sql ="CREATE TABLE IF NOT EXISTS route (";
		sql+="id varchar(10) primary key, ";
		sql+="name varchar(100), ";
		sql+="origincode varchar(100), ";
		sql+="originname varchar(100), ";
		sql+="destcode varchar(100), ";
		sql+="destname varchar(100), ";
		sql+="distance varchar(100), ";
		sql+= "duration varchar(100), ";
		sql+="kind varchar(100), ";
		sql+="totalschedule varchar(100))";
		database.execSQL(sql);

	}

	public void RouteStopTable()
	{
		String sql ="CREATE TABLE IF NOT EXISTS routestop (";
		sql+="address varchar(100) primary key, ";
		sql+="routeId varchar(10),";
		sql+=" types varchar(5), ";
		sql+=" name varchar(100), ";
		sql+=" latitude varchar(100), ";
		sql+=" longtitude varchar(100), ";
		sql+=" odernumber varchar(100), ";
		sql+=" distance varchar(100), ";
		sql+= " duration varchar(100),"
				+ " foreign key(routeId) references route(id))";
		database.execSQL(sql);

	}

	public void ThongTinKhachHangTable()
	{
		String sql="CREATE TABLE IF NOT EXISTS thongtinkhachhang (";
		sql+="soDt varchar(15) primary key,";
		sql+="ten varchar(255),";
		sql+="email varchar(255),";
		sql+="ngaysinh varchar(255),";
		sql+="tinh varchar(255),";
		sql+="quanhuyen varchar(255))";
		database.execSQL(sql);	 

	}

	public void ChonGheTable()
	{
		String sql="CREATE TABLE IF NOT EXISTS chonghe (";
		sql+="Id varchar(15) primary key,";
		sql+="FloorNo varchar(255),";
		sql+="Chair varchar(255),";
		sql+="RowNo varchar(255),";
		sql+="ColumnNo varchar(255),";
		sql+="InSelect varchar(255),";
		sql+="LockChair varchar(255),";
		sql+="BookStatus varchar(255),";
		sql+="Discount varchar(255))";
		database.execSQL(sql);	
	}


	public void doInsertRecord()
	{
		ContentValues values=new ContentValues();
		values.put("sodt",  "0986863897");
		values.put("ten", "Pham Tuan Anh");
		values.put("email", "phamtuananh2008gmail.com");
		values.put("ngaysinh", "20/08/1993");
		values.put("Tinh", "Dong Nai");
		values.put("quanhuyen", "Dinh Quan");
		String msg="";
		if(database.insert("thongtinkhachhang", null, values)==-1){
			msg="Failed to insert record";
		}
		else{
			msg="insert record is successful";
		}

	}
	public ArrayList<ArrayList<String>> find(String tables,String[] cols,String selection,String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
	{
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		Cursor cur = database.query(true, tables,cols , selection, selectionArgs, groupBy, having, orderBy, limit);
		cur.moveToFirst();

		do{
			if(cur.getCount() <= 0)
				break;
			ArrayList<String>colData = new ArrayList<String>();
			for (int i = 0; i < cur.getColumnCount(); i++) {
				colData.add(cur.getString(i));
			}
			data.add(colData);
		}while(cur.moveToNext());
		cur.close();
		return data;
	}

	public void save(String table,String nullColumnHack,String[] cols,String[] vls,String conditionUpdate)
	{

		//TODO check null value;

		try{
			database.insertOrThrow(table, nullColumnHack, this.parse(cols, vls,0));
		}catch(SQLException e){
			String[] key = {vls[0]};
			database.update(table, this.parse(cols, vls,0), conditionUpdate,key );
		}
	}
	private ContentValues parse(String[]cols,String[] vls,int start) {
		ContentValues ctvls = new ContentValues();
		for (int i = 0; i < cols.length; i++) {
			ctvls.put(cols[i], vls[i]);
		}
		return ctvls;
	}

}
