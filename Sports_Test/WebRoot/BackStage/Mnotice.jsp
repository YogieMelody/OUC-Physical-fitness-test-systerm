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

<title>公告管理</title>
<meta charset="UTF-8">
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
	NoticeDaoProxy ndp = new NoticeDaoProxy();
	List<Notice> all = null;
	if (request.getAttribute("queryList") != null) {
		all = (ArrayList<Notice>) request.getAttribute("queryList");
	} else {
		all = ndp.findAll();
	}
	pageContext.setAttribute("ref", all);
%>
<%
	Notice n = (Notice) request.getAttribute("single");
	pageContext.setAttribute("single", n);
%>
<body>
	<div class="section">
		<form action="MnoticeServlet" method="post">
			<table>
				<c:forEach items="${ref}" var="notice">
					<tr>
						<td class="title">${notice.title}</td>
						<td class="date">${notice.time}</td>
					</tr>
					
					<tr>
						<td><button value="${notice.id}" name="update"
								onClick="return window.confirm('确定要修改吗')">修改</button></td>
						<td><button value="${notice.id}" name="delete"
								onClick="return window.confirm('确定要删除吗')">删除</button></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<!-- 增加新闻 -->
	<div class="section">
	<form id="insert" action="MnoticeServlet" method="post"
		onsubmit="return notInsert();">
		<table>
		<tr><td><h2>发布公告</h2></td></tr>
		<tr><td>公告标题:<input type="text" name="insertNoticeTitle" /></td></tr>
		<tr><td>公告内容:
		<textarea rows="5" cols="40" name="insertNoticeContent"></textarea></td></tr>
		<tr><td><br/></td></tr>
		<tr><td>
			<button type="submit" value="增加公告" onclick="return window.confirm('确定要发布吗？')" >填加公告</button>
			</td></tr>
		</table>
		<input type="hidden" name="hid" value="create" /><br /> <br />
		</form>
		</div>
	<!-- 关键字搜索 -->
	<div class="section">
	<form id="queryByKeyWord" action="MnoticeServlet" method="post">
		<table>
		<tr><td><h2>公告查询</h2></td></tr>
		<tr><td>请输入公告标题的关键字进行公告的查询:</td></tr>
		<tr><td><input type="text" name="keyword" /></td></tr>
		<tr><td><button type="submit" name="queryAndDoSomething" value="查询" >查询</button></td></tr>
		</table>
		<input type="hidden" name="hid" value="query" /><br />
	</form>
	</div>
	<!-- 修改新闻 -->
	<div class="section">
		<h2>修改公告/预约开放</h2>
	<form id="update" action="MnoticeServlet" method="post" onSubmit="return notUpdate();">
		<!-- 要修改的公告的id -->
		<input type="hidden" name="updateNoticeId" value="${single.id}" /> 
		<span>标题：</span>
		<input type="text" name="updateNoticeTitle" value="${single.title}" /><br/>
		<span>时间：</span><input type="text" name="updateNoticeTime" value="${single.time}" readOnly/><br/>
		<span>内容：</span><textarea cols="50" name="updateNoticeContent" >${single.content}</textarea> <br/>
		<button type="submit" value="修改公告"
			onClick="return window.confirm('确定要修改公告吗');" >修改公告</button>
		<input name="hid"
			type="hidden" value="update" />
	</form>
    <!-- 预约开放 -->
	<form style="margin-top:-50px;" id="OpenOrClose" action="MnoticeServlet" method="post">
		<input type="hidden" name="hid" value="open" />
		<button type="submit" name="doOpen" value="开放预约申请" onclick="return window.confirm('确定要打开全校预约开关吗？')" >开放预约申请</button>
		<button type="submit" name="doOpen" value="关闭预约申请" onclick="return window.confirm('确定要关闭全校预约开关吗？')" >关闭预约申请</button>
	</form>
	</div>
</body>
</html>

