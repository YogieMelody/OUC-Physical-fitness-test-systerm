<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.io.PrintWriter" %>
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
</head>
<%
	StudentView sv = (StudentView) session.getAttribute("student");
	pageContext.setAttribute("sv", sv);

	//获取到当前测试学期的id和已经登陆的学生的id，进入reserve表进行查找
	TestTermDaoProxy ttdp = new TestTermDaoProxy();
	int testTermId = ttdp.findNow().getId();
	int stuId = sv.getId();
	ReserveDaoProxy rdp = new ReserveDaoProxy();
	Reserve reserve = rdp.findNowReserve(testTermId, stuId);
	if (reserve != null) {
		TestClassDaoProxy tcdp = new TestClassDaoProxy();
		TestClassView tcv = tcdp.findByIdV(reserve.getTestClassId());
		pageContext.setAttribute("tcv", tcv);
	} else {
	PrintWriter pw=response.getWriter();
	pw.print("<script language='javascript'>alert('尚无预约记录');window.location.href='index.jsp';</script>");
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
					href="<%=basePath%>Student/OrderSearch">预约信息查询</a></span>
			</div>
			<div class="body">
				<div class="order">
					<h2>预约信息查询</h2>
					<div class="clear"></div>
					<table class="order_title">
						<tr>
							<td class="o_fir">测试学期</td>
							<td class="o_sec">测试时间</td>
							<td class="o_thi">负责教师</td>
							<td class="o_for">测试地点</td>
						</tr>
					</table>
					<form action="StuCancleReserveServlet" method="post">
						<table class="order_info">
							<tr>
								<td class="o_fir">${fn:substringBefore(tcv.termName,"体")}</td>
								<td class="o_sec">${fn:substringBefore(tcv.testClassName," ")}&nbsp;${fn:substringAfter(tcv.testClassName,"节")}</td>
								<td class="o_thi">${tcv.teaName}</td>
								<td class="o_for">${tcv.testArea}</td>
								<td><button value="${sv.id}" name="cancle"
										onclick="return window.confirm('确定要取消预约吗？')">取消预约</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</body>
</html>
