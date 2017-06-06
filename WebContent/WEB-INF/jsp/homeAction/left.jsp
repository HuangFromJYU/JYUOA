<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>导航菜单</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
</head>
<body style="margin: 0">
<div id="Menu"> 

    <ul id="MenuUl">
		<%-- 一级菜单 --%>
		<s:iterator value="#application.topPrivilegeList">
		<s:if test=" #session.user.hasPrivilegeByName(name) ">
	        <li class="level1">
	            <div onClick=" $(this).next().toggle() " class="level1Style">
	            	<img src="style/images/MenuIcon/${id}.gif" class="Icon" /> 
	            	${name}
	            </div>
	            <ul class="MenuLevel2">
	            	<%-- 二级菜单 --%>
	            	<s:iterator value="children">
	      		 	<s:if test=" #session.user.hasPrivilegeByName(name) ">
		                <li class="level2">
		                    <div class="level2Style">
		                    	<img src="style/images/MenuIcon/menu_arrow_single.gif" />
		                    	<%-- 这里不要使用<s:a>标签 --%>
		                    	<a href="${pageContext.request.contextPath}${url}.do" target="right">${name}</a>	
		                    </div>
		                </li>
	                </s:if>
	            	</s:iterator>
	            </ul>
	        </li>
        </s:if>
		</s:iterator>
    </ul>
    
</div>
</body>
</html>
