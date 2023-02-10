package com.sh.mybatis.student.model.service;

import static com.sh.mybatis.common.SqlSessionTemplate.getSqlSession; // Static으로 import

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.common.SqlSessionTemplate;
import com.sh.mybatis.student.model.dao.StudentDao;
import com.sh.mybatis.student.model.dto.Student;

public class StudentServiceImpl implements StudentService { // 인터페이스 구현 
	
	private StudentDao studentDao;

	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override 
	public int insertStudent(Student student) {
		SqlSession session = getSqlSession(); // SqlSession: connection보다 진화된형태. common하위에 Template 생성
		int result = 0;
		try {
			result = studentDao.insertStudent(session, student);
			session.commit(); // 성공시 커밋
		} catch (Exception e) {
			session.rollback(); // 실패시 롤백
			throw e; // 오류 던지기
		} finally {
			session.close(); // DBCP에 반환됨 (메모리해제 아님!)
		}
		
		return result;
	}

	@Override
	public int insertStudent(Map<String, Object> studentMap) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session, studentMap);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public int getTotalCount() {
		SqlSession session = getSqlSession();
		int totalCount = studentDao.getTotalCount(session);
		session.close();
		return totalCount;
	}

	@Override
	public Student selectOneStudent(int no) {
		SqlSession session = getSqlSession();
		Student student = studentDao.selectOneStudent(no, session);
		session.close();
		return student;
	}
	
	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		SqlSession session = getSqlSession();
		Map<String, Object> studentMap = studentDao.selectOneStudentMap(no, session);
		session.close();
		return studentMap;
	}
	


	@Override
	public int updateStudent(Map<String, Object> studentUpdateMap) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.updateStudent(session, studentUpdateMap);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int deleteStudent(int no) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.deleteStudent(session, no);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<Student> selectStudentList() {
		SqlSession session = getSqlSession();
		List<Student> studentList = studentDao.selectStudentList(session);
		session.close();
		return studentList;
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> studentMapList = studentDao.selectStudentMapList(session);
		session.close();
		return studentMapList;
	}



}
