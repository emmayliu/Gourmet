<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User of Gourmet</title>
</head>
<body>
	<form action="findusers" method="post">
		<h1>Search for a User by Name </h1>
		<p>
			<label for="username">Name</label>
			<input id="username" name = "username" value = "${fn:escapeXml(param.name)}">
		</p>
		<p>
			<input type = "submit">
			<br/><br/><br/>
			<span id = "successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id = "userCreate"><a href="usercreate">Create User</a></div>
	<br/>
	<h1>Matching Users</h1>
		<table border="1">
			<tr>
				<th>UserId</th>
                <th>Name</th>
                <th>Password</th>
                <th>CreateSince</th>
                <th>ReviewCount</th>
                <th>Gender</th>
                <th>Delete User</th>
                <th>Update User</th>
			</tr>
			<c:forEach items = "${users}" var = "user">
				<tr>
					<td><c:out value="${user.getUserId()}" /></td>
					<td><c:out value="${user.getName()}" /></td>
					<td><c:out value="${user.getPassword()}" /></td>
					<td><fmt:formatDate value="${user.getCreatedSince()}" pattern="yyyy-MM-dd"/></td>
					<td><c:out value="${user.getReviewCount()}" /></td>
					<td><c:out value="${user.getGender()}" /></td>
                    <td><a href="userdelete?userId=<c:out value="${user.getUserId()}"/>">Delete</a></td>
                    <td><a href="userupdate?userId=<c:out value="${user.getUserId()}"/>">Update</a></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>