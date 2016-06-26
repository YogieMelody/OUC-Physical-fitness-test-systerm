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
<link rel="stylesheet" href="<%=basePath%>Student/css/css.css">
</head>
<%
	//获取登录的学生信息
	StudentView sv = (StudentView) session.getAttribute("student");
	pageContext.setAttribute("sv", sv);
	//获取前五条公告
	NoticeDaoProxy ndp=new NoticeDaoProxy();
	List<Notice> allNotice=ndp.findAll();
	List<Notice> topFive=new ArrayList<Notice>();
	for(int i=0;i<5&&i<allNotice.size();i++){
	topFive.add(allNotice.get(i));
	}
	pageContext.setAttribute("nList", topFive);
	
	//获取测试学期
	TestTermDaoProxy ttdp=new TestTermDaoProxy();
	TestTerm tt=ttdp.findNow();
	pageContext.setAttribute("tt", tt);
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
			<form action="StuEnterTestClassServlet" method="post">
				<div id="choose" style="display:none;">
					<h3>校区选择</h3>
					<div class="cancel" onclick="HideSchool()"></div>
					<button name="schoolArea" value="崂山">崂山校区</button>
					<button name="schoolArea" value="鱼山">鱼山校区</button>
				</div>
			</form>
			<div class="guide">
				<span>当前位置:</span> <span><a
					href="<%=basePath%>Student/index.jsp">首页</a></span>
			</div>
			<div class="body">
				<div class="new">
					<h2>新闻公告</h2>
					<div class="clear"></div>
					<ul>
						<c:forEach items="${nList}" var="notice">
							<li>
								<div class="circle"></div> <a href="LookNoticeServlet?ID=${notice.id}"><div class="new_info">${notice.title}</div></a>
								<div class="new_time">${notice.time}</div>
							</li>
						</c:forEach>

					</ul>
				</div>
				<div class="term">
					<h2>测试学期</h2>
					<div class="clear"></div>
					<ul>
						<li>
							<div class="term_info">${tt.testTermName}</div>
							<button value="${tt.id}" name="tryBook" onclick="ShowSchool()">进入预约</button>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>

<script>
	function ShowSchool() {
		var cho = document.getElementById('choose');
		cho.style.display = "block";
	};
	function HideSchool() {
		var cho = document.getElementById('choose');
		cho.style.display = "none";
	};
</script>
</html>
