<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="employees.free" /> </h1>
 
<br/><br/>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
	<tr>
		<th><spring:message code="employee.id" /></th>
		<th><spring:message code="employee.name" /></th>
		<th><spring:message code="employee.surname" /></th>
		<th><spring:message code="speciality" /></th>
		<th><spring:message code="crew" /></th>
		<th></th>
	</tr>
	
	<c:forEach items="${employees}" var="employee">

		<tr id="${employee.id}">
		    <td>${employee.id}</td>
		    <td>${employee.name}</td>
		    <td>${employee.surname}</td>
		    <td>${employee.speciality.specialityName}</td>
		    <td>${employee.crew.crewName}</td>
		    <td>
		    	<a href="<c:url value="/employee/${employee.id}/add-from-crew/${crewId}" />"> <spring:message code="add.from.crew" /> </a> &nbsp;
		   	</td>
	   	</tr>
	</c:forEach>
</table>
 <input type="reset" value="<spring:message code="back" />" onclick="window.history.back();"/>
</security:authorize>
</security:authorize>

 
