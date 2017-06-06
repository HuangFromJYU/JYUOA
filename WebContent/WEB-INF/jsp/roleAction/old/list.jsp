<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- 显示数据 --%>
<html>
	<body>
		
		<s:iterator value="roleList">
			<s:property value="id"/>,
			<s:property value="name"/>,
			<s:property value="description"/>
			
			<%-- 使用s:a标签，可以帮我们自动的加上前面的工程名称和后面的扩展名 --%>
			<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
			<s:a action="role_editUI?id=%{id}">修改</s:a>
			<br/>
		</s:iterator>
		
		
		<s:a action="role_addUI">添加</s:a>
		
	</body>
</html>