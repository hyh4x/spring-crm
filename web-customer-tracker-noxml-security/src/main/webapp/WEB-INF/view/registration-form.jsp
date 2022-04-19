<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/registration.css" />
</head>
<body>
	<table>
		<tr>
			<th colspan="2">New user registration</th>
		</tr>
	<form:form action = "${pageContext.request.contextPath}/registration/processRegistrationForm"
			   modelAttribute="crmUser" method = "POST">
		<c:if test="${registrationError!=null}">
		 	<c:forEach items="${registrationError}" var="tempError">
		 		<tr>
			 		<td colspan="2">
			 			<p class="failed">${tempError}</p>
			 		</td>
		 		</tr>
		 	</c:forEach>
		</c:if>
		
			<tr>
				<td>USERNAME:</td>
				<td>
					<form:input type="text" path="username" />
				</td>
			</tr>
			<tr>
				<td>PASSWORD:</td>
				<td>
					<form:input type="password" path="password" />
				</td>
			</tr>
			<tr>
				<td>CONFIRM PASSWORD:</td>
				<td>
					<form:input type="password" path="matchingPassword" />
				</td>
			</tr>
			<tr>
				<td>FIRST NAME:</td>
				<td>
					<form:input type="text" path="firstName" />
				</td>
			</tr>
			<tr>
				<td>LAST NAME:</td>
				<td>
					<form:input type="text" path="lastName" />
				</td>
			</tr>
			<tr>
				<td>EMAIL:</td>
				<td>
					<form:input type="text" path="email" />
				</td>
			</tr>
			<td colspan="2" align="center">
				<input type="submit" value="Register"/>
			</td>
		</table>
	</form:form>
		<a href="${pageContext.request.contextPath}/showLoginPage">
			Back to Login
		</a>
</body>
</html>