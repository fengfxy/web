<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
					<form class="form-horizontal" action="<%=basePath %>form/LoginServlet.action" method="post">
						<div class="form-group">
							<label for="inputText1" class="col-xs-3 control-label">账号:</label>
							<div class="col-xs-5">
								<input type="text" name="username" class="form-control" id="inputText1" placeholder="请输入账号">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword1" class="col-xs-3 control-label">密码:</label>
							<div class="col-xs-5">
								<input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-3 col-xs-9">
								<div class="checkbox">
									<label><input type="checkbox">记住我</label>
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
										<a href="<%=basePath %>ForgetUserPassWord.jsp" class="btn btn-default">忘记密码</a>
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