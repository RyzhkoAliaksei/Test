<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
   

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_CLIENT">

<h1><spring:message code="task.new" /> </h1>
 
<form:form action="${action}" commandName="task" modelAttribute="task" method="POST">
	<a>${task.demand.demandId}</a>
	<form:hidden path="demand" />
	<form:hidden path="taskId" />
	<form:label path="taskName"><spring:message code="task.name" />: </form:label>	
	<form:input path="taskName" /> 		
	<br/>
	<form:errors path="taskName" cssClass="alert alert-danger col-sm-8" />
	<br/>
	
<input type="submit" value="<spring:message code="save" />"/>
	<input type="reset" value="<spring:message code="cancel" />" onclick="window.history.back();"/>
  
</form:form> 
</security:authorize>
</security:authorize>
