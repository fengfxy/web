<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<title>Title</title>
		<jsp:include page="link.jsp"></jsp:include>
	</head>

	<body>
		<jsp:include page="header.jsp"></jsp:include>

		<div class="centent login-wrap">

			<div class="panel panel-primary login">
				<div class="panel-heading" align="center">用户注册</div>
				<div class="panel-body">
					<form class="form-horizontal" id="from1" action="<%=basePath%>servlet/UsersRegisterServlet" method="post">
						<div class="form-group">
							<label for="inputText1" class="col-xs-3 control-label">用户名:</label>
							<div class="col-xs-5">
								<input type="text" name="userName" class="form-control" id="inputText1" placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword1" class="col-xs-3 control-label">密码:</label>
							<div class="col-xs-5">
								<input type="password" class="form-control" id="inputPassword1" placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword2" class="col-xs-3 control-label">确认密码:</label>
							<div class="col-xs-5">
								<input type="password" name="userPassWord" class="form-control" id="inputPassword2" placeholder="请重新输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputText2" class="col-xs-3 control-label">邮箱:</label>
							<div class="col-xs-5">
								<input type="email" name="userEmail" class="form-control" id="inputText2" placeholder="请输入邮箱账号">
							</div>
						</div>
						<div class="form-group">
							<label for="inputText3" class="col-xs-3 control-label">验证码:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control" id="inputText3" placeholder="请输入图片上的验证码">
							</div>
							<div class="col-xs-4">
								<img id="Kaptcha" src="<%=basePath%>Kaptcha.jpg">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-3 col-xs-10">
								<div class="row">
									<div class="col-xs-3">
										<button type="submit" id="sub" onclick="doSubmit()" class="btn btn-default">注册</button>
									</div>
									<div class="col-xs-3">
										<button type="reset" class="btn btn-default">重置</button>
									</div>
									<div class="col-xs-3">
										<a href="login.jsp" class="btn btn-default">返回登录</a>
									</div>
								</div>

							</div>
						</div>
					</form>
				</div>
				<div id="mess" class="panel-footer">欢迎注册FXY信息系统</div>
			</div>

		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>

</html>

<!--脚本区域-->
<!--点击切换验证码-->
<script>
	//提交
	function doSubmit() {
		var verifyCodeValue = $("#inputText3").val();
		if (verifyCodeValue.replace(/\s/g, "") == "") {
			$('#mess').text("请输入验证码");
		} else {
			//提交前先异步检查验证码是否输入正确
			var verifyUrl = "<%=basePath%>servlet/UsersRegisterServlet?Kaptcha=" + verifyCodeValue;
			$.ajax({
				type: "GET",
				url: verifyUrl,
				success: function(returnData) {
					if (returnData != "Y") {
						$('#mess').text("请输入正确的验证码！");
					} else {
						//验证码正确，进行提交操作
						$('#mess').text("欢迎注册");
					}
				},
				error: function(e) {
					$('#mess').text(e);
				}
			});
		}
	}

	$(function() {
		$('#Kaptcha').click(function() {
			$(this).attr('src', '<%=basePath%>Kaptcha.jpg?' + Math.floor(Math.random() * 100));
		});

		$('#inputPassword2').blur(function() {
			var str = $(this).val();
			var str1 = $('#inputPassword1').val();
			if (str != str1) {
				$('#mess').text('两次输入密码不一致');
			} else {
				$('#mess').text('欢迎注册');
			}
		});

		var userName = document.getElementById('inputText1');
		var email = document.getElementById('inputText2');
		var userPassWord = document.getElementById('inputPassword2');
		var kaptcha = document.getElementById('inputText3');
		var form = document.getElementById('from1');
		form.onsubmit = function() {

			if (userName.value == '' || userName.value == null) {
				$('#mess').text('请输入用户名');
				return false;
			} else {

				mess.innerText = '';
			}
			if (userPassWord.value == '' || userPassWord.value == null) {

				$('#mess').text('请输入密码');
				return false;
			} else {
				mess.innerText = '';
			}
			if (email.value == '' || email.value == null) {

				$('#mess').text('请输入邮箱');
				return false;
			} else {
				mess.innerText = '';
			}

			if (kaptcha.value == '' || kaptcha.value == null) {

				$('#mess').text('请输入验证码');
				return false;
			} else {
				mess.innerText = '';
			}
			if ($('#mess').text() == '欢迎注册') {
				return true;
			} else {
				$('#mess').text('请输入密码');
				userPassWord.value = '';
				return false;
			}
		}
	});
</script>