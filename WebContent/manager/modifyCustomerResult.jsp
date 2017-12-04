<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/modifyC.css" />
</head>
<body>
	<form action="<%=request.getContextPath()%>/GetCustomerServlet">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户信息修改结果</font>
			</legend>
			<table>
				<tr>
					<td><font color="#FFFFFF">${requestScope.message}</font></td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="encoding" value="ISO-8859-1">
						<input type="hidden" name="cindex" value="${ResultCName}">
						<input type="submit" value="返回查看结果" class="but1">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>