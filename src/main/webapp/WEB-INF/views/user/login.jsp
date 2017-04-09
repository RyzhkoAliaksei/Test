<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 
 <security:authorize access="isAuthenticated()">
 	<c:redirect url="/profile"/>
 </security:authorize>
 
<h3><spring:message code="please" /> &nbsp;<spring:message code="login" /></h3>

<c:if test="${not empty error}">
	<div class="danger">
		<p class="alert alert-danger">${error}</p>
	</div>
</c:if>

<c:if test="${not empty msg}">
	<div class="danger">
		<p class="alert alert-danger">${msg}</p>
	</div>
</c:if>
		
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	<div class="danger">
	  <p class="alert alert-danger"><spring:message code="login.error"/></p>
	</div>
</c:if>
    
<form name='loginForm' action="<c:url value='/j_spring_security_check'/>" method='POST' class="form-horizontal">
	<div class="form-group">
		<label for="username" class="col-sm-4 control-label"><spring:message code="user" />:</label>
		<div class="col-sm-8">
			<input type="text" id="username" name="username" class="form-control" placeholder="Enter user name">
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-4 control-label"><spring:message code="password" />:</label>
		<div class="col-sm-8">
			<input type="text" id="password" name="password" class="form-control" placeholder="Enter password">
		</div>
	</div>
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-8">
			<input name="submit" type="submit" class="btn btn-primary" value=" Sign In " />
		</div>
	</div>
</form>
