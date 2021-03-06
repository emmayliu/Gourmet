<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Gourmet User Delete</title>
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
	<h1>${messages.title}</h1>
	<div class="signin">
			<form action="userdelete" method="post">
		      <p>
				<div class="pass" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="userId"><h5>USER ID</h5></label>
				<input type="text" class="pass" id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
			   </div>
			</p>
			<p>
				<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<input type="submit">
				</span>
			</p>
			<p>
				<span id = "successMessage"><b>${messages.success}</b></span>
			</p>
			</form>	
	</div>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</div>
<div class="footer" style= "color: #ffffff;">
     <p><h5>Copyright &copy; 2015 Gourmet. All Rights Reserved | Design by BEE</h5></a></p>
</div>
	<br/><br/><br/> 	
</body>
</html>