<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="free.crews" /> </h1>


<br/><br/>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">

	<tr>
		<th><spring:message code="crew.id" /></th>
		<th><spring:message code="crew.name" /></th>
		<th><spring:message code="employees" /></th>
		<th></th>
	</tr>
	
	<c:forEach items="${crews}" var="crew">

		<tr id="${crew.crewId}">
		    <td>${crew.crewId}</td> 
		    <td>${crew.crewName}</td>
		    <td><a href="<c:url value="/employee/employees/${crew.crewId}" />"> <spring:message code="employees" /> </a> &nbsp;</td>
		    <td>
		    <a href="<c:url value="/demand/${demandId}/add-free-crew/${crew.crewId}" />"> <spring:message code="add.free.crew" /> </a>
		   	</td>
	   	</tr>
	</c:forEach>
</table>
 <input type="reset" value="<spring:message code="back" />" onclick="window.history.back();"/>
</security:authorize>
</security:authorize>
