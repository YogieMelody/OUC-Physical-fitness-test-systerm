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

<title>测试班级管理</title>

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
<%
	String inf = (String) request.getAttribute("inf");
%>
<h3>${inf}</h3>
<%
	TeacherDaoProxy tdp = new TeacherDaoProxy();
	List<Teacher> allTea = tdp.findAll();
	pageContext.setAttribute("teacher", allTea);
%>
<%
	TestClassDaoProxy tcdp = new TestClassDaoProxy();
	List<TestClassView> all = null;
	if (request.getAttribute("queryList") != null) {
		all = (ArrayList<TestClassView>) request
				.getAttribute("queryList");
	} else {
		all = tcdp.findAllV();
	}
	pageContext.setAttribute("ref", all);
%>
<%
	TestClass tc = null;
	if (request.getAttribute("single") != null) {
		tc = (TestClass) request.getAttribute("single");
		pageContext.setAttribute("single", tc);
		TeacherDaoProxy tdp1=new TeacherDaoProxy();
		Teacher singleTeacher=tdp1.findById(tc.getTeaId());
		
	    pageContext.setAttribute("singleTeacher", singleTeacher);
	    //以下是为了将相应信息填入表单中的select元素中
	    String singleTestClassName=tc.getTestClassName();
	    int a=singleTestClassName.indexOf("月");
	    int b=singleTestClassName.indexOf("日");
	    int c=singleTestClassName.indexOf("周");
	    int d=singleTestClassName.indexOf("节");
	    String singleYear=singleTestClassName.substring(0, 4);
	    String singleMonth=singleTestClassName.substring(5,a);
	    String singleDay=singleTestClassName.substring(a+1,b);
	    String singleWeek=singleTestClassName.substring(c+1,c+2);
	    String singleClassTime=singleTestClassName.substring(c+2,d);
	    String singleTime=singleTestClassName.substring(d+1);
	    pageContext.setAttribute("singleYear", singleYear);
	    pageContext.setAttribute("singleMonth", singleMonth);
	    pageContext.setAttribute("singleDay", singleDay);
	    pageContext.setAttribute("singleWeek", singleWeek);
	    pageContext.setAttribute("singleClassTime", singleClassTime);
	    pageContext.setAttribute("singleTime", singleTime);
	}
	
