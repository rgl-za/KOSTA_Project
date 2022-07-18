package com.project.map;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Polygon {

	static ArrayList<Point> pointList;

	public static double ComputeAngle(double x, double y, double x2, double y2) {
		double dx = x2 - x;
		double dy = y2 - y;

		double angle;

		if ((dx >= 0) && (dy == 0))
			angle = 0;
		else {
			angle = Math.abs(dy) / (Math.abs(dx) + Math.abs(dy));
			if ((dx < 0) && (dy >= 0))
				angle = 2 - angle;
			else if ((dx <= 0) && (dy < 0))
				angle = 2 + angle;
			else if ((dx > 0) && (dy < 0))
				angle = 4 - angle;
		}
		return angle * 90;
	}

	// 다각형을 삼각형으로 분할
	public static List<Triangle> divideToTriangle(List<Point> pointList) {

		// pointList에서 index0에 위치하는 점이 기준점, index 1과 마지막 idex에 위치하는 점이 양쪽 인접점이다.
		List<Point> pointListCp = pointList;
		int cntTri = pointList.size() - 2;// 만들어질 삼각형의 갯수
		List<Triangle> triangleList = new ArrayList<>();// 삼각형 리스트

		// 만들어질 삼각형의 갯수가 1개 이상이라면
		if (cntTri > 0) {
			triangleList = new ArrayList<>(cntTri);// 삼각형의 갯수만큼 삼각형 리스트 생성

			for (int i = 0; i < cntTri; i++) {

				triangleList.add(new Triangle(pointListCp.get(0), pointListCp.get(1), pointListCp.get(2)));// 삼각형 생성

				pointListCp.remove(1);// 삼각형의 중간점은 지운다. 삼각형ABC라면, B점은 지운다.

				System.out.println("남은 pointList");
				// 출력
				for (int j = 0; j < pointListCp.size(); j++) {
					System.out.print(pointListCp.get(j).x + " ");// lng
					System.out.println(pointListCp.get(j).y + " ");// lat
				}
			}
		}
		System.out.println("만들어진 삼각형");
		// 출력
		for (int j = 0; j < triangleList.size(); j++) {
			System.out.println("삼각형" + j);
			System.out.print(triangleList.get(j).A.x + " ");// lng
			System.out.print(triangleList.get(j).A.y + " ");// lng
			System.out.print(triangleList.get(j).B.x + " ");// lng
			System.out.print(triangleList.get(j).B.y + " ");// lng
			System.out.print(triangleList.get(j).C.x + " ");// lng
			System.out.println(triangleList.get(j).C.y + " ");// lng
		}

		return triangleList;

	}// 다각형을 삼각형으로 분할 end

	public Point getPolygonMidPoint(List<Point> pointList) {
		
		List<Point> points = new ArrayList<>();
		Point resultPoint = new Point();
	
		int rsSize = 1000000;// 결과로 나온 point의 size initialize

		points.addAll(pointList);//deep copy 
		System.out.println(">>points>" + points);

		while (true) {
			
			rsSize = points.size();
			
			if (rsSize <= 2) {
				// 종료

				System.out.println(">>>>>rsSize>>>" + rsSize);

				// 점이 2개라면 거리계산 또 해야함.
				if (rsSize == 2) {

					// 점 2개 중간 지점
					double midx = points.get(0).x + (points.get(1).x - points.get(0).x) / 2;
					double midy = points.get(0).y + (points.get(1).y - points.get(0).y) / 2;
					// Point midBy2Points = new Point(midx,midy);
					// System.out.println(">>>>찐 최종>>"+midBy2Points.x+","+midBy2Points.y);

					System.out.println(">>>점이2개일때result>>>"+midx+","+midy);
					
					resultPoint.setX(midx);
					resultPoint.setY(midy);
					
					

				} else if (rsSize == 1) {
					System.out.println(">>>>점이1개일때result>>>" + points.get(0).x + "," + points.get(0).y);
					resultPoint.setX(points.get(0).x);
					resultPoint.setY(points.get(0).y);
				}

				break;
			} else {

				// 점이 3개 이상이라면

				points = pointToMidPoint(points);
			
				System.out.println("pointToMidPoint를 거친 points" + points.size());

				//이 부분 없어도 작동할까?
				rsSize = points.size();
				System.out.println("resultsize 남은 점의 갯수" + rsSize);

			}
		}
		System.out.println("오타수정결과" + resultPoint.x + "," + resultPoint.y);
		return resultPoint;
		// return new Point();
	}

	public static List<Point> pointToMidPoint(List<Point> inputPoints) {

		System.out.println("ponitToMidPoint에 들어옴" + inputPoints.size());// ok
		// 그냥 점들을 triangle리스트로 만드는 것까지
		List<Triangle> trianlgeList = init(inputPoints);
		List<Point> basketPoints = new ArrayList<>();
		List<Point> resultPoints = new ArrayList<>();

		// System.out.println(trianlgeList);//ok
		// trianlge 리스트에서 각각의 triangle에 getOnePoint()로 나온 점들로 또다른 리스트를 만든다.
		for (int i = 0; i < trianlgeList.size(); i++) {

			// p는 삼각형의 중간지점임.
			Point p = getMidPoint(trianlgeList.get(i));
			basketPoints.add(p);// basketPoints에 넣는다. 결과물

		}
		resultPoints = basketPoints;

		return resultPoints;
	}


	// 초기설정, 정렬되지 않은 point리스트를 받아서 다각형리스트로 변환하고 삼각형리스트로 반환.
	public static List<Triangle> init(List<Point> pointList) {
		// int number =8;//총 숫자의 갯수
		int number = pointList.size();// 총 숫자의 갯수
		System.out.println("number:" + number);

		double x, y;

		// 시작 index 찾기
		double minX = 999, minY = 999;
		int minIndex = 0;
		for (int i = 0; i < number; i++) {
			x = pointList.get(i).x;
			y = pointList.get(i).y;
			if ((minY > y) || (minY == y && minX > x)) {
				minIndex = i;
				minX = x;
				minY = y;
			}
		}

		for (int i = 0; i < number; i++) {
			pointList.get(i).tangent = ComputeAngle(pointList.get(minIndex).x, pointList.get(minIndex).y,
					pointList.get(i).x, pointList.get(i).y);
		}

		Collections.sort(pointList); // 다각형 정보 리스트

		// 출력 -> Point 객체로

		System.out.println(pointList);
		System.out.println("--------------");

		// 출력
		for (int i = 0; i < number; i++) {
			System.out.print(pointList.get(i).x + " ");// lng
			System.out.println(pointList.get(i).y + " ");// lat
		}
		// 마지막 꼭짓점
		System.out.print(pointList.get(0).x + " ");
		System.out.println(pointList.get(0).y + " ");

		System.out.println("--------------");

		// pointList(다각형)을 삼각형으로 분할
		return divideToTriangle(pointList);
	}

	// Triangle의 중간지점 반환
	public static Point getMidPoint(Triangle tri) {
		// Triangle tri = new Triangle( new Point(0.0, 0.0) ,new Point(9.0, 0.0), new
		// Point(4.0, 8.0) );

		while (true) {
			if (tri.getDisSml2P() < 0.0001) { // 거리가 0.0001보다 좁혀질 때까지 반복
				break;
			} else {

				System.out.println("getSmalA:" + tri.getSmalA().getX());
				System.out.println("getSmalB:" + tri.getSmalB().getX());

				System.out.println("작은 삼각형 두 점 사이의 거리:" + tri.getDisSml2P());
				tri = new Triangle(tri.getSmalA(), tri.getSmalB(), tri.getSmalC());

				System.out.println("------");
				continue;

			}

		} /// while
		System.out.println("마지막 최종 중간 점" + tri.getOnePoint());

		return tri.getOnePoint();

	}

	// 새로운 알고리즘
	public Point getPolyMid2(List<Point> pointList) {
		// int number =8;//총 숫자의 갯수
		int number = pointList.size();// 총 숫자의 갯수
		System.out.println("알고리즘 2 number:" + number);

		double x, y;

		// 시작 index 찾기
		double minX = 999, minY = 999;
		int minIndex = 0;
		for (int i = 0; i < number; i++) {
			x = pointList.get(i).x;
			y = pointList.get(i).y;
			if ((minY > y) || (minY == y && minX > x)) {
				minIndex = i;
				minX = x;
				minY = y;
			}
		}

		for (int i = 0; i < number; i++) {
			pointList.get(i).tangent = ComputeAngle(pointList.get(minIndex).x, pointList.get(minIndex).y,
					pointList.get(i).x, pointList.get(i).y);
		}

		Collections.sort(pointList); // 다각형 정보 리스트

		// List<Point> basketList = new ArrayList<>();
		List<Point> resultList = new ArrayList<>();
		resultList = pointList;// 초기화

		System.out.println("resultList.size()" + resultList.size());// 8

		Loop1: while (true) {
			
			if(resultList.size()==1) {
				break;
			}

			//System.out.println("중간지점 계산 반복");

			double dist = disPointToPoint(resultList.get(0), resultList.get(1));

			if (dist < 0.0001) {
				break Loop1;
			}

			List<Point> basketList = new ArrayList<>();

			Loop2: for (int i = 0; i < resultList.size(); i++) {

				if (i == resultList.size() - 1) {
					// 마지막 요소는 첫번째 요소와 계산
					double midx = (resultList.get(0).x + resultList.get(i).x) / 2;
					double midy = (resultList.get(0).y + resultList.get(i).y) / 2;
					basketList.add(new Point(midx, midy));
					
					//System.out.println("resultList" + resultList);
					
					//System.out.println("resultList" + resultList);
					//System.out.println("마지막 요소 계산" + i);

					break Loop2;// 마지막까지 계산 되었다면 반복문 탈출

				}
				double midx = (resultList.get(i + 1).x + resultList.get(i).x) / 2;
				double midy = (resultList.get(i + 1).y + resultList.get(i).y) / 2;
				basketList.add(new Point(midx, midy));

				//System.out.println("i번째 반복 중" + i);
			} // Loop2
			resultList = basketList;

			System.out.println("마지막resultList" + resultList);

		} // Loop1
		return new Point(resultList.get(0).x, resultList.get(0).y);

	}//

	public double disPointToPoint(Point a, Point b) {
		return Math.pow((b.x - a.x), 2) + Math.pow(b.y - a.y, 2);

	}

	public static void main(String[] args) {

		/*
		 * 사용자의 각 좌표(x,y)를 입력.
		 */
		pointList = new ArrayList<Point>();

		pointList.add(new Point(2, 8));// 1
		pointList.add(new Point(3, 5));// 2
		pointList.add(new Point(4, -1));// 3
		pointList.add(new Point(5, 10));// 4
		pointList.add(new Point(6, 7));// 5
		pointList.add(new Point(-1, 2));// 6
		pointList.add(new Point(7, 1));// 7
		pointList.add(new Point(10, 6));// 8

		System.out.println(pointList);// ok

		// List<Point> result = pointToMidPoint(pointList);
		List<Point> result = new ArrayList<>();
		List<Point> points = new ArrayList<>();
		// System.out.println(">>>>result>>>>"+result);//ok
		// System.out.println(">>>>result>>>>"+result.size());//ok

		int rsSize = 1000000;// 결과로 나온 point의 size

		points = pointList;
		System.out.println(">>points>" + points);

		while (true) {
			if (rsSize <= 2) {
				// 종료

				//System.out.println(">>>>>>>>>>rsSize>>>>>>>>>>>>>>>>>>>>>" + rsSize);// 2또는 1이 나오겠지?

				// 점이 2개라면 거리계산 또 해야함.
				if (rsSize == 2) {

					// test
					//System.out.println(">>>>찐 최종 이전 1벙 좌표>>" + points.get(0).x + "," + points.get(0).y);
					//System.out.println(">>>>찐 최종 이전 2벙 좌표>>" + points.get(1).x + "," + points.get(1).y);

					// 점 2개 중간 지점
					double midx = points.get(0).x + (points.get(1).x - points.get(0).x) / 2;
					double midy = points.get(0).x + (points.get(1).x - points.get(0).x) / 2;
					Point midBy2Points = new Point(midx, midy);
					System.out.println(">>>>찐 최종>>" + midBy2Points.x + "," + midBy2Points.y);

				} else if (rsSize == 1) {
					System.out.println(">>>>찐 최종>>" + points.get(0).x + "," + points.get(0).y);
				}

				break;
			} else {
				
				//System.out.println("반복문에 들어옴" + points.size());

				points = pointToMidPoint(points);

				//System.out.println("pointToMidPoint를 거친 points" + points.size());

				rsSize = points.size();
				//System.out.println("resultsize 남은 점의 갯수" + rsSize);

			}
		}

	}// main method

}