package com.sh.mybatis.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController { // 추상클래스
	
	/**
	 * 자식클래스에서 오버라이드하지 않고 호출시 예외 발생.
	 * - return절을 적지 않는다. 오류나서 return할게 없음. 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	// 사용자가 doGet으로 요청했을 시
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new RuntimeException("GET 요청은 허락되지 않습니다."); // 자식객체에서 오버라이드하지 않고 사용할때 오류를 발생시키려고 쓰는 코드 
		// 추상메소드는 컴파일 안됐을때의 오류. 
		// 오버라이드 안해도 컴파일오류는 안나지만 예외를 발생하게 함. 
	}

	// 사용자가 doPost로 요청했을 시
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new RuntimeException("POST 요청은 허락되지 않습니다.");
	}
}
