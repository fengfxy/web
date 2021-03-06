<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<nav class="navbar navbar-default my-nav">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="">我的网站</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">首页</a></li>
				<li><a href="NewsView.jsp">新闻信息</a></li>
				
			</ul>
			<form class="navbar-form navbar-left" action="<%=basePath%>NewsQueryServlet.action" method="post">
				<div class="form-group">
					<input type="text" name="newsInfo" class="form-control" placeholder="请输入关键字">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
			
			
			<ul class="nav navbar-nav navbar-right">

				<c:choose>

					<c:when test="${sessionScope.Users==null }">
						<li><a href="login.jsp">登录</a></li>
						<li><a href="register.jsp">注册</a></li>
					</c:when>
					<c:when test="${sessionScope.Users!=null }">
						<li><img src="<%=basePath%>userPhoto/${Users.photo}" width="50px" height="50px" class="img-circle"></li>
						<li> <a>欢迎：${sessionScope.Users.username }</a> </li>
						<li><a href="<%=basePath%>UsersInfoEditBeforeServlet.action?id=${Users.id}&reqType=q">管理</a></li>
						<li><a href="<%=basePath %>LogoutServlet.action">注销</a></li>

					</c:when>
				</c:choose>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
