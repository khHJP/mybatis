package com.sh.mybatis.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int insertStudent(SqlSession session, Student student) {
		// session.insert(statement:String, parameter:Object)
		// statement - mapper파일의 namespace.id값 (쿼리태그의 id)
		// parameter - 0개 / 1개 전달가능. 두가지 값을 전달하려고 한다면 객체안에 담기 or Map으로 담아서 하나로 만들어야함.	
		return session.insert("student.insertStudent", student); // Sqlsession이 delete, insert, select 등.. 메소드를 제공함
	}

	@Override
	public int insertStudent(SqlSession session, Map<String, Object> studentMap) {
		return session.insert("student.insertStudent", studentMap); // student-mapper.xml에 쿼리 등록
	}

	@Override
	public int getTotalCount(SqlSession session) {
		return session.selectOne("student.getTotalCount");
	}

	@Override
	public Student selectOneStudent(int no, SqlSession session) {
		return session.selectOne("student.selectOneStudent", no);
	}

	@Override
	public int updateStudent(SqlSession session, Map<String, Object> studentUpdateMap) {
		return session.update("student.updateStudent", studentUpdateMap);
	}

	@Override
	public Map<String, Object> selectOneStudentMap(int no, SqlSession session) {
		return session.selectOne("student.selectOneStudentMap", no);
	}

	@Override
	public int deleteStudent(SqlSession session, int no) {
		return session.update("student.deleteStudent", no);
	}

	@Override
	public List<Student> selectStudentList(SqlSession session) {
		return session.selectList("student.selectStudentList");
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList(SqlSession session) {
		return session.selectList("student.selectStudentMapList");
	}
}
