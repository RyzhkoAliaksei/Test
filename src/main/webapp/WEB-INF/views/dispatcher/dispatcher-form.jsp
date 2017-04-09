<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_DISPATCHER">

<h1><spring:message code="dispatcher.new" />:</h1>

<form:form action="${action}" commandName="dispatcher" modelAttribute="dispatcher"  method="POST">

	<table>
		<tr>
			<td><form:label path="name">
					<spring:message code="user.name" />
				</form:label></td>
			<td><form:input path="name" />
			<br/>
			<form:errors path="name" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td><form:label path="surname">
					<spring:message code="user.surname" />
				</form:label></td>
			<td><form:input path="surname" />
			<br/>
			<form:errors path="surname" cssClass="error" />
			</td>
		</tr>
		<tr>
		<td><form:label path="joiningDate">
					<spring:message code="dispatcher.joiningDate" />
				</form:label></td>
		<td>		
		<fmt:formatDate pattern="yy/MM/dd"
			value="${dispatcher.joiningDate}" var="joiningDate" />
		<form:input path="joiningDate" value="${joiningDate}" placeholder="YY/MM/DD" />
		<form:errors path="joiningDate" cssClass="error" />
		</td>
			</tr>
		<tr>
			<td><form:label path="login">
					<spring:message code="user.login" />
				</form:label></td>
			<td><form:input path="login" />
			<br/>
			<form:errors path="login" cssClass="error" />
			</td>
			
		</tr>
		<tr>
			<td><form:label path="password">
					<spring:message code="user.password" />
				</form:label></td>
			<td><form:input path="password" />
			<br/>
			<form:errors path="password" cssClass="error" />
			</td>
		</tr>
		<tr>
		<td>
			<input type="submit" value="<spring:message code="save" />"/>
	<input type="reset" value="<spring:message code="cancel" />" onclick="window.history.back();"/>
		</td>
		</tr>
	</table>
</form:form>

</security:authorize>
</security:authorize>