%>
<body>
	<!-- 
	<form name="queryForm" action="MtestClassServlet" method="post">
		<input type="submit" value="上一页" /> <input type="submit" value="下一页" />
		<input type="text" name="pageNum" /> <input type="submit" value="跳转" />
		<input type="hidden" value="page" />
	</form>
 -->
 	<div class="section">
	<form action="MtestClassServlet" method="post">
		<table>
			<c:forEach items="${ref}" var="testClassView">
				<tr>
					<td>${testClassView.testClassName}&nbsp;</td>
					<td>${testClassView.testArea}&nbsp;</td>
					<td>${testClassView.termName}&nbsp;</td>
					<td>${testClassView.schoolArea}&nbsp;</td>
					<td>${testClassView.teaName}&nbsp;</td>
					<td>${testClassView.limitNum}&nbsp;</td>
					<td>${testClassView.nowNum}&nbsp;</td>
					<td><button value="${testClassView.id}" name="update"
							onClick="return window.confirm('确定要修改吗')">修改</button></td>
					<td><button value="${testClassView.id}" name="delete"
							onClick="return window.confirm('确定要删除吗')">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</div>
	<div class="section">
	<form name="insertForm" action="MtestClassServlet" method="post">
		测试班级的名称会自动生成(并且自动添加到当前学期)<br /> 请输入测试的时间&nbsp;&nbsp;&nbsp;： </br><select
			name="year" id="test_time">
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
		</select>年 <select name="month" id="test_time">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>月 <select name="day" id="test_time">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
		</select>日&nbsp; 周<select name="week" id="test_time">
			<option value="一">一</option>
			<option value="二">二</option>
			<option value="三">三</option>
			<option value="四">四</option>
			<option value="五">五</option>
			<option value="六">六</option>
			<option value="日">日</option>
		</select>&nbsp; <select name="classTime" id="classTime"
			onChange="getRightTimeInsert(this.value)">
			<option value="12">12</option>
			<option value="34">34</option>
			<option value="56">56</option>
			<option value="78">78</option>
		</select>节&nbsp;&nbsp;时间<select name="time" id="time" >
			<option value="8:00-9:30">8:00-9:30</option>
		</select><br /> 请输入测试地点：<input type="text" name="testArea" /><br/>
		请选择校区：<select id="schoolArea" name="schoolArea">
			<option value="崂山">崂山</option>
			<option value="鱼山">鱼山</option>
			<option value="浮山">浮山</option>
		</select><br /> 请选择老师：<select id="teachar" name="teacher">
			<c:forEach items="${teacher}" var="tea">
				<option value="${tea.getTeaName()}">${tea.getTeaName()}</option>
			</c:forEach>
		</select><br /> 测试班人数上限:<input type="text" id="limit" name="limit"
			required="required" />人<br /> <input type="hidden" value="create"
			name="hid" /> 
			<button type="submit" value="增加测试班级"
			onclick="return window.confirm('确认要增加吗？');" >增加测试班级</button>
	</form>
	</div>
	<div class="section">
	<form name="updateForm" action="MtestClassServlet" method="post">
		<input type="hidden" name="updateTestClassId" value="${single.id}" />
		测试班级信息修改（之前信息未自动填入）<br /> 请输入测试的时间&nbsp;&nbsp;&nbsp;： <br><select
			name="updateYear" id="test_time">
			<option value="${singleYear}">${singleYear}</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
		</select>年 <select name="updateMonth" id="test_time">
		<option value="${singleMonth}">${singleMonth}</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>月 <select name="updateDay" id="test_time">
		<option value="${singleDay}">${singleDay}</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
		</select>日&nbsp; 周<select name="updateWeek" id="test_time">
			<option value="${singleWeek}">${singleWeek}</option>
			<option value="一">一</option>
			<option value="二">二</option>
			<option value="三">三</option>
			<option value="四">四</option>
			<option value="五">五</option>
			<option value="六">六</option>
			<option value="日">日</option>
		</select>&nbsp; <select name="updateClassTime" id="classTime"
			onChange="getRightTimeUpdate(this.value)">
			<option value="${singleClassTime}">${singleClassTime}</option>
			<option value="12">12</option>
			<option value="34">34</option>
			<option value="56">56</option>
			<option value="78">78</option>
		</select>节&nbsp;&nbsp;时间 <select name="updateTime" id="updateTime">
			<option value="${singleTime}">${singleTime}</option>
		</select><br /> 请输入测试地点：<input type="text" name="updateTestArea" value="${single.testArea}"/><br/>请选择校区：<select id="updateSchoolArea" name="updateSchoolArea">
			<option value="${single.schoolArea}">${single.schoolArea}</option>
			<option value="崂山">崂山</option>
			<option value="鱼山">鱼山</option>
			<option value="浮山">浮山</option>
		</select><br /> 请选择老师：<select id="updateTeachar" name="updateTeacher">
			<option value="${singleTeacher.teaName}" selected>${singleTeacher.teaName}</option>
			<c:forEach items="${teacher}" var="tea">
				<option value="${tea.getTeaName()}">${tea.getTeaName()}</option>
			</c:forEach>
		</select><br /> 测试班人数上限:<input type="text" id="updateLimit" name="updateLimit"
			value="${single.limitNum}" required="required" />人<br /> <input
			type="hidden" value="update" name="hid" /> 
			<button type="submit"
			value="修改测试班级" onclick="return window.confirm('请确认信息无误后修改');" >修改测试班级</button>
	</form>
	</div>
	<div class="section">
	<form name="queryForm" action="MtestClassServlet" method="post">
		测试班级查询(输入相应的信息进行关键字和条件查询)<br /> 请输入测试的时间&nbsp;&nbsp;&nbsp;： <br><select
			name="queryYear" id="test_time">
			<option value=""></option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
		</select>年 <select name="queryMonth" id="test_time">
		<option value=""></option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>月 <select name="queryDay" id="test_time">
		<option value=""></option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
		</select>日&nbsp; 周<select name="queryWeek" id="test_time">
		<option value=""></option>
			<option value="一">一</option>
			<option value="二">二</option>
			<option value="三">三</option>
			<option value="四">四</option>
			<option value="五">五</option>
			<option value="六">六</option>
			<option value="日">日</option>
		</select>&nbsp; <select name="queryClassTime" id="test_time">
		<option value=""></option>
			<option value="12">12</option>
			<option value="34">34</option>
			<option value="56">56</option>
			<option value="78">78</option>
			<option value="90">90</option>
		</select>节&nbsp;&nbsp; <br /> 请选择校区：<select name="querySchoolArea">
		<option value=""></option>
			<option value="崂山">崂山</option>
			<option value="鱼山">鱼山</option>
			<option value="浮山">浮山</option>
		</select><br /> 请选择老师：<select name="queryTeacher">
		<option value=""></option>
			<c:forEach items="${teacher}" var="tea">
				<option value="${tea.getTeaName()}">${tea.getTeaName()}</option>
			</c:forEach>
		</select><br /> 测试班人数上限:<input type="text" name="queryLimit"
			required="required" />人<br /> <input type="hidden" value="query"
			name="hid" /> 
			<button type="submit" value="条件查询"
			onclick="return window.confirm('请确认信息无误后查询');" >条件查询</button>
	</form>
	</div>
</body>
</html>
