package com.datve.sqlite;

public class Destination {
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public Destination(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
