<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sports.dao.proxy.*"%>
<%@ page import="com.sports.entity.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName

			() + ":" + request.getServerPort() + path + "/";
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
<link rel="stylesheet" href="<%=basePath%>Teacher/css/css.css">

</head>
<%
	Teacher t = (Teacher) session.getAttribute("teacher");
	pageContext.setAttribute("t", t);
%>

<body>
	<div class="header">
		<div class="logo">
			<img src="<%=basePath%>Teacher/img/logom.png">
		</div>
		<ul class="mainlist">
			<li><a href="<%=basePath%>Teacher/TeacherIndex.jsp">首页 </a></li>
		</ul>
		<div class="clear"></div>
	</div>
	<div class="main">
		<div class="side">
			<div class="info">
				<ul>
					<li><span>姓名: ${t.teaName}</span></li>
					<li><span>工号: ${t.teaNumber}</span></li>
				</ul>
				<a href="<%=basePath%>LoginServlet">注销</a> <a
					href="<%=basePath%>Teacher/PasswordChange.jsp">修改 密码</a>
			</div>
			<ul class="list">
				<li><a href="<%=basePath%>Teacher/TeacherIndex.jsp">测试学期查询</a></li>
			</ul>
		</div>
		<div class="mainbody">
			<div class="guide">
				<span>当前位置:</span> <span><a
					href="<%=basePath%>Teacher/TeacherIndex.jsp"> 首页</a></span> <span><a
					href="<%=basePath%>Teacher/PasswordChange.jsp">修改密码</a></span>
			</div>
			<div class="body">
				<div class="password">
					<h2>修改密码</h2>
					<div class="clear"></div>
					<form action="StuPwdChangeServlet" method="post">
						<input type="hidden" name="teaNumber" value="${t.teaNumber}" />
						<p>输入新密码:</p>
						<input type="password" maxlength="12" name="newPwd1" />
						<p>确认新密码:</p>
						<input type="password" maxlength="12" name="newPwd2" />
						<button>确认</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>