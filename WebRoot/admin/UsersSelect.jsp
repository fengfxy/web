<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:useBean id="dateObject" class="java.util.Date" scope="page"></jsp:useBean>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<jsp:include page="link.jsp"></jsp:include>



<!-- B3日期组件 -->
<link href="<%=basePath%>css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=basePath%>css/bootstrap-datetimepicker-standalone.css"
	rel="stylesheet">
<script src="<%=basePath%>js/moment.min.js"></script>
<script src="<%=basePath%>js/moment-with-locales.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>



</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="leftbar.jsp"></jsp:include>
	<div class="main">
		<!--页头-->
		<div class="page-header" style="margin-top: 0px!important;">
			<h1>
				新闻列表 <small>新闻的操作界面</small>
			</h1>
		</div>

		<!--表格工具栏目-->
		<div style="margin-bottom: 10px">
			<button onclick="add()" class="btn btn-primary" type="button">
				<span class="glyphicon glyphicon-remove"></span>清理垃圾
			</button>
		</div>

		<!--表格-->
		<table class="table table-bordered table-hover">

			<!--表头-->
			<tr class="info">
				<td style="width: 3%">id</td>
				<td style="width: 10%">用户名</td>
				<td style="width: 20%">注册时间</td>
				<td style="width: 7%">账号状态</td>
				<td style="width: 20%">注册邮箱</td>
				<td style="width: 20%">昵称</td>
				<td style="width: 10%">头像</td>
				<td style="width: 10%">操作</td>
			</tr>

			<!--表数据-->
			<c:forEach var="item" items="${users }">
				<tr>
					<td style="vertical-align: middle">${item.id }</td>
					<td style="vertical-align: middle">${item.username }</td>
					<td style="vertical-align: middle">
						<jsp:setProperty property="time" name="dateObject" value="${item.st }" /> 
						<fmt:formatDate value="${dateObject}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<c:choose>
						<c:when test="${item.state==0 }">
							<td style="vertical-align: middle">未激活</td>
						</c:when>
						<c:when test="${item.state==1 }">
							<td style="vertical-align: middle">已激活</td>
						</c:when>
					</c:choose>
					
					<td style="vertical-align: middle">${item.email }</td>
					<td style="vertical-align: middle">${item.nick }</td>
					<td style="vertical-align: middle"><img
						src="<%=basePath%>userPhoto/${item.photo }"
						style="width: 134px;height: 90px;vertical-align: bottom"
						class="img-thumbnail"></td>

					<td>
						<button
							onclick="openDel('<%=basePath%>admin/UsersDelServlet.action?usersId=${item.id }')"
							class="btn btn-danger" type="button">
							<span class="glyphicon glyphicon-remove"></span>删除数据
						</button>
					</td>
				</tr>
			</c:forEach>

		</table>

	</div>


	<!--删除确认框-->
	<div class="modal fade" id="delModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">删除确认</h4>
				</div>
				<div class="modal-body">
					<p>你确定要删除吗？</p>
				</div>
				<div class="modal-footer">
					<a id="delAlink" type="button" class="btn btn-primary">确定</a>
					<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->







	<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>

<script>

	function add() {
		window.location = "<%=basePath%>admin/ClearUsersServlet.action";
	}
	function openDel(url) {
		$('#delAlink').attr('href', url);
		$('#delModal').modal('show');

	}

</script>


