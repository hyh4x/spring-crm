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
</head>


<body>
	<div id = "wrapper">
		<div id= "header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	<div id = "container">
		<div id="content">
			<form:form style="margin: 0; padding: 0; width: 72%; text-align: right;" action="${pageContext.request.contextPath}/logout">
			You're logged in as <i><sec:authentication property="principal.username"/>, <sec:authentication property="principal.authorities"/></i>.
			<input type="submit" value="Logout" class="add-button" />
			</form:form>
			<table>
				<tr class="white-table" style="width: 72%;">
					<td class="white-table" style="width: 450px">
					 	<form:form action="search" method="GET">
							Search customer:<input type="text" name="searchName" />
							<input type="submit" value="Search" class="add-button" />
						</form:form>
					</td>
					<td class="white-table">
						<input type="button" value="Add Customer" 
							onclick="window.location.href='showFormForAdd'; return false;"
							class="add-button" />
					</td>
				</tr>
			</table>
			
			<table>
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
					
					
					<tr>
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						<td> <a href = "${updateLink}">Update</a>
							|
							<a href = "${deleteLink}"
							   onclick = "if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a></td>
					</tr>
				</c:forEach>
				
			</table>
			
			
		</div>
	
	</div>
	
	

</body>

</html>