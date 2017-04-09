<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="employees"/> </h1>
 
<a href="<c:url value="/employee/choose-from-crew/${crew.crewId}" />"><spring:message code="employee.new" /></a> 

<br/><br/>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
	<tr>
		<th><spring:message code="employee.id" /></th>
		<th><spring:message code="employee.name" /></th>
		<th><spring:message code="employee.surname" /></th>
		<th><spring:message code="speciality" /></th>
		<th> </th>
	</tr>
	
	<c:forEach items="${employees}" var="employee">

		<tr id="${employee.id}">
		    <td>${employee.id}</td>
		    <td>${employee.name}</td>
		    <td>${employee.surname}</td>
		    <td>${employee.speciality.specialityName}</td>
		    <td>
		    	<a href="<c:url value="/employee/delete-from-crew/${employee.id}" />"> <spring:message code="delete" /> </a>
		   	</td>
	   	</tr>
	</c:forEach>
</table>
<input type="reset" value="<spring:message code="back" />" onclick="window.history.back();"/>
</security:authorize>
</security:authorize>


 
