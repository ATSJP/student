<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main.css" />
</head>
<body>
	<table align="center" cellpadding="10" cellspacing="10">
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=0"
				target="mainFrame" class="xyz">全部客户信息</a></td>
		</tr>
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/manager/addCustomer.jsp"
				target="mainFrame">增加客户</a></td>
		</tr>
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/manager/modifyCustomer.jsp"
				target="mainFrame">修改客户</a></td>
		</tr>
		<tr>
			<td><a
				href="<%=request.getContextPath()%>/UserServlet?method=logout"
				target="_top">退出系统</a></td>
		</tr>
	</table>
</body>
</html>