package edu.jyu.oa.util;

import java.util.ArrayList;
import java.util.List;

import edu.jyu.oa.base.DaoSupport;
import edu.jyu.oa.domain.PageBean;

import com.opensymphony.xwork2.ActionContext;

import edu.jyu.oa.util.QueryHelper;

/**
 * 辅助拼接HQL语句的工具类
 * 
 * @author hjj
 * 
 */
public class QueryHelper {

	private String fromClause; // From子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表

	/**
	 * 生成From子句
	 * 
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接Where子句
	 * 
	 * @param condition
	 * @param args
	 */
	public QueryHelper addWhereCondition(String condition, Object... args) {
		// 拼接
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}
		// 处理参数
		if (args != null && args.length > 0) {
			for (Object arg : args) {
				parameters.add(arg);
			}
		}
		return this;
	}

	/**
	 * 如果第一个参数的值为true，就拼接Where子句
	 * 
	 * @param append
	 * @param condition
	 * @param args
	 */
	public QueryHelper addWhereCondition(boolean append, String condition, Object... args) {
		if (append) {
			addWhereCondition(condition, args);
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public QueryHelper addOrderByProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第一个参数的值为true，就拼接OrderBy子句
	 * 
	 * @param append
	 * @param propertyName
	 * @param asc
	 */
	public QueryHelper addOrderByProperty(boolean append, String propertyName, boolean asc) {
		if (append) {
			addOrderByProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * 获取查询数据列表的HQL语句
	 * 
	 * @return
	 */
	public String getQueryListHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取查询总记录数的HQL语句（没有OrderBy子句）
	 * 
	 * @return
	 */
	public String getQueryCountHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取参数列表
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * 准备PageBean对象到Struts2的栈顶
	 * @param service
	 * @param pageNum
	 */
	public void preparePageBean(DaoSupport<?> service, int pageNum){
		PageBean pageBean = service.getPageBean(pageNum, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
}
