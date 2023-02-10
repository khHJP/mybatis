package com.sh.mybatis.emp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.emp.model.service.EmpService;

public class EmpSearch2Controller extends AbstractController {

	private EmpService empService;
	
	public EmpSearch2Controller(EmpService empService) {
		this.empService = empService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		String gender = request.getParameter("gender");
		
		Integer salary = null;
		try {
			salary = Integer.parseInt(request.getParameter("salary"));
		} catch (NumberFormatException e) {} // 사용자가 급여는 작성하지 않은 경우 catch만 함
		String salaryCompare = request.getParameter("salaryCompare");

		// 입사일
		// 1. 쿼리에서 변환 - 이편이 더 간단하다 
//		String hireDate = request.getParameter("hire_date");
		String hiredateCompare = request.getParameter("hiredateCompare");
		
		// 2. 자바에서 변환
		System.out.println(request.getParameter("hire_date"));
		LocalDate hireDate = null;
		try {
			hireDate = LocalDate.parse(request.getParameter("hire_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} catch (Exception e) {}
		
		System.out.println("hireDate = " + hireDate);
		
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		param.put("gender", gender);
		param.put("salary", salary);
		param.put("salaryCompare", salaryCompare);
		param.put("hireDate", hireDate);
		param.put("hiredateCompare", hiredateCompare);
		
		// 2. 업무로직
		List<Map<String, Object>> empList = empService.search2(param);
		System.out.println("empList = " + empList);
	
		
		// 3. jsp데이터 전달
		request.setAttribute("empList", empList);
		
		return "emp/search2";
	}
}
