package edu.jyu.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.jyu.oa.domain.Department;

public class DepartmentUtils {

	/**
	 * 遍历部门树，把所有的部门都改掉名称后放到同一个List中返回。通过名称中的空格表示层次
	 * 
	 * @param topList
	 * @param removedDepartment
	 *            这个部门和这个部门的子孙部门都不要，如果为null，表示没有要移除的部门分支
	 * @return
	 */
	public static List<Department> getAllDepartmentList(List<Department> topList, Department removedDepartment) {
		List<Department> list = new ArrayList<Department>();
		walkTree(topList, "┣", list, removedDepartment);
		return list;
	}

	// 递归遍历
	private static void walkTree(Collection<Department> topList, String prefix, List<Department> list, Department removedDepartment) {
		for (Department top : topList) {
			// 去掉指定的部门分支
			if (removedDepartment != null && top.getId().equals(removedDepartment.getId())) {
				continue;
			}

			// 顶点
			Department copy = new Department(); // 不要修改Session缓存中的对象，最好使用副本
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy); // 注意，添加的是copy的对象
			// 子树
			walkTree(top.getChildren(), "　" + prefix, list, removedDepartment); // 要使用全角的空格，要不然在html中只显示一个空格
		}
	}

}
