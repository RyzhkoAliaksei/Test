<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
   <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="${form_modification}" /> </h1>
 
<form:form action="${action}" commandName="speciality" modelAttribute="speciality" method="POST">

	<form:label path="specialityName"><spring:message code="speciality.name" />: </form:label>	
	<form:input path="specialityName"/> 	
	<br/>
	<form:errors path="specialityName" cssClass="error" />
	<br/>	
	<input type="submit" value="<spring:message code="save" />"/>
	<input type="reset" value="<spring:message code="cancel" />" onclick="window.history.back();"/>
  
</form:form> 

</security:authorize>
</security:authorize>