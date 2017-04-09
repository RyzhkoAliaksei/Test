<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css"/>" />

<div class="navbar navbar-default" role="navigation">
	<div align="right">
	<c:set var="currentLocale">${pageContext.response.locale}</c:set>
	<c:set var="localeCode" value="${fn:toUpperCase(currentLocale)}" />
	
	<c:set var="availLanguages" value="EN,RU" />
	<c:if test="${!fn:contains(availLanguages, localeCode)}">
	  <c:set var="localeCode" value="EN" />
	</c:if>
	
 	<c:forEach var="lang" items="${availLanguages}">
 	    <c:set var="langHTML" value="${lang}" />
	 	
	 	<c:if test="${lang eq localeCode}">
		  <c:set var="langHTML" value="<b><u>${lang}</u></b>" />
		</c:if>
    	
    	<a href="${currentPage}?lang=${lang}">${langHTML}</a> &nbsp;
	</c:forEach>
</div>
	
	<div class="container">
		
		<ul class="nav navbar-nav navbar-right">
			<security:authorize access="isAuthenticated()">
				
				<li><a href="<c:url value="/profile/${user.userId}" />">${user.name} (ID: ${user.userId})</a></li>
				<li><a href="<c:url value="/profile/${user.userId}" />"><spring:message code="profile" /></a></li>
				<li><a href="javascript:logout()"><spring:message code="logout" /></a></li>
				
				<c:url var="logoutUrl" value="/j_spring_security_logout"/>
				<form action="${logoutUrl}" method="POST" id="logoutForm">
				  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			 
				<script>
					function logout() {
						$('#logoutForm').submit();
					}
				</script>
			</security:authorize>
			
			<!-- c:if test="${empty user}"-->
			<security:authorize access="isAnonymous()">
				<li><a href="<c:url value="/client/register" />"> <spring:message code="client.registration" /></a> </li>
			</security:authorize>
			<!--/c:if-->
			
		</ul>
	</div>
</div>