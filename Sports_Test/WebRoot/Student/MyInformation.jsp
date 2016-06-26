<%@page import="sun.org.mozilla.javascript.internal.regexp.SubString"%>
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
<link rel="stylesheet" href="<%=basePath%>Student/css/css.css">

</head>
<%
	StudentView sv = (StudentView) session.getAttribute("student");
	pageContext.setAttribute("sv", sv);
	String six=sv.getStuIdentification().substring(0,6);
	pageContext.setAttribute("six", six);
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
					href="<%=basePath%>Student/index.jsp">首页</a></span> <span><a
					href="<%=basePath%>Student/MyInformation.jsp">我的信息</a></span>
			</div>
			<div class="body">
				<div class="myinfo">
					<h2>我的信息</h2>
					<div class="clear"></div>
					<table cellpadding="0">
						<tr>
							<td></td>
							<td class="sec_info"></td>
							<td></td>
							<td class="sec_info"></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td class="sec_info">${sv.stuName}</td>
							<td>学号</td>
							<td class="sec_info">${sv.stuNumber}</td>
						</tr>
						<tr>
							<td>性别</td>
							<td class="sec_info">${sv.stuSex}</td>
							<td>身份证号</td>
							<td class="sec_info">${six}************</td>
						</tr>
						<tr>
							<td>出生年月</td>
							<td class="sec_info">${sv.birthday}</td>
							<td>民族</td>
							<td class="sec_info">${sv.nationName}</td>
						</tr>
						<tr>
							<td>入学年份</td>
							<td class="sec_info">${sv.gradeNow}</td>
							<td>政治面貌</td>
							<td class="sec_info">${sv.politics}</td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td class="sec_info">${sv.stuPhone}</td>
							<td>家庭住址</td>
							<td class="sec_info">${sv.address}</td>
						</tr>
						<tr>
							<td>学院</td>
							<td class="sec_info">${sv.departmentName}</td>
							<td>专业</td>
							<td class="sec_info">${sv.majorClassName}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
