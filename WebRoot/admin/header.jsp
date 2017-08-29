<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--头部导航-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">后台管理系统V1.0</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a>当前账户：${sessionScope.Admin.username } </a></li>
                <li><a class="a-time">-------年---月---日----:---:----星期-----</a></li>
                <li>
                    <button type="button" onclick="adminout()" class="btn btn-default navbar-btn">退出登录</button>
                </li>


            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<script>
function adminout(){
	window.location.href="<%=basePath%>admin/adminOut.action";

}

</script>