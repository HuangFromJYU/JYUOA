<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>发表新主题</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
	<%-- 使用FCKeditor --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"></script>
	<script type="text/javascript">
		$(function(){
			var oFCKeditor = new FCKeditor( 'content' ) ; // 参数就是提交表单时的参数名
			oFCKeditor.BasePath	= "${pageContext.request.contextPath}/fckeditor/"; // 必须要指定的属性。用于指定fckeditor的核心文件的存放路径（editor文件夹所在的路径）。要求一定要以'/'结尾。
			oFCKeditor.Width = "90%";
			oFCKeditor.ToolbarSet = "bbs"; // 提定工具栏的功能项配置，默认是Default
			oFCKeditor.ReplaceTextarea(); // 替换指定的textarea为FCKeditor
		});
	</script>
	
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 发表新主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">


	<s:form action="topic_add" cssStyle="margin: 0; padding: 0;">
		<s:hidden name="forumId"></s:hidden>

		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1">
				<div width=85% style="float:left">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="forum_list">论坛</s:a>
					<font class="MenuPoint"> &gt; </font>
					<s:a action="forum_show?id=%{#forum.id}">${forum.name}</s:a>
					<font class="MenuPoint"> &gt;&gt; </font>
					发表新主题
				</div>
			</div>
			<div class="ItemBlockBorder">
				<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
					<tr>
						<td class="InputAreaBg" height="30" width="50px"><div class="InputTitle">标题</div></td>
						<td class="InputAreaBg"><div class="InputContent">
							<s:textfield name="title" cssClass="InputStyle" cssStyle="width:100%"/></div>
						</td>
					</tr>
					<tr height="240">
						<td class="InputAreaBg"><div class="InputTitle">内容</div></td>
						<td class="InputAreaBg">
							<div class="InputContent">
								<s:textarea name="content" cssStyle="width:600px;height:200px"></s:textarea>
							</div>
						</td>
						
					</tr>
					<tr height="30">
						<td class="InputAreaBg" colspan="2" align="center">
							<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
							<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png"/></a>
						</td>
					</tr>
				</table>
			</div>
		</center>
	</s:form>
</div>

<div class="Description">
	说明：<br />
	
</div>

</body>
</html>
	