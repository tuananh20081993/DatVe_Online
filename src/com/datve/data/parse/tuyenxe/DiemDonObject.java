package com.datve.data.parse.tuyenxe;

import org.json.JSONException;
import org.json.JSONObject;

public class DiemDonObject extends JSONObject{
	private String  Type, Name ,Address,Phone,Latitude,Longitude,Distance,Duration,OfficeId;

	public DiemDonObject(String type, String name, String address, String phone, String latitude, String longitude,
			String distance, String duration, String officeId) throws JSONException {
		super("{\"Type\":\""+type+"\",\"Name\":\""+name+"\",\"Address\":\""+address+"\",\"Phone\":\""+phone+"\",\"Latitude\":\""+latitude+"\""
				+ ",\"Longtitude\":\""+longitude+"\",\"Distance\":\""+distance+"\",\"Duration\":\""+duration+"\",\"OfficeId\":\""+officeId+"\" }");
		
		this.Type = type;
		this.Name = name;
		this.Address = address;
		this.Phone = phone;
		this.Latitude = latitude;
		this.Longitude = longitude;
		this.Distance = distance;
		this.Duration = duration;
		this.OfficeId = officeId;
	}
	
	
	public DiemDonObject(JSONObject jsonObject) throws JSONException{
		// TODO Auto-generated constructor stub
		super(jsonObject.toString());
	}


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}


	public String getLatitude() {
		return Latitude;
	}


	public void setLatitude(String latitude) {
		Latitude = latitude;
	}


	public String getLongitude() {
		return Longitude;
	}


	public void setLongitude(String longitude) {
		Longitude = longitude;
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


	public String getOfficeId() {
		return OfficeId;
	}


	public void setOfficeId(String officeId) {
		OfficeId = officeId;
	}
	
	public String getStringJson() {
		return super.toString();
	}

	public String toString() {
		// TODO Auto-generated method stub
		try {
			return this.getString("Address");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
