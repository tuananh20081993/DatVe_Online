package com.datve.data.parse;

import org.json.JSONException;
import org.json.JSONObject;

public class RouteStop extends JSONObject{
	
	private String routeId,type,name,address,latitude,longtitude,odernumber,distance, duration;
	
	
	
	public RouteStop(String type, String name, String address, String latitude, String longtitude, String odernumber,
			String distance, String duration) throws JSONException {
		super("{\"Type\":\""+type+"\",\"Name\":\""+name+"\",\"Address\":\""+address+"\",\"Latitude\":\""+latitude+"\""
				+ ",\"Longtitude\":\""+longtitude+"\",\"OrderNumber\":\""+odernumber+"\",\"Distance\":\""+distance+"\",\"Duration\":\""+duration+"\" }");
		this.type = type;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.odernumber = odernumber;
		this.distance = distance;
		this.duration = duration;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getOdernumber() {
		return odernumber;
	}
	public void setOdernumber(String odernumber) {
		this.odernumber = odernumber;
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
	public RouteStop(JSONObject obj) throws JSONException{
		super(obj.toString());
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	
	public String []getColumns() {
		String rets[] = {"Address","Types","name","latitude", "longtitude","odernumber","distance","duration","routeId"};
		return rets;
	}
	public String [] getValues() throws JSONException {
		String rets[] = {super.getString("Address"),super.getString("Type"),super.getString("Name"),super.getString("Latitude"),super.getString("Longitude"),super.getString("OrderNumber"),super.getString("Distance"),super.getString("Duration"),this.routeId};
		return rets;
	}
}
