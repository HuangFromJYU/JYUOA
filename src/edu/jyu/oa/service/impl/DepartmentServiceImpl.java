package edu.jyu.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jyu.oa.base.DaoSupportImpl;
import edu.jyu.oa.domain.Department;
import edu.jyu.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {

	// @Resource
	// private DepartmentDao departmentDao;
	//
	// public void delete(Long id) {
	// departmentDao.delete(id);
	// }
	//
	// public List<Department> findAll() {
	// return departmentDao.findAll();
	// }
	//
	// public Department getById(Long id) {
	// return departmentDao.getById(id);
	// }
	//
	// public void save(Department department) {
	// departmentDao.save(department);
	// }
	//
	// public void update(Department department) {
	// departmentDao.update(department);
	// }

	public List<Department> findChildren(Long parentId) {
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}

	public List<Department> findTopList() {
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

}
