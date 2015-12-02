<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User of Gourmet</title>
</head>
<body>
	<h1>Create a User of Gourmet</h1>
	<form action="usercreate" method="post">
		<p>
			<label for="username">Name</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label for="password">Password</label>
			<input id="password" name="password" value="">
		</p>
		<p>
			<label for="reviewCount">ReviewCount</label>
			<input id="reviewCount" name="reviewCount" value="">
		</p>
		<p>
			<label for="gender">Gender</label>
			<input id="gender" name="gender" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	</br></br>
	<p>
		<span id="successManage"><b>${message.success}</b></span>
	</p>
</body>
</html>