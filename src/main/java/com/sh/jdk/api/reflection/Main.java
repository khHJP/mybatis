package com.sh.jdk.api.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

	/**
	 * 	 * Reflection API 
	 * 	- jdk에서 제공하는 기본 API
	 *  - 클래스객체를 통해 클래스정보/객체정보 열람, 객체생성, 필드값주입, 메소드호출 등 제어하는 API 
	 */
	public static void main(String[] args) throws Exception { // 예외처리 넘겨버림
		Main main = new Main();
//		main.test1();
//		main.test2();
		main.test3();
		
	}

	/**
	 * 필드 제어
	 */
	private void test3() throws Exception {
		Class<?> clz = Class.forName("com.sh.jdk.api.reflection.Sample");
		Sample sample = (Sample) clz.getDeclaredConstructor(int.class, String.class).newInstance(123, "여보세요"); // int, String받는 생성자
		
		Field num = clz.getDeclaredField("num"); // 필드명 // java.lang.reflect하위로 import
		num.setAccessible(true); // 접근제한자가 private이어도 접근 가능하게 해줌 
		Object value = num.get(sample);

		System.out.println(value); 
		
		num.set(sample, 789);
		value = num.get(sample);
		
		System.out.println(value); 
		//  java.lang.IllegalAccessException: 
		// class com.sh.jdk.api.reflection.Main cannot access a member of class com.sh.jdk.api.reflection.Sample with modifiers "private"
	}


	/**
	 * 메소드 호출
	 */
	private void test2() throws Exception {
		Class<?> clz = Class.forName("com.sh.jdk.api.reflection.Sample");
		Sample sample = (Sample) clz.getDeclaredConstructor(null).newInstance(null); // 기본생성자
		
		Method setNum = clz.getDeclaredMethod("setNum", int.class); // 메소드명, 매개변수타입 
		Object returnValue = setNum.invoke(sample, 100);
		
		System.out.println(sample);
		System.out.println(returnValue); // null
		
		// getNum 호출
		Method getNum = clz.getDeclaredMethod("getNum"); // 가변인자라서 0개도 가능함!
		returnValue = getNum.invoke(sample);
		
		System.out.println(returnValue);
	}
	
	/**
	 *  객체 생성
	 */
	private void test1() throws Exception {
		// Sample 클래스객체 - 
		Class<?> clz = Class.forName("com.sh.jdk.api.reflection.Sample");
		Class<?> clz2 = Sample.class;
		Class<?> clz3 = new Sample().getClass();
		System.out.println(clz == clz2); // true 
		System.out.println(clz == clz3); // true 클래스객체는 하나만 만들어서 재사용
		
		// 기본생성자를 이용한 객체 생성
		Constructor<Sample> constructor = (Constructor<Sample>) clz.getDeclaredConstructor(null); // 기본생성자를 가져옴
		Sample sample = constructor.newInstance(null);
		System.out.println(sample);
		
		// 파라미터생성자를 이용한 객체 생성
		Constructor<Sample> constructor2 = (Constructor<Sample>) clz.getDeclaredConstructor(int.class, String.class);
		Sample sample2 = constructor2.newInstance(10, "홍길동");
		System.out.println(sample2);
	}
	
}
