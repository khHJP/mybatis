package com.sh.mybatis.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.student.model.dto.Student;

public interface StudentDao {

	int insertStudent(SqlSession session, Student student);

	int insertStudent(SqlSession session, Map<String, Object> studentMap);

	int getTotalCount(SqlSession session);

	Student selectOneStudent(int no, SqlSession session);

	int updateStudent(SqlSession session, Map<String, Object> studentUpdateMap);

	Map<String, Object> selectOneStudentMap(int no, SqlSession session);

	int deleteStudent(SqlSession session, int no);

	List<Student> selectStudentList(SqlSession session);

	List<Map<String, Object>> selectStudentMapList(SqlSession session);

}
