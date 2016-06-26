<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'demo.jsp' starting page</title>

<meta http-equiv="X-UA-Compatible" content="IE=11/10/9/8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>BackStage/BackStage.css">
<script type="text/javascript"
	src="<%=basePath%>BackStage/validateInput.js"></script>
</head>

<body>
	<ul id="nav">
		<li><a href="<%=basePath%>BackStage/Mdepartment.jsp">院系管理</a></li>
		<li><a href="<%=basePath%>BackStage/MmajorClass.jsp">专业管理</a></li>
		<li><a href="<%=basePath%>BackStage/Mnotice.jsp">公告管理</a></li>
		<li><a href="<%=basePath%>BackStage/MtestTerm.jsp">测试学期管理</a></li>
		<li><a href="<%=basePath%>BackStage/MtestClass.jsp">测试班级管理</a></li>
		<li><a href="<%=basePath%>BackStage/Mstudent.jsp">学生管理</a></li>
		<li><a href="<%=basePath%>BackStage/Mteacher.jsp">教师管理</a></li>
		<li><a href="<%=basePath%>BackStage/MtestScore.jsp">成绩管理</a></li>
	</ul>
</body>
</html>
