package com.project.map;

import java.awt.Polygon;
import java.lang.reflect.Array;

import org.springframework.boot.SpringApplication;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Triangle {

	// 세 점으로 이루어진 삼각형의 각 선분을 이등분한 점들로 다시 만든 작은 삼각형의 길이와 넓이

	public Point A;// = new LatLng(0.0, 0.0);
	public Point C;// =new LatLng(9.0, 0.0);
	public Point B;// = new LatLng(4.0, 8.0);
	
	public Triangle() {
		super();
	}

	public Triangle(Point a, Point c, Point b) {
		super();
		A = a;
		C = c;
		B = b;
	}

	// 높이 = y = 위도 = lat
	public double getHigh() {
		return A.y + (B.y - A.y) / 2;

	}

	// 밑변 = x = 경도 = long
	public double getBase() {
		return A.x + (C.x - A.x) / 2;
	}

	public double getArea() {
		return this.getBase() * this.getHigh() / 2;
	}

	public Point getOriA() {
		return new Point(A.x + (B.x - A.x) / 2, A.y + (B.y - A.y) / 2);
	}

	public Point getOriB() {
		return new Point(B.x + (C.x - B.x) / 2, B.y + (C.y - B.y) / 2);
	}

	public Point getOriC() {
		return new Point(A.x + (C.x - A.x) / 2, A.y + (C.y - A.y) / 2);
	}

	public Point getSmalA() {
		return new Point((A.x + B.x) / 2, (A.y + B.y) / 2);
	}

	public Point getSmalB() {
		return new Point((B.x + C.x) / 2, (B.y + C.y) / 2);
	}

	public Point getSmalC() {
		return new Point((A.x + C.x) / 2, (A.y + C.y) / 2);
	}

	// 삼각형의 3점 중 한 점의 x,y 반환
	public Point getOnePoint() {
		return this.A;
	}

	// 각 빗변의 중점으로 만들어진 작은 삼각형의 두 점의 거리
	// 루트( (x2-x1)^2 + (y2-y1)^2 )<0.000001
	// ( (x2-x1)^2 + (y2-y1)^2 )<0.001
	public double getDisSml2P() {
		double front = (B.x + C.x) / 2 - (A.x + B.x) / 2;// x2 - x1
		double back = (B.y + C.y) / 2 - (A.y + B.y) / 2;// y2 - y1
		return Math.pow(front, 2) + Math.pow(back, 2);
	}

}
