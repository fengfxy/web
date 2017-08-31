<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>密码找回</title>
<jsp:include page="link.jsp"></jsp:include>
</head>
<body>
 <jsp:include page="header.jsp"></jsp:include>
 
 
		<div class="centent login-wrap">

			<div class="panel panel-primary login">
				<div class="panel-heading" align="center">密码重置</div>
				<div class="panel-body">
					<form class="form-horizontal" action="<%=basePath %>/servlet/ResetUserPassWordServlet.action" method="post">
						<div class="form-group">
							<label for="inputText1" class="col-xs-3 control-label">用户名:</label>
							<div class="col-xs-5">
								<input type="text" name="username" class="form-control" id="inputText1" placeholder="请输入账号">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword1" class="col-xs-3 control-label">注册的邮箱:</label>
							<div class="col-xs-5">
								<input type="email" name="userEmail" class="form-control"
								id="inputText2" placeholder="请输入邮箱账号">
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-xs-offset-3 col-xs-10">
								<div class="row">
									<div class="col-xs-3">
										<button type="submit" class="btn btn-default">找回</button>
									</div>
									<div class="col-xs-3">
										<a href="register.jsp" class="btn btn-default">注册</a>
									</div>
									<div class="col-xs-3">
										<a href="login.jsp" class="btn btn-default">返回登录</a>
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