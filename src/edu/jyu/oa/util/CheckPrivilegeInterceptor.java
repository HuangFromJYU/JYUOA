package edu.jyu.oa.util;

import edu.jyu.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("============> 拦截器（前）  <============");
		// String result = invocation.invoke(); // 放行
		// System.out.println("============> 拦截器（后）  <============");
		// return result;

		// 准备数据
		// a, 当前登录的用户
		User user = (User) ActionContext.getContext().getSession().get("user");

		// b, 当前访问的URL
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		if (null == namespace || "".equals(namespace)) {
			namespace = "/";
		}
		if (!namespace.endsWith("/")) {
			namespace += "/";
		}
		String url = namespace + actionName;

		// 一，如果用户未登录，就转到登录页面
		if (user == null) {
			if (url.startsWith("/loginout_login")) { // 包含 loginout_loginUI 和 loginout_login
				// a, 如果当前访问的是登录功能，就放行
				return invocation.invoke();
			} else {
				// b, 如果当前访问的不是登录功能，就转到登录页面
				return "loginUI";
			}
		}
		// 二，如果用户已经登录，就判断权限
		else {
			if (user.hasPrivilegeByUrl(url)) {
				// a, 如果有权限访问当前的URL，则放行
				return invocation.invoke();
			} else {
				// b, 如果没有权限访问当前的URL，则转到提示消息的页面
				return "noPrivilegeUI";
			}
		}
	}
}
