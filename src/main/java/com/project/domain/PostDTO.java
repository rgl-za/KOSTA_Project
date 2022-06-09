package com.project.domain;


public class PostDTO {
	// 게시글 고유 번호
	private Long pnum;
	
	// 카테고리 고유 번호
	private Long catNum;
	
	// 방장 아이디
	private String leaderid;
	
	// 게시글 제목
	private String title;
	
	// 게시글 내용
	private String content;
	
	// 제품 사진
	private String photo;
	
	// 제품 링크
	private String link;
	
	// 제품 원가
	private int price;
	
	// 우편번호
	private int postnum;
	
	// 거래 주소
	private String dealaddress;
	
	// 최대 인원
	private int maxpeople;
	
	// 최소 인원
	private int minpeople;
	
	// 게시글 전용 계좌
	private String accountpost;
	
	// 게시글 업로드 날짜
	private String uploaddate;
	
	// 게시글 거래 마감 날짜
	private String enddate;
	
	// 삭제 키
	private String delete_yn;
	
	
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	public String getLeaderid() {
		return leaderid;
	}
	public void setLeaderid(String leaderid) {
		this.leaderid = leaderid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	public String getDealaddress() {
		return dealaddress;
	}
	public void setDealaddress(String dealaddress) {
		this.dealaddress = dealaddress;
	}
	public int getMaxpeople() {
		return maxpeople;
	}
	public void setMaxpeople(int maxpeople) {
		this.maxpeople = maxpeople;
	}
	public int getMinpeople() {
		return minpeople;
	}
	public void setMinpeople(int minpeople) {
		this.minpeople = minpeople;
	}
	public String getAccountpost() {
		return accountpost;
	}
	public void setAccountpost(String accountpost) {
		this.accountpost = accountpost;
	}
	public String getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Long getPnum() {
		return pnum;
	}
	public void setPnum(Long pnum) {
		this.pnum = pnum;
	}
	public Long getCatNum() {
		return catNum;
	}
	public void setCatNum(Long catNum) {
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
	@Override
	public String toString() {
		return "PostDTO [pnum=" + pnum + ", catNum=" + catNum + ", leaderid=" + leaderid + ", title=" + title
				+ ", content=" + content + ", photo=" + photo + ", link=" + link + ", price=" + price + ", postnum="
				+ postnum + ", dealaddress=" + dealaddress + ", maxpeople=" + maxpeople + ", minpeople=" + minpeople
				+ ", accountpost=" + accountpost + ", uploaddate=" + uploaddate + ", enddate=" + enddate
				+ ", delete_yn=" + delete_yn + "]";
	}

}