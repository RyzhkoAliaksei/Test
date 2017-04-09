<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
   <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="${form_modification}" /> </h1>
 
<form:form action="${action}" commandName="employee" modelAttribute="employee" method="POST">

	<form:label path="name"><spring:message code="employee.name" />: </form:label>	
	<form:input path="name" /> <br />
	<form:errors path="name" cssClass="error" />
	<br/>
	<form:label path="surname"><spring:message code="employee.surname" />: </form:label>	
	<form:input path="surname" /> 	<br />
	<form:errors path="surname" cssClass="error" />	
		<br />
	<form:label path="speciality"><spring:message code="speciality"/></form:label>
  
     <form:select path="speciality">
     <c:if test="${form_modification eq 'employee.edit'}">
      <form:option value="${employee.speciality.specialityId}" label="${employee.speciality.specialityName}" />
        </c:if>
        <form:options items="${specialityList}" />
    </form:select>
	
	<input type="submit" value="<spring:message code="save" />"/>
	<input type="reset" value="<spring:message code="cancel" />" onclick="window.history.back();"/>
  
</form:form>

</security:authorize>
</security:authorize> 
