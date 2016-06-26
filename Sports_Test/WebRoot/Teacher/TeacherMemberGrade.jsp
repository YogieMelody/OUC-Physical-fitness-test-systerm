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
<link rel="stylesheet" href="<%=basePath%>Teacher/css/css.css">
<script>
	function ShowChange() {
		var cha = document.getElementById('change_grade');
		cha.style.display = "block";
	};
	function HideChange() {
		var cha = document.getElementById('change_grade');
		cha.style.display = "none";
	};
</script>
</head>
<%
	Teacher t = (Teacher) session.getAttribute("teacher");
	pageContext.setAttribute("t", t);

	if (session.getAttribute("singleStudent") != null) {
		StudentView sv = (StudentView) session
				.getAttribute("singleStudent");
		pageContext.setAttribute("sv", sv);
	}
	if (session.getAttribute("singleScore") != null) {
		TestScoreView tsv = (TestScoreView) session
				.getAttribute("singleScore");
		pageContext.setAttribute("tsv", tsv);
	}
%>
<body>
	<div class="header">
		<div class="logo">
			<img src="<%=basePath%>Teacher/img/logom.png">
		</div>
		<ul class="mainlist">
			<li><a href="<%=basePath%>Teacher/TeacherIndex.jsp">首页</a></li>
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
					href="<%=basePath%>Teacher/PasswordChange.jsp">修改密码</a>
			</div>
			<ul class="list">
				<li><a href="<%=basePath%>Teacher/TeacherIndex.jsp">测试学期查看</a></li>
				<li><a href="<%=basePath%>Teacher/TeacherClassView.jsp">测试班级查看</a></li>
			</ul>
		</div>
		<div class="mainbody">
			<div class="guide">
				<span>当前位置:</span> <span><a
					href="<%=basePath%>Teacher/TeacherIndex.jsp">首页</a></span> <span><a
					href="<%=basePath%>Teacher/TeacherIndex.jsp">测试学期查看</a></span> <span><a
					href="<%=basePath%>Teacher/TeacherClassView.jsp">测试班级查看</a></span>
			</div>
			<div class="body">
				<div class="grade_search">
					<h2>学生成绩查询</h2>
					<div class="clear"></div>
					<div class="classes">
						<h3>学生信息</h3>
						<table>
							<tr>
								<td style="width:150px;">姓名</td>
								<td style="width:150px;">学号</td>
								<td style="width:150px;">性别</td>
								<td style="width:150px;">学院</td>
								<td style="width:150px;">专业</td>
							</tr>
							<tr>
								<td style="width:150px;">${sv.stuName}</td>
								<td style="width:150px;">${sv.stuNumber}</td>
								<td style="width:150px;">${sv.stuSex}</td>
								<td style="width:150px;">${sv.departmentName}</td>
								<td style="width:150px;">${sv.majorClassName}</td>
							</tr>
						</table>
					</div>
					<div class="grade">
						<h3>测试成绩</h3>
						<table>
							<tr>
								<td>身高/体重(cm/kg)</td>
								<td>肺活量(ml)</td>
								<td>坐位体前屈(cm)</td>
								<td>立定跳远(cm)</td>
								<td>引体向上(男)</td>
								<td>仰卧起坐(女)</td>
							</tr>
							<tr>
								<td>${tsv.height}/${tsv.weight}</td>
								<td>${tsv.vitalCapacity}</td>
								<td>${tsv.sitAndReach}</td>
								<td>${tsv.jump}</td>
								<td>${tsv.pullup}</td>
								<td>${tsv.situp}</td>
							</tr>
							<tr>
								<td>1000米(男)</td>
								<td>800米(女)</td>
								<td>50米(s)</td>
							</tr>
							<tr>
								<td>${tsv.oneThousandRun}</td>
								<td>${tsv.eightHundredsRun}</td>
								<td>${tsv.fiftyRun}</td>
							</tr>
						</table>
						<button onclick="ShowChange()">修改成绩</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="change_grade" style="display:none;">
		<h3>修改成绩</h3>
		<div class="cancel" onclick="HideChange()"></div>
		<form action="TeaChangeScoreServlet" method="post">
			<table>
				<tr>
					<td>身高/体重(cm/kg)</td>
					<td>肺活量(ml)</td>
					<td>坐位体前屈(cm)</td>
					<td>立定跳远(cm)</td>
					<td>引体向上(男)</td>
					<td>仰卧起坐(女)</td>
				</tr>
				<tr>
					<td><input type="text" value="${tsv.height}/${tsv.weight}"
						name="heightAndWeight"></td>
					<td><input type="text" value="${tsv.vitalCapacity}" name="vit"></td>
					<td><input type="text" value="${tsv.sitAndReach}" name="sit"></td>
					<td><input type="text" value="${tsv.jump}" name="jump"></td>
					<td><input type="text" value="${tsv.pullup}" name="pullup"></td>
					<td><input type="text" value="${tsv.situp}" name="situp"></td>
				</tr>
				<tr>
					<td>1000米(男)</td>
					<td>800米(女)</td>
					<td>50米</td>
				</tr>
				<tr>
					<td><input type="text" value="${tsv.oneThousandRun}"
						name="one"></td>
					<td><input type="text" value="${tsv.eightHundredsRun}"
						name="eight"></td>
					<td><input type="text" value="${tsv.fiftyRun}" name="fifty"></td>
				</tr>
			</table>
			<span>*不修改的信息照常即可</span>
			<button>提交</button>
		</form>
	</div>
</body>
</html>
