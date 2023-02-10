package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentRedirectController extends AbstractController {

	private StudentService studentService;
	
	public StudentRedirectController(StudentService studentService) {
		this.studentService = studentService; // 외부에서 받아서 필드에 세팅 -> 모든 controller가 하나의 service를 공유하도록 함
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "redirect:/";
	}
}
