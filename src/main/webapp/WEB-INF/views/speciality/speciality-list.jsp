<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="specialities" /> </h1>
 
<a href="<c:url value="/speciality/new" />"><spring:message code="speciality.new" /></a> 

<br/><br/>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
	<tr>
		<th><spring:message code="speciality.id" /></th>
		<th><spring:message code="speciality.name" /></th>
		<th></th>
	</tr>
	
	<c:forEach items="${specialities}" var="speciality">

		<tr id="${speciality.specialityId}">
		    <td>${speciality.specialityId}</td>
		    <td>${speciality.specialityName} &nbsp;  &nbsp;</td>
		    <td>
		    	<a href="<c:url value="/speciality/edit/${speciality.specialityId}" />"> <spring:message code="edit" /> </a> &nbsp;
		    	<a href="<c:url value="/speciality/delete/${speciality.specialityId}" />"> <spring:message code="delete" /> </a>
		   	</td>
	   	</tr>
	</c:forEach>
</table>

</security:authorize>
</security:authorize>

 
