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

<title>My JSP 'newsTypeAdd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="link.jsp"></jsp:include>


<!-- 富文本编辑器 -->

<link href="<%=basePath%>umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="<%=basePath%>umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>umeditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>umeditor/lang/zh-cn/zh-cn.js"></script>


<!-- B3日期组件 -->
<link href="<%=basePath%>css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=basePath%>css/bootstrap-datetimepicker-standalone.css"
	rel="stylesheet">
<script src="<%=basePath%>js/moment.min.js"></script>
<script src="<%=basePath%>js/moment-with-locales.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>


<!--图片占位符-->
<script src="<%=basePath%>js/holder.js"></script>


</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="leftbar.jsp"></jsp:include>
	<div class="main">

		<!--标题-->
		<div class="page-header" style="margin-top: 0px!important;">
			<h1>
				新闻数据添加 <small>新闻的操作界面</small>
			</h1>
		</div>


		<!--添加form-->
		<div style="margin-bottom: 10px">

			<form action="<%=basePath%>admin/NewsAddServlet.action" method="post"
				class="form-horizontal" style="width: 1200px"
				enctype="multipart/form-data">

				<div class="form-group">
					<label class="col-xs-2 control-label">标题：</label>
					<div class='input-group date col-xs-6'>
						<input name="title" type='text' class="form-control"
							placeholder="请输入标题" />
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label">作者：</label>
					<div class='input-group date col-xs-6'>
						<input name="author" type='text' class="form-control"
							placeholder="请输入作者" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label">引言：</label>
					<div class='input-group date col-xs-6'>
						<input name="startInfo" type='text' class="form-control"
							placeholder="请输入引言" />
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label">类型</label>
					<div class='input-group date col-xs-6'>
						<select name="type" class="form-control">
							<c:forEach var="item" items="${newsTypes }">
								<option value="${item.id }">${item.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label">日期：</label>
					<div class='input-group date col-xs-6' id='datetimepicker1'>
						<input name="st" type='text' class="form-control" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label">图片：</label>
					<div class='input-group date col-xs-10'>
						<input name="photoA" type="file" class="form-control"
							id="fileInput" /> 
		<img id="fileImgA" src="holder.js/640x426"
							style="width: 640px;height: 426px;vertical-align: bottom"
							class="img-thumbnail"> 
							<img id="fileImgB"
							src="holder.js/240x170"
							style="width: 240px;height: 170px;vertical-align: bottom"
							class="img-thumbnail"> 
							<img id="fileImgC"
							src="holder.js/134x90"
							style="width: 134px;height: 90px;vertical-align: bottom"
							class="img-thumbnail">

					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label">内容：</label>
					<div class='input-group date col-xs-8'>
						<script name="info" type="text/plain" id="myEditor"
							style="width:1000px;height:500px;">
    <p>请输入内容</p>

                    </script>
					</div>
				</div>

				<div class="form-group">
					<div class="col-xs-offset-2 col-xs-10">
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
				</div>
			</form>

		</div>


	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>


<script>


    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    $(function () {

        //设置日期插件
        $('#datetimepicker1').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn')
        });


        //实例化编辑器
        var um = UM.getEditor('myEditor');
        um.addListener('blur', function () {
            $('#focush2').html('编辑器失去焦点了')
        });
        um.addListener('focus', function () {
            $('#focush2').html('')
        });


        //显示选中的图片
        $('#fileInput').on('change', function () {
            console.log(this);
            console.log(this.files);
            console.log(this.files[0]);
            var url = getObjectURL(this.files[0]);
            $('#fileImgA').attr('src', url);
            $('#fileImgB').attr('src', url);
            $('#fileImgC').attr('src', url);

        });


    });
</script>
