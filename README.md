# 前言
我最近在复习和汇总以前学习过的知识和做过的东西，[JYUOA](https://github.com/HuangFromJYU/JYUOA)（JYU全称是Jiaying University）是我在大二刚学完SSH框架的时候，做的第一个SSH的项目。怎么说呢，这可以说是我的启蒙项目，通过这个项目，我学到很多东西，像什么框架之间的整合，还有SSH框架的具体实战使用，还有三层架构设计，以及权限管理等知识，所以我觉得这是一个十分适合刚学完SSH框架的朋友的项目。

# 项目简介
OA是Office Automation的缩写，本意为利用技术的手段提高办公的效率，进而实现办公的自动化处理。实现信息化、无纸化办公，可方便的生成统计报表等。

当然了，真正的一个OA系统的功能是很多的，下面这张图是说一个OA系统能够具有的全部功能，现在的OA系统的功能基本上就是下面这张图的子集。

![](http://img.blog.csdn.net/20170606220944139?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

而JYUOA只是实现了其中很小的一部分功能，不过这样也够呛了，也需要具备很多知识的了，在后面我会介绍一下它的整体架构和里面的功能。下面是项目的主页

![](http://img.blog.csdn.net/20170606223345297?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



# 项目整体架构
一般这个时候就要扯到三层架构和MVC模式了，不过很多人对这两个其实会搞混，说不出个所以然来，这里我强烈建议你去看看[对三层和MVC的认识过程](http://blog.csdn.net/timheath/article/details/72887267)。

现在回到我们这个项目，这个项目有采用MVC模式，至于三层嘛，其实这个项目最开始用的也是三层，但后来进行重构，直接把业务逻辑层与数据访问层合并了，所以变成了两层，为什么要这么做呢，因为这个项目的业务逻辑并不是特别复杂，普遍都是增删改查操作，这样一来的话业务逻辑层的Service类的方法只是简单地调用一下数据访问层的Dao类的方法，所以干脆把它们整合一起。

还是普遍增删改查的问题还有分页等公共功能，所以把这些普通的功能都抽取出来了，如在界面层（UI）就抽取了`Action`，抽取出来一个`edu.jyu.oa.base.BaseAction`和`edu.jyu.oa.base.ModelDrivenBaseAction<T>`，其中`ModelDrivenBaseAction`继承了`BaseAction`，一个`Action`类如果需要用到Struts2的ModelDriven的话，就可以继承`ModelDrivenBaseAction`，否则继承`BaseAction`，例如`edu.jyu.oa.view.action.ProcessDefinitionAction`。

那么就以部门为例，整个项目的层次结构如下图

![](http://img.blog.csdn.net/20170606221018109?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

还有就是实体类图，可能在这里看不清楚，但是我已经放在**相关资料**那里了

![](http://img.blog.csdn.net/20170606222129967?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)




# 所用框架、技术

- Struts2 2.1.8.1
- Hibernate 3.6.0
- Spring 2.5	
- jBPM 4.4：工作流支持
- Junit 4：单元测试
- jQuery 1.4	

说明：还有jQuery.validate与jQuery.treeview等小插件

# 项目目录结构与包结构

**目录结构**

- 源码文件夹

1. src：项目源代码
2. conf：配置文件
3. test：单元测试

- WebContent文件夹下

1. script：JavaScript脚本文件
2. style：CSS样式文件
3. WEB-INF/jsp：jsp页面文件（再创建子文件夹分类存放）

**包结构**

- edu.jyu.oa.base：抽取公共功能的一些类
- edu.jyu.oa.cfg：管理配置信息
- edu.jyu.oa.domain：实体
- edu.jyu.oa.install：安装程序所在包
- edu.jyu.oa.service：Service接口
- edu.jyu.oa.service.impl：Service的实现类
- edu.jyu.oa.view.action：Struts的Action
- edu.jyu.oa.util：一些工具类



# 项目功能

## 系统管理

### 岗位管理
这个岗位管理其实可以看作是角色管理了，而角色有什么用呢？角色其实就是用户跟权限中间的一个桥梁，一个用户可以拥有很多权限，但是如果有些用户的所拥有的权限是一样的，那么分别给这些用户分配权限的话就会很麻烦，所以不如就将权限分配到角色去，然后给用户分配角色，这样子，用户就拥有了角色所拥有的权限。**一个用户可以有多个角色，一个角色可以有多个权限**。如果一个用户没有某一项权限，那么就不会显示那个权限对应的操作给该用户看。

![](http://img.blog.csdn.net/20170606221112344?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

岗位管理有以下功能：

- 查看岗位列表
- 添加岗位
- 修改岗位
- 设置岗位权限：可以给岗位设置多个权限
- 删除岗位：一个用户如果关联着该岗位，则会取消关联该岗位


岗位对应的实体类是`edu.jyu.oa.domain.Role`

### 部门管理
部门呢，就是部门咯，它可以有一个上级部门，也可以有多个下级部门，没有上级部门的可以称作顶级部门，一个部门下面可以有多个用户，即用户与部门的关系是1对多。

![](http://img.blog.csdn.net/20170606221134423?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

部门管理有以下功能：

- 查看所有顶级部门列表
- 查看一个部门所有的子部门
- 查看一个部门的上级部门
- 添加部门
- 修改部门
- 删除部门：会将下级部门一并删除，原本在这部门上的用户就没有所属部门了

部门对应的实体类是`edu.jyu.oa.domain.Department`

### 用户管理
用户管理呢，真的没什么好说的，大家都懂的。

![](http://img.blog.csdn.net/20170606221147824?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

用户管理有以下功能：

- 查看用户列表
- 添加用户：可以在这里设置岗位和部门
- 修改用户：可以在这里设置岗位和部门
- 删除用户
- 初始化用户密码：其实用户创建出来密码默认就是1234，所以初始化也是1234，修改用户密码可以在个人设置中修改，不过我没做这个功能……大家喜欢的话可以补上

用户对应的实体类是`edu.jyu.oa.domain.User`

## 网上交流

### 论坛管理 
这里应该说称作论坛版块管理比较适合点。

![](http://img.blog.csdn.net/20170606221207746?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

论坛管理有以下功能：

- 添加板块
- 查看论坛列表
- 修改板块
- 删除板块
- 对列表中的板块进行上移下移排序：板块的顺序在论坛列表中的顺序相同

论坛版块对应的实体类是`edu.jyu.oa.domain.Forum`

### 论坛
这里就是真正可以去论坛版块交流的地方了，你可以选择一个版块，然后再该版块上发帖子

![](http://img.blog.csdn.net/20170606221227714?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![](http://img.blog.csdn.net/20170606221237465?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

论坛有以下功能：

- 查看论坛版块以及相关信息，如论坛版块的帖子数等
- 查看一个论坛板块的所有帖子，还可以根据各种条件进行排序
- 发帖
- 回帖

编辑帖子和删除帖子功能没有做。置顶和精华也没做，其实就是修改`edu.jyu.oa.domain.Topic`的`type`值。有兴趣的也可以做一下，也不是很难的。

论坛中的帖子对应着实体类`edu.jyu.oa.domain.Topic`和`edu.jyu.oa.domain.Article`，其中`Topic`继承了`Article`

## 审批流转

### 审批流程管理
你要要申请一个东西，就要有一个流程，比如说请假，先给谁审批，再给谁审批。这里就可以部署一个流程的定义文件了，这个流程定义文件是用Eclipse的JBPM插件做的。

![](http://img.blog.csdn.net/20170606221256378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

审批流程管理有以下功能：

- 查看流程定义列表
- 部署流程定义文档：如果是具有同一个name的流程定义文档的话，再次部署相当于修改，就是将已部署的该文档的版本号加1，比如说我部署了一个请假流程名称为“员工请假流程”，流程步骤是 **开始-》提交申请-》总经理审批-》结束**，后来改成 **开始-》提交申请-》部门经理审批-》总经理审批-》结束**，这样子的话，即使流程改变，流程的name没有改变的话，再次部署就会把该流程版本+1，每次使用流程定义的时候都是用最新版本的
- 删除流程定义：会删除该流程定义以及与它具有相同name或者说key值的所有版本的流程定义
- 查看流程图：查看部署的的文件中xxx.png

流程定义对应的是`org.jbpm.api.ProcessDefinition`，这是一个接口，由JBPM提供的

### 申请模板管理
有了流程定义，我们就可以创建一个申请模板了，该申请模板可以引用一个流程定义，并且关联一个申请文件（.doc格式），员工可以根据该申请模板进行申请事务。

![](http://img.blog.csdn.net/20170606221559310?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

申请模板管理有以下功能：

- 查看申请模板列表
- 添加申请模板：要关联一个流程，上传一个模板文件
- 修改申请模板
- 删除申请模板
- 下载申请模板中的相关模板文件.doc

申请模板对应的实体类是`edu.jyu.oa.domain.Template`

### 起草申请
有了申请模板以后，就可以起草申请了。

![](http://img.blog.csdn.net/20170606221613086?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

起草申请有以下功能：

- 查看所有的申请模板
- 选择一个申请模板然后填写申请，申请后跳到我的申请查询

申请对应的实体类是`edu.jyu.oa.domain.Application`

### 待我审批
在申请以后，就需要对申请进行审批。

![](http://img.blog.csdn.net/20170606221629321?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

待我审批有以下功能：

- 显示出待我审批的申请记录
- 审批记录：可以同意申请和不同意申请，如果有多条路线选择的话，还可以在同意后指定下一条路线，比如说在部门经理审批同意后，可以直接结束，也可以转到总经理，这时候部门经理就可以选择走哪条路线。
- 查看流转记录

审批有关的实体类是`edu.jyu.oa.domain.ApproveInfo`

### 我的申请查询
在这里可以看到历史申请记录的和正在申请的记录。

![](http://img.blog.csdn.net/20170606221832745?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGltSGVhdGg=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

我的申请查询有以下功能：

- 查看申请的记录：可以根据申请模板和状态来作为查询条件
- 查看流转记录

# 项目搭建
整个项目大概就介绍到这里，现在就来说说怎么在你的机器上泡起来。我在本机运行这个项目的时候，用的JDK版本是1.7，Tomcat版本是8，千万不要用JDK 1.8，因为这个项目的Spring版本跟JDK 1.8不兼容，所以关于一些环境版本的选择，你要小心咯。

1. 你要创建一个数据库，我的是`jyuoa`，如果你创建的数据库名不是这个的话，就得到`config`中的`jdbc.properties`里修改一下数据库配置，还有就是数据库账号密码什么也是在那修改的，注意了，你创建的这个数据库要选择UTF-8编码。
2. 下载这个项目的代码，然后在你自己的IDE中创建Web工程，随便起你喜欢的名字，把下载好的代码拷贝到工程的对应位置中。你可以发现我没有上传`.project`文件，因为我用的是Eclipse，你可能用的是其它工具。我也没有上传`.classpath`，因为像Eclipse的`WebContent`到了`MyEclipse`就是`WebRoot`，`.classpath`文件可能会让产生一些让你疑惑的问题，比如说同时出现了`WebContent`和`WebRoot`，反正我就被懵过，至于其它的IDE我也没用过了，也不知道会出现什么问题，所以你要记得把`src`、`test`还有`config`文件夹Build Path为源码目录(Source Folder)。
3. 在配置好工程后，找到`edu.jyu.oa.install.Installer`类，运行一下，把项目的基本信息插入到数据库中，像超级管理员信息还有一些权限的信息，不过可能第一次运行会比较慢，因为要建很多表。
4. 然后就是部署到你的Web容器中啦，我只试过在在Tomcat 8中部署。

大概就是这样子吧，出了错别慌，尽情地谷歌和百度，实在不行你找我。


# 总结
这个项目可能对于以后我遇到的项目来讲，是**a piece of cake**，但是这对于我来说是一个很好的开始，每次积累一点点，最后整合起来就是一大块。但是这个项目也有很多缺点，比如说日志没怎么做，异常处理也不好，有些功能没有完成，不过只要花点时间，肯定是能行的！最后需要说的是，这个项目我是学习传智播客的OA项目的，所以非常感谢传智播客将他们的教学资源分享出来。






