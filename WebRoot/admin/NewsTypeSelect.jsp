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

<title>My JSP 'NewsTypeSelect.jsp' starting page</title>

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
		<!--页头-->
		<div class="page-header" style="margin-top: 0px!important;">
			<h1>
				新闻类型列表 <small>新闻类型的操作界面</small>
			</h1>
		</div>

		<!--表格工具栏目-->
		<div style="margin-bottom: 10px">
			<button onclick="add()" class="btn btn-default" type="button">
				<span class="glyphicon glyphicon-plus"></span>添加数据
			</button>
		</div>

		<!--表格-->
		<table class="table table-bordered table-hover">

			<!--表头-->
			<tr class="info">
				<td style="width: 10%">id</td>
				<td style="width: 70%">名称</td>
				<td style="width: 20%">操作</td>
			</tr>

			<!--表数据-->
			<c:forEach var="newsType" items="${NewsTypes }">
				<tr>
					<td style="vertical-align: middle">${newsType.id }</td>
					<td style="vertical-align: middle">${newsType.name }</td>
					<td>
						<a href="<%=basePath %>admin/NewsTypeEditBeforeServlet.action?newsTypeId=${newsType.id }" class="btn btn-default" type="button">
							<span class="glyphicon glyphicon-pencil"></span>编辑数据
						</a>
						<button onclick="openDel('<%=basePath %>admin/NewsTypeDelServlet.action?newsTypeId=${newsType.id }')" class="btn btn-default"
							type="button">
							<span class="glyphicon glyphicon-remove"></span>删除数据
						</button>
					</td>
				</tr>
			</c:forEach>

		</table>
		
		
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
						<a id="delAlink" type="button" class="btn btn-default">确定</a>
						<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->



	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

<script>
function add(){
	window.location.href="<%=basePath%>admin/NewsTypeAdd.jsp";

}

function openDel(url) {

		$('#delAlink').attr('href', url);
		$('#delModal').modal('show');

}

</script>
