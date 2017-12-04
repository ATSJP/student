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
	href="<%=request.getContextPath()%>/css/modifyC.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#buttonbin").click(function() {
			//检查输入信息是否不为空钩
			//alet("--");
			var $cindexEle = $("#index");
			var reg = /\s/;
			if (!reg.test($cindexEle)) {
				alert("输入信息为空，请重新输入");
				//$cindexEle[0].focus();
				return false;
			}
			return true;
		});

		$("#buttonbin1")
				.click(
						function() {
							//检查用户输入修改信息格式是否正确
							//alert("--");
							//检查姓名只能输入中文且不能为空,最少1个汉字最多5个汉字,必须输入
							var $nameEle = $("#name");
							var reg = /^[\u4e00-\u9fa5]{1,5}$/;
							if (!reg.test($nameEle.val())) {
								alert("姓名只能输入中文且不能为空,最少1个汉字最多5个汉字");
								$nameEle[0].focus();
								return false;
							}

							//检查密码: 长度为6-12位, 只能输入数字和英文,可以不输入
							var $passwordEle = $("#password");
							reg = /^[a-zA-Z0-9]{6,12}$/;
							if ($passwordEle.val() != ""
									&& !reg.test($passwordEle.val())) {
								alert("密码的长度为6-12位, 只能输入数字和英文");
								$passwordEle[0].focus();
								return false;
							}

							//检查性别，是否输入正确,可以不输入
							var $genderEle = $("#gender");
							if ($genderEle.val() != "") {
								if ($genderEle.val() != "男"
										&& $genderEle.val() != "女") {
									alert("请输入正确的性别");
									$genderEle[0].focus();
									return false;
								}
							}

							/*
							 //检查birth，是否输入正确,可以不输入
							 var $birthEle = $("#birth");
							 reg = /^\d{4}(-)\d{2}(-)\d{2}$/;
							 if ($birthEle.val() != "" && !reg.test($birthEle)) {
							 alert("请输入正确的出生日期（例：1988-05-20）");
							 $birthEle.focus();
							 return false;
							 }*/

							//邮箱格式必须正确, 可以不输入
							var $emailEle = $("#email");
							reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
							if ($emailEle.val() != ""
									&& !reg.test($emailEle.val())) {
								$emailEle[0].focus();
								alert("邮箱格式不正确!");
								return false;
							}

							//手机号码必须为11位, 必须输入
							var $phoneEle = $("#phone");
							reg = /^(13|14|15|18|17)[0-9]{9}$/;
							if (!reg.test($phoneEle.val())) {
								$phoneEle[0].focus();
								alert("不能为空，手机格式不正确!");
								return false;
							}

							return true;
						});

	});
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/GetCustomerServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户信息查找栏</font>
			</legend>
			<table>
				<tr>
					<td><input type="text" class="input"
						placeholder="请输入你要修改的客户姓名或者手机号码" id="index" name="cindex">
						<input type="submit" class="but" id="buttonbin" value="搜索"></td>
				</tr>
			</table>
		</fieldset>
	</form>
	<br>
	<br>
	<fieldset>
		<legend>
			<font color="#FFFFFF">客户信息显示栏</font>
		</legend>
		<c:choose>
			<c:when test="${empty Customer}">
				<font color="#FFFFFF">您输入的是:${requestScope.result}<br> <br>无此用户信息，请检查您是否已搜索或客户姓名填写是否正确。
				</font>
			</c:when>
			<c:when test="${!empty Customer}">
				<table class="bordered" bgcolor="#FFFFFF">
					<tr>
						<td width="50px">Name(*)</td>
						<td width="105px">Password</td>
						<td width="10px">Gender</td>
						<td width="85px">Birth</td>
						<td width="100px">Majority</td>
						<td>Interest</td>
						<td width="200px">Email</td>
						<td width="100px">Phone(*)</td>
					</tr>
					<tr>
						<td width="50px">${Customer.cname}</td>
						<td width="105px">${Customer.cpassword}</td>
						<td width="10px">${Customer.cgender}</td>
						<td width="85px">${Customer.cbirth}</td>
						<td width="100px">${Customer.cmajority}</td>
						<td>${Customer.cinterest}</td>
						<td width="200px">${Customer.cemail}</td>
						<td width="100px">${Customer.cphone}</td>
					</tr>
				</table>
			</c:when>
		</c:choose>

	</fieldset>
	<br>
	<br>
	<form action="<%=request.getContextPath()%>/ModifyCustomerServlet"
		method="post">
		<fieldset>
			<legend>
				<font color="#FFFFFF">客户信息修改栏</font>
			</legend>
			<c:choose>
				<c:when test="${empty Customer}">
					<font color="#FFFFFF">请先进行查询操作。</font>
				</c:when>
				<c:when test="${!empty Customer}">
					<table class="bordered" bgcolor="#FFFFFF">
						<tr>
							<td>Name(*)</td>
							<td>Password</td>
							<td>Gender</td>
							<td>Birth</td>
							<td>Majority</td>
							<td>Interest(use ";" to split)</td>
							<td>Email</td>
							<td>Phone(*)</td>
						</tr>
						<tr>
							<td width="50px"><input type="text" class="input1"
								name="CName" id="name" value="${Customer.cname}"></td>
							<td width="105px"><input type="text" class="input1"
								name="CPassword" id="password" value="${Customer.cpassword}"></td>
							<td width="10px"><input type="text" class="input1"
								name="CGender" id="gender" value="${Customer.cgender}"></td>
							<td width="85px"><input type="text" class="input1"
								name="CBirth" id="birth" value="${Customer.cbirth}"></td>
							<td width="100px"><input type="text" class="input1"
								name="CMajority" id="majority" value="${Customer.cmajority}"></td>
							<td><input type="text" class="input1" name="CInterest"
								id="cinterest" value="${Customer.cinterest}"></td>
							<td width="200px"><input type="text" class="input1"
								name="CEmail" id="email" value="${Customer.cemail}"></td>
							<td width="100px"><input type="text" class="input1"
								name="CPhone" id="phone" value="${Customer.cphone}"></td>
						</tr>
					</table>
					<br>
					<font color="#FFFFFF">Tips：直接在上方表格中直接输入对应的修改内容，姓名一定要有，其他信息无需修改的可以不输入。</font>
					<table>
						<tr>
							<td><input type="hidden" id="id" name="Id"
								value="${Customer.id}"> <input type="submit"
								id="buttonbin1" class="but1" value="提交"></td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</fieldset>
	</form>
</body>
</html>