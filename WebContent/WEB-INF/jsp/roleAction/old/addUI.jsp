<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<body>
	
		<s:form action="role_add">
			名称：<s:textfield name="name"></s:textfield><br/>
			说明：<s:textarea name="description"></s:textarea><br/>
			<s:submit value="提交"></s:submit>
		</s:form>
			
	</body>
</html>