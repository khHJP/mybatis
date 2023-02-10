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

public class StudentDeleteController extends AbstractController {
	private StudentService studentService;

	public StudentDeleteController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. 사용자입력값
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		
		// 2. 업무로직 - db에서 update (deleted_at)
		int result = studentService.deleteStudent(no);
		System.out.println("result = " + result);
		
		
		// 3. 사용자피드백 전달
		Map<String, Object> map = new HashMap<>();
		if(result > 0) {
			map.put ("result","학생정보를 성공적으로 삭제했습니다.");
		} else {
			map.put ("result","학생정보 삭제에 실패했습니다.");
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
		
		return null; // 비동기
	}
}
