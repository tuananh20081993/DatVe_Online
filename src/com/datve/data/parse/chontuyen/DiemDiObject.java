package com.datve.data.parse.chontuyen;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class DiemDiObject extends JSONObject{
	private String id,name,origincode,originname,destcode,destname,distance,duration,kind,totalschedule;
	private ArrayList<DiemDenObject>diemDenObject;
	public DiemDiObject(JSONObject obj) throws JSONException{
		super(obj.toString());
	}
	
	public DiemDiObject(String id, String name, String origincode, String originname, String destcode, String destname,
			String distance, String duration, String kind, String totalschedule) throws JSONException {
		super("{\"Id\":\""+id+"\",\"Name\":\""+name+"\",\"OriginCode\":\""+origincode+"\",\"DestCode\":\""+destcode+"\""
				+ ",\"DestName\":\""+destname+"\",\"Distance\":\""+distance+"\",\"Kind\":\""+kind+"\",\"TotalSchedule\":\""+totalschedule+"\" }");
		this.id = id;
		this.name = name;
		this.origincode = origincode;
		this.originname = originname;
		this.destcode = destcode;
		this.destname = destname;
		this.distance = distance;
		this.duration = duration;
		this.kind = kind;
		this.totalschedule = totalschedule;
		
	}

	public void addRoutes(DiemDenObject diemDenObject) {
		this.diemDenObject.add(diemDenObject);
	}

	public String getId() {
		try {
			return super.getString("Id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrigincode() {
		return origincode;
	}

	public void setOrigincode(String origincode) {
		this.origincode = origincode;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getDestcode() {
		return destcode;
	}

	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}

	public String getDestname() {
		return destname;
	}

	public void setDestname(String destname) {
		this.destname = destname;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTotalschedule() {
		return totalschedule;
	}

	public void setTotalschedule(String totalschedule) {
		this.totalschedule = totalschedule;
	}

	public ArrayList<DiemDenObject> getRouteStop() {
		return diemDenObject;
	}

	public void setRouteStop(ArrayList<DiemDenObject> diemDenObject) {
		this.diemDenObject = diemDenObject;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String []getColumns() {
		String rets[] = {"id","name","origincode","originname", "destcode","destname","distance","duration","kind","totalschedule"};
		return rets;
	}
	public String [] getValues() throws JSONException {
		String rets[] = {super.getString("Id"),super.getString("Name"),super.getString("OriginCode"),super.getString("OriginName"),super.getString("DestCode"), super.getString("DestName"),super.getString("Distance"),super.getString("Duration"),super.getString("Kind"),super.getString("TotalSchedule")};
		return rets;
	}
	private boolean fromSrc;
	
	public boolean isFromSrc() {
		return fromSrc;
	}

	public void setFromSrc(boolean fromSrc) {
		this.fromSrc = fromSrc;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.fromSrc?this.originname:this.destname;
	}
}
