<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>用户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="link.jsp"></jsp:include>

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="centent login-wrap">
		<jsp:include page="sidebar.jsp"></jsp:include>

		<div class="panel panel-primary login">
			<div class="panel-heading" align="center">
				<h3>基本信息</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal"
					action="<%=basePath%>serlvet/UsersInfoEditServlet.action" method="post">
					<div class="form-group">
					<input name="id"  value="${Users.id}" type='hidden' />
						<label for="inputText1" class="col-xs-3 control-label">用户名:</label>
						<div class="col-xs-5">
							<label name="username" class="form-control" id="inputText1">${sessionScope.Users.username }</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputText1" class="col-xs-3 control-label">昵称:</label>
						<div class="col-xs-5">
							<input type="text" name="nick" class="form-control"
								id="inputText1" value="${sessionScope.Users.nick }"
								placeholder="请输入昵称">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword1" class="col-xs-3 control-label">邮箱:</label>
						<div class="col-xs-5">
							<label name="email" class="form-control" id="inputPassword3">${sessionScope.Users.email }</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-offset-3 col-xs-10">
							<div class="row">
								<div class="col-xs-3">
									<button type="submit" class="btn btn-default">保存</button>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>
			<div id="mess" class="panel-footer">FXY信息系统——用户信息界面</div>
		</div>

	</div>
<jsp:include page="footer.jsp"></jsp:include>
	
</body>

</html>