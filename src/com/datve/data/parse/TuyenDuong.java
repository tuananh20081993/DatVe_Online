package com.datve.data.parse;

public class TuyenDuong {

	String diemdi;
	String diemden;
	String gia;
	int ghe;
	int sove;
	String ngaydi;
	String ngayden;
	String thoigiandi;
	String thoigianden;
	String noidon;
	public TuyenDuong(String diemdi, String diemden, String gia, int ghe, int sove, String ngaydi, String ngayden,
			String thoigiandi, String thoigianden, String noidon) {
		super();
		this.diemdi = diemdi;
		this.diemden = diemden;
		this.gia = gia;
		this.ghe = ghe;
		this.sove = sove;
		this.ngaydi = ngaydi;
		this.ngayden = ngayden;
		this.thoigiandi = thoigiandi;
		this.thoigianden = thoigianden;
		this.noidon = noidon;
	}
	public String getDiemdi() {
		return diemdi;
	}
	public void setDiemdi(String diemdi) {
		this.diemdi = diemdi;
	}
	public String getDiemden() {
		return diemden;
	}
	public void setDiemden(String diemden) {
		this.diemden = diemden;
	}
	public String getGia() {
		return gia;
	}
	public void setGia(String gia) {
		this.gia = gia;
	}
	public int getGhe() {
		return ghe;
	}
	public void setGhe(int ghe) {
		this.ghe = ghe;
	}
	public int getSove() {
		return sove;
	}
	public void setSove(int sove) {
		this.sove = sove;
	}
	public String getNgaydi() {
		return ngaydi;
	}
	public void setNgaydi(String ngaydi) {
		this.ngaydi = ngaydi;
	}
	public String getNgayden() {
		return ngayden;
	}
	public void setNgayden(String ngayden) {
		this.ngayden = ngayden;
	}
	public String getThoigiandi() {
		return thoigiandi;
	}
	public void setThoigiandi(String thoigiandi) {
		this.thoigiandi = thoigiandi;
	}
	public String getThoigianden() {
		return thoigianden;
	}
	public void setThoigianden(String thoigianden) {
		this.thoigianden = thoigianden;
	}
	public String getNoidon() {
		return noidon;
	}
	public void setNoidon(String noidon) {
		this.noidon = noidon;
	}
	@Override
	public String toString() {
		return "TuyenDuong [diemdi=" + diemdi + ", diemden=" + diemden + ", gia=" + gia + ", ghe=" + ghe + ", sove="
				+ sove + ", ngaydi=" + ngaydi + ", ngayden=" + ngayden + ", thoigiandi=" + thoigiandi + ", thoigianden="
				+ thoigianden + ", noidon=" + noidon + "]";
	}
	
	
}
