package edu.jyu.oa.service;

import java.util.List;

import edu.jyu.oa.base.DaoSupport;
import edu.jyu.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * 查询所有顶级的权限列表
	 * 
	 * @return
	 */
	List<Privilege> findTopList();

	/**
	 * 查询所有权限的URL的集合（不包含null值和重复的值）
	 * 
	 * @return
	 */
	List<String> getAllPrivilegeUrls();

}
