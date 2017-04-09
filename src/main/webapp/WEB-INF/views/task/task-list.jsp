<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<security:authorize access="isAuthenticated()">


<h1><spring:message code="tasks" /> </h1>

<security:authorize ifAnyGranted="ROLE_CLIENT">
 
<a href="<c:url value="/task/new/for-demand/${demand.demandId}" />"><spring:message code="task.new" /></a> 
</security:authorize>
<br/><br/>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
	<tr>
		<th><spring:message code="task.id" /></th>
		<th><spring:message code="task.name" /></th>
		<th></th>
	</tr>
	
	<c:forEach items="${tasks}" var="task">

		<tr id="${task.taskId}">
		 <td>${task.taskId}</td>
		    <td>${task.taskName}</td>
		    <td>
		    <security:authorize ifAnyGranted="ROLE_CLIENT">
		    	<a href="<c:url value="/task/edit/${task.taskId}" />"> <spring:message code="edit" /> </a> &nbsp;
		    	<a href="<c:url value="/task/delete/${task.taskId}" />"> <spring:message code="delete" /> </a>
			</security:authorize>
		   	</td>
	   	</tr>
	</c:forEach>
</table>
   <security:authorize ifAnyGranted="ROLE_DISPATCHER">
		   <input type="reset" value="<spring:message code="back" />" onclick="window.history.back();"/>
			</security:authorize>
</security:authorize>

 
