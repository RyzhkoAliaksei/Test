<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="dispatchers" /> </h1>
 
<a href="<c:url value="/dispatcher/new" />"><spring:message code="dispatcher.new" /></a> 

<br/><br/>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
	<tr>
		<th><spring:message code="user.id" /></th>
		<th><spring:message code="user.name" /></th>
		<th><spring:message code="user.surname" /></th>
		<th><spring:message code="user.joiningDate" /></th>
		<th><spring:message code="user.login" /></th>
		<th></th>
	</tr>
	
	<c:forEach items="${dispatchers}" var="dispatcher">

		<tr id="${dispatcher.userId}">
		    <td>${dispatcher.userId}</td>
		    <td>${dispatcher.name} </td>
		    <td>${dispatcher.surname} </td>
		    <td><fmt:formatDate pattern="yy/MM/dd" value="${dispatcher.joiningDate}" /></td>
		    <td>${dispatcher.login} </td>
		    <td>
		     	<a href="<c:url value="/dispatcher/delete/${dispatcher.userId}" />"> <spring:message code="delete" /> </a>
		   	</td>
	   	</tr>
	</c:forEach>
</table>

</security:authorize>
</security:authorize>


 
