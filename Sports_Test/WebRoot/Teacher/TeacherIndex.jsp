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
</head>
<%
	Teacher t = (Teacher) session.getAttribute("teacher");
	pageContext.setAttribute("t", t);

	TestTermDaoProxy ttdp = new TestTermDaoProxy();
	List<TestTerm> tList = ttdp.findAll();
	pageContext.setAttribute("tList", tList);
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
				<div class="term">
					<h2>测试学期查看</h2>
					<div class="clear"></div>
					<form action="TeaLookTestClassServlet" method="post">
						<ul>
							<c:forEach items="${tList}" var="tt">
								<li>
									<div class="term_info">${tt.testTermName}</div>
									<button value="${tt.id}" name="testTermId">查看班级</button></li>
							</c:forEach>
						</ul>
					</form>
				</div>
				<div class="clear"></div>
				<!-- 
				<form>
					<ul class="changelist">
						<li><a href="<%=basePath%>Teacher/TeacherIndex.jsp?ID=next">下一页</a></li>
						<input type="text" name="跳转页数" />/15
						<button>确认</button>
						<li><a href="<%=basePath%>Teacher/TeacherIndex.jsp?ID=previous">上一页</a></li>
					</ul>
				</form>
				 -->
			</div>
		</div>
	</div>
</body>
</html>
