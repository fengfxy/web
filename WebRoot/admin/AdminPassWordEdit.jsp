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


</head>

<body>

<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="leftbar.jsp"></jsp:include>
	<div class="main">

		<!--标题-->
		<div class="page-header" style="margin-top: 0px!important;">
			<h1>
				管理员密码修改 <small>密码的操作界面</small>
			</h1>
		</div>


		<!--添加form-->
		<div style="margin-bottom: 10px">

			<form action="<%=basePath%>/admin/AdminPassWordEditServlet.action" method="post"
				class="form-horizontal" style="width: 500px">

				<input name="id"  value="${Admin.id}" type='hidden' />

				<div class="form-group">
						<label for="inputPassword1" class="col-xs-3 control-label">新密码:</label>
						<div class="col-xs-5">
							<input type="password" class="form-control" id="inputPassword1"
								placeholder="请输入新的密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword2" class="col-xs-3 control-label">确认密码:</label>
						<div class="col-xs-5">
							<input type="password" name="adminPassWord" class="form-control"
								id="inputPassword2" placeholder="请确认新密码">
						</div>
					</div>

					<div class="form-group">
						<label for="inputText3" class="col-xs-3 control-label">验证码:</label>
						<div class="col-xs-5">
							<input type="text" class="form-control" id="inputText3"
								placeholder="请输入图片上的验证码">
						</div>
						<div class="col-xs-4">
							<img id="Kaptcha" src="<%=basePath%>Kaptcha.jpg">
						</div>
					</div>

				<div class="form-group">
					<div class="col-xs-offset-2 col-xs-10">
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
				</div>
				
				
				<div class="form-group">
					<label id="mess" class="col-xs-3 control-label">管理员密码修改</label>
				</div>
			</form>
			
		</div>


	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<script>
	//提交
	$("#inputText3").blur(function() {
		var verifyCodeValue = $("#inputText3").val();
		if (verifyCodeValue.replace(/\s/g, "") == "") {
			$('#mess').text("请输入验证码");
		} else {
			//提交前先异步检查验证码是否输入正确
			var verifyUrl = "<%=basePath%>servlet/UsersRegisterServlet?Kaptcha=" + verifyCodeValue;
			$.ajax({
				type : "GET",
				url : verifyUrl,
				success : function(returnData) {
					if (returnData != "Y") {
						$('#mess').text("请输入正确的验证码！");
						$('#Kaptcha').attr('src', '<%=basePath%>Kaptcha.jpg?' + Math.floor(Math.random() * 100));
					} else {
						//验证码正确，进行提交操作
						$('#mess').text("FXY信息系统——重置密码");
					}
				},
				error : function(e) {
					$('#mess').text(e);
				}
			});
		}
	});

	<!--点击切换验证码-->
	$(function() {
		$('#Kaptcha').click(function() {
			$(this).attr('src', '<%=basePath%>Kaptcha.jpg?' + Math.floor(Math.random() * 100));
		});

		$(function() {
			$('#from1').bind('submit', function() { //给form标签绑定submit事件
				var i = 0;
				var name;
				$("#from1 input").each(function() { //遍历input标签，判断是否有内容未填写
					var vl = $(this).val();
					if (vl == "") {
						i = 1;
						name = $(this).attr('placeholder');
					}
				});

				if (i == 1) { //如果有未填写的，则return false阻止提交
					$('#mess').text(name);
					return false;
				} else if ($('#inputPassword1').val() != $('#inputPassword2').val()) {
					$('#mess').text('两次输入的密码不一致');
					return false;
				}
				if ($('#mess').text() != "FXY信息系统——重置密码") {
					$('#mess').text('验证码输入有误');
					return false;
				}
			});

		});

	});
</script>
