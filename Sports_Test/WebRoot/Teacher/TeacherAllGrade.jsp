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
<link rel="stylesheet" href="<%=basePath%>Teacher/css/css.css">
<script type="text/javascript" charset="utf-8">
	function goPage() {
		var reg = new RegExp("^[0-9]*$");
		var p = document.getElementsByName("jumpPage")[0].value;
		if (!reg.test(p)) {
			alert("请输入数字!");
		} else {
			if (judge()) {
				window.location.href = "Teacher/TeacherAllGrade.jsp?ID=" + p;
			} else {
				window.location.href = "TeacherAllGrade.jsp?ID=" + p;
			}
		}
	}
	function goPageNext() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p = p - 1 + 2;
		if (judge()) {
				window.location.href = "Teacher/TeacherAllGrade.jsp?ID=" + p;
			} else {
				window.location.href = "TeacherAllGrade.jsp?ID=" + p;
			}
		}
	}
	function goPagePrevious() {
		var p = document.getElementsByName("jumpPage")[0].value;
		p -= 1;
		if (judge()) {
				window.location.href = "Teacher/TeacherAllGrade.jsp?ID=" + p;
			} else {
				window.location.href = "TeacherAllGrade.jsp?ID=" + p;
			}
		}
	}
</script>
</head>
<%
	Teacher t = (Teacher) session.getAttribute("teacher");
	pageContext.setAttribute("t", t);

	if (session.getAttribute("testClassId") != null) {
		int testClassId = Integer.parseInt(session.getAttribute(
				"testClassId").toString());
		//这里存在一个设计的巨大失误，没有存储学生的id而是学号，临时增加TestScoreViewNew实体
		TestScoreDaoProxy tsdp = new TestScoreDaoProxy();
		List<TestScoreViewNew> tsvnList = tsdp
				.findByTestClassId(testClassId);

		TestClassDaoProxy tcdp = new TestClassDaoProxy();
		TestClassView tcv = tcdp.findByIdV(testClassId);
		pageContext.setAttribute("tcv", tcv);

		if (!tsvnList.isEmpty()) {
			//找到该集合的记录条目数
			int count = tsvnList.size();
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
			List<TestScoreViewNew> showTsList = new ArrayList<TestScoreViewNew>();
			for (int i = 0; i < pageNum
					&& (pageNum * (pageCount - 1) + i) < tsvnList
							.size(); i++) {
				showTsList.add(i,
						tsvnList.get(pageNum * (pageCount - 1) + i));
			}
			pageContext.setAttribute("showTsList", showTsList);
			pageContext.setAttribute("pageCount", pageCount);
			pageContext.setAttribute("pageAll", pageAll);
		}
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
					<h2>测试成绩查询</h2>
					<div class="clear"></div>
					<div class="classes">
						<h3>班级信息</h3>
						<table>
							<tr>
								<td style="width:250px;">测试时间</td>
								<td style="width:100px;">负责教师</td>
								<td style="width:250px;">测试地点</td>
							</tr>
							<tr>
								<td style="width:250px;">${fn:substringBefore(tcv.testClassName," ")}&nbsp;${fn:substringAfter(tcv.testClassName,"节")}</td>
								<td style="width:100px;">${tcv.teaName}</td>
								<td style="width:250px;">${tcv.testArea}</td>
							</tr>
						</table>
					</div>
					<div class="teacher_search_grade">
						<h3>本班成绩</h3>
						<table>
							<tr>
								<td>姓名</td>
								<td>身高/体重(cm/kg)</td>
								<td>肺活量(ml)</td>
								<td>坐位体前屈(cm)</td>
								<td>立定跳远(cm)</td>
								<td>引体向上(男)</td>
								<td>仰卧起坐(女)</td>
								<td>1000米(男)</td>
								<td>800米(女)</td>
								<td>50米(s)</td>
							</tr>

							<c:forEach items="${showTsList}" var="ts">
								<tr>
									<td>${ts.stuName}</td>
									<td>${ts.height}/${ts.weight}</td>
									<td>${ts.vitalCapacity}</td>
									<td>${ts.sitAndReach}</td>
									<td>${ts.jump}</td>
									<td>${ts.pullup}</td>
									<td>${ts.situp}</td>
									<td>${ts.oneThousandRun}</td>
									<td>${ts.eightHundredsRun}</td>
									<td>${ts.fiftyRun}</td>
								</tr>
							</c:forEach>
						</table>
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
	</div>
</body>
<script src="<%=basePath%>Teacher/js/judgeBrowser.js"
	type="text/javascript" charset="utf-8"></script>
</html>
