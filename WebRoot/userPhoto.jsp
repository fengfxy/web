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
<meta charset="UTF-8">
<title>用户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="link.jsp"></jsp:include>

<!--图片占位符-->
<script src="js/holder.js"></script>

</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="centent login-wrap">
		<jsp:include page="sidebar.jsp"></jsp:include>

		<div class="panel panel-primary login">
			<div class="panel-heading" align="center">
				<h3>修改头像</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal"
					action="<%=basePath%>serlvet/UsersInfoEditServlet.action" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-xs-2 control-label">头像：</label>
						<input name="id"  value="${Users.id}" type='hidden' />
						<input name="reqType"  value="修改头像" type='hidden' />
						<div class='input-group date col-xs-6'>
							<input name="photo" type="file" class="form-control"
								id="fileInput" /> <br>
							<br>
							<br>
							<br> <img id="fileImg" src="holder.js/240x170"
								style="width: 240px;height: 170px;vertical-align: bottom"
								class="img-thumbnail">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-offset-3 col-xs-10">
							<div class="row">
								<div class="col-xs-3">
									<button type="submit" class="btn btn-default">保存</button>
								</div>

							</div>

						</div>
					</div>
				</form>
			</div>
			<div id="mess" class="panel-footer">FXY信息系统——用户头像修改</div>
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

        //显示选中的图片
        $('#fileInput').on('change', function () {
            console.log(this);
            console.log(this.files);
            console.log(this.files[0]);
            var url = getObjectURL(this.files[0]);
            $('#fileImg').attr('src', url);

        });


    });
</script>