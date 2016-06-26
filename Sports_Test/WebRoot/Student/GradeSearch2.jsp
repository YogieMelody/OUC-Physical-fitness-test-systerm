<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sports.dao.proxy.*"%>
<%@ page import="com.sports.entity.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	
	TestScore ts=(TestScore)session.getAttribute("ts");
	int testClassId=ts.getTestClassId();
	//根据测试班级的Id找到详情
	TestClassDaoProxy tcdp=new TestClassDaoProxy();
	TestClassView tcv=tcdp.findByIdV(testClassId);
	//如果成绩不存在，就打0
	if(("".equals(ts.getEightHundredsRun())||(ts.getEightHundredsRun()==null))){
	ts.setEightHundredsRun("0");
	}
	if(("".equals(ts.getOneThousandRun())||(ts.getOneThousandRun()==null))){
	ts.setOneThousandRun("0");
	}
	pageContext.setAttribute("tcv", tcv);
	pageContext.setAttribute("ts", ts);
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
	        <a href="<%=basePath%>LoginServlet">注销</a>
	        <a href="<%=basePath%>Student/PasswordChange.jsp">修改密码</a>
	      </div>
	      <ul class="list">
	        <li><a href="<%=basePath%>Student/index.jsp">测试学期查询</a></li>
				<li><a href="<%=basePath%>Student/OrderSearch.jsp">预约信息查询</a></li>
				<li><a href="<%=basePath%>Student/GradeSearch1.jsp">测试成绩查询</a></li>
	      </ul>
	    </div>
	    <div class="mainbody">
	      <div class="guide">
	      	<span>当前位置:</span>
	        <span><a href="<%=basePath%>Student/index.jsp">首页</a></span>
	        <span><a href="<%=basePath%>Student/GradeSearch1">测试成绩查询</a></span>
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
	      					<td style="width:250px;">${tcv.testClassName}</td>
	      					<td style="width:100px;">${tcv.teaName}</td>
	      					<td style="width:250px;">${tcv.testArea}</td>
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
	      					<td>${ts.height}/${ts.weight}</td>
	      					<td>${ts.vitalCapacity}</td>
	      					<td>${ts.sitAndReach}</td>
	      					<td>${ts.jump}</td>
	      					<td>${ts.pullup}</td>
	      					<td>${ts.situp}</td>
	      				</tr>
	      				<tr>
	      					<td>1000米(男)</td>
	      					<td>800米(女)</td>
	      					<td>50米(s)</td>
	      				</tr>
	      				<tr>
	      					<td>${ts.oneThousandRun}</td>
	      					<td>${ts.eightHundredsRun}</td>
	      					<td>${ts.fiftyRun}</td>
	      				</tr>
	      			</table>
	      		</div>
	      	</div>
	      </div>
	    </div>
	  </div>
  </body>
</html>
