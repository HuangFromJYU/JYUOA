<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>用户列表</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">登录名</td>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td width="200">岗位</td>
                <td>备注</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td>${loginName}&nbsp;</td>
                <td>${name}&nbsp;</td>
                <td>${department.name}&nbsp;</td>
                <td>
                	<s:iterator value="roles">
                		${name}
                	</s:iterator>&nbsp;
                </td>
                <td>${description}&nbsp;</td>
                <td>
                
                	<%-- version 1  加个判断
                	<s:if test="#session.user.hasPrivilegeByName('用户删除')">
	                	<s:a action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
                	</s:if>
                	<s:if test="#session.user.hasPrivilegeByName('用户修改')">
                    	<s:a action="user_editUI?id=%{id}">修改</s:a>
                    </s:if>
                	<s:if test="#session.user.hasPrivilegeByName('用户初始化密码')">
						<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
					</s:if>
					 --%>
					
					<%-- version 2  用自定义标签实现（判断权限的名称）
					<oa:a action="user_editUI?id=%{id}" privName="用户修改">修改</oa:a>
					--%>
					
					<%-- version 3.1  用自定义标签实现（判断权限的URL）
					<oa:a action="user_delete?id=%{id}" privUrl="user_delete">删除</oa:a>
					<oa:a action="user_editUI?id=%{id}" privUrl="user_edit">修改</oa:a>
					 --%>
					
					<%-- version 3.2  用自定义标签实现（判断权限的URL，而URL在action中就有，但需要做一下处理才能得到我们想要的格式）
					<oa:a action="user_delete?id=%{id}">删除</oa:a>
					<oa:a action="user_editUI?id=%{id}">修改</oa:a>
					 --%>
					
					<%-- version 4 还是s:a标签，我们要修改<s:a>标签所对应的源码 --%>
					<s:a action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
					<s:a action="user_editUI?id=%{id}">修改</s:a>
					<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>

                </td>
            </tr>
        </s:iterator> 
            
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="user_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>


<%-- 分页信息 --%>
<s:form id="pageForm" action="user_list"></s:form>
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>

</body>
</html>
