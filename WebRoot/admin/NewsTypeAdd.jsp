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
<base href="<%=basePath%>">

<title>My JSP 'NewsTypeAdd.jsp' starting page</title>

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
				新闻类型数据添加 <small>新闻类型的操作界面</small>
			</h1>
		</div>



		<!--添加form-->
		<div style="margin-bottom: 10px">

			<form action="<%=basePath%>admin/newsTypeAddServlet.action"
				method="post" class="form-horizontal" style="width: 800px">

				<div class="form-group">
					<label class="col-xs-2 control-label">名称：</label>
					<div class="col-xs-6">
						<input type="text" name="name" class="form-control"
							placeholder="请输入名称">
					</div>
				</div>

				<div class="form-group">
					<div class="col-xs-offset-2 col-xs-10">
						<button type="submit" class="btn btn-default">保存</button>
					</div>
				</div>
			</form>

		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
