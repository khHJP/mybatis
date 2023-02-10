package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentSelectListController extends AbstractController {
	
	private StudentService studentService; // 인터페이스로 제어
	
	public StudentSelectListController(StudentService studentService) {
		this.studentService = studentService; // 외부에서 받아서 필드에 세팅 -> 모든 controller가 하나의 service를 공유하도록 함
	}
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업무로직 
		// dto로 조회
		List<Student> studentList = studentService.selectStudentList();
		System.out.println("studentList = " + studentList);
		
		// map으로 조회
		List<Map<String, Object>> studentMapList = studentService.selectStudentMapList();
		System.out.println("studentMapList = " + studentMapList);
		
		// 2. jsp 데이터 전달
		request.setAttribute("studentList", studentList);
		request.setAttribute("studentMapList", studentMapList);
		
		return "student/selectList";
	}
}
