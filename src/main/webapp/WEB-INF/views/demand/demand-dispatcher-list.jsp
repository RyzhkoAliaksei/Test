<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

	<security:authorize ifAnyGranted="ROLE_DISPATCHER">

		<h1>
			<spring:message code="demands" />
		</h1>

		<a href="<c:url value="/demand/choose/status" />"><spring:message
				code="show.by.status" /></a>
				<br />
				<a href="<c:url value="/demand/choose/surname" />"><spring:message
				code="show.by.surname" /></a>

		<br />
		<br />
		<table id="clientsTable" border="1" cellpadding="10" cellspacing="0"
			class="table table-striped table-bordered">
			<tr>
				<th><spring:message code="demand.id" /></th>
				<th><spring:message code="client" /></th>
				<th><spring:message code="demand.preference.time" /></th>
				<th><spring:message code="tasks" /></th>
				<th><spring:message code="status" /></th>
				<th><spring:message code="crew" /></th>
				<th><spring:message code="demand.execute.time" /></th>
				<th></th>
			</tr>

			<c:forEach items="${demands}" var="demand">

				<tr id="${demand.demandId}">
					<td>${demand.demandId}</td>
					<td>${demand.client.name} ${demand.client.surname}</td>
					<td><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${demand.preferenceTime}" /></td>
					<td>
						<a href="<c:url value="/task/tasks/${demand.demandId}" />">
							<spring:message code="tasks" />
					</a> &nbsp;
					</td>
					<td>${demand.status.string}</td>
					<td>${demand.crew.crewName}</td>
					<td><fmt:formatDate pattern="yy/MM/dd HH:mm" value="${demand.beginTime}" /> -- <fmt:formatDate
								pattern="yy/MM/dd HH:mm"
								value="${demand.endTime}" /></td>
					<td><a
						href="<c:url value="/demand/edit-status/${demand.demandId}" />">
							<spring:message code="demand.edit.status" />
					</a> 
					</br>
					<a
						href="<c:url value="/demand/edit-time/${demand.demandId}" />">
							<spring:message code="demand.edit.time" />
					</a> 
					</br>
					 <a
						href="<c:url value="/demand/choose-crew/${demand.demandId}" />">
							<spring:message code="choose.crew.demand" />
					</a>
					</br>
					 <a
						href="<c:url value="/demand/delete-crew/${demand.demandId}" />">
							<spring:message code="delete" /><spring:message code="crew" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
		<input type="reset" value="<spring:message code="back" />" onclick="window.history.back();"/>
	</security:authorize>

