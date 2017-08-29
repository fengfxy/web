<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>fxy信息管理系统</title>
	<meta charset="UTF-8">
	<link href="css/base.css" rel="stylesheet">
	<link href="css/adminLogin.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<meta name="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

</head>
<body class="default">
<div class="login-hd">
	<div class="left-bg"></div>
	<div class="right-bg"></div>
	<div class="hd-inner">
		<span class="logo"></span> <span class="split"></span> <span
			class="sys-name">FXY后端管理平台</span>
	</div>
</div>
<div class="login-bd">
	<div class="bd-inner">
		<div class="inner-wrap">
			<div class="lg-zone">
				<div class="lg-box">
					<div class="lg-label">
						<h4>用户登录</h4>
					</div>
					<form id="adminForm"
						  action="<%=basePath%>adminLoginServlet.action"
						  method="post">
						<div class="lg-username input-item clearfix">
							<i>账号:</i> <input type="text" name="username"
											  placeholder="后台管理账号">
						</div>
						<div class="lg-password input-item clearfix">
							<i>密码:</i> <input type="password" name="password"
											  placeholder="请输入密码">
						</div>


						<div class="enter">
							<a class="purchaser">管理员登录</a>
						</div>
					</form>
				</div>
			</div>
			<div class="lg-poster"></div>
		</div>
	</div>
</div>


<div class="login-ft">
	<div class="ft-inner">
		<div class="about-us">
			<a href="javascript:;">关于我们</a> <a href="javascript:;">法律声明</a> <a
				href="javascript:;">服务条款</a> <a href="javascript:;">联系方式</a>
		</div>
		<div class="address">地址：广州市天河区中山大道中珠村BRT旁盈富大厦4楼&nbsp;邮编：210019&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2017&nbsp;-&nbsp;2018&nbsp;优才创智科技有限公司&nbsp;版权所有</div>
		<div class="other-info">建议使用IE8及以上版本浏览器&nbsp;京ICP备&nbsp;13004859号&nbsp;E-mail：1721023048@qq.com</div>
	</div>
</div>
</body>
</html>
<script>
	$(function(){
		$("a.purchaser").bind('click',function(){
			$("#adminForm").submit();
		});
	});
</script>
