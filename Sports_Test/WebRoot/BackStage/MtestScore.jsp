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

<title>测试成绩管理</title>

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
<h3>${inf}</h3>

<body>
	<div class="section">
		<form name="ImportAndExport" action="MtestScoreServlet" method="post">
			<input type="hidden" name="hid" value="ExcelAndDb" /> <br /> <br />
			
			<button name="exportScore">导出全校成绩</button>
			<br /> <br />
			<button name="exportOrders">导出所有测试名单</button>
		</form>
<br /><br /><br /><br /><br />
		<form name="upload" action="Uploader" method="post"
			enctype="multipart/form-data">
			<h3>请务必将要导入数据库的文件放置在桌面上</h3>
			<input type="file" name="fileName" /><br />
			<button name="import">导入数据库</button>
		</form>
	</div>
</body>
</html>
