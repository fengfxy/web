<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="sidebar">

	<ul class="nav nav-sidebar-top">

		<div class="list-group">
			<img src="./img/sess.png" alt="..." class="img-circle">
			<h3 align="center">用户名</h3>
		</div>
	</ul>

	<ul class="nav nav-sidebar">
		<li ${sessionScope.menu==1? "class='active'": "" }><a
			href="<%=basePath%>user.jsp">基本信息</a></li>
	</ul>

	<ul class="nav nav-sidebar">
		<li ${sessionScope.menu==2? "class='active'": "" }><a
			href="<%=basePath%>userPhoto.jsp">设置头像</a></li>
	</ul>

	<ul class="nav nav-sidebar">
		<li ${sessionScope.menu==3? "class='active'": "" }><a
			href="<%=basePath%>userPassWord.jsp">修改密码</a></li>
	</ul>
</div>

