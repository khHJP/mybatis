package com.sh.mybatis.common;


import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

	/**
	 * 1. FactoryBuilder
	 * 2. Factory (설정파일)
	 * 3. SqlSession 
	 */
	public static SqlSession getSqlSession() { // static메소드로 생성
		SqlSession session = null;
		String resource = "/mybatis-config.xml"; // 읽어올 파일
		
		// 1. FactoryBuilder 불러오기
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactory factory = builder.build(is); // Factory 만들기 (인풋스트림)
			session = factory.openSession(false); // 공장에서 session 찍어내기 // auto-commit: false
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
	}
	
}
