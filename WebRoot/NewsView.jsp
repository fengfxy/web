<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>新闻信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="link.jsp"></jsp:include>
</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="shuffling.jsp"></jsp:include>


	<div class="centent">

		<div class="row">
			<c:forEach var="item" items="${sessionScope.viewNews }">
				<div class="col-xs-3">
					<div class="thumbnail">
						<img src="<%=basePath%>admin/upload/${item.photoB}" alt="网络似乎有点问题" style="height: 200px; width: 100%; display: block;">
						<div class="caption">
							<h6>${item.newsType.name } || ${item.st }</h6>
							<h3>${item.title }</h3>
							<p>${item.startInfo }</p>
							<p>
								<a href="<%=basePath%>NewsInfoViewServlet.action?id=${item.id }" class="btn btn-primary" role="button">查看详情</a>
							</p>
						</div>
					</div>
					
				</div><!-- 每个模块结束 -->
			</c:forEach>
		</div><!-- 每行结束 -->

	</div><!-- centent结束 -->

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
