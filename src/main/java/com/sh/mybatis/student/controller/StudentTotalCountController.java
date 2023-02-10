package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentTotalCountController extends AbstractController {
	
	private StudentService studentService;

	public StudentTotalCountController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업무로직 - 총 학생수 조회
		int totalCount = studentService.getTotalCount();
		System.out.println("totalCount = " + totalCount);
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		
		// 2. 응답에 json형식으로 출력
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	
		return null; // 비동기. 조회된 결과를 직접 뿌려줌
	}
}
