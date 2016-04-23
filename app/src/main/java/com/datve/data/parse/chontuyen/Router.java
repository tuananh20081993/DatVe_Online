package com.datve.data.parse.chontuyen;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Router {
	private JSONArray json;
	private String origin;
	private ArrayList<Point>dest;
	public String getOrigin() {
		return origin;
	}
	public Router(String origin)
	{
		new Router(origin, false);
	}
	public Router(String origin,boolean isAll) {
		this.origin = origin;
		try {
			this.json = new JSONArray(origin);
			this.dest = new ArrayList<Point>();
			String exist = "";
			for (int i = 0; i < this.json.length(); i++) {
				Point dest = new Point(this.json.getJSONObject(i));
				if(!exist.contains(dest.getString("DestCode")) || isAll){
					this.dest.add(dest);
					exist+=dest.getString("DestCode")+",";
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("json", "sss");
			e.printStackTrace();
		}
	}
	public ArrayList<Point> getDest() {
		return dest;
	}
	public void setDest(ArrayList<Point> dest) {
		this.dest = dest;
	}
	
	
	
}
