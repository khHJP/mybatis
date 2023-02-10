package com.sh.mybatis.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.emp.model.service.EmpService;

public class EmpSearch1Controller extends AbstractController {

	private EmpService empService;
	
	public EmpSearch1Controller(EmpService empService) {
		this.empService = empService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		// 2. 업무로직
		List<Map<String, Object>> empList = null;
		
		// 검색요청인 경우
		if(searchType != null && searchKeyword != null) {
			empList = empService.search1(param);
		}
		// 일반요청인 경우
		else {
			empList = empService.selectEmpList();			
		}
		System.out.println("empList = " + empList);
		
		// 3. jsp데이터 설정 
		request.setAttribute("empList", empList);
		
		return "emp/search1";
	}
}
