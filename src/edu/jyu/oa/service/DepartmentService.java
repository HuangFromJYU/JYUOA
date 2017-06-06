package edu.jyu.oa.service;

import java.util.List;

import edu.jyu.oa.base.DaoSupport;
import edu.jyu.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {
	//
	// List<Department> findAll();
	//
	// void delete(Long id);
	//
	// void save(Department department);
	//
	// Department getById(Long id);
	//
	// void update(Department department);

	/**
	 * 查询所有顶级部门的列表
	 * 
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查询所有子部门的列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
