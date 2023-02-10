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

public class StudentUpdateController extends AbstractController {
	private StudentService studentService;

	public StudentUpdateController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		System.out.println("no = " + no + " name = " + name + " tel = " + tel);
		
		// Map에 담기
		Map<String, Object> studentUpdateMap = new HashMap<>();
		studentUpdateMap.put("no", no);
		studentUpdateMap.put("name", name);
		studentUpdateMap.put("tel", tel);
		System.out.println(studentUpdateMap);
		
		
		// 2. 업무로직 - db에서 update
		int result = studentService.updateStudent(studentUpdateMap);
		System.out.println("result = " + result);
		
		// 3. 사용자피드백 전달
		Map<String, Object> map = new HashMap<>();
		if(result > 0) {
			map.put("result", "학생정보를 성공적으로 수정했습니다.");
		} else {
			map.put("result", "학생정보 수정에 실패했습니다.");
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
		
		// 4.비동기
		return null;
	}
}
