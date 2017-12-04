<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞书客户信息管理系统</title>

<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/login.css" />
<!-- 给页面添加ico -->
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<script>
	function check() { // 对用户名和密码文本框进行不为空的校验函数
		with (document.login) { // 为下面语句块中的变量指明所属对象
			//相当于var user = doucument.loginMain.loginName.value;
			var user = username.value;
			var pass = password.value;
			if (user == null || user == "") {
				alert("请填写用户名");
			} else if (pass == null || pass == "") {
				alert("请填写密码");
			} else {
				submit();
			}
		}
	}
</script>
<!-- 
	document.getElementById("login2").onclick = function() {
	alert("click login2222!");
	}; 
-->
</head>

<body>
	<div id="login">
		<h1>Login</h1>
		<form action="<%=request.getContextPath()%>/UserServlet?method=login"
			method="post" name="login">
			<input type="text" name="username" placeholder="用户名" name="username"></input>
			<input type="password" name="password" placeholder="密码"
				name="password"></input>
			<button class="but" type="button" onclick="check()">登录</button>
			<!-- <p>忘记密码？>>>&nbsp;&nbsp;&nbsp;&nbsp;新用户注册 >>></p> -->
			<br> <font color="FF0000">${requestScope.message}</font>
		</form>
	</div>
</body>
</html>