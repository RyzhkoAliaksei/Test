<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<h1><spring:message code="client.new" />:</h1>

<form:form action="${action}" commandName="client" modelAttribute="client"  method="POST">

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
				</form:label>
				</td>
			<td><form:input path="surname" />
			<br/>
			<form:errors path="surname" cssClass="error" />
			
		</tr>
		<tr>
			<td><form:label path="address">
					<spring:message code="client.address" />
				</form:label></td>
			<td><form:input path="address" />
			<br/>
			<form:errors path="address" cssClass="error" />
			
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
			<td colspan="2"><input type="submit"
				value="<spring:message code="client.add"/>" /></td>
		</tr>
	</table>
</form:form>

