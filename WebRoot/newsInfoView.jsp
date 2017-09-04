<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>新闻预览</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="link.jsp"></jsp:include>


</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="centent">


		<!--标题-->
		<div class="page-header" style="margin-top: 0px!important;">
			<h1>${news.title }
				
			</h1>
		</div>


		<div class="row">


			<div class="col-xs-6">
				<div>
					<span>作者：${news.author }</span> <span>日期：${news.st }</span><span>类型：${news.newsType.name }</span>
				</div>

				<div>

                  <img  src="<%=basePath%>admin/upload/${news.photoA}"
							style="width: 640px;height: 426px;vertical-align: bottom"
							class="img-thumbnail"> 
                </div>
				<div>
			
			${newsInfo.info }		
					
				</div>

			</div>

		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

