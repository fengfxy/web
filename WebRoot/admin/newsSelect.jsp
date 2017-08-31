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

<title>My JSP 'newsTypeSelect.jsp' starting page</title>

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
				<span class="glyphicon glyphicon-plus"></span>添加数据
			</button>

			<form action="<%=basePath%>admin/NewsQueryServlet.action"
				method="post" class="form-inline">
				<div class="form-group">
					<label> <input type="checkbox" name="c1" value="title">标题
					</label><input type="text" name="title" class="form-control"
						id="exampleInputName2" placeholder="输入标题">
				</div>
				
				<div class="form-group">
					<label><input type="checkbox" name="c1" value="type">类型</label>
					<select name="type" class="form-control">
						<c:forEach var="item" items="${newsTypes }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>


				<div class="form-group">
					<label><input type="checkbox" name="c1" value="st">日期：</label>
					<div class='input-group date' id='datetimepicker1'>
						<input type='text' name="stStart" class="form-control" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>


				<div class="form-group">
					<label>至</label>
					<div class='input-group date' id='datetimepicker2'>
						<input type='text' name="stEnd" class="form-control" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>



				<button type="submit" class="btn btn-primary">查询</button>
			</form>

		</div>

		<!--表格-->
		<table class="table table-bordered table-hover">

			<!--表头-->
			<tr class="info">
				<td style="width: 10%">id</td>
				<td style="width: 40%">标题</td>
				<td style="width: 10%">作者</td>
				<td style="width: 15%">时间</td>
				<td style="width: 10%">类型</td>
				<td style="width: 10%">图片</td>
				<td style="width: 10%">引言</td>
				<td style="width: 20%">操作</td>
			</tr>

			<!--表数据-->
			<c:forEach var="item" items="${news }">
				<tr>
					<td style="vertical-align: middle">${item.id }</td>
					<td style="vertical-align: middle">${item.title }</td>
					<td style="vertical-align: middle">${item.author }</td>
					<td style="vertical-align: middle">${item.st }</td>
					<td style="vertical-align: middle">${item.newsType.name }</td>
					<td style="vertical-align: middle"><img src="<%=basePath%>admin/upload/${item.photoA }" style="width: 134px;height: 90px;vertical-align: bottom"  class="img-thumbnail"></td>
					<td style="vertical-align: middle">${item.startInfo }</td>

					<td><a
						href="<%=basePath%>admin/NewsEditBeforeServlet.action?id=${item.id }"
						class="btn btn-warning" type="button"> <span
							class="glyphicon glyphicon-pencil"></span>编辑数据
					</a>
						<button
							onclick="openDel('<%=basePath%>admin/NewsDelServlet.action?id=${item.id }')"
							class="btn btn-danger" type="button">
							<span class="glyphicon glyphicon-remove"></span>删除数据
						</button></td>
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


	function openDel(url) {
		$('#delAlink').attr('href', url);
		$('#delModal').modal('show');

	}

	function add() {
		window.location = "<%=basePath%>admin/NewsAddBeforServlet.action";
	}


	$(function() {

		//设置日期插件
		$('#datetimepicker1').datetimepicker({
			format : 'YYYY-MM-DD',
			locale : moment.locale('zh-cn')
		});

		//设置日期插件
		$('#datetimepicker2').datetimepicker({
			format : 'YYYY-MM-DD',
			locale : moment.locale('zh-cn')
		});


	});
</script>


