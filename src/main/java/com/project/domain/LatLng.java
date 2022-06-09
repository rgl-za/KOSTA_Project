package com.project.domain;

public class LatLng {
	private double lat;
	private double lng;
	
	
	public LatLng() {
		super();
	}
	public LatLng(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	

}
