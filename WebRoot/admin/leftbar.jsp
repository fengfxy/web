<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--左侧导航-->
<div class="sidebar">
    <ul class="nav nav-sidebar">
        <li class="active"><a href="<%=basePath%>/admin/newsTypeSelectServlet.action">新闻类型列表</a></li>
        <li><a href="#">新闻列表</a></li>
    </ul>


    <ul class="nav nav-sidebar">
        <li><a href="#">用户提问列表</a></li>
    </ul>


    <ul class="nav nav-sidebar">
        <li><a href="#">用户列表</a></li>
    </ul>


    <ul class="nav nav-sidebar">
        <li><a href="#">管理员密码修改</a></li>
    </ul>

	<ul class="nav nav-sidebar">
       <li><a href="#">管理员退出</a></li>
    </ul>
</div>

