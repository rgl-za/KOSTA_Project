package com.project.map;

import lombok.Data;

@Data
public class Dist2Point {
	public Point stand;
	public Point compare;
	
	public Dist2Point(Point stand, Point compare) {
		super();
		this.stand = stand;
		this.compare = compare;
	}
	
	public double getDist() {
		
		double front = stand.x-compare.x;// x2 - x1
		double back =  stand.y-compare.y;;// y2 - y1
		return Math.pow(front, 2) + Math.pow(back, 2);

	}
	

}
