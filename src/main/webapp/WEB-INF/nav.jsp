<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <a class="navbar-brand" href="javascript:void(0)">${user.username}</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarCollapse">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${root}/index">首页</a>
      </li>
      <c:forEach items="${modules}" var="m" varStatus="s">
      <c:if test="${user.containsParentModule(m)}">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="javascript:void(0)" id="dropdown${m.id}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${m.name}</a>
        <div class="dropdown-menu" aria-labelledby="dropdown${m.id}">
          <c:forEach items="${m.children}" var="m2">
          <c:if test="${user.containsModule(m2)}">
          <a class="dropdown-item" href="${root}${m2.code}">${m2.name}</a>
          </c:if>
          </c:forEach>
        </div>
      </li>
      </c:if>
      <c:if test="${!user.containsParentModule(m)}">
      <li class="nav-item">
        <a class="nav-link disabled" href="javascript:void(0)">${m.name}</a>
      </li>
      </c:if>
      </c:forEach>      
      <li class="nav-item">
        <a class="nav-link" href="${root}/user/logout">注销</a>
      </li>        
    </ul>
    <form class="form-inline mt-2 mt-md-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>