package com.sh.mybatis.emp.model.service;

import static com.sh.mybatis.common.SqlSessionTemplate.getSqlSession;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.emp.model.dao.EmpDao;

public class EmpServiceImpl implements EmpService {
	private EmpDao empDao;
	
	public EmpServiceImpl(EmpDao empDao) {
		this.empDao = empDao;
	}

	@Override
	public List<Map<String, Object>> selectEmpList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> empList = empDao.selectEmpList(session);
		session.close();
		return empList;
	}
	
	@Override
	public List<Map<String, Object>> search1(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> empList = empDao.search1(session, param);
		session.close();
		return empList;
	}

	@Override
	public List<Map<String, Object>> search2(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> empList = empDao.search2(session, param);
		session.close();
		return empList;
	}
}
