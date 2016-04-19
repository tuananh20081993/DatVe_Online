package com.datve.data.parse.chontuyen;

import java.util.Iterator;
import java.util.List;

public class KhachHang {

	String sdt;
	String name;
	String email;
	String ngaysinh;
	String tinh;
	String quanhuyen;
	
	public KhachHang(String sdt, String name, String email, String ngaysinh, String tinh, String quanhuyen) {
		super();
		this.sdt = sdt;
		this.name = name;
		this.email = email;
		this.ngaysinh = ngaysinh;
		this.tinh = tinh;
		this.quanhuyen = quanhuyen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getTinh() {
		return tinh;
	}

	public void setTinh(String tinh) {
		this.tinh = tinh;
	}

	public String getQuanhuyen() {
		return quanhuyen;
	}

	public void setQuanhuyen(String quanhuyen) {
		this.quanhuyen = quanhuyen;
	}

	@Override
	public String toString() {
		return "KhachHang [sdt=" + sdt + ", name=" + name + ", email=" + email + ", ngaysinh=" + ngaysinh + ", tinh="
				+ tinh + ", quanhuyen=" + quanhuyen + "]";
	}
	
	
	
}
