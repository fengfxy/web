<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'mess.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="link.jsp"></jsp:include>

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="leftbar.jsp"></jsp:include>
	<div class="main">


		<!--标题-->
		<div class="page-header" style="margin-top: 0px!important;">
			<h1>
				消息界面 <small>操作消息</small>
			</h1>
		</div>

		<div class="row">

			<c:choose>

				<c:when test="${sessionScope.mess.state==1 }">

					<div class=" col-xs-offset-3 col-xs-6 alert alert-success">
						<h2 class="text-center">${sessionScope.mess.type}</h2>
						<h3 class="text-center">${sessionScope.mess.mess}</h3>
					</div>

				</c:when>
				<c:when test="${sessionScope.mess.state==2 }">
					<div class=" col-xs-offset-3 col-xs-6 alert alert-danger">
						<h2 class="text-center">${sessionScope.mess.type}</h2>
						<h3 class="text-center">${sessionScope.mess.mess}</h3>
					</div>

				</c:when>
				<c:when test="${sessionScope.mess.state==3 }">

					<div class=" col-xs-offset-3 col-xs-6 alert alert-warning">
						<h2 class="text-center">${sessionScope.mess.type}</h2>
						<h3 class="text-center">${sessionScope.mess.mess}</h3>
					</div>
				</c:when>
			</c:choose>


		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
