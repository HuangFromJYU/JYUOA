package edu.jyu.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import edu.jyu.oa.base.DaoSupport;
import edu.jyu.oa.cfg.Configuration;
import edu.jyu.oa.domain.PageBean;
import edu.jyu.oa.util.QueryHelper;

// 这个@Transactional注解对子类中的方法也有效！
@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz = null; // 这是一个问题！
 
	// public BaseDaoImpl(Class<T> clazz) {
	// this.clazz = clazz;
	// }

	public DaoSupportImpl() {
		// 通过反射获取T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

		System.out.println("---> clazz = " + clazz);
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		if (id == null) {
			return;
		}

		Object entity = getById(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				// 注意空格！
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)// 注意一定要使用setParameterList()方法！
				.list();
	}

	public List<T> findAll() {
		// 注意空格！
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	// 公共的查询分页信息的方法
	public PageBean getPageBean(int pageNum, String hql, Object[] args) {
		System.out.println("------------> DaoSupportImpl.getPageBean()");

		// 获取pageSize信息
		int pageSize = Configuration.getPageSize();

		// 查询一页的数据列表
		Query query = getSession().createQuery(hql);
		if (args != null && args.length > 0) { // 设置参数
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list(); // 查询

		// 查询总记录数
		query = getSession().createQuery("SELECT COUNT(*) " + hql); // 注意空格！
		if (args != null && args.length > 0) { // 设置参数
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		Long count = (Long) query.uniqueResult(); // 查询

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param queryHelper
	 *            查询语句 + 参数列表
	 * @return
	 */
	public PageBean getPageBean(int pageNum, QueryHelper queryHelper) {
		System.out.println("------------> DaoSupportImpl.getPageBean( int pageNum, QueryHelper queryHelper )");

		// 获取pageSize等信息
		int pageSize = Configuration.getPageSize();
		List<Object> parameters = queryHelper.getParameters();

		// 查询一页的数据列表
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list(); // 查询

		// 查询总记录数
		query = getSession().createQuery(queryHelper.getQueryCountHql()); // 注意空格！
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) query.uniqueResult(); // 查询

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
