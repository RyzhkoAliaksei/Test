<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<form:form action="${action}" commandName="demand"
	modelAttribute="demand" method="POST">

	<br />
	<form:label path="client">
		<spring:message code="client" />: ${demand.client.name} ${demand.client.surname} </form:label>
	<br />
	<form:label path="status">
		<spring:message code="status" />
	</form:label>
	<form:select path="status">
	  <form:options/>
	</form:select>


	<input type="submit" value="<spring:message code="save" />" />
	<input type="reset" value="<spring:message code="cancel" />"
		onclick="window.history.back();" />

</form:form>
</security:authorize>
