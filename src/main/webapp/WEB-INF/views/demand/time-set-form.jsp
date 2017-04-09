<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<security:authorize ifAnyGranted="ROLE_DISPATCHER">
	<h1>
		<spring:message code="demand.add.time" />
	</h1>

	<form:form action="${action}" commandName="demand"
		modelAttribute="demand" method="POST">

		<form:label path="preferenceTime">
			<spring:message code="demand.preference.time" />
		
			<fmt:formatDate pattern="yy/MM/dd HH:mm"
				value="${demand.preferenceTime}" var="preferenceTime" />
	${preferenceTime}
	</form:label>
		<br />
		<form:label path="beginTime">
			<spring:message code="demand.begin.time" />
		</form:label>
		<fmt:formatDate pattern="yy/MM/dd HH:mm"
			value="${demand.beginTime}" var="beginTime" />
		<form:input path="beginTime" value="${beginTime}" placeholder="YY/MM/DD HH:mm"/>
		<form:errors path="beginTime" cssClass="error" />
		<br />
		<form:label path="endTime">
			<spring:message code="demand.end.time" />
		</form:label>
		<fmt:formatDate pattern="yy/MM/dd HH:mm"
			value="${demand.endTime}" var="endTime" />
		<form:input path="endTime" value="${endTime}" placeholder="YY/MM/DD HH:mm" />
		<form:errors path="endTime" cssClass="error" />
		<br />
		<br />
		<input type="submit" value="<spring:message code="save" />" />
		<input type="reset" value="<spring:message code="cancel" />"
			onclick="window.history.back();" />

	</form:form>
</security:authorize>
