package com.datve.data.parse.chontuyen;

import org.json.JSONException;
import org.json.JSONObject;

public class Point extends JSONObject{
	public Point(JSONObject obj) throws JSONException {
		// TODO Auto-generated constructor stub
		super(obj.toString());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		try {
			return this.getString("DestName");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.toString();
	}
}
