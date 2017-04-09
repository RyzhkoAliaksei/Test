<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
   <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="choose.surname" /> </h1>


 
<form:form action="${action}" commandName="client"  modelAttribute="client" method="POST">
	
	<form:label path="surname"><spring:message code="user.surname"/></form:label> 
   <form:input path="surname" />
	<input type="submit" value="<spring:message code="search" />"/>
</form:form> 

</security:authorize>