<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<security:authorize access="isAuthenticated()">
	<security:authorize ifAnyGranted="ROLE_DISPATCHER">

		<h1>
			<spring:message code="choose.status" />
		</h1>



		<form:form action="${action}" commandName="demand"
			modelAttribute="demand" method="POST">

			<form:label path="status">
				<spring:message code="status" />
			</form:label>
			<form:select path="status">
				<form:options />
			</form:select>
			<input type="submit" value="<spring:message code="search" />" />
		</form:form>

	</security:authorize>
</security:authorize>