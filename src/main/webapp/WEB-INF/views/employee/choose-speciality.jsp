<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
   <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="${form_modification}" /> </h1>

<form:form action="${action}" commandName="employee"  modelAttribute="employee" method="POST">
	<form:hidden path="crew" />
	<form:label path="speciality"><spring:message code="speciality"/></form:label> 
     <form:select path="speciality">
     <form:options items="${specialityList}" />
    </form:select>
	<input type="submit" value="<spring:message code="save" />"/>
</form:form> 

</security:authorize>
</security:authorize>