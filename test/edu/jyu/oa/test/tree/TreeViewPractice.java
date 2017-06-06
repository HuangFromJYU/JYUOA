package edu.jyu.oa.test.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.jyu.oa.domain.Department;

/**
 * 说明：不能使用多层循环的方式，因为需要能支持任意层。
 */
public class TreeViewPractice {

	/**
	 * 练习一：打印所有顶层部门及其子孙部门的信息（名称） 提示：假设有一个 打印部门树 的信息 的方法
	 * 
	 * 要求打印如下效果：
	 * 
	 * <pre>
	 * 市场部
	 * 宣传部
	 * 业务部
	 * 业务一部
	 * 业务二部
	 * 开发部
	 * 开发一部
	 * 开发二部
	 * </pre>
	 */
	@Test
	public void printAllDepts_1() {
		List<Department> topList = findTopLevelDepartmentList();

		// 方式一
		// for(Department d : topList){
		// showTreeInfo(d);
		// }

		// 方式二
		showTreeListInfo(topList);
	}

	// 方式一：
	// 显示一颗树的信息
	private void showTreeInfo(Department top) {
		// 顶点
		System.out.println(top.getName());
		// 子树
		for (Department child : top.getChildren()) {
			showTreeInfo(child);
		}
	}

	// 方式二：
	// 显示多颗树的信息
	private void showTreeListInfo(Collection<Department> topList) {
		for (Department top : topList) {
			// 顶点
			System.out.println(top.getName());
			// 子树
			showTreeListInfo(top.getChildren());
		}
	}

	/**
	 * 练习二：打印所有顶层部门及其子孙部门的信息（名称），用不同的缩进表示层次（使用全角空格）。<br>
	 * 子部门的名称前比上级部门多一个空格，最顶层部门的名字前没有空格。 提示：假设有一个打印部门集合中所有部门信息的方法
	 * 
	 * 要求打印如下效果：
	 * 
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 */
	@Test
	public void printAllDepts_2() {
		List<Department> topList = findTopLevelDepartmentList();
		showTreeList_2(topList, "");
	}

	private void showTreeList_2(Collection<Department> topList,  String prefix) {
		for (Department top : topList) {
			// 顶点
			System.out.println(prefix+ "┣" + top.getName());
			// 子树
			showTreeList_2(top.getChildren(), "    " + prefix);
		}
	}

	/**
	 * 结构如下：
	 * 
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 * 
	 * @return 所有最顶层的部门的列表
	 */
	public static List<Department> findTopLevelDepartmentList() {
		Department dept_1_1 = new Department();
		dept_1_1.setId(new Long(11));
		dept_1_1.setName("宣传部");

		Department dept_1_2 = new Department();
		dept_1_2.setId(new Long(12));
		dept_1_2.setName("业务部");

		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(new Long(121));
		dept_1_2_1.setName("业务一部");

		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(new Long(122));
		dept_1_2_2.setName("业务二部");

		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		Set<Department> children_0 = new LinkedHashSet<Department>();
		children_0.add(dept_1_2_1);
		children_0.add(dept_1_2_2);
		dept_1_2.setChildren(children_0);

		// ================================

		Department dept_1 = new Department();
		dept_1.setId(new Long(1));
		dept_1.setName("市场部");

		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> children_1 = new LinkedHashSet<Department>();
		children_1.add(dept_1_1);
		children_1.add(dept_1_2);
		dept_1.setChildren(children_1);

		// ---

		Department dept_2_1 = new Department();
		dept_2_1.setId(new Long(21));
		dept_2_1.setName("开发一部");

		Department dept_2_2 = new Department();
		dept_2_2.setId((new Long(22)));
		dept_2_2.setName("开发二部");

		Department dept_2 = new Department();
		dept_2.setId(new Long(2));
		dept_2.setName("开发部");

		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> children_2 = new LinkedHashSet<Department>();
		children_2.add(dept_2_1);
		children_2.add(dept_2_2);
		dept_2.setChildren(children_2);

		// ---

		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		return depts;
	}

}
