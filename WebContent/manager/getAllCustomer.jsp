<%@page import="com.atsjp.webDemo.servlet.GetPageCustomerServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/getAllC.css" />
</head>
<body>
	<font color="#FFF">总记录：&nbsp;${requestScope.totalCount}</font>&nbsp;&nbsp;
	<font color="#FFF">总页数：&nbsp;${requestScope.lastPage}</font>
	<c:if test="${empty CustomerList}">
		<c:if test="${!empty deleteResult}">
			<font color="#FFF">&nbsp;&nbsp;&nbsp;${requestScope.deleteResult}</font>&nbsp;&nbsp;&nbsp;<br>
			<a
				href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=first"><font
				color="#FFF" class="de">返回继续查看</font></a>
		</c:if>
		<c:if test="${empty deleteResult}">
			<center>
				<font color="#FFF">信息记录为空，请添加数据后在查看！</font>
			</center>
		</c:if>
	</c:if>
	<c:if test="${!empty CustomerList}">
		<br>
		<br>
		<br>
		<table class="bordered" bgcolor="#FFFFFF">
			<tr>
				<td>Name</td>
				<td>Password</td>
				<td>Gender</td>
				<td>Birth</td>
				<td>Majority</td>
				<td>Interest</td>
				<td>Email</td>
				<td>Phone</td>
				<td>Delete</td>
			</tr>
			<c:forEach items="${CustomerList}" var="item">
				<tr>
					<td>${item.cname}</td>
					<td>${item.cpassword}</td>
					<td>${item.cgender}</td>
					<td>${item.cbirth}</td>
					<td>${item.cmajority}</td>
					<td>${item.cinterest}</td>
					<td>${item.cemail}</td>
					<td>${item.cphone}</td>
					<td><a
						href="<%=request.getContextPath()%>/DeleteCustomerServlet?cname=${item.cname}"
						class="de">删除</a></td>
					<!-- 注意url传值中文乱码 -->
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<a
				href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=first">首页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=up">上一页</a>
			<c:if test="${requestScope.beginPage>1}">
				<a href="#">...</a>
			</c:if>
			<c:forEach begin="${requestScope.beginPage}"
				end="${requestScope.endPage}" var="num">
				<c:choose>
					<c:when test="${num==requestScope.currentPage}">
						<a
							href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=${num}"
							class="de">${num}</a>
					</c:when>
					<c:otherwise>
						<a
							href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=${num}">${num}</a>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${endPage < lastPage}">
				<a href="#">...</a>
			</c:if>
			<a
				href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=down">下一页</a>
			<a
				href="<%=request.getContextPath()%>/GetPageCustomerServlet?page=last">末页</a>
		</div>
	</c:if>
</body>
</html>