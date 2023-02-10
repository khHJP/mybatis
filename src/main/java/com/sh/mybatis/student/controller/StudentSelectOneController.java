package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;
import com.sh.mybatis.student.model.service.StudentServiceImpl;

public class StudentSelectOneController extends AbstractController {

	private StudentService studentService;
	
	/**
	 * 의존객체 주입 Dependency Injection
	 * - Controller가 제 역할을 하려면 Service객체가 반드시 필요함 -> 의존
	 * @param studentService
	 */
	public StudentSelectOneController(StudentService studentService) {
		this.studentService = studentService; // 외부에서 받아서 필드에 세팅 -> 모든 controller가 하나의 service를 공유하도록 함
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "student/selectOne";
	}
	

}
