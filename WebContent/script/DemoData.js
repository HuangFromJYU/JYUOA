// JavaScript Document
var demoData = {
	roleList: [
		{ "role.name": "档案管理员", "role.description": "管理档案"},
		{ "role.name": "系统管理员", "role.description": "管理系统"},
		{ "role.name": "部门经理", "role.description": ""},
		{ "role.name": "副总经理", "role.description": ""},
		{ "role.name": "财务经理", "role.description": ""},
		{ "role.name": "财务经理", "role.description": ""},
		{ "role.name": "总经理", "role.description": ""}
	],
	departmentList:[
		{ "department.name": "总经理室", "department.parent.name": "", "department.description": ""},
		{ "department.name": "市场部", "department.parent.name": "", "department.description": ""},
		{ "department.name": "OA产品部", "department.parent.name": "", "department.description": ""},
		{ "department.name": "售服部", "department.parent.name": "", "department.description": ""}
	],
	departmentList2:[
		{ "department.name": "研发部", "department.parent.name": "OA产品部", "department.description": ""},
		{ "department.name": "市场部", "department.parent.name": "OA产品部", "department.description": ""},
		{ "department.name": "售服部", "department.parent.name": "OA产品部", "department.description": ""}
	],
	userList:[
		{ "user.loginName": "zs", "user.name": "张三", "user.department": "研发部", "user.roles": "程序员", "user.description": "" },
		{ "user.loginName": "ls", "user.name": "李四", "user.department": "研发部", "user.roles": "程序员", "user.description": "" },
		{ "user.loginName": "ww", "user.name": "王五", "user.department": "测试部", "user.roles": "测试员", "user.description": "" }
	], 
	menuList:[
		{ "menu.name": "系统设置", "menu.prefix": "", "menu.icon": "FUNC20082.gif", "menu.url": "", "menu.description": "" },
			{ "menu.name": "部门管理", "menu.prefix": "　　", "menu.icon": "menu_arrow_single.gif", "menu.url": "/DepartmentAction.do", "menu.description": "" },
			{ "menu.name": "岗位管理", "menu.prefix": "　　", "menu.icon": "menu_arrow_single.gif", "menu.url": "/RoleAction.do", "menu.description": "" },
			{ "menu.name": "用户管理", "menu.prefix": "　　", "menu.icon": "menu_arrow_single.gif", "menu.url": "/UserAction", "menu.description": "" },
		{ "menu.name": "网络硬盘", "menu.prefix": "", "menu.icon": "FUNC20056.gif", "menu.url": "/LanDiskAction.do", "menu.description": "" },
		{ "menu.name": "审批流转", "menu.prefix": "", "menu.icon": "FUNC20057.gif", "menu.url": "", "menu.description": "" },
			{ "menu.name": "起草申请", "menu.prefix": "　　", "menu.icon": "menu_arrow_single.gif", "menu.url": "/DocumentAction.do?method=list", "menu.description": "" },
			{ "menu.name": "待我审批", "menu.prefix": "　　", "menu.icon": "menu_arrow_single.gif", "menu.url": "/DocumentAction.do?method=myTaskList", "menu.description": "" },
			{ "menu.name": "表单模板管理", "menu.prefix": "　　", "menu.icon": "menu_arrow_single.gif", "menu.url": "/DocumentTemplateAction.do", "menu.description": "" },
			{ "menu.name": "审批流程管理", "menu.prefix": "　　", "menu.icon": "menu_arrow_single.gif", "menu.url": "/ProcessAction.do", "menu.description": "" }
	], 
	formTemplateList:[
		{ "formTemplate.name": "请假申请单" },
		{ "formTemplate.name": "工程款申请单" },
		{ "formTemplate.name": "费用报销单" },
		{ "formTemplate.name": "差旅费报销单" },
		{ "formTemplate.name": "借支单" }
	],
	messageList:[
		{ "message.priority": "！", "message.title": "<b>您发送的※派车申请单-网络中心-管理员-0115被退文</b>", "message.sender": "系统", "message.recipient": "刘东风", "message.postTime": "2010-04-15 18:41" },
		{ "message.priority": "", "message.title": "2010年公司年会举办活动征集", "message.sender": "系统", "message.recipient": "周顺利", "message.postTime": "2010-04-15 18:41" },
		{ "message.priority": "！", "message.title": "销售部任务安排", "message.sender": "系统", "message.recipient": "王发财", "message.postTime": "2010-04-15 18:41" }
    ],
	messageList2:[
		{ "message.priority": "", "message.title": "2010年公司年会举办活动征集", "message.sender": "系统", "message.recipient": "周顺利", "message.postTime": "2010-04-15 18:41" },
		{ "message.priority": "！", "message.title": "销售部任务安排", "message.sender": "系统", "message.recipient": "王发财", "message.postTime": "2010-04-15 18:41" }
    ],
    processDefList:[
         { "processDef.id": 1, "processDef.name": "员工请假", "processDef.version": 2, "processDef.description": "" },
         { "processDef.id": 2, "processDef.name": "费用报销", "processDef.version": 3, "processDef.description": "" },
         { "processDef.id": 3, "processDef.name": "工作报告", "processDef.version": 2, "processDef.description": "" }
    ],
	documentTemplateList:[
         { "documentTemplate.id": 1, "documentTemplate.name": "员工请假单", "documentTemplate.processName": "员工请假流程", "documentTemplate.description": "" },
         { "documentTemplate.id": 2, "documentTemplate.name": "费用报销单", "documentTemplate.processName": "费用报销流程", "documentTemplate.description": "" },
         { "documentTemplate.id": 3, "documentTemplate.name": "工作报告", "documentTemplate.processName": "工作报告流程", "documentTemplate.description": "" }
    ],
	systemMenuList: [
		{ "systemMenu.id": 1, "systemMenu.namePrefix": "", "systemMenu.name": "系统管理", "systemMenu.parent.id": ""  },
			{ "systemMenu.id": 11, "systemMenu.namePrefix": "　　", "systemMenu.name": "部门管理", "systemMenu.parent.id": "1"  },
			{ "systemMenu.id": 12, "systemMenu.namePrefix": "　　", "systemMenu.name": "岗位管理", "systemMenu.parent.id": "1"  },
			{ "systemMenu.id": 13, "systemMenu.namePrefix": "　　", "systemMenu.name": "员工管理", "systemMenu.parent.id": "1"  },
		{ "systemMenu.id": 2, "systemMenu.namePrefix": "", "systemMenu.name": "权限管理", "systemMenu.parent.id": ""  },
		{ "systemMenu.id": 3, "systemMenu.namePrefix": "", "systemMenu.name": "知识管理", "systemMenu.parent.id": ""  },
		{ "systemMenu.id": 4, "systemMenu.namePrefix": "", "systemMenu.name": "网上交流", "systemMenu.parent.id": ""  },
			{ "systemMenu.id": 41, "systemMenu.namePrefix": "　　", "systemMenu.name": "站内消息", "systemMenu.parent.id": "4"  },
				{ "systemMenu.id": 411, "systemMenu.namePrefix": "　　　　", "systemMenu.name": "收件箱", "systemMenu.parent.id": "41"  },
				{ "systemMenu.id": 412, "systemMenu.namePrefix": "　　　　", "systemMenu.name": "发件箱", "systemMenu.parent.id": "41"  },
				{ "systemMenu.id": 413, "systemMenu.namePrefix": "　　　　", "systemMenu.name": "草稿箱", "systemMenu.parent.id": "41"  },
			{ "systemMenu.id": 52, "systemMenu.namePrefix": "　　", "systemMenu.name": "内部论坛", "systemMenu.parent.id": "4"  },
			{ "systemMenu.id": 53, "systemMenu.namePrefix": "　　", "systemMenu.name": "即时聊天", "systemMenu.parent.id": "4"  },
		
		{ "systemMenu.id": 5, "systemMenu.namePrefix": "", "systemMenu.name": "审批流转", "systemMenu.parent.id": ""  }
	],
	processNodeList: [
		{ "processNode.ordinal": 2, "processNode.name": "部门经理审批", "processNode.type": "审批", "processNode.description": "" },
		{ "processNode.ordinal": 3, "processNode.name": "副总经理审批", "processNode.type": "审批", "processNode.description": "" },
		{ "processNode.ordinal": 4, "processNode.name": "总经理审批", "processNode.type": "审批", "processNode.description": "" }
	],
	formList: [
		{ "form.id": 1, "form.title": "设备采购单_管理员_2010-05-01 ", "form.applicant.name": "管理员", "form.applyTime": "2010-05-01 09:30", "form.status": "审批中" },
		{ "form.id": 2, "form.title": "出车申请单_张雪_2010-05-12 ", "form.applicant.name": "张雪", "form.applyTime": "2010-05-12 09:30", "form.status": "未通过" },
		{ "form.id": 3, "form.title": "请假申请单_张三_2010-07-23 ", "form.applicant.name": "张三", "form.applyTime": "2010-07-23 16:41", "form.status": "审批中" },
		{ "form.id": 4, "form.title": "领款单_李明_2010-05-16 ", "form.applicant.name": "李明", "form.applyTime": "2010-05-16 09:30", "form.status": "已通过" }
	],
	approveInfoList: [
		{ "approveInfo.index": 2, "approveInfo.type": "审批", "approveInfo.approveTime": "2010-05-17 09:45", "approveInfo.approver": "李四", "approveInfo.approval": "同意", "approveInfo.comment": "" },
		{ "approveInfo.index": 3, "approveInfo.type": "审批", "approveInfo.approveTime": "2010-05-17 09:45", "approveInfo.approver": "王五", "approveInfo.approval": "同意", "approveInfo.comment": "" },
	],
	dirList:[
		{ "dir.name": "安装手册", "dir.creationTime": "2010-5-24 09:56:33" },
		{ "dir.name": "功能列表", "dir.creationTime": "2010-5-24 09:56:33" },
		{ "dir.name": "OA方案", "dir.creationTime": "2010-5-24 09:56:33" }
	],
	fileList:[
		{ "file.name": "固定岗位.doc", "file.fileType.icon": "doc.gif", "file.size": "150B", "file.creationTime": "2010-5-24 09:56:33" },
		{ "file.name": "条件流转.doc", "file.fileType.icon": "doc.gif", "file.size": "1.12KB", "file.creationTime": "2010-5-24 09:56:33" },
		{ "file.name": "使用手册.doc", "file.fileType.icon": "doc.gif", "file.size": "3.05MB", "file.creationTime": "2010-5-24 09:56:33" }
	],
	forumList: [
		{ "forum.name": "JavaSE/JavaEE", "forum.description": "Java开发有关的讨论都过来发帖", "forum.topicCount": 56, "forum.articleCount": 107, "forum.lastTopic.title": "OA是什么？ ", "forum.lastTopic.author.name": "管理员", "forum.lastTopic.postTime": "2010-06-12 17:47"  },
		{ "forum.name": "新功能建议", "forum.description": "把好的想法、先进思想和理念都随时提出来，供产品修改时使用", "forum.topicCount": 56, "forum.articleCount": 107, "forum.lastTopic.title": "OA是什么？ ", "forum.lastTopic.author.name": "管理员", "forum.lastTopic.postTime": "2010-06-12 17:47"  },
		{ "forum.name": "灌水专区", "forum.description": "大家可以尽情的来这里灌水", "forum.topicCount": 56, "forum.articleCount": 107, "forum.lastTopic.title": "OA是什么？ ", "forum.lastTopic.author.name": "管理员", "forum.lastTopic.postTime": "2010-06-12 17:47"  }
	],
	topicList: [
		{ "topic.type": "2", "topic.title": "项目管理", "topic.replyCount": 35 },
		{ "topic.type": "0", "topic.title": "审批流转是干什么用的？ ", "topic.replyCount": 33 },
		{ "topic.type": "1", "topic.title": "FAQ", "topic.replyCount": 75 },
		{ "topic.type": "0","topic.title": "我的邮箱为什么不能正常使用？", "topic.replyCount": 998 },
		{ "topic.type": "0","topic.title": "流程类别是干什么用的？", "topic.replyCount": 17 }
	],
	replyList: [
		{ "reply.title": "回复：新手发帖", "reply.content": "欢迎，欢迎，热烈欢迎！<img src='../script/fckeditor/editor/images/smiley/wangwang/15.gif'/>", "reply.floor": "1", "reply.faceIcon": "2" },
		{ "reply.title": "回复：新手发帖", "reply.content": "路过...", "reply.floor": "2", "reply.faceIcon": "5" },
		{ "reply.title": "回复：新手发帖", "reply.content": "<img src='../script/fckeditor/editor/images/smiley/wangwang/11.gif'/>", "reply.floor": "3", "reply.faceIcon": "3" }
	],
	faceIcon1_7: [
		{ "faceIconIndex" : "1" },
		{ "faceIconIndex" : "2" },
		{ "faceIconIndex" : "3" },
		{ "faceIconIndex" : "4" },
		{ "faceIconIndex" : "5" },
		{ "faceIconIndex" : "6" },
		{ "faceIconIndex" : "7" }
	],
    xxList: []
};

// list的最后一个元素后不要有逗号，否则在页面中显示数据时，会多出一行模板行。是因为没有数据内容造成的。
// （是使用替换的方式完成的显示数据，如果没有替换值，就不会替换，所以会多一个模板行）
