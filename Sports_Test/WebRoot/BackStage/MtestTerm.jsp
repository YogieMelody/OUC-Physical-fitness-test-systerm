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

<title>测试学期管理</title>

<meta http-equiv="X-UA-Compatible" content="IE=11/10/9/8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <meta charset="utf-8">
<link rel="stylesheet" href="BackStage.css"> -->
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<jsp:include page="demo.jsp"></jsp:include>
</head>
<%
	String inf = (String) request.getAttribute("inf");
%>
<h3>${inf}</h3>
<%
	TestTermDaoProxy ttdp = new TestTermDaoProxy();
	List<TestTerm> all = null;
	if (request.getAttribute("queryList") != null) {
		all = (ArrayList<TestTerm>) request.getAttribute("queryList");
	} else {
		all = ttdp.findAll();
	}
	pageContext.setAttribute("ref", all);
%>
<%
	TestTerm t = null;
	if (request.getAttribute("single") != null) {
		t = (TestTerm) request.getAttribute("single");
		String name = t.getTestTermName();
		String nameP1=name.substring(2, 4);
		String nameP2=name.substring(5, 6);
		String nameP3=name.substring(14, name.length()-1);
		pageContext.setAttribute("nameP1", nameP1);
		pageContext.setAttribute("nameP2", nameP2);
		pageContext.setAttribute("nameP3", nameP3);
		pageContext.setAttribute("single", t);
	}
%>
<body>
	<div class="section">
	<form action="MtestTermServlet" method="post">
		<table>
			<c:forEach items="${ref}" var="testTerm">
				<tr>
					<td>${testTerm.id}&nbsp;</td>
					<td>${testTerm.testTermName}&nbsp;</td>
					<td>${testTerm.isNow}&nbsp;</td>
					<td>${testTerm.isOpen}&nbsp;</td>
					<td><button value="${testTerm.id}" name="update"
							onClick="return window.confirm('确定要修改吗')">修改</button></td>
					<td><button value="${testTerm.id}" name="delete"
							onClick="return window.confirm('确定要删除吗')">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</div>
	<div class="section">
	<form id="insert" action="MtestTermServlet" method="post">
		请输入要增加的测试学期的名称：20<input type="text" name="insertTestTermNameP1"
			style="width: 38px; height: 22px" required="required"/>年 <select
			name="insertTestTermNameP2" id="term">
			<option value="春">春</option>
			<option value="夏">夏</option>
			<option value="秋">秋</option>
		</select> 季学期体质测试 （<input type="text" name="insertTestTermNameP3"
			style="width: 142px; " />）(补充内容)<br /> <input type="hidden"
			name="hid" value="create" />
			<button type="submit" value="新增测试学期" >新增测试学期</button>
	</form>
	</div>
	<div class="section">
	<form id="queryByKeyWord" action="MtestTermServlet" method="post">
		<table>
			<tr>
				<td><h2>测试学期查询</h2></td>
			</tr>
			<tr>
				<td>请输入测试学期名称的关键字进行学期的查询:</td>
			</tr>
			<tr>
				<td><input type="text" name="keyword" /></td>
			</tr>
			<tr>
				<td><button type="submit" name="queryAndDoSomething" value="查询">查询</button></td>
			</tr>
		</table>
		<input type="hidden" name="hid" value="query" /><br />
	</form>
	<form id="update" action="MtestTermServlet" method="post">
		<!-- 要修改名称的测试学期的id -->
		<input type="hidden" name="updateTestTermId" value="${single.id}" /><br />
		请输入修改后的名称：20<input type="text" name="updateTestTermNameP1"
			value="${nameP1}" style="width: 35px; " required="required"/>年
			<select name="updateTestTermNameP2" id="term">
			<option value="${nameP2}">${nameP2}</option>
			<option value="春">春</option>
			<option value="夏">夏</option>
			<option value="秋">秋</option>
			</select>
			季学期体质测试（<input
			type="text" name="updateTestTermNameP3"
			value="${nameP3}"
			style="width: 142px; " />）(补充内容)<br /> <input type="hidden"
			name="hid" value="update" />
			<button type="submit" value="确认修改"
			onClick="return window.confirm('确定要修改公告吗');" >确认修改</button>
	</form>

	<form id="setNow" action="MtestTermServlet" method="post">
		请输入要设置为当前测试学期的学期id：<input type="text" name="setNowTestTerm" required="required"/><br />
		<input type="hidden" name="hid" value="set" />
		<button type="submit"
			value="确认设置" >确认设置</button>
	</form>
	</div>
</body>
</html>
