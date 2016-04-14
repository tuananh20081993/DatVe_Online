package com.datve.data.parse.tuyenxe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TuyenXeObject extends JSONObject{
	
	private String id;
	private String name;
	private String OriginCode;
	private String OriginName;
	private String DestCode;
	private String DestName;
	private String Distance;
	private String Duration;
	private String  TotalSchedule;
	private String Price;
	private String TribId;
	public TuyenXeObject(String id, String name, String originCode, String originName, String destCode,
			String destName, String distance, String duration, String totalSchedule, String price, String tribId) 
	throws JSONException{
		super("{\"Id\":\""+id+"\",\"Name\":\""+name+"\",\"OriginCode\":\""+originCode+"\",\"OriginName\":\""+originName+"\",\"DestCode\":\""+destCode+"\""
				+ ",\"DestName\":\""+destName+"\",\"Distance\":\""+distance+"\",\"Duration\":\""+duration+"\",\"TotalSchedule\":\""+totalSchedule+"\",\"Price\":\""+price+"\",\"TribId\":\""+tribId+"\" }");
		this.id = id;
		this.name = name;
		this.OriginCode = originCode;
		this.OriginName = originName;
		this.DestCode = destCode;
		this.DestName = destName;
		this.Distance = distance;
		this.Duration = duration;
		this.TotalSchedule = totalSchedule;
		this.Price = price;
		this.TribId = tribId;
	}
	public TuyenXeObject(JSONObject jsonObject) throws JSONException{
		// TODO Auto-generated constructor stub
		super(jsonObject.toString());
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginCode() {
		return OriginCode;
	}
	public void setOriginCode(String originCode) {
		OriginCode = originCode;
	}
	public String getOriginName() {
		return OriginName;
	}
	public void setOriginName(String originName) {
		OriginName = originName;
	}
	public String getDestCode() {
		return DestCode;
	}
	public void setDestCode(String destCode) {
		DestCode = destCode;
	}
	public String getDestName() {
		return DestName;
	}
	public void setDestName(String destName) {
		DestName = destName;
	}
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public String getTotalSchedule() {
		return TotalSchedule;
	}
	public void setTotalSchedule(String totalSchedule) {
		TotalSchedule = totalSchedule;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getTribId() {
		return TribId;
	}
	public void setTribId(String tribId) {
		TribId = tribId;
	}
	
	public String getStringJson() {
		return super.toString();
	}

	
	public String toString() {
		// TODO Auto-generated method stub
		try {
			return this.getString("Name")+" : "+this.getString("Price");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
