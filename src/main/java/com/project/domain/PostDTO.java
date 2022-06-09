package com.project.domain;

import java.time.LocalDateTime;



public class PostDTO {
	private int pnum;
	private int catNum;
	private String title;
	private String content;
	private String link;
	private int price;

	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getCatNum() {
		return catNum;
	}
	public void setCatNum(int catNum) {
		this.catNum = catNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
	
	

	
}