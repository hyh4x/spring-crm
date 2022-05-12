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
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
				rel="stylesheet" 
				integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
				crossorigin="anonymous">
</head>
<body>
	<div class="wrapper">
		<div class="form-area">
			<table class="table table-dark table-striped">
				<tr>
					<th colspan="2" class="text-center">New user registration</th>
				</tr>
			<form:form action = "${pageContext.request.contextPath}/registration/processRegistrationForm"
					   modelAttribute="crmUser" method = "POST">
					<tr>
						<td width="30%">Username:</td>
						<td>
							<form:input type="text" path="username" />
						</td>
					</tr>
					<tr>
						<td>Password:</td>
						<td>
							<form:input type="password" path="password" />
						</td>
					</tr>
					<tr>
						<td>Confirm Password:</td>
						<td>
							<form:input type="password" path="matchingPassword" />
						</td>
					</tr>
					<tr>
						<td>First Name:</td>
						<td>
							<form:input type="text" path="firstName" />
						</td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td>
							<form:input type="text" path="lastName" />
						</td>
					</tr>
					<tr>
						<td>Email:</td>
						<td>
							<form:input type="text" path="email" />
						</td>
					</tr>
					<c:if test="${registrationError!=null}">
					 	<c:forEach items="${registrationError}" var="tempError">
					 		<tr>
						 		<td colspan="2">
						 			<p class="failed">${tempError}</p>
						 		</td>
					 		</tr>
					 	</c:forEach>
					</c:if>
					<td colspan="2" align="center">
						<button type="submit" value="Register" class="btn btn-secondary">
							Submit
						</button>
					</td>
				</table>
			</form:form>
		</div>
		<a href="${pageContext.request.contextPath}/showLoginPage" class="btn btn-secondary">
			Back to Login
		</a>
	</div>
</body>
</html>