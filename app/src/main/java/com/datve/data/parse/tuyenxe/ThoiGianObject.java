package com.datve.data.parse.tuyenxe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class ThoiGianObject extends JSONObject implements Serializable {
	private String Id, Time, Kind, IDKind, IDTimeStart, IDWay;
	
	public ThoiGianObject(String id, String time, String kind, String idkind, String idtimeStart,String idway) 
	throws JSONException{
		super("{\"Id\":\""+id+"\",\"Time\":\""+time+"\",\"Kind\":\""+kind+"\",\"IDKind\":\""+idkind+"\",\"IDTimeStart\":\""+idtimeStart+"\""
				+ ",\"IDWay\":\""+idway+"\" }");
		this.Id=id;
		this.Time=time;
		this.Kind=kind;
		this.IDKind=idkind;
		this.IDTimeStart=idtimeStart;
		this.IDWay=idway;
		
	}
	
	public ThoiGianObject(JSONObject jsonObject) throws JSONException{
		// TODO Auto-generated constructor stub
		super(jsonObject.toString());
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTime() {
		try {
			return super.getString("Time");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public String getIDKind() {
		return IDKind;
	}

	public void setIDKind(String iDKind) {
		IDKind = iDKind;
	}

	public String getIDTimeStart() {
		return IDTimeStart;
	}

	public void setIDTimeStart(String iDTimeStart) {
		IDTimeStart = iDTimeStart;
	}

	public String getIDWay() {
		return IDWay;
	}

	public void setIDWay(String iDWay) {
		IDWay = iDWay;
	}
		
	public String getStringJson() {
		return super.toString();
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		try {
			return this.getString("Time")+" ("+this.getString("Kind")+")";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
