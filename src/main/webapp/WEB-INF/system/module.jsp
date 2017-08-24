<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="root"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${root}/img/favicon.ico">

    <title>权限分配</title>

    <!-- Bootstrap core CSS -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style type="text/css">
    body {
	  min-height: 75rem;
	  padding-top: 4.5rem;
	}
    </style>
  </head>

  <body>

    <%@include file="/WEB-INF/nav.jsp" %>

    <div class="container">
      <ol class="breadcrumb">
		  <li class="breadcrumb-item"><a href="${root}/index">首页</a></li>
		  <li class="breadcrumb-item">系统管理</li>
		  <li class="breadcrumb-item active">权限分配</li>
	  </ol>  
	  <div class="row">
	  	<div class="col-sm-4">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">门店经理</h4>
					<p class="card-text">主要负责本门店订单查询与统计、商品查询与统计操作，也可以做退单、商品上下架、商品库存等操作.</p>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#moduleModal" data-roleid="3">编辑</button>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">订单业务员</h4>
					<p class="card-text">主要负责本门店订单相关操作.</p>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#moduleModal" data-roleid="4">编辑</button>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">商品业务员</h4>
					<p class="card-text">主要负责本门店商品相关操作.</p>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#moduleModal" data-roleid="5">编辑</button>
				</div>
			</div>
		</div>		
	  </div>
	  <div class="clearfix" style="margin-bottom: 15px;"></div>
	  <div class="row">
		<div class="col-sm-6">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">系统管理员</h4>
					<p class="card-text">主要负责邮件设置、短信设置、用户管理、权限分配操作.</p>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#moduleModal" data-roleid="1">编辑</button>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">地区主管</h4>
					<p class="card-text">主要负责本地区订单查询与统计、商品查询与统计操作.</p>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#moduleModal" data-roleid="2">编辑</button>
				</div>
			</div>
		</div>
	  </div>
	</div>
	
	<div class="modal fade" id="moduleModal" tabindex="-1" role="dialog" aria-labelledby="moduleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="moduleModalLabel">编辑模块</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      		<c:forEach items="${modules}" var="m" varStatus="vs">
			    <div class="row">
			      <div class="col-md-4">
					<button class="btn">
						${m.name} <span class="badge badge-secondary">0</span>
						<span class="sr-only">unread messages</span>
					</button>
				  </div>
			      <div class="col-md-8">
			      	<c:forEach items="${m.children}" var="m2" varStatus="vs2">
			      	<c:if test="${vs2.index%2==0 and vs2.index > 0}">
					<div class="clearfix" style="margin-bottom: 10px;"></div>
					</c:if>
					<div class="form-check form-check-inline">
						<label class="form-check-label"> 
							<input class="form-check-input" type="checkbox" value="${m2.id}"> ${m2.name}
						</label>
					</div>					
					</c:forEach>
			      </div>
			    </div>
			    <c:if test="${vs.count<modules.size()}">
			    <hr/>
			    </c:if>
			    </c:forEach>
		    </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary confirm">确认修改</button>
	      </div>
	    </div>
	  </div>
	</div>

   <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${root}/js/jquery-3.2.1.min.js"></script>
    <script src="${root}/js/popper.min.js"></script>
    <script src="${root}/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug 
    <script src="../../../../assets/js/ie10-viewport-bug-workaround.js"></script>
    -->
    <script type="text/javascript">
    $(function(){
    	// 编辑角色       
        $("#moduleModal").on("show.bs.modal", function (event) {
 		  var button = $(event.relatedTarget);
 		  var roleId = button.data('roleid'); // 注意data-* 中*名称会被转换为小写
    	  var modal = $(this);
 		  modal.data("roleId", roleId);
 		  // 获取角色模块
 		  $.get("${root}/system/module/findModulesByRoleId", {roleId:roleId}).done(function(modules){
 			  modal.find(":checkbox").each(function(i,checkbox){
 				  var $c= $(checkbox);
 				  $c.prop("checked",false).change();
 				  // 如果角色拥有该模块，选中
 				  if(modules.find(function(m){return m==$c.val()})){
 					  $c.prop("checked",true).change();
 				  }
 			  });
 		  });
    	});
    	
    	// 更新选中计数
        $("#moduleModal").on("change", ":checkbox", function(){
        	var $row = $(this).parents("div.row");
        	var count = $row.find(":checkbox:checked").length;
  		  	$row.find(".badge").text(count);
        });
    	
     	// 确认修改角色
        $("#moduleModal :button.confirm").click(function(){
     	   var modal = $("#moduleModal");
     	   var roleId = modal.data('roleId');
     	   var moduleIds = [];
     	   modal.find(":checkbox:checked").each(function(i,checkbox){
     		  moduleIds.push(checkbox.value);
     	   });
     	   $.post("${root}/system/module/updateModules", {roleId:roleId, moduleIds:moduleIds.join(",")}).done(function(){
     		   modal.modal('hide');
     		   modal.removeData("roleId");
     	   });
        });
    });
    </script>
  </body>
</html>