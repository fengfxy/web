package com.fxy.beans;

public class NewsQuery {
	String title;
	String stStart;
	String stEnd;
	int newsTypeId;

	
	
	public NewsQuery() {
	
		//初始化查询默认值
		this.title ="";
		this.stStart ="";
		this.stEnd ="";
		this.newsTypeId =-1;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getStStart() {
		return stStart;
	}



	public void setStStart(String stStart) {
		this.stStart = stStart;
	}



	public String getStEnd() {
		return stEnd;
	}



	public void setStEnd(String stEnd) {
		this.stEnd = stEnd;
	}



	public int getNewsTypeId() {
		return newsTypeId;
	}



	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	
	
}
