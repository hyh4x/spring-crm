<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>Customer Form</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
				rel="stylesheet" 
				integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
				crossorigin="anonymous">
</head>
<body>
	<div class="wrapper">
		<div id="header">
			<h2>CRM APP</h2>
		</div>
	
	<div class="form-area">
		<form:form action="saveCustomer" modelAttribute="customer" method="POST">
			<form:hidden path="id"/>
			
			<table class="table table-dark">
				<thead>
					<tr>
						<th colspan="2" class="text-center">Save Customer</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName"/></td>						
					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName"/></td>						
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email"/></td>						
					</tr>
					<tr>
						<td><label></label></td>
						<td>
							<button type = "submit" value="Save" class="btn btn-secondary btn-lg">
								Save
							</button>
						</td>
					</tr>
				</tbody>			
			</table>
		</form:form>
		
		<div style="clear; both;"></div>
		
		<p>
			<a href = "${pageContext.request.contextPath}/customer/list" class="btn btn-secondary">
				Back to List
			</a>
		</p>
	</div>
	</div>
</body>
</html>