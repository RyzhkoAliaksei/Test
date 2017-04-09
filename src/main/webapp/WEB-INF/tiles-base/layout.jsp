<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="pragma" content="no-cache" />
	
	<!-- default header name is X-CSRF-TOKEN -->
	<!--meta name="_csrf" content="${_csrf.token}"/-->
    <!--meta name="_csrf_header" content="${_csrf.headerName}"/-->
    <security:csrfMetaTags />

	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/bootstrap.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/bootstrap-theme.css"/>" />
 	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/layout.css"/>" />
 	
 	<script src="<c:url value="/resources/js/jquery.js" />"></script>
 	<script src="<c:url value="/resources/js/json2.js" />"></script>
 	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="row" >
			<security:authorize access="isAuthenticated()">
				<div class="col-md-3">
					<tiles:insertAttribute name="menu" />
				</div>
				<div class="col-md-9 well">
					<tiles:insertAttribute name="body" />
				</div>
			</security:authorize>
			
			<security:authorize access="isAnonymous()">
				<div class="col-md-4"></div>
				<div class="col-md-4 well">
					<tiles:insertAttribute name="body" />
				</div>
				<div class="col-md-4"></div>
			</security:authorize>
			
		</div>
	</div>
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>

<!--
<sec:authorize var="loggedIn" access="isAuthenticated()" />
<c:choose>
    <c:when test="${loggedIn}">
        You are loged in
    </c:when>
    <c:otherwise>
        You are logged out
    </c:otherwise>
</c:choose> 
 -->