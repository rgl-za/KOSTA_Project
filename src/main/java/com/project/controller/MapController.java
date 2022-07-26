package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.domain.PostDTO;
import com.project.map.Point;
import com.project.map.Polygon;
import com.project.service.CommentService;
import com.project.service.PostService;

@Controller
public class MapController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(path = "/getMidPoint")
	@ResponseBody
	public Map<String, Object> insertMemberInfo(@RequestParam String data) throws ParseException, JSONException {

		Map<String, Object> returnToAjax = new HashMap<String, Object>();

		List<Point> pointList = new ArrayList<>();
		/**** JSONArray 받는 방법 ****/
		// request로 문자열형식의 데이터를 받고
		String jsonStr = data;
		System.out.println("string형태" + jsonStr);

		JSONArray jsonArr = new JSONArray();
		JSONParser parser = new JSONParser();

		// 문자열 형식의 데이터를 JSONArray로 가공
		jsonArr = (JSONArray) parser.parse(jsonStr);
		System.out.println("JSONArray형태" + jsonArr);

		for (int i = 0; i < jsonArr.size(); i++) {

			// JSONArray->Map
			Map<String, Object> getObj = (HashMap<String, Object>) jsonArr.get(i);

			// List<Point>로 변환

			// double idx = (long) getObj.get("idx");
			// object -> double 한번에 변환 불가
			String sx = (String) getObj.get("x");// object -> String
			String sy = (String) getObj.get("y");// object -> String

			double x = Double.parseDouble(sx);// String -> double
			double y = Double.parseDouble(sy);// String -> double

			Point userP = new Point(x, y);

			pointList.add(userP);

		}

		Polygon poly = new Polygon();
		// System.out.println("test출력결과"+poly.getPolygonMidPoint(pointList));

		long beforeTime1 = System.currentTimeMillis();
		System.out.println("3번 알고리즘 전 pointList:  " + pointList.size());
		
		returnToAjax.put("res", poly.getPolygonMidPoint(pointList));// point객체임
		System.out.println("3번 알고리즘 후 pointList:  " + pointList.size());

		long afterTime1 = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime1 = (afterTime1 - beforeTime1); // 두 시간에 차 계산
		System.out.println("3번 알고리즘 소요시간(m) : " + secDiffTime1);

		// 알고리즘2 적용
		long beforeTime2 = System.currentTimeMillis();
		System.out.println("2번 알고리즘 전 pointList:  " + pointList.size());
		returnToAjax.put("two", poly.getPolyMid2(pointList));

		long afterTime2 = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime2 = (afterTime2 - beforeTime2); // 두 시간에 차 계산
		System.out.println("2번 알고리즘 소요시간(m) : " + secDiffTime2);

		System.out.println("컨트롤러에서 ajax반환" + returnToAjax);

		return returnToAjax;
	}

	@RequestMapping(path = "/getNearCS2")
	@ResponseBody
	public Map<String, Object> getNearCS2(@RequestParam String data) throws ParseException, JSONException {

		Map<String, Object> returnToAjax = new HashMap<String, Object>();

		List<Point> pointList = new ArrayList<>();
		/**** JSONArray 받는 방법 ****/
		// request로 문자열형식의 데이터를 받고
		String jsonStr = data;
		System.out.println("string형태" + jsonStr);

		JSONArray jsonArr = new JSONArray();
		JSONParser parser = new JSONParser();

		// 문자열 형식의 데이터를 JSONArray로 가공
		jsonArr = (JSONArray) parser.parse(jsonStr);
		System.out.println("JSONArray형태" + jsonArr);

		for (int i = 0; i < jsonArr.size(); i++) {

			// JSONArray->Map
			Map<String, Object> getObj = (HashMap<String, Object>) jsonArr.get(i);

			// List<Point>로 변환

			// double idx = (long) getObj.get("idx");
			// object -> double 한번에 변환 불가
			String sx = (String) getObj.get("x");// object -> String
			String sy = (String) getObj.get("y");// object -> String

			double x = Double.parseDouble(sx);// String -> double
			double y = Double.parseDouble(sy);// String -> double

			Point userP = new Point(x, y);

			pointList.add(userP);

		}

		Polygon poly = new Polygon();
		// System.out.println("test출력결과"+poly.getPolygonMidPoint(pointList));

		long beforeTime1 = System.currentTimeMillis();

		returnToAjax.put("res", poly.getPolygonMidPoint(pointList));// point객체임

		long afterTime1 = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime1 = (afterTime1 - beforeTime1); // 두 시간에 차 계산
		System.out.println("3번 알고리즘 소요시간(m) : " + secDiffTime1);

		// 알고리즘2 적용
		long beforeTime2 = System.currentTimeMillis();
		returnToAjax.put("two", poly.getPolyMid2(pointList));

		long afterTime2 = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime2 = (afterTime2 - beforeTime2); // 두 시간에 차 계산
		System.out.println("2번 알고리즘 소요시간(m) : " + secDiffTime2);

		System.out.println("컨트롤러에서 ajax반환" + returnToAjax);

		return returnToAjax;
	}

	// post의 dealaddress변경
	@RequestMapping(value = "/alterDealAdd.do")
	@ResponseBody
	public String alterDealAdd(@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "pnum", required = false) long pnum) {
		String add = address;
		System.out.println(">>>>PostController>>alterDealAdd 로 들어옴" + add + "pnum>" + pnum + ">>");
		PostDTO params = new PostDTO();
		params.setDealaddress(address);
		params.setPnum(pnum);
		boolean isAlterDealAdd = postService.alterDealAdd(params);
		return "success";
	}

}
