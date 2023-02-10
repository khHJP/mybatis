package com.sh.gson.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class GsonTestServlet
 */
@WebServlet("/gson/test")
public class GsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 응답데이터 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "안녕하세요");
		map.put("num", 1234567890);
		
		
		// 2. json으로 출력 -> Gson 필요
		// gson.jar파일을 직접 넣을 필요 없이 pom.xml에서 maven에게 요청
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
