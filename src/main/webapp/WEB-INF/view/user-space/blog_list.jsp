<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的文章</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/cms.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    </style>
  </head>
  <body>
    <jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
	
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 my_banner">
			</div>
		</div>
	</div>
	<br/>
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="/WEB-INF/inc/my_left.jsp">
					<jsp:param value="blogs" name="module"/>
				</jsp:include>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default">
				  <div class="panel-body">
				    	<h1>我的文章</h1>
				    	<hr/>
				    	<c:forEach items="${articleList }" var="article">
							
							<div>
								${article.title }			
								
								<div>
									<div style="float: left;">
										浏览量:${article.hits }&nbsp;&nbsp;			
										<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd"/>	
									</div>	
									
									<div style="float: right;">
										<c:choose>
											<c:when test="${article.status == 1 }">
												<span style="color: green;">审核通过</span>
											</c:when>
											<c:when test="${article.status == 0 }">
												<span style="color: red;">审核未通过</span>
											</c:when>
											<c:otherwise>
												<span style="color: gray;">审核中</span>
											</c:otherwise>
										</c:choose>
										
											&nbsp;&nbsp;
										<input type="button" value="删除">
									</div>
									
								</div>
							</div>
							<br>
							<hr>
									
						</c:forEach>
				  </div>
				</div>
				
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/inc/footer.jsp"/>
	
	<script type="text/javascript">
		function removeBlog(id){
			if(confirm("您是否要删除这篇文章？")){
				$.ajax({
					url:'/my/blog/remove?id=' + id,
					type:'get',
					success:function(data){
						if(data.status){
							$("#item_" + id).remove();
						}else{
							alert(data.message);
						}
					}
				});
			}
			return false;
		}
	</script>
  </body>
</html>