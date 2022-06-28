//package com.project.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.project.map.Point;
//import com.project.map.Polygon;
//import com.project.service.CommentService;
//import com.project.service.PostService;
//
//@Controller
//public class MapController {
//
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private PostService postService;
//
//	@Autowired
//	private CommentService commentService;
//
//	
//	@RequestMapping(path = "/getMidPoint")
//	@ResponseBody
//	public Map<String, Object> insertMemberInfo(@RequestParam String data) throws ParseException, JSONException{
//		
//		Map<String, Object> returnToAjax = new HashMap<String, Object>();
//		
//		
//		List<Point> pointList= new ArrayList<>();
//		/**** JSONArray 받는 방법 ****/
//	    // request로 문자열형식의 데이터를 받고
//	    String jsonStr = data;
//	    System.out.println("string형태"+jsonStr);
//	   
//	    JSONArray jsonArr = new JSONArray();
//	    JSONParser parser = new JSONParser();
//	    
//	    // 문자열 형식의 데이터를 JSONArray로 가공
//	    jsonArr = (JSONArray)parser.parse(jsonStr);
//	    System.out.println("JSONArray형태"+jsonArr);
//	    
//	    for(int i=0; i<jsonArr.size(); i++) {
//	    	
//	    	//JSONArray->Map
//	    	Map<String, Object> getObj = (HashMap<String, Object>) jsonArr.get(i);
//	    	
//	    	//List<Point>로 변환
//	    	
//	    	//double idx = (long) getObj.get("idx");
//	    	//object -> double 한번에 변환 불가
//	    	String sx= (String) getObj.get("x");//object -> String
//	    	String sy= (String) getObj.get("y");//object -> String
//	    	
//	    	double x= Double.parseDouble(sx);//String -> double
//	    	double y= Double.parseDouble(sy);//String -> double
//	    	
//	    	Point userP = new Point(x, y);
//	    	
//	    	pointList.add(userP);
//	    	
//	    }
//	    System.out.println("pointList로 변환된 결과:" );
//	    for(int i=0; i<pointList.size(); i++) {
//	  
//	    	System.out.print(pointList.get(i).getX()+" / ");
//	    	System.out.println(pointList.get(i).getY());
//	    }
//	    
//	    Polygon poly = new Polygon();
//	    //System.out.println("test출력결과"+poly.getPolygonMidPoint(pointList));
//	    
//	    returnToAjax.put("res", poly.getPolygonMidPoint(pointList));//point객체임
//	    
//	    System.out.println("컨트롤러에서 ajax반환"+returnToAjax);
//
//		return returnToAjax;

//	  Map<String, Object> result = new HashMap<>();
//	  try {
//	    /*JSONArray jsonArray = JSONArray.fromObject(paramData);*/
//	    List<Map<String,Object>> info = new ArrayList<Map<String,Object>>();
//	    info = JSONArray.fromObject(paramData);
//
//	    for (Map<String, Object> memberInfo : info) {
//	        System.out.println(memberInfo.get("memberNo") + " : " + memberInfo.get("name"));
//	    }  
//	      result.put("result", true);
//	  } catch (Exception e) {
//	      result.put("result", false);
//	  }
//	  return result;
//
//	}
//	
//}
