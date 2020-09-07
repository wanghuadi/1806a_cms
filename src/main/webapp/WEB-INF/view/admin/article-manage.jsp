<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CMS后台管理系统</title>
    <!-- Bootstrap core CSS-->
   <link href="/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  

    <!-- Custom fonts for this template-->
    <link href="/libs/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="/libs/sb-admin/sb-admin.css" rel="stylesheet">

  </head>

  <body id="page-top">

    <!-- 后台管理系统顶部 -->
 	<jsp:include page="_inc_top.jsp"/>

    <div id="wrapper">

      	<!-- 后台管理系统左部菜单 -->
 		<jsp:include page="_inc_left.jsp"/>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- 面包屑 -->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="index.html">后台首页</a>
            </li>
            <li class="breadcrumb-item active">用户管理</li>
          </ol>

          <!-- Page Content -->
          <h1>文章管理</h1>
          
          <table class="table">
          	<tr>
          		<td>文章ID</td>
          		<td>文章标题</td>
          		<td>文章内容</td>
          		<td>发布人</td>
          		<td>发布时间</td>
          		<td>状态</td>
          		<td>操作</td>
          	</tr>
          <c:forEach items="${list }" var="a">
          	<tr class="active">
          		<td>${a.id }</td>
          		<td>${a.title }</td>
          		<td>${a.content }</td>
          		<td>${a.author.nickname }</td>
          		<td>
          			<fmt:formatDate value="${a.created }" pattern="yyyy-MM-dd"/>
          		</td>
          		<td>${a.status == 0?"审核不通过":a.status == 1?"审核通过":"待审核" }</td>
          		<td>
          			
          			<c:choose>
          				<c:when test="${a.status == 1 }">
          					<input type="button" value="上热门" class="btn btn-primary">
          				</c:when>
          				<c:when test="${a.status == 0 }">
          					<input type="button" value="删除"  class="btn btn-danger">
          				</c:when>
          				<c:otherwise>
          					<input type="button" value="通过" class="btn btn-success" onclick="shenhe('1',${a.id })">
          					<input type="button" value="未通过" class="btn btn-danger" onclick="shenhe('0',${a.id })">
          				</c:otherwise>
          			</c:choose>
          		</td>
          	</tr>
          </c:forEach>
          </table>
          <ul class="pagination">
			    <li><a href="#">&laquo;</a></li>
			    <li><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">4</a></li>
			    <li><a href="#">5</a></li>
			    <li><a href="#">&raquo;</a></li>
			</ul>
			
        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright © Your Website 2018</span>
            </div>
          </div>
        </footer>

      </div>
      <!-- /.content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="login.html">Logout</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/libs/jquery/jquery.min.js"></script>
    <script src="/libs/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/libs/sb-admin/sb-admin.min.js"></script>

	<script type="text/javascript">
		//文章审核
		function shenhe(status,id){
			$.post("/admin/updateStatusById",{"status":status,"id":id},function(data){
				if(data){
					alert("审核成功!");
					location.href="http://localhost/admin/articles";
				}else{
					alert("审核失败!")
				}
			})
			
		}
	</script>
  </body>

</html>
