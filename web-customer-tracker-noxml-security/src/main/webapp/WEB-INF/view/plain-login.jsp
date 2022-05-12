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
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
				rel="stylesheet" 
				integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
				crossorigin="anonymous">
</head>
<body>
	<div class="wrapper">
		<table style="width: 400px" class="table table-dark table-striped">
			<tr>
				<th colspan="2" class="text-center">Sign in</th>
			</tr>
			<form:form action="${pageContext.request.contextPath}/authenticate"
						method="POST">
			<tr>
				<td>
					
						<c:if test="${param.error != null}">
							<p>
								<b class="failed">Sorry, invalid username or password</b>
							</p>
						</c:if>
				</td>
			</tr>
						<tr><td>
						Username: <input type="text" name="username" />
						</td></tr>
						<tr><td>
						Password: <input type="password" name="password" />
						</td></tr>
						<tr class="text-center"><td>
						<input type="submit" value="Login" class="btn btn-secondary"/>
						</td>
					
				</td>
			</tr>
			</form:form>
		</table>
		
		
		<form:form action="${pageContext.request.contextPath}/registration/showRegistrationForm"
			method="GET">
			<input type="submit" value="Register New User" class="btn btn-secondary"> 
		</form:form>
	</div>
</body>
</html>