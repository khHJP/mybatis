package com.sh.mybatis.emp.model.service;

import java.util.List;
import java.util.Map;

public interface EmpService {

	List<Map<String, Object>> selectEmpList();

	List<Map<String, Object>> search1(Map<String, Object> param);

	List<Map<String, Object>> search2(Map<String, Object> param);

}
