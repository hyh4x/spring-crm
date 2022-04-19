<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<table style="width: 400px">
		<tr>
			<th>Sign in</th>
		</tr>
		<tr>
			<td style="text-align: left">
				<form:form action="${pageContext.request.contextPath}/authenticate"
					method="POST">
					<c:if test="${param.error != null}">
						<p>
							<b class="failed">Sorry, invalid username or password</b>
						</p>
					</c:if>
					<p>
					USERNAME: <input type="text" name="username" />
					</p>
					<p>
					PASSWORD: <input type="password" name="password" />
					</p>
					<p>
					<input type="submit" value="Login" class="add-button"/>
					</p>
				</form:form>
			</td>
		</tr>
	</table>
	
	
	<form:form action="${pageContext.request.contextPath}/registration/showRegistrationForm"
		method="GET">
		<input type="submit" value="Register New User" class="add-button"> 
	</form:form>

</body>
</html>