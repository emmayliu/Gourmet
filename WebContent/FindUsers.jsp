<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gourmet User Search</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/table.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/navigation.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/fonts.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/fontFace.css" type="text/css" media="all" />
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="script.js"></script>
</head>
<body>

<div id='cssmenu'>
<ul>
   <li><a href='/Gourmet/Controller'><span>HOME</span></a></li>
   <li class='active has-sub'><a href='#'><span>RESTAURANT</span></a>
      <ul>
         <li class='has-sub'><a href='/Gourmet/findrestaurants'><span>Find Restaurants</span></a>
         </li>
         <li class='has-sub'><a href='/Gourmet/restaurantcreate'><span>Create Restaurants</span></a>
         </li>
         <li class='has-sub'><a href='/Gourmet/restaurantupdate'><span>Update Restaurants</span></a>
         </li>
         <li class='has-sub'><a href='/Gourmet/restaurantdelete'><span>Delete Restaurants</span></a>
         </li>
      </ul>
   </li>
   <li class='active has-sub'><a href='#'><span>USER</span></a>
      <ul>
         <li class='has-sub'><a href='/Gourmet/findusers'><span>Find Users</span></a>
         </li>
         <li class='has-sub'><a href='/Gourmet/usercreate'><span>Create Users</span></a>
         </li>
         <li class='has-sub'><a href='/Gourmet/userupdate'><span>Update Users</span></a>
         </li>
         <li class='has-sub'><a href='/Gourmet/userdelete'><span>Delete Users</span></a>
         </li>
      </ul>
   </li>
   <li class='active has-sub'><a href='#'><span>REVIEW</span></a>
      <ul>
         <li class='has-sub'><a href='/Gourmet/findreviews'><span>Find Reviews</span></a>
         </li>
         <li class='has-sub'><a href='/Gourmet/reviewcreate'><span>Create Reviews</span></a>
         </li>
      </ul>
   </li>
</ul>
</div>


<div class="container" >
	<h1>FIND USERS</h1>
	<div class="signin">
		<form action="findusers" method="post">
			<p>
				<div class="user"><label for="username"><h5>NAME</h5></label>
				<input type="text" class="user" id="username" name = "username" value = "${fn:escapeXml(param.name)}"
				onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Pizano';}"></div>
			</p>
			<p>
				<input type = "submit">
				<br/>
				<span id = "successMessage"><b><h5>${messages.success}</h5></b></span>
			</p>
		</form>
		<br/>
		<div id = "userCreate"><a href="usercreate"><h5>Create User</h5></a></div>
	</div>
	
	<br/>
	<div class="table">
		<table border="1" id ="keywords" cellspacing="0" cellpadding="0">
			<thead>
			<tr>
				<th><h5>UserId</h5></th>
                <th><h5>Name</h5></th>
                <th><h5>Password</h5></th>
                <th><h5>CreateSince</h5></th>
                <th><h5>ReviewCount</h5></th>
                <th><h5>Gender</h5></th>
                <th><h5>Delete User</h5></th>
                <th><h5>Update User</h5></th>
			</tr>
			</thead>
			<c:forEach items = "${users}" var = "user">
			<tbody>
			<tr>
				<td><h5><c:out value="${user.getUserId()}" /></h5></td>
				<td><h5><c:out value="${user.getName()}" /></h5></td>
				<td><h5><c:out value="${user.getPassword()}" /></h5></td>
				<td><h5><fmt:formatDate value="${user.getCreatedSince()}" pattern="yyyy-MM-dd"/></h5></td>
				<td><h5><c:out value="${user.getReviewCount()}" /></h5></td>
				<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
				<c:set var="gender" value="${user.getGender()}"/>
				  <td><h5><c:choose>
					<c:when test="${gender == 'true'}">M</c:when>
   					<c:when test="${gender == 'false'}">F</c:when>
					</c:choose>
				</h5></td>
                <td><a href="userdelete?userId=<c:out value="${user.getUserId()}"/>"><h5>Delete</h5></a></td>
                <td><a href="userupdate?userId=<c:out value="${user.getUserId()}"/>"><h5>Update</h5></a></td>
			</tr>
			</tbody>
			</c:forEach>
		</table>
	</div>
	<div class="footer" style= "color: #ffffff;">
    <p><h5>Copyright &copy; 2015 Gourmet. All Rights Reserved | Design by BEE</h5></a></p>
</div>
</body>
</html>