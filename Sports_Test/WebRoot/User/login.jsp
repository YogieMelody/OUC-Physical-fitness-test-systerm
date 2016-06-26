<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>中国海洋大学体质测试预约管理系统</title>

<meta http-equiv="X-UA-Compatible" content="IE=11/10/9/8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath%>User/css/css.css">
</head>
<script language="JavaScript">
	function refresh() {
		document.getElementById("Captcha").src = "Captcha?now="
				+ new Date().getTime();//使用时间作为参数避免浏览器从缓存取图片  
	}
	function validate(f) {
		if (!(/^\w{5,15}$/.test(f.stuNumber.value))) {
			alert("用户ID必须是5-15位");
			f.stuNumber.focus();
			return false;
		}
		if (!(/^\w{5,15}$/.test(f.stuPassword.value))) {
			alert("密码必须是5-15位");
			f.stuPassword.focus();
			return false;
		}
		return ture;
	}
</script>
<body>
<body class="background">
	<div>
		<div class="loginlogo"></div>
		<img src="<%=basePath%>User/img/b1.jpg" height="255" width="460">
		<div class="login">
			<h2>系统登录</h2>
			<form action="LoginServlet" method="post"
				onSubmit="return validate(this)">
				<span>学/工号：</span><input class="text" type="text" placeholder="学/工号" title="请输入学/工号" maxlength="15" name="number">
				<span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input class="text" type="password" placeholder="密码" title="请输入密码" name="password">
				<div style="position:relative;">
				<span>&nbsp;验证码：</span><input class="check" type="text" name="vercode" title="验证码">
				<img alt="" src="Captcha" id="Captcha" style="border:none;width:80px;height:20px;margin-left:130px;">
				</div>
				
				<a class="refresh" href="javascript:refresh()"> <span style="font-size:12px">刷新验证码</span></a>
				<input type="radio" class="choose" name="choose" value="学生" checked>学生
				<input type="radio" class="choose" name="choose" value="教师">教师
				<input type="radio" class="choose" name="choose" value="管理员">管理员
				<button>登录</button>
			</form>
		</div>
		<div class="footer">
			<span>版权所有：中国海洋大学体育系 © 2015-2016</span> <span>地址：青岛市崂山区松岭路238号</span>
		</div>
	</div>
</body>
</html>
