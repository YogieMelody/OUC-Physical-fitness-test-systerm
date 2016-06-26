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
<link rel="stylesheet" href="<%=basePath%>Teacher/css/css.css">
<script type="text/javascript" charset="utf-8">
	function goPage() {
		var reg = new RegExp("^[0-9]*$");
		var p = document.getElementsByName("jumpPage")[0].value;
		if (!reg.test(p)) {
			alert("请输入数字!");
		} else {
			if (judge()) {
				window.location.href = "Teacher/TeacherClassMember.jsp?ID=" + p;
			} else {
				window.location.href = "TeacherClassMember.jsp?ID=" + p;
			}
		}
	}

	function goPageNext() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p = p - 1 + 2;
		if (judge()) {
			window.location.href = "Teacher/TeacherClassMember.jsp?ID=" + p;
		} else {
			window.location.href = "TeacherClassMember.jsp?ID=" + p;
		}
	}
	function goPagePrevious() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p -= 1;
		if (judge()) {
			window.location.href = "Teacher/TeacherClassMember.jsp?ID=" + p;
		} else {
			window.location.href = "TeacherClassMember.jsp?ID=" + p;
		}
	}
</script>
</head>
<%
	Teacher t = (Teacher) session.getAttribute("teacher");
	pageContext.setAttribute("t", t);

	if (session.getAttribute("svList") != null) {
		List<StudentView> svList = (List<StudentView>) session
				.getAttribute("svList");
		if (!svList.isEmpty()) {
			//找到该集合的记录条目数
			int count = svList.size();
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
			//此处没有再写分页函数，实际上没必要写，之前那样写太多麻烦，可以直接从已有的全部集合中按个数读取
			List<StudentView> showSvList = new ArrayList<StudentView>();
			for (int i = 0; i < pageNum
					&& (pageNum * (pageCount - 1) + i) < svList.size(); i++) {
				showSvList.add(i,
						svList.get(pageNum * (pageCount - 1) + i));
			}
			pageContext.setAttribute("showSvList", showSvList);
			pageContext.setAttribute("pageCount", pageCount);
			pageContext.setAttribute("pageAll", pageAll);
		}
	} else {
		response.sendRedirect("TeacherIndex.jsp");
	}

	if (session.getAttribute("testClassId") != null) {
		int testClassId = Integer.parseInt(session.getAttribute(
				"testClassId").toString());
		pageContext.setAttribute("testClassId", testClassId);
	} else {
		response.sendRedirect("TeacherIndex.jsp");
	}
%>
<body>
	<div class="header">
		<div class="logo">
			<img src="<%=basePath%>Teacher/img/logom.png">
		</div>
		<ul class="mainlist">
			<li class="index"><a
				href="<%=basePath%>Teacher/TeacherIndex.jsp">首页</a></li>
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
					href="<%=basePath%>Teacher/TeacherClassView.jsp">测试班级</a></span>
			</div>
			<div class="body">
				<div class="class">
					<h2>测试班级查看</h2>
					<form action="<%=basePath%>Teacher/TeacherAllGrade.jsp"
						method="post">
						<button class="all_grade" name="lookAllStudent"
							value="${testClassId}">查看本班成绩</button>
					</form>
					<div class="clear"></div>
					<table class="title" border="0">
						<tr>
							<td class="T_fir_title">学号</td>
							<td class="T_sec_title">姓名</td>
							<td class="T_sec_title">性别</td>
							<td class="T_thi_title">学院</td>
							<td class="T_fif_title">专业</td>
						</tr>
					</table>
					<form action="TeaLookSingleStudentServlet" method="post">
						<table class="class_info">
							<c:forEach items="${showSvList}" var="sv">
								<tr>
									<td class="T_fir_title">${sv.stuNumber}</td>
									<td class="T_sec_title">${sv.stuName}</td>
									<td class="T_sec_title">${sv.stuSex}</td>
									<td class="T_thi_title">${sv.departmentName}</td>
									<td class="T_fif_title">${sv.majorClassName}</td>
									<td class="T_six_title"><button value="${sv.stuNumber}"
											name="lookSingleStudent">查看成绩</button></td>
								</tr>
							</c:forEach>
						</table>
					</form>
				</div>
				<ul class="changelist">
					<li><a onclick="goPageNext();" href="javascript:void(0);">下一页</a></li>
					<li><a onclick="goPage();" href="javascript:void(0);">确认</a></li>
					<span>/${pageAll}</span>
					<input type="text" name="jumpPage" value="${pageCount}" />
					<li><a onclick="goPagePrevious();" href="javascript:void(0);">上一页</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
<script src="<%=basePath%>Teacher/js/judgeBrowser.js"
	type="text/javascript" charset="utf-8"></script>
</html>
