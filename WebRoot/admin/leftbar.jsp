<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--左侧导航-->
<div class="sidebar">
    <ul class="nav nav-sidebar">
        <li ${sessionScope.menu==1?"class='active'":"" }><a href="<%=basePath%>admin/newsTypeSelectServlet.action">新闻类型列表</a></li>
    </ul>

	 <ul class="nav nav-sidebar">
        <li ${sessionScope.menu==2?"class='active'":"" }><a href="<%=basePath%>admin/NewsSelectServlet.action">新闻列表</a></li>
    </ul>
    
    <ul class="nav nav-sidebar">
        <li ${sessionScope.menu==3?"class='active'":"" }><a href="#">用户提问列表</a></li>
    </ul>


    <ul class="nav nav-sidebar">
        <li ${sessionScope.menu==4?"class='active'":"" }><a href="<%=basePath%>admin/UsersSelectServlet.action">用户列表</a></li>
    </ul>


    <ul class="nav nav-sidebar">
        <li ${sessionScope.menu==5?"class='active'":"" }><a href="<%=basePath%>admin/AdminPassWordEdit.jsp">管理员密码修改</a></li>
    </ul>

	<ul class="nav nav-sidebar">
       <li ${sessionScope.menu==6?"class='active'":"" }><a href="<%=basePath%>admin/adminOut.action">管理员退出</a></li>
    </ul>
</div>

