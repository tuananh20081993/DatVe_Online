package com.datve_online.request;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class Request extends AsyncTask<String, Void, String>{
	private String host = "http://192.168.1.110:8081/RestfulDatVeWedService/rest/DatVeService/";
	private String url;
	private String method;
	private HttpURLConnection conn;
	public Request(String url,String method, String host){

		this.url = host+url;
		this.method = method;
		try {
			this.conn = (HttpURLConnection) new URL(this.url).openConnection();
			if(this.method.equalsIgnoreCase("get")) {
				this.conn.setDoOutput(false);
				this.conn.setRequestMethod(this.method.toUpperCase());
			} else {
				this.conn.setDoOutput(true);
				this.conn.setRequestMethod(this.method.toUpperCase());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Request(String url,String method) {
		// TODO Auto-generated constructor stub
		this.url = this.host+url;
		this.method = method;
		try {
			this.conn = (HttpURLConnection) new URL(this.url).openConnection();
			if(this.method.equalsIgnoreCase("get")) {
				this.conn.setDoOutput(false);
				this.conn.setRequestMethod(this.method.toUpperCase());
			} else {
				this.conn.setDoOutput(true);
				this.conn.setRequestMethod(this.method.toUpperCase());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String ret = "";
		try {
			if(this.conn.getDoOutput()){
				OutputStream out = this.conn.getOutputStream();
				out.write(params[0].getBytes());
				out.flush();
				out.close();
				
				
			}else{
				
			}
			this.conn.connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if(this.conn.getResponseCode() == 200){
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
				String read = "";
				while ((read = reader.readLine()) != null) {
					ret += read;
				}
				Log.d("JSON", "JSON: "+ret);
				reader.close();
			} else{
				Log.d("COnnet", "lose");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
}
