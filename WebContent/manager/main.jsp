<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.atsjp.webDemo.entity.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sessionScope.user.name}的飞书管理中心</title>
<!-- 给页面添加ico -->
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/login.css" />
</head>
<%--
	HttpSession s = request.getSession();
	User user = (User) s.getAttribute("user");
	System.out.println(user.toString());
--%>
<frameset rows="185,*" cols="*" frameborder="1" border="2"
	framespacing="5">
	<frame src="<%=request.getContextPath()%>/manager/top.jsp"
		name="topFrame" scrolling="no" noresize="noresize">

	<frameset rows="*" cols="250,*" frameborder="1" border="2">
		<frame src="<%=request.getContextPath()%>/manager/left.jsp"
			name="leftFrame" scrolling="no" noresize="noresize">
		<frame src="<%=request.getContextPath()%>/manager/right.jsp"
			name="mainFrame">
	</frameset>
</frameset>
<noframes></noframes>
</html>