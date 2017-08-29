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
					<form class="form-horizontal" id="from1" action="<%=basePath %>servlet/UsersRegisterServlet" method="post">
						<div class="form-group">
							<label for="inputText1" class="col-xs-3 control-label">用户名:</label>
							<div class="col-xs-5">
								<input type="text" name="userName" class="form-control" id="inputText1" placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword1" class="col-xs-3 control-label">密码:</label>
							<div class="col-xs-5">
								<input type="password" name="userPassWord" class="form-control" id="inputPassword1" placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputText2" class="col-xs-3 control-label">确认密码:</label>
							<div class="col-xs-5">
								<input type="password" class="form-control" id="inputText2" placeholder="请重新输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputText3" class="col-xs-3 control-label">邮箱:</label>
							<div class="col-xs-5">
								<input type="email" class="form-control" id="inputText3" placeholder="请输入邮箱账号">
							</div>
						</div>
						<div class="form-group">
							<label for="inputText4" class="col-xs-3 control-label">验证码:</label>
							<div class="col-xs-5">
								<input type="text" name="Kaptcha" class="form-control" id="inputText4" placeholder="请输入图片上的验证码">
							</div>
							<div class="col-xs-4"><img id="Kaptcha" src="<%=basePath %>Kaptcha.jpg"></div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-3 col-xs-10">
								<div class="row">
									<div class="col-xs-3">
										<button type="submit" class="btn btn-default">注册</button>
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
	window.onload = function() {
		var form = document.getElementById('from1');
		form.onsubmit = function() {

			for (var i = 1; i < 5; i++) {
				var str = $('#inputText' + i + '').val();
				if (str != "") {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	$(function() {
		$('#Kaptcha').click(function() {
			$(this).attr('src', '<%=basePath%>Kaptcha.jpg?' + Math.floor(Math.random() * 100));
		});

//		$('.form-control').blur(function() {
//			var str = $(this).val();
//			if (str == "") {
//				$('#mess').text(this + '不能为空');
//			} else {
//				$('#mess').text('欢迎注册');
//			}
//		});

		//		$('#inputText1').blur(function() {
		//			var str = $(this).val();
		//			if(str==""){
		//				$('#mess').text('用户名不能为空');
		//			}else{
		//				$('#mess').text('欢迎注册');
		//			}
		//		});
		//		$('#inputPassword1').blur(function() {
		//			var str = $(this).val();
		//			if(str==""){
		//				$('#mess').text('密码不能为空');
		//			}else{
		//				$('#mess').text('欢迎注册');
		//			}
		//		});
		//		$('#inputPassword2').blur(function() {
		//			var str = $(this).val();
		//			var str1=$('#inputPassword1').val();
		//			if(str!=str1){
		//				$('#mess').text('两次输入密码不一致');
		//			}else{
		//				$('#mess').text('欢迎注册');
		//			}
		//		});
		//		$('#inputText2').blur(function() {
		//			var str = $(this).val();
		//			if(str==""){
		//				$('#mess').text('邮箱不能为空');
		//			}else{
		//				$('#mess').text('欢迎注册');
		//			}
		//		});
		//		$('#inputText3').blur(function() {
		//			var str = $(this).val();
		//			if(str==""){
		//				$('#mess').text('验证码不能为空');
		//			}else{
		//				$('#mess').text('欢迎注册');
		//			}
		//		});
	});
</script>