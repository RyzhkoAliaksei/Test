<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 
<security:authorize access="isAuthenticated()">
<security:authorize ifAnyGranted="ROLE_CLIENT">
	<ul class="nav nav-pills nav-stacked">
		<li><a href="<c:url value="/demand/demands" />"> <spring:message code="demands"/></a></li>
	</ul>
</security:authorize>
<security:authorize ifAnyGranted="ROLE_DISPATCHER">
<ul class="nav nav-pills nav-stacked">
		<li class="active" ><a href="<c:url value="/speciality" />">
				 <spring:message code="specialities"/></a></li>	
		<li><a href="<c:url value="/employee" />"> <spring:message code="employees"/></a></li>
		<li><a href="<c:url value="/crew" />"> <spring:message code="crews"/></a></li>
		<li><a href="<c:url value="/demand" />"> <spring:message code="demands"/></a></li>
		<li><a href="<c:url value="/dispatcher" />"> <spring:message code="dispatchers"/></a></li>
			</ul>
</security:authorize>
</security:authorize>
