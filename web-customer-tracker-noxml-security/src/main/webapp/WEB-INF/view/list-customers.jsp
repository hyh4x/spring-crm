<%@page import="no.group.springdemo.util.SortBy"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<title>Customer List</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
			rel="stylesheet" 
			integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
			crossorigin="anonymous">
</head>


<body>
	<div id = "wrapper">
		<div id= "header">
			<h2>CRM APP</h2>
		</div>
	</div>
	<div id = "container">
		<div id="content">
			<form:form style="margin: 0; padding: 0; text-align: right;" action="${pageContext.request.contextPath}/logout">
				<div class="d-flex align-items-center justify-content-end">
					You're logged in as 
					<i class="mx-3">
						<sec:authentication property="principal.username"/>,
						<sec:authentication property="principal.authorities"/>
					</i>
					<input type="submit" value="Logout" class="btn btn-secondary mx-3" />
				</div>
			</form:form>
				<div class="d-flex align-items-center justify-content-start">
					<form:form action="search" method="GET">
						<b>SEARCH FOR CUSTOMER:</b><input type="text" name="searchName" class="mx-2"/>
						<input type="submit" value="Search" class="btn btn-secondary my-2" />
					</form:form>
					<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
						<input type="button" value="Add Customer" 
							onclick="window.location.href='showFormForAdd'; return false;"
							class="btn btn-secondary my-2 mx-2" />
					</sec:authorize>
				</div>
			<table class="table table-bordered table-dark table-striped">
				<c:url var="sortLinkFirstName" value="/customer/list">
						<c:param name="sortBy" value="<%= SortBy.FIRST_NAME.name() %>" />
				</c:url>
				<c:url var="sortLinkLastName" value="/customer/list">
						<c:param name="sortBy" value="<%= SortBy.LAST_NAME.name() %>" />
				</c:url>
				<c:url var="sortLinkEmail" value="/customer/list">
						<c:param name="sortBy" value="<%= SortBy.EMAIL.name() %>" />
				</c:url>
				<tr>
					<th> <a href=${sortLinkFirstName} class="header-link">First Name</a></th>
					<th> <a href=${sortLinkLastName} class="header-link">Last Name</a></th>
					<th> <a href=${sortLinkEmail} class="header-link">Email</a></th>
					<th> Action </th>
				</tr>
				
				<c:forEach var="tempCustomer" items="${customers}">
					<!-- Update Link -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<!-- Delete Link -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					
					<tr class="align-middle">
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						<td> 
							<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
								<a href = "${updateLink}" class="btn btn-primary">Update</a>
							</sec:authorize>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								|
								<a href = "${deleteLink}" class="btn btn-danger"
								   onclick = "if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
							</sec:authorize>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			
			
		</div>
	
	</div>
	
	

</body>

</html>