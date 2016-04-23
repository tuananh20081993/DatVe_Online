package com.datve.fragment.chonghe;

import org.json.JSONException;
import org.json.JSONObject;

public class ChonGheObject extends JSONObject{
	private String floorno, id,chair,rowno,columnno,inselect,lockchair,bookstatus,discount;

	public ChonGheObject(String floorno, String id, String chair, String rowno, String columnno, String inselect,
			String lockchair, String booksatus, String discount) throws JSONException {
		super("{\"FloorNo\":\""+floorno+"\" ,\"Id\":\""+id+"\", \"Chair\":\""+chair+"\", \"RowNo\":\""+rowno+", \"ColunmNo\":\""+columnno+" "
				+ ",\"InSelect\":\""+inselect+"\",\"LockChair\":\""+lockchair+"\",\"BookStatus\":\""+booksatus+"\",\"Discount\":\""+discount+"\",}");
		this.floorno = floorno;
		this.id = id;
		this.chair = chair;
		this.rowno = rowno;
		this.columnno = columnno;
		this.inselect = inselect;
		this.lockchair = lockchair;
		this.bookstatus = booksatus;
		this.discount = discount;
	}
	
	public ChonGheObject(JSONObject jsonObject) throws JSONException{
		// TODO Auto-generated constructor stub
		super(jsonObject.toString());
		this.floorno = jsonObject.getString("FloorNo");
		this.id = jsonObject.getString("Id");
		this.chair = jsonObject.getString("Chair");
		this.rowno = jsonObject.getString("RowNo");
		this.columnno = jsonObject.getString("ColumnNo");
		this.inselect = jsonObject.getString("InSelect");
		this.lockchair = jsonObject.getString("LockChair");
		this.bookstatus = jsonObject.getString("BookStatus");
		this.discount = jsonObject.getString("Discount");
	}

	public String getFloorno() {
		return floorno;
	}

	public void setFloorno(String floorno) {
		this.floorno = floorno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChair() {
		return chair;
	}

	public void setChair(String chair) {
		this.chair = chair;
	}

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	public String getColumnno() {
		return columnno;
	}

	public void setColumnno(String columnno) {
		this.columnno = columnno;
	}

	public String getInselect() {
		return inselect;
	}

	public void setInselect(String inselect) {
		this.inselect = inselect;
	}

	public String getLockchair() {
		return lockchair;
	}

	public void setLockchair(String lockchair) {
		this.lockchair = lockchair;
	}

	public String getBookstatus() {
		return bookstatus;
	}

	public void setBookstatus(String booksatus) {
		this.bookstatus = booksatus;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public String []getColumns() {
		String rets[] = {"Id","FloorNo","Chair","RowNo", "ColumnNo","InSelect","LockChair","BookStatus","DisCount"};
		return rets;
	}
	public String [] getValues() throws JSONException {
		String rets[] = {super.getString("Id"),super.getString("FloorNo"),super.getString("Chair"),super.getString("RowNo"),super.getString("ColumnNo"),super.getString("InSelect"),super.getString("LockChair"),super.getString("BookStatus"),super.getString("DisCount")};
		return rets;
	}

}
