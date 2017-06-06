package edu.jyu.oa.test.tree;

import java.util.HashSet;
import java.util.Set;

import edu.jyu.oa.test.tree.Department;
import edu.jyu.oa.domain.User;

/**
 * 部门
 * 
 * @author hjj
 * 
 */
public class Department {
	private Long id;
	private Set<User> users = new HashSet<User>();
	private Department parent;
	private Set<Department> children = new HashSet<Department>();

	private String name;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
