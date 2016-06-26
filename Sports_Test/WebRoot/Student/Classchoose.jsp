<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sports.dao.proxy.*"%>
<%@ page import="com.sports.entity.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>

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
<script type="text/javascript" charset="utf-8">
	function goPage() {
		var reg = new RegExp("^[0-9]*$");
		var p = document.getElementsByName("jumpPage")[0].value;
		if (!reg.test(p)) {
			alert("请输入数字!");
		} else {
			if (judge()) {
				window.location.href = "Student/Classchoose.jsp?ID=" + p;
			} else {
				window.location.href = "Student/Classchoose.jsp?ID=" + p;
			}
		}
	}
	function goPageNext() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p = p - 1 + 2;
		if (judge()) {
			window.location.href = "Student/Classchoose.jsp?ID=" + p;
		} else {
			window.location.href = "Student/Classchoose.jsp?ID=" + p;
		}
	}
	function goPagePrevious() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p -= 1;
		if (judge()) {
			window.location.href = "Student/Classchoose.jsp?ID=" + p;
		} else {
			window.location.href = "Student/Classchoose.jsp?ID=" + p;
		}
	}
</script>
</head>
<%
	StudentView sv = (StudentView) session.getAttribute("student");
	pageContext.setAttribute("sv", sv);

	if (session.getAttribute("schoolArea") != null) {
		String schoolArea = (String) (session
				.getAttribute("schoolArea"));
		TestClassDaoProxy tcdp = new TestClassDaoProxy();
		//找到该测试学期此校区测试班级的记录条目数
		int count = tcdp.findCount();
		//记录页数，初始值为1，采用limit语句进行分页
		int pageCount = 1;
		//记录每页显示的记录数,按照前端设计，显示10条最为美观
		int pageNum = 10;
		//记录总页数
		int pageAll = 0;
		if (count % pageNum == 0) {
			pageAll = count / pageNum;
		} else {
			pageAll = count / pageNum + 1;
		}
		if (request.getParameter("ID") != null) {
			int want = Integer.parseInt(request.getParameter("ID"));
			if (want < 1) {
				pageCount = 1;
			} else if (want > pageAll) {
				pageCount = pageAll;
			} else {
				pageCount = want;
			}
		}
		TestClassDaoProxy tcdp1 = new TestClassDaoProxy();
		List<TestClassView> tList = tcdp1.findByNowTermAndSchoolArea(
				schoolArea, pageNum * (pageCount - 1), pageNum);
		pageContext.setAttribute("tList", tList);
		pageContext.setAttribute("pageCount", pageCount);
		pageContext.setAttribute("pageAll", pageAll);
	} else {
		response.sendRedirect("index.jsp");
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
					href="<%=basePath%>Student/index.jsp">首页</a></span> <span><a
					href="<%=basePath%>Student/Classchoose.jsp">测试学期选择</a></span>
			</div>
			<div class="body">
				<div class="class">
					<h2>测试班级选择</h2>
					<div class="clear"></div>
					<table class="title" border="0">
						<tr>
							<td class="fir_title">测试时间</td>
							<td class="sec_title">负责教师</td>
							<td class="thi_title">测试地点</td>
							<td class="for_title">预约人数</td>
							<td class="fif_title">预约状态</td>
						</tr>
					</table>
					<form action="StuReserveServlet" method="post">
						<table class="class_info">
							<c:forEach items="${tList}" var="testClass">
								<tr>
									<td class="fir_title">${fn:substringBefore(testClass.testClassName," ")}&nbsp;${fn:substringAfter(testClass.testClassName,"节")}</td>
									<td class="sec_title">${testClass.teaName}</td>
									<td class="thi_title">${testClass.testArea}</td>
									<td class="for_title">${testClass.nowNum}/${testClass.limitNum}</td>
									<td class="fif_title"><button value="${testClass.id}"
											name="reserve" onclick="return window.confirm('确定要预约吗？')">可预约</button></td>
								</tr>
							</c:forEach>
						</table>
					</form>
				</div>
				<form action="" method="post">
					<ul class="changelist">
						<li><a onclick="goPageNext();" href="javascript:void(0);">下一页</a></li>
						<li><a onclick="goPage();" href="javascript:void(0);">确认</a></li>
						<span>/${pageAll}</span>
						<input type="text" name="jumpPage" value="${pageCount}" />
						<li><a onclick="goPagePrevious();" href="javascript:void(0);">上一页</a></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
</body>
<script src="<%=basePath%>Student/js/judgeBrowser.js"
	type="text/javascript" charset="utf-8"></script>
</html>
