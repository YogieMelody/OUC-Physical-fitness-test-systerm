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
<link rel="stylesheet" href="<%=basePath%>Teacher/css/css.css">
<script type="text/javascript" charset="utf-8">
	function goPage() {
		var reg = new RegExp("^[0-9]*$");
		var p = document.getElementsByName("jumpPage")[0].value;
		if (!reg.test(p)) {
			alert("请输入数字!");
		} else {
			if (judge()) {
				window.location.href = "Teacher/TeacherClassView.jsp?ID=" + p;
			} else {
				window.location.href = "TeacherClassView.jsp?ID=" + p;
			}
		}
	}
	function goPageNext() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p = p - 1 + 2;
		if (judge()) {
			window.location.href = "Teacher/TeacherClassView.jsp?ID=" + p;
		} else {
			window.location.href = "TeacherClassView.jsp?ID=" + p;
		}
	}
	function goPagePrevious() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p -= 1;
		if (judge()) {
			window.location.href = "Teacher/TeacherClassView.jsp?ID=" + p;
		} else {
			window.location.href = "TeacherClassView.jsp?ID=" + p;
		}
	}
</script>
</head>
<%
	Teacher t = (Teacher) session.getAttribute("teacher");
	pageContext.setAttribute("t", t);

	if ("".equals(session.getAttribute("testTermId"))
			|| (session.getAttribute("testTermId") == null)) {
		response.sendRedirect("TeacherIndex.jsp");
	} else {
		int testTermId = (Integer) session.getAttribute("testTermId");
		int teaId = t.getId();
		TestClassDaoProxy tcdp = new TestClassDaoProxy();
		//找到总条目数
		int count = tcdp.findCountByTeaIdAndTestTermID(teaId,
				testTermId);
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
		List<TestClassView> tcvList = tcdp1
				.findByTeaIdAndTestTermIdByLimit(teaId, testTermId,
						pageNum * (pageCount - 1), pageNum);
		pageContext.setAttribute("tcvList", tcvList);
		pageContext.setAttribute("pageCount", pageCount);
		pageContext.setAttribute("pageAll", pageAll);
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
			</ul>
		</div>
		<div class="mainbody">
			<div class="guide">
				<span>当前位置:</span> <span><a
					href="<%=basePath%>Teacher/TeacherIndex.jsp">首页</a></span>
			</div>
			<div class="body">
				<div class="class">
					<h2>测试班级查看</h2>
					<div class="clear"></div>
					<table class="title" border="0">
						<tr>
							<td class="fir_title">测试时间</td>
							<td class="sec_title">负责教师</td>
							<td class="thi_title">测试地点</td>
							<td class="for_title">预约人数</td>
							<td class="fif_title"></td>
							<td class="final_title"></td>
						</tr>
					</table>
					<form action="TeaLookTestStudentServlet" method="post">
						<table class="class_info">
							<c:forEach items="${tcvList}" var="tcv">
								<tr>
									<td class="fir_title">${fn:substringBefore(tcv.testClassName," ")}&nbsp;${fn:substringAfter(tcv.testClassName,"节")}</td>
									<td class="sec_title">${tcv.teaName}</td>
									<td class="thi_title">${tcv.testArea}</td>
									<td class="for_title">${tcv.nowNum}/${tcv.limitNum}</td>
									<td class="fif_title"><button value="${tcv.id}"
											name="lookStudent">查看</button></td>
									<td id="exportStudent" class="final_title"><button
											value="${tcv.id}" name="exportStudent">导出</button></td>
								</tr>
								<!-- 新增操作，导出测试名单 -->
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
				<form class="input_grade" name="upload" action="Uploader"
					method="post" enctype="multipart/form-data">
					<h3>导入成绩</h3>
					<input type="file" name="fileName" /><br />
					<button class="button" name="import">导入</button>
				</form>
			</div>
		</div>
	</div>
	<script src="..js/jquery-1.12.0.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="<%=basePath%>Teacher/js/judgeBrowser.js"
		type="text/javascript" charset="utf-8"></script>
</body>
</html>
