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
<title>用户登录</title>
<jsp:include page="link.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>


	<div class="centent login-wrap">

		<div class="panel panel-primary login">
			<div class="panel-heading" align="center">用户登录</div>
			<div class="panel-body">
				<form class="form-horizontal"
					action="<%=basePath%>form/LoginServlet.action" method="post">
					<div class="form-group">
						<label for="inputText1" class="col-xs-3 control-label">账号:</label>
						<div class="col-xs-5">
							<input type="text" name="username" class="form-control"
								id="inputText1" placeholder="请输入账号">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword1" class="col-xs-3 control-label">密码:</label>
						<div class="col-xs-5">
							<input type="password" name="password" class="form-control"
								id="inputPassword3" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-3 col-xs-9">
							<div class="checkbox">
								<label for="remember-me"> <input name="aaaa"
									type="checkbox" onclick="remember();"> 记住我
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-3 col-xs-10">
							<div class="row">
								<div class="col-xs-3">
									<button type="submit" class="btn btn-default">登录</button>
								</div>
								<div class="col-xs-3">
									<a href="register.jsp" class="btn btn-default">注册</a>
								</div>
								<div class="col-xs-3">
									<a href="<%=basePath%>ForgetUserPassWord.jsp"
										class="btn btn-default">忘记密码</a>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
	$(document).ready(function() {
		//记住密码功能
		var str = getCookie("loginInfo");
		str = str.substring(1, str.length - 1);
		var username = str.split(",")[0];
		var password = str.split(",")[1];
		//自动填充用户名和密码
		$("#inputText1").val(username);
		$("#inputPassword3").val(password);
	});

	//获取cookie
	function getCookie(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') c = c.substring(1);
			if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
		}
		return "";
	}

	//记住密码功能
	function remember() {
		var remFlag = $("input[type='checkbox']").is(':checked');
		if (remFlag == true) { //如果选中设置remFlag为1
			//cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题.
			var conFlag = confirm("记录密码功能不宜在公共场所(如网吧等)使用,以防密码泄露.您确定要使用此功能吗?");
			if (conFlag) { //确认标志
				$("#remFlag").val("1");
			} else {
				$("input[type='checkbox']").removeAttr('checked');
				$("#remFlag").val("");
			}
		} else { //如果没选中设置remFlag为""
			$("#remFlag").val("");
		}
	}
</script>