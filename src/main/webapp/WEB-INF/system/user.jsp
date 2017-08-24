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

    <title>用户管理</title>

    <!-- Bootstrap core CSS -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
    
    <link href="${root}/css/metroStyle/metroStyle.css" rel="stylesheet">

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
		  <li class="breadcrumb-item active">用户管理</li>
	  </ol>
      <div class="row">
	    <div class="col-3">
	      <ul id="orgTree" class="ztree"></ul>
	    </div>
	    <div class="col-9">
	      <table id="userTable" class="table">
	      	<thead>
	      		<tr>
	      			<th>编号</th><th>登录名</th><th>功能权限</th><th>数据权限</th>
	      		</tr>
	      	</thead>
	      	<tbody></tbody>
	      </table>
	    </div>
	  </div>
    </div>
    
    <div class="modal fade" id="functionModal" tabindex="-1" role="dialog" aria-labelledby="functionModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="functionModalLabel">编辑功能权限(角色)</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form>
	          <c:forEach items="${roles}" var="r">
	          <div class="form-check">
			    <label class="form-check-label">
			      <input class="form-check-input" type="checkbox" value="${r.id}">
			      ${r.name}
			    </label>
			  </div>
			  </c:forEach>		
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary confirm">确认修改</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="dataModal" tabindex="-1" role="dialog" aria-labelledby="dataModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="dataModalLabel">编辑数据权限</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <ul id="modalOrgTree" class="ztree"></ul>
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
    <script src="${root}/js/jquery.ztree.all.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug 
    <script src="../../../../assets/js/ie10-viewport-bug-workaround.js"></script>
    -->
    <script type="text/javascript">
    
    function createLeftOrgTree(){
    	var zTreeObj;
        var setting = {
       		async: {
       			enable: true,
       			url: "${root}/system/user/findOrgs",
       			autoParam:["id"]
       		},
       		data: {
       			simpleData: {
       				enable: true,
       				idKey: "id",
       				pIdKey: "pid",
       				rootPId: -1
       			}
       		},
       		callback: {
       			onClick: function(event, id, node){
       				$.get("${root}/system/user/findUsersByOrgId", {orgId:node.id}).done(function(users){
   						var html = "";
   						for(var i = 0 ; i < users.length; i++) {
   							html += "<tr><td>"+users[i].id+"</td><td>"+users[i].username+"</td>"+
   									"<td><button type='button' data-toggle='modal' data-target='#functionModal' data-userid='"+users[i].id+"' class='btn btn-info btn-sm'>编辑</button></td>"+
   									"<td><button type='button' data-toggle='modal' data-target='#dataModal' data-userid='"+users[i].id+"' class='btn btn-info btn-sm'>编辑</button></td></tr>";
   						}
   						$("#userTable tbody").html(html);
   					});
       			}
       		}
        };
        zTreeObj = $.fn.zTree.init($("#orgTree"), setting);
    }
    
    function createModalOrgTree(){
    	var zTreeObj;
        var setting = {
       		async: {
       			enable: true,
       			url: "${root}/system/user/findOrgs",
       			autoParam:["id"]
       		},
       		data: {
       			simpleData: {
       				enable: true,
       				idKey: "id",
       				pIdKey: "pid",
       				rootPId: -1
       			}
       		},
       		check: {
       			enable: true
       		}       		
        };
        zTreeObj = $.fn.zTree.init($("#modalOrgTree"), setting);
    }
    
    $(document).ready(function(){
       createLeftOrgTree();
       createModalOrgTree();
       
       // 编辑功能权限       
       $("#functionModal").on("show.bs.modal", function (event) {
		  var button = $(event.relatedTarget);
		  var userId = button.data('userid'); // 注意data-* 中*名称会被转换为小写
   	  	  var modal = $(this);
		  modal.data("userId", userId);
		  // 获取用户角色
		  $.get("${root}/system/user/findUserById", {userId:userId}).done(function(user){
			  modal.find(":checkbox").each(function(i,checkbox){
				  var $c= $(checkbox);
				  $c.prop("checked",false);
				  // 如果用户拥有该角色，选中
				  if(user.roles.find(function(r){return r.id==$c.val()})){
					  $c.prop("checked",true);
				  }				  
			  });
		  });
   	   });
       
       // 确认修改功能权限
       $("#functionModal :button.confirm").click(function(){
    	   var modal = $("#functionModal");
    	   var userId = modal.data("userId");
    	   var roleIds = [];
    	   modal.find(":checkbox:checked").each(function(i,checkbox){
    		   roleIds.push(checkbox.value);
    	   });
    	   $.post("${root}/system/user/updateRoles", {userId:userId, roleIds:roleIds.join(",")}).done(function(){
    		   modal.modal('hide');
    		   modal.removeData("userId");
    	   });
       });
       
       // 编辑数据权限       
       $("#dataModal").on("show.bs.modal", function (event) {
		  var button = $(event.relatedTarget);
		  var userId = button.data('userid'); // 注意data-* 中*名称会被转换为小写
   	  	  var modal = $(this);
		  modal.data("userId", userId);
		  // 获取用户能控制的组织机构
		  $.get("${root}/system/user/findUserById", {userId:userId}).done(function(user){
			  var tree = $.fn.zTree.getZTreeObj("modalOrgTree");
			  var nodes = tree.transformToArray(tree.getNodes());
			  for (var i = 0; i < nodes.length; i++) {
				  // 如果用户可控制该组织机构，选中
			  	  if(user.orgIds.find(function(id){return id==nodes[i].id})){
			  		  tree.checkNode(nodes[i], true, false);
			  	  } else {
			  		  tree.checkNode(nodes[i], false, false);
			  	  }
			  }
		  });
   	   });
       
       // 确认修改数据权限
       $("#dataModal :button.confirm").click(function(){
    	   var modal = $("#dataModal");
    	   var userId = modal.data("userId");
    	   var tree = $.fn.zTree.getZTreeObj("modalOrgTree");
    	   var nodes = tree.getCheckedNodes();
    	   var orgIds = [];
    	   for(var i = 0; i < nodes.length; i++){
    		   orgIds.push(nodes[i].id);
    	   }
    	   $.post("${root}/system/user/updateOrgs", {userId:userId, orgIds:orgIds.join(",")}).done(function(){
    		   modal.modal('hide');
    		   modal.removeData("userId");
    	   });
       });
    });
    </script>
  </body>
</html>