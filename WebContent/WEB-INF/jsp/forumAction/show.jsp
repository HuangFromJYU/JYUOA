<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>【常见问题】中的主题列表</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript">
		function onSortByChange( selectedValue ){
			if(selectedValue == 0){
				$("select[name=asc]").attr("disabled", "disabled");	
			}else{
				$("select[name=asc]").removeAttr("disabled");	
			}
		}
		$(function(){
			if($("select[name=orderBy]").val() == '0'){
				$("select[name=asc]").attr("disabled", "disabled");		
			}
		});
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 【${forum.name}】中的主题列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<s:form id="pageForm" action="forum_show?id=%{id}">
<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_list">论坛</s:a>
			<font class="MenuPoint"> &gt; </font>
			${forum.name}
			<span style="margin-left:30px;">
			
				<s:a action="topic_addUI?forumId=%{#forum.id}">
					<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/>
				</s:a>
			
			</span>
		</div>
		
		<div class="ForumPageTableBorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!--表头-->
				<tr align="center" valign="middle">
					<td width="3" class="ForumPageTableTitleLeft">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
					<td width="50" class="ForumPageTableTitle"><!--状态/图标-->&nbsp;</td>
					<td class="ForumPageTableTitle">主题</td>
					<td width="130" class="ForumPageTableTitle">作者</td>
					<td width="100" class="ForumPageTableTitle">回复数</td>
					<td width="130" class="ForumPageTableTitle">最后回复</td>
					<td width="3" class="ForumPageTableTitleRight">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="8"></td></tr>
				<tr height=3><td colspan=8></td></tr>
					
				<!--主题列表-->
				<tbody class="dataContainer" datakey="topicList">
				
				<s:iterator value="recordList">
					<tr height="35" id="d0" class="template">
						<td></td>
						<td class="ForumTopicPageDataLine" align="center">
							<img src="${pageContext.request.contextPath}/style/images/topicType_${type}.gif" />
						</td>
						<td class="Topic">
							<s:a cssClass="Default" action="topic_show?id=%{id}">${title}</s:a>
						</td>
						<td class="ForumTopicPageDataLine">
							<ul class="ForumPageTopicUl">
								<li class="Author">${author.name}</li>
								<li class="CreateTime"><s:date name="postTime" format="yyyy年MM月dd日 HH:mm:ss"/></li>
							</ul>
						</td>
						<td class="ForumTopicPageDataLine Reply" align="center"><b>${replyCount}</b></td>
						<td class="ForumTopicPageDataLine">
							<ul class="ForumPageTopicUl">
								<li class="Author">${lastReply.author.name}</li>
								<li class="CreateTime"><s:date name="lastReply.postTime" format="yyyy年MM月dd日 HH:mm:ss"/></li>
							</ul>
						</td>
						<td></td>
					</tr>
				</s:iterator>	
					
				</tbody>
				<!--主题列表结束-->	
					
				<tr height="3"><td colspan="9"></td></tr>
				
			</table>
			
			<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td></td>
							<td>
								
								<%-- 使用自定义标签，以便于回显数据 --%>
								<s:select name="viewType" list="%{  #{0:'全部主题', 1:'全部精华贴'}  }"/>
								<s:select name="orderBy" onchange="onSortByChange(this.value)"
									list="%{ #{0:'默认排序(所有置顶帖在前面，并按最后更新时间降序排列)', 1:'只按最后更新时间排序', 2:'只按主题发表时间排序', 3:'只按回复数量排序'} }"
								/>
								<s:select name="asc" list="%{ #{false:'降序', true:'升序'} }"/>

								<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
	</center>
</div>
</s:form>


<!--分页信息-->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>



<div class="Description">
	说明：<br />
	1，主题默认按最后更新的时间降序排列。最后更新时间是指主题最后回复的时间，如果没有回复，就是主题发表的时间。<br />
	2，帖子有普通、置顶、精华之分。置顶贴始终显示在最上面，精华贴用不同的图标标示。<br />
</div>

</body>
</html>
	