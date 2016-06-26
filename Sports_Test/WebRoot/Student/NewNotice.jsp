<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sports.dao.proxy.*"%>
<%@ page import="com.sports.entity.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>中国海洋大学体质测试预约系统</title>

<meta http-equiv="X-UA-Compatible" content="IE=11/10/9/8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="<%=basePath%>Student/css/css.css">
</head>
<%
	//获取登录的学生信息
	StudentView sv = (StudentView) session.getAttribute("student");
	pageContext.setAttribute("sv", sv);

	//获取LookNoticeServlet传过来的公告
	if (session.getAttribute("notice") != null) {
		Notice notice = (Notice) session.getAttribute("notice");
		pageContext.setAttribute("notice", notice);
	} else {
		response.sendRedirect("Student/index.jsp");
	}
%>
<body>
	<div class="header">
		<div class="logo">
			<img src="<%=basePath%>Student/img/logom.png">
		</div>
		<ul class="mainlist">
			<li><a href="<%=basePath%>Student/index.jsp">首页</a></li>
			<li><a href="<%=basePath%>Student/MyInformation.jsp">我的信息</a></li>
		</ul>
		<div class="clear"></div>
	</div>
	<div class="main">
		<div class="side">
			<div class="info">
				<ul>
					<li><span>姓名: ${sv.stuName}</span></li>
					<li><span>学号: ${sv.stuNumber}</span></li>
				</ul>
				<a href="<%=basePath%>LoginServlet">注销</a> <a
					href="<%=basePath%>Student/PasswordChange.jsp">修改密码</a>
			</div>
			<ul class="list">
				<li><a href="<%=basePath%>Student/index.jsp">测试学期查询</a></li>
				<li><a href="<%=basePath%>Student/OrderSearch.jsp">预约信息查询</a></li>
				<li><a href="<%=basePath%>Student/GradeSearch1.jsp">测试成绩查询</a></li>
			</ul>
		</div>
		<div class="mainbody">
			<div class="guide">
				<span>当前位置:</span> <span><a
					href="<%=basePath%>Student/index.jsp">首页</a></span>
			</div>
			<div class="body">
				<div class="article">
					<h2>${notice.title}</h2>
					<span>${notice.time}</span>
					<p>${notice.content}</p>
					<!-- <span>XXXXXXXXXX学院</span> -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>
