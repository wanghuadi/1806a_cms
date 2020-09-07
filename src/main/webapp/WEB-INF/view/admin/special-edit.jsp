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
	<script type="text/javascript">
		function addArticle(sid){
			var aid = $("#aid").val();
			
			$.post("/admin/specialArticle/addSA",{"sid":sid,"aid":aid},function(data){
					
				if(data){
					alert("追加成功!");
					location.reload();
				}else{
					alert("追加失败!");
				}
				
			})
			
		}
	
	</script>
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
            <li class="breadcrumb-item active">专题管理</li>
          </ol>

          <!-- Page Content -->
          <h1 align="center">专题文章设置</h1>
          
	          专题名称:${special.title}<br>
	          专题摘要:${special.abstracts }<br>
	           专题文章:
          
          <table class="table">
          	<tr>
          		<td>文章ID</td>
          		<td>文章标题</td>
          		<td>文章发布时间</td>
          		<td>操作</td>
          	</tr>
          <c:forEach items="${articleList }" var="a">
          	<tr class="active">
          		<td>${a.id }</td>
          		<td>${a.title }</td>
          		<td>
          			<fmt:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
          		</td>
          		<td>
          			<a href="">移除</a>
          		</td>
          	</tr>
          </c:forEach>
          </table>
          <input type="text" name="aid" id="aid">
          <input type="button" value="添加文章" onclick="addArticle('${special.id}')" class="btn btn-danger">
<!--           <ul class="pagination">
			    <li><a href="#">&laquo;</a></li>
			    <li><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">4</a></li>
			    <li><a href="#">5</a></li>
			    <li><a href="#">&raquo;</a></li>
			</ul>
			
 -->        </div>
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
