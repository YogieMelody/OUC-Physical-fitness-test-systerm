<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sports.entity.*"%>
<%@ page import="com.sports.dao.proxy.*"%>
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

<title>部门管理</title>

<meta http-equiv="X-UA-Compatible" content="IE=11/10/9/8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<jsp:include page="demo.jsp"></jsp:include>
</head>

<%
	String inf = (String) request.getAttribute("inf");
%>
<h3>${inf }</h3>
<%
	DepartmentDaoProxy ddp = new DepartmentDaoProxy();
	List<Department> all = ddp.findAll();
	pageContext.setAttribute("ref", all);
%>
<%
	Department d = (Department) request.getAttribute("single");
	pageContext.setAttribute("single", d);
%>
<body>
	<div class="section">
		<form action="MdepartmentServlet" method="post">
			<table>
				<c:forEach items="${ref}" var="dep">
					<tr>
						<td>${dep.departmentName}&nbsp;&nbsp;</td>
						<td><button value="${dep.id}" name="update"
								onClick="return window.confirm('确定要修改吗')">修改</button>&nbsp;&nbsp;</td>
						<td><button value="${dep.id}" name="delete"
								onClick="return window.confirm('确定要删除吗')">删除</button></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<br />
	<div class="section">
		<form id="create" action="MdepartmentServlet" method="post">
			<h2>请输入要增加的学院的名称：</h2>
			<input type="text" name="insertDepartmentName" /><br /> <br /> <input
				type="submit" value="新增学院" onClick="return window.confirm('确定要插入吗')" />
			<br /> <br /> <br /> <br /> <input name="hid" type="hidden"
				value="create" />
		</form>
	</div>
	<div class="section">
		<form id="update" action="MdepartmentServlet" method="post"
			onSubmit="return depUpdate();">
			<!-- 要修改的学院的id： -->
			<input type="hidden" name="updateDepartmentId" value="${single.id }" /><br />
			<h2>请输入修改后的名称：</h2>
			<input type="text" name="newDepartmentName"
				value="${single.departmentName }" /><br /> <br /> <input
				type="submit" value="修改名称"
				onClick="return window.confirm('确认修改吗？');" /> <input name="hid"
				type="hidden" value="update" />
		</form>
	</div>
</body>
</html>
