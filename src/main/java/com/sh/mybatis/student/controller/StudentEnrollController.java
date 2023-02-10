package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentEnrollController extends AbstractController {
	
	private StudentService studentService;

	public StudentEnrollController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "student/studentEnroll"; // student/studentEnroll.jsp 생성
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 가져오기
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		// dto객체에 담기 (student)
		Student student = new Student();
		student.setName(name);
		student.setTel(tel);
		System.out.println(student);
				
		// 2. 업무로직 - db저장
		int result = studentService.insertStudent(student); 
		// createMethod하면 interface에 만들어짐. studentService가 인터페이스 타입이기 때문. 
		// 이후 구현클래스에서 실제로 메소드 override
		
		// 3. 사용자 피드백 전달
		HttpSession session = request.getSession();
		session.setAttribute("msg", "학생을 성공적으로 등록했습니다.");
		
		// 4. redirect
		return "redirect:/student/studentEnroll.do"; // 동기요청의 dml은 redirect
	}
}
