package com.sh.mybatis.common;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.emp.model.dao.EmpDaoImpl;
import com.sh.mybatis.emp.model.service.EmpService;
import com.sh.mybatis.emp.model.service.EmpServiceImpl;
import com.sh.mybatis.student.model.dao.StudentDaoImpl;
import com.sh.mybatis.student.model.service.StudentService;
import com.sh.mybatis.student.model.service.StudentServiceImpl;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 요청 url과 이를 처리할 controller객체를 매핑(연결)
	 *     	// 컨트롤러 객체를 추상화한 필드 추가
	 *   - /student/selectList.do -> StudentSelectListController 연결
	 *   - /student/selectOne.do -> StudentSelectOneController 연결
	 *    		=> command-map.properties에 정보 저장 
	 * 모든 컨트롤러는 AbstractController의 자식 클래스! 
	 * 	- AbstractController타입으로 제어될 것 (다형성)
	 */
	private Map<String, AbstractController> commandMap = new HashMap<>();
	// ctrl + c <= command패턴 => copy
	// ctrl + x <= command패턴 => cut
	// ctrl + v <= command패턴 => paste
	// 특정 단축키와 기능을 연결. command패턴 정보가 담긴것이 commandMap 
	// String url이 오면, 해당 Controller로 연결 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
    	// 1. command-map.properties 내용을 prop 객체로 옮겨오기
    	Properties prop = new Properties();
    	String filepath = DispatcherServlet.class.getResource("/command-map.properties").getPath();
    	try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	// 2. prop -> commandMap으로 옮기기 (reflection api)
    	// prop은 key, value 모두 문자열 (key = value형태)
    	// commandMap<String, controller객체>
    	// 문자열 com.sh.mybatis.student.controller.StudentSelectListController 를 가지고 와서 객체로 만들기 -> reflection api
    	StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    	EmpService empService = new EmpServiceImpl(new EmpDaoImpl());
    	
    	Set<String> propNames = prop.stringPropertyNames(); // key값만 set에 넣기 (/student/selectList.do)
    	
    	try {
    		for(String url : propNames) {
    			String className = prop.getProperty(url);
    			Class<?> clz = Class.forName(className); // class객체를 만들어 반환하는 메소드
//    			Constructor<?> constructor = clz.getDeclaredConstructor(null); // 생성자 객체로 기본생성자 가져옴
//    			AbstractController controller = (AbstractController) constructor.newInstance(null); // new XXXController()
    			
    			// service 통일
    			AbstractController controller = null;
    			if(url.startsWith("/student")) {
        			Constructor<?> constructor = clz.getDeclaredConstructor(StudentService.class); 
        			controller = (AbstractController) constructor.newInstance(studentService);
    			}
    			else if (url.startsWith("/emp")) {
    				Constructor<?> constructor = clz.getDeclaredConstructor(EmpService.class); 
    				controller = (AbstractController) constructor.newInstance(empService);				
    			}
    			
//    			AbstractController controller = new StudentSelectListController(); - 다형성 
    			commandMap.put(url, controller);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("commandMap : " + commandMap);
  
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청주소 가져오기
		String url = request.getRequestURI(); // /mybatis/student/selectList.do
		// mybatis(ContextPath) 제거 작업
		url = url.replace(request.getContextPath(), ""); // /student/selectList.do
		
		// 2. commandMap에서 요청주소에 해당하는 controller 가져오기
		AbstractController controller = commandMap.get(url); // key값으로 가져오기
		if(controller == null) { // 주소가 잘못되었을때
			// 404 notFound 에러처리
			response.sendError(HttpServletResponse.SC_NOT_FOUND, url); // 404 상수처리, 에러메시지전달 (url)
			return; // 하위코드실행중단
		}
		
		// 3. 전송방식에 따라 doGet 또는 doPost 오버라이드(AbstractController)
		String method = request.getMethod();
		String viewName = null;
		switch(method) {
		case "GET" : viewName = controller.doGet(request, response); // AbstractController의 doGet 메소드 String에 넣어서 반환될 값
						break;
		case "POST" : viewName = controller.doPost(request, response); break;
		default : throw new RuntimeException("허용되지 않은 전송방식 - " + method); // get, post외의 방식 요청시
		}
		
		// 4. 응답처리 forwarding | redirect | bypass(controller에서 응답직접 출력) 
		if(viewName != null) {
			// redirect시 주소: redirect:/
			if(viewName.startsWith("redirect:")) {
				response.sendRedirect(request.getContextPath() + viewName.replace("redirect:", "")); // redirect를 제거 /mybatis/ 
			}
			// forward시 주소(jsp경로): /student/selectList => /WEB-INF/views/student/selectList.jsp  
			else {
				String prefix = "/WEB-INF/views/"; // 접두사
				String suffix = ".jsp"; // 접미사
				viewName = prefix + viewName + suffix;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // doGet으로 가도록 코드 남겨두기
	}

}
