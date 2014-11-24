package com.housee.app.model;


public class BlockAddress{
	private int rowNum;
	private int colNum;
	
	public BlockAddress(int rowNum,int colNum){
		this.rowNum = rowNum;
		this.colNum = colNum;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colNum;
		result = prime * result + rowNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockAddress other = (BlockAddress) obj;
		if (colNum != other.colNum)
			return false;
		if (rowNum != other.rowNum)
			return false;
		return true;
	}
	

	
}