package com.housee.app.json;

public class UserMonthlyChart {

	private String month;
	private String year;
	private String value;
	private String value1;
	private String value2;
	
	//------------------changes-9/Dec----------------------
	private String value3;
	private String value4;
	private String value5;
	private String value6;
	//-----------------------------------------------------
	
	public UserMonthlyChart(Object year, Object value, Object value1, Object value2, Object value3, Object value4, Object value5, Object value6){
		this.year = String.valueOf(year);
		this.value = String.valueOf(value);
		this.value1 = String.valueOf(value1);
		this.value2 = String.valueOf(value2);
		this.value3 = String.valueOf(value3);
		this.value4 = String.valueOf(value4);
		this.value5 = String.valueOf(value5);
		this.value6 = String.valueOf(value6);
	}

	public UserMonthlyChart(Object month, Object year, Object value, Object value1, Object value2, Object value3, Object value4, Object value5, Object value6){
		this.month = String.valueOf(month);
		this.year = String.valueOf(year);
		this.value = String.valueOf(value);
		this.value1 = String.valueOf(value1);
		this.value2 = String.valueOf(value2);
		this.value3 = String.valueOf(value3);
		this.value4 = String.valueOf(value4);
		this.value5 = String.valueOf(value5);
		this.value6 = String.valueOf(value6);
	}
	
	public UserMonthlyChart(Object year, Object value){
		this.year = String.valueOf(year);
		this.value = String.valueOf(value);
	
	}

	public UserMonthlyChart(Object month, Object year, Object value){
		this.month = String.valueOf(month);
		this.year = String.valueOf(year);
		this.value = String.valueOf(value);	
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	//----changes---9/Dec.----------------
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getValue5() {
		return value5;
	}
	public void setValue5(String value5) {
		this.value5 = value5;
	}
	public String getValue6() {
		return value6;
	}
	public void setValue6(String value6) {
		this.value6 = value6;
	}
	
	
	@Override
	public String toString() {
		return "UserMonthlyChart [month=" + month + ", year=" + year
				+ ", value=" + value +", value1="+ value1 +", value2 =" + value2 +", value3 =" + value3
				+", value4 =" + value4 +", value5 =" + value5 +", value6 =" + value6 + "]";
	}
	
	//------------------------------------
	
}
