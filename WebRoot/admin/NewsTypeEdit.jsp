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

<title>My JSP 'NewsTypeDelete.jsp' starting page</title>

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
		<!--修改form-->
		<div style="margin-bottom: 10px">

			<form action="<%=basePath%>admin/NewsTypeEditServlet.action"
				method="post" class="form-horizontal" style="width: 800px">

				<div class="form-group">
					<label class="col-xs-2 control-label">ID：</label>
					<div class="col-xs-6">
						<input type="text" class="form-control " value="${newsType.id }"
							disabled> <input type="hidden" class="form-control "
							name="newsTypeId" value="${newsType.id }">
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label">名称：</label>
					<div class="col-xs-6">
						<input type="text" class="form-control" value="${newsType.name }"
							name="newsTypeName" placeholder="请输入名称">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-xs-10">
						<button type="submit" class="btn btn-default">保存</button>
					</div>
				</div>
			</form>
		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
