<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入css样式的时候引入绝对路径 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/addC.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {

	$("#bubmitBtn")
				.click(
						function() {
							//检查姓名: 姓名只能输入中文且不能为空
							//alert("--");
							var $nameEle = $("#name");
							var reg = /^[\u4e00-\u9fa5]{1,5}$/;
							if (!reg.test($nameEle.val())) {
								alert("姓名只能输入中文且不能为空,最少1个汉字最多5个汉字");
								$nameEle[0].focus();
								return false;
							}

							//检查密码: 长度为6-12位, 只能输入数字和英文
							var $passwordEle = $("#password1");
							reg = /^[a-zA-Z0-9]{6,12}$/;
							if (!reg.test($passwordEle.val())) {
								alert("密码的长度为6-12位, 只能输入数字和英文");
								$passwordEle[0].focus();
								return false;
							}

							//确认密码必须与密码相同
							var $password2Ele = $("#password2");
							if ($password2Ele.val() != $passwordEle.val()) {
								$password2Ele[0].focus();
								alert("确认密码必须与密码相同");
								return false;
							}

							//邮箱格式必须正确, 可以不输入
							var $emailEle = $("#email");
							reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
							if ($emailEle.val() != ""
									&& !reg.test($emailEle.val())) {
								$emailEle[0].focus();
								alert("邮箱格式不正确!");
								return false;
							}

							//手机号码必须为11位
							var $phoneEle = $("#phone");
							reg = /^(13|14|15|18|17)[0-9]{9}$/;
							if (!reg.test($phoneEle.val())) {
								$phoneEle[0].focus();
								alert("手机格式不正确!");
								return false;
							}

							return true;
						});

		// 检查重复名
		$("#name")
				.blur(
						function() {
							var reg = /^[\u4e00-\u9fa5]{1,5}$/;
							if (!reg.test(this.value)) {
								this.focus();
								$("#result").html("");
								return false;
							} else {
								var url = "${pageContext.request.contextPath}/AddCustomerServlet";
								$
										.post(
												url,
												{
													method : "checkNameExist",
													customerName : this.value
												},
												function(data) {
													if (data == "true") {
														$("#result")
																.html(
																		"&nbsp;<font color='red'>此姓名已存在</font>");
													} else {
														$("#result")
																.html(
																		"&nbsp;<font color='blue'>姓名未入数据库,可用</font>");
													}
												});
							}
						});

		//检查重复手机号码
		$("#phone")
				.blur(
						function() {
							var reg = /^(13|14|15|18|17)[0-9]{9}$/;
							if (!reg.test(this.value)) {
								this.focus();
								$("#result1").html("");
								return false;
							} else {
								var url = "${pageContext.request.contextPath}/AddCustomerServlet";
								$
										.post(
												url,
												{
													method : "checkPhoneExist",
													customerPhone : this.value
												},
												function(data) {
													if (data == "true") {
														("#result1")
																.html("&nbsp;<font color='red'>此手机已存在</font>");
													} else {
														$("#result1")
																.html(
																		"&nbsp;<font color='blue'>此手机可用</font>");
													}
												});
							}
						});

	});
</script>
</head>
<body>
	<form method="post"
		action="<%=request.getContextPath()%>/AddCustomerServlet?method=addCustomer">
		<fieldset>
			<legend>客户信息</legend>
			<table align="center">
				<tr>
					<td>姓名(*):</td>
					<td><input type="text" name="CName" id="name"><span
						id="result"></span></td>
				</tr>
				<tr>
					<td>密码(*):</td>
					<td><input type="password" name="CPassword1" id="password1"></td>
				</tr>
				<tr>
					<td>再次确认密码(*):</td>
					<td><input type="password" name="CPassword2" id="password2"></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><input type="radio" name="CGender" value="1">男&nbsp;
						<input type="radio" name="CGender" value="0">女</td>
				</tr>
				<tr>
					<td>出生日期:</td>
					<td><input type="text" name="CBirth" id="birth"></td>
				</tr>
				<tr>
					<td>专业:</td>
					<td><select name="CMajority" id="select2">
							<option value="其他">其他(默认)</option>
							<option value="软件工程">软件工程</option>
							<option value="计算机与科学">计算机与科学</option>
							<option value="网络工程">网络工程</option>
							<option value="电子信息">电子信息</option>
							<option value="建筑学">建筑学</option>
							<option value="小学教育">小学教育</option>
							<option value="历史师范">历史师范</option>
							<option value="会计学">会计学</option>
							<option value="财务管理">财务管理</option>
							<option value="工商管理">工商管理</option>
					</select></td>
				</tr>
				<tr>
					<td>兴趣爱好:</td>
					<td><input type="checkbox" name="CInterest1" value="篮球">篮球&nbsp;
						<input type="checkbox" name="CInterest2" value="足球">足球&nbsp;
						<input type="checkbox" name="CInterest3" value="游泳">游泳&nbsp;
						<input type="checkbox" name="CInterest4" value="羽毛球">羽毛球</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="checkbox" name="CInterest5" value="排球">排球&nbsp;
						<input type="checkbox" name="CInterest6" value="台球">台球&nbsp;
						<input type="checkbox" name="CInterest7" value="拳击">拳击&nbsp;
						<input type="checkbox" name="CInterest8" value="其他" checked>其他&nbsp;
					</td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input type="text" name="CEmail" id="email"></td>
				</tr>
				<tr>
					<td>联系方式(*):</td>
					<td><input type="text" name="CPhone" id="phone"><span
						id="result1"></span></td>
				</tr>
				<tr align="center">
					<td colspan="2"><br> <br> <input type="submit"
						name="button" id="bubmitBtn" class="but" value="提交"></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>