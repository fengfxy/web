<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="sidebar">

	<ul class="nav nav-sidebar-top">

		<div class="list-group">
			<img src="<%=basePath%>userPhoto/${Users.photo}" width="60px" height="60px" class="img-circle">
			<h3 align="center">${sessionScope.Users.username }</h3>
		</div>
	</ul>

	<ul class="nav nav-sidebar">
		<li ${sessionScope.menu==11? "class='active'": "" }><a
			href="<%=basePath%>UsersInfoEditBeforeServlet.action?id=${Users.id}&reqType=q">基本信息</a></li>
	</ul>

	<ul class="nav nav-sidebar">
		<li ${sessionScope.menu==12? "class='active'": "" }><a
			href="<%=basePath%>UsersInfoEditBeforeServlet.action?id=${Users.id}&reqType=w">设置头像</a></li>
	</ul>

	<ul class="nav nav-sidebar">
		<li ${sessionScope.menu==13? "class='active'": "" }><a
			href="<%=basePath%>UsersInfoEditBeforeServlet.action?id=${Users.id}&reqType=r">修改密码</a></li>
	</ul>
</div>

