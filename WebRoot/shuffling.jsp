<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Integer num = 7;
%>

<div id="myCarousel" class="carousel slide">
	<!-- 轮播（Carousel）指标 -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<c:forEach var="items1" items="${sessionScope.viewNews }" begin="1"
			end="7">
			<li data-target="#myCarousel" data-slide-to="${items1 }"></li>
		</c:forEach>
	</ol>
	<!-- 轮播（Carousel）项目 -->
	<div class="carousel-inner">
		<div class="item active">
			<img
				src="<%=basePath%>admin/upload/${sessionScope.viewNews[0].photoA }"
				alt="网络似乎不给力" style="height: 200px; width: 100%; display: block;">
		</div>
		<c:forEach var="items" items="${sessionScope.viewNews }" begin="1"
			end="7">
			<div class="item">
				<a href="<%=basePath%>NewsInfoViewServlet.action?id=${items.id }">
					<img src="<%=basePath%>admin/upload/${items.photoA}" alt="网络似乎不给力"
					style="height: 200px; width: 100%; display: block;">
				</a>

			</div>
		</c:forEach>
	</div>
	<!-- 轮播（Carousel）导航 -->
	<a class="carousel-control left" href="#myCarousel" data-slide="prev">‹</a>
	<a class="carousel-control right" href="#myCarousel" data-slide="next">›</a>
</div>
<script>
	$(function() {
		// 初始化轮播
		$(".start-slide").click(function() {
			$("#myCarousel").carousel('cycle');
		});
		// 循环轮播到某个特定的帧 
		for (var i=0;i<8;i++){
			$(".slide-one").click(function() {
				$("#myCarousel").carousel(i);
			});
		}

	});
</script>

