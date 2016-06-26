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

<title>学生管理</title>

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
<h3>${inf}</h3>
<%
	//获取所有民族信息用于绑定添加学生信息时的民族下拉框
	NationDaoProxy ndp = new NationDaoProxy();
	List<Nation> allNation = ndp.findAll();
	pageContext.setAttribute("allNation", allNation);
	//获取所有院系信息用于绑定添加学生信息时的院系下拉框
	DepartmentDaoProxy ddp = new DepartmentDaoProxy();
	List<Department> allDep = ddp.findAll();
	pageContext.setAttribute("allDep", allDep);
	//获取所有专业信息用于绑定添加学生信息时的专业下拉框
	MajorClassDaoProxy mcdp = new MajorClassDaoProxy();
	List<MajorClass> allMaj = mcdp.findAll();
	pageContext.setAttribute("allMaj", allMaj);
%>
<%
	StudentDaoProxy sdp = new StudentDaoProxy();
	List<Student> allStu = new ArrayList<Student>();
	if (request.getAttribute("condition") != null) {
		allStu = (List<Student>) request.getAttribute("condition");
	} else {
		allStu = sdp.findAll();
	}
	pageContext.setAttribute("stu", allStu);
%>
<body>
	<div class="section">
	<form action="MstudentServlet" method="post">
		<table>
			<c:forEach items="${stu}" var="student">
				<tr>
					<td>${student.stuNumber}&nbsp;</td>
					<td>${student.stuName}&nbsp;</td>
					<td><button value="${student.id}" name="detail">详情</button></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<form name="ImportAndExport" action="MstudentServlet" method="post">
		<input type="hidden" name="hid" value="ExcelAndDb" />
		<h3>请务必将要导入数据库的文件放置在桌面上</h3>
		<input type="file" name="fileName" /><br />
		<button name="import">导入数据库(将excel文件放置在桌面上命名为   学生信息.xls)</button>
		<br /> <br />
		<button name="export">导出至Excel</button>
	</form>
	</div>
	<div class="section">
	<h3>单条增加学生信息</h3>
	<form name="Insert" action="MstudentServlet" method="post">
		<input type="hidden" name="hid" value="create" /> 学号<input
			type="text" name="stuNumber" required="required" /><br /> 姓名<input
			type="text" name="stuName" required="required" /><br /> 性别<select
			name="stuSex">
			<option value="男">男</option>
			<option value="女">女</option>
		</select><br /> 身份证<input type="text" name="stuIdentification"
			required="required"> <input type="radio" name="isGat" class="choose" 
			value="yes" />是否港澳生<br /> 电话<input type="text" name="stuPhone"
			required="required" /> <br />院系<select name="stuDep">
			<c:forEach items="${allDep}" var="dep">
				<option value="${dep.id}">${dep.departmentName}</option>
			</c:forEach>
		</select><br /> 专业<select name="stuMaj">
			<c:forEach items="${allMaj}" var="maj">
				<option value="${maj.id}">${maj.majorClassName}</option>
			</c:forEach>
		</select><br /> 入学年份<select name="gradeNow">
			<option value="2011">2011</option>
			<option value="2012">2012</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
		</select><br /> 民族<select name="stuNation">
			<c:forEach items="${allNation}" var="nat">
				<option value="${nat.id}">${nat.nationName}</option>
			</c:forEach>
		</select><br /> 出生日期<select name="year" id="birth">
			<option value="1992">1992</option>
			<option value="1993">1993</option>
			<option value="1994">1994</option>
			<option value="1995">1995</option>
			<option value="1996">1996</option>
			<option value="1997">1997</option>
			<option value="1998">1998</option>
		</select>年 <select name="month" id="birth">
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>
			<option value="06">06</option>
			<option value="07">07</option>
			<option value="08">08</option>
			<option value="09">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>月 <select name="day" id="birth">
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>
			<option value="06">06</option>
			<option value="07">07</option>
			<option value="08">08</option>
			<option value="09">09</option>
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
		</select><br /> 地址<input type="text" name="stuAdd" required="required" /><br />
		政治面貌<select name="stuPol">
			<option value="共青团员">共青团员</option>
			<option value="中共党员">中共党员</option>
			<option value="群众">群众</option>
		</select><br /> <input type="submit" value="增加学生信息 " />
	</form>
	</div>
	<div class="section">
	<h3>学生信息修改</h3>
	<form name="Update" action="MstudentServlet" method="post">
		学号<input type="text" name="updateStuNumber"
			value="${singleStu.stuNumber}" required="required" /><br /> 姓名<input
			type="text" name="updateStuName" value="${singleStu.stuName}"
			required="required" /><br /> 性别<select name="updateStuSex">
			<option value="${singleStu.stuSex}">${singleStu.stuSex}</option>
			<option value="男">男</option>
			<option value="女">女</option>
		</select><br /> 身份证<input type="text" name="updateStuIdentification"
			value="${singleStu.stuIdentification}" required="required"> <input class="choose" 
			type="radio" name="updateIsGat" value="yes" />是否港澳生<br /> 电话<input
			type="text" name="updateStuPhone" value="${singleStu.stuPhone}"
			required="required" /> <br />院系<select name="updateStuDep">
			<option value="${singleStu.departmentId}">${oldDep}</option>
			<c:forEach items="${allDep}" var="dep">
				<option value="${dep.id}">${dep.departmentName}</option>
			</c:forEach>

		</select><br /> 专业<select name="updateStuMaj">
			<option value="${singleStu.majorId}">${oldMaj}</option>
			<c:forEach items="${allMaj}" var="maj">
				<option value="${maj.id}">${maj.majorClassName}</option>
			</c:forEach>
		</select><br /> 入学年份<select name="updateGradeNow">
			<option value="${singleStu.gradeNow}">${singleStu.gradeNow}</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
		</select><br /> 民族<select name="updateStuNation">
			<option value="${singleStu.nationMark}">${oldNat}</option>
			<c:forEach items="${allNation}" var="nat">
				<option value="${nat.id}">${nat.nationName}</option>
			</c:forEach>
		</select><br /> 出生日期<select name="updateYear" id="birth">
			<option value="${oldYear}">${oldYear}</option>
			<option value="1992">1992</option>
			<option value="1993">1993</option>
			<option value="1994">1994</option>
			<option value="1995">1995</option>
			<option value="1996">1996</option>
			<option value="1997">1997</option>
			<option value="1998">1998</option>
		</select>年 <select name="updateMonth" id="birth">
			<option value="${oldMonth}">${oldMonth}</option>
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>
			<option value="06">06</option>
			<option value="07">07</option>
			<option value="08">08</option>
			<option value="09">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>月 <select name="updateDay" id="birth">
			<option value="${oldDay}">${oldDay}</option>
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>
			<option value="06">06</option>
			<option value="07">07</option>
			<option value="08">08</option>
			<option value="09">09</option>
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
		</select><br /> 地址<input type="text" name="updateStuAdd"
			value="${singleStu.address}" required="required" /><br /> 政治面貌<select
			name="updateStuPol">
			<option value="${singleStu.politics}">${singleStu.politics}</option>
			<option value="共青团员">共青团员</option>
			<option value="中共党员">中共党员</option>
			<option value="群众">群众</option>
		</select><br />
		<button value="${singleStu.id}" name="delete">删除</button>
		<button value="${singleStu.id}" name="update">更新</button>
		<button value="${singleStu.id}" name="queryTestTerm">查看此学生参与的测试学期</button>
	</form>
	</div>
	<!-- 显示测试学期 -->
	<div class="section">
	<form action="MstudentServlet" method="post">
		<table>
			<c:forEach items="${queryListTestTerm}" var="testTerm">
				<tr>
					<td>${testTerm.testTermName}&nbsp;</td>
					<td><button value="${testTerm.id}" name="queryTestClass">查看此学生参与的测试班级</button></td>
					<td><input type="hidden" name="queryListTestTermStuNumber"
						value="${queryListTestTermStuNumber}" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</div>
	<div class="section">
	<h3>测试成绩查询 （包含测试班级名称）</h3>
	<table>
	<tr>
	<td>测试学期</td>
	<td>测试班级</td>
	<td>学号</td>
	<td>身高</td>
	<td>体重</td>
	<td>肺活量</td>
	<td>50米</td>
	<td>跳远</td>
	<td>坐位体前屈</td>
	<td>800米</td>
	<td>1000米</td>
	<td>仰卧起坐</td>
	<td>引体向上</td>
	</tr>
	<tr>
	<td>${score.testTermName}</td>
	<td>${score.testClassName}</td>
	<td>${score.stuNumber}</td>
	<td>${score.height}</td>
	<td>${score.weight}</td>
	<td>${score.vitalCapacity}</td>
	<td>${score.fiftyRun}</td>
	<td>${score.jump}</td>
	<td>${score.sitAndReach}</td>
	<td>${score.eightHundredsRun}</td>
	<td>${score.oneThousandRun}</td>
	<td>${score.situp}</td>
	<td>${score.pullup}</td>
	</tr>
	</table>
	</div>
	<div class="section">
	<h3>根据条件查询学生信息</h3>
	<form name="Query" action="MstudentServlet" method="post">
		<input type="hidden" name="queryStu" value="query" /> 学号<input
			type="text" name="queryStuNumber" /><br /> 姓名<input type="text"
			name="queryStuName" /><br />
		<!-- 院系
			<select
			name="queryStuDep">
			<option value=""></option>
			<c:forEach items="${allDep}" var="dep">
				<option value="${dep.id}">${dep.departmentName}</option>
			</c:forEach>
		</select> 
		 -->
		专业<select name="queryStuMaj">
			<option value=""></option>
			<c:forEach items="${allMaj}" var="maj">
				<option value="${maj.id}">${maj.majorClassName}</option>
			</c:forEach>
		</select><br /> 入学年份<select name="queryGradeNow">
			<option value="2011">2011</option>
			<option value="2012">2012</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
		</select><br /> <input type="submit" value="查询" />
	</form>
	</div>
</body>
</html>
