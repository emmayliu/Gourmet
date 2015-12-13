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
<title>Find Restaurants on Gourmet</title>
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
	<h1>FIND RESTAURANTS</h1>
	<div class="signin">
			<form action="findrestaurants" method="post">
			 <!--  <p>
				<div class="user"><label for="restaurantId">RESTAURANT ID</label>
				<input type="text" class="user" id="restaurantId" name= "restaurantId" value= "${fn:escapeXml(param.restaurantId)}" 
				       onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'XYZ';}"></div>
			  </p>-->
		      <p>
				<div class="pass"><label for="restaurantName"><h5>NAME</h5></label>
				<input type="text" class="pass" id="restaurantName" name= "restaurantName" value= "${fn:escapeXml(param.restaurantName)}"
				      ></div>
		      </p>
		      <p>
				<div class="pass"><label for="zip"><h5>ZIP CODE</h5></label>
				<input type="text" class="pass" id="zip" name="zip" value="${fn:escapeXml(param.zip)}"
				      ></div>
				<div class="clear"></div>
			  </p>
		      <p>
				<div class="user"><label for="priceRange"><h5>PRICE RANGE</h5></label>
				<input type="text" class="user" id="priceRange" name="priceRange" value="${fn:escapeXml(param.priceRange)}"
				      ></div>
		      </p>
		      <p>
				<input type = "submit">
				<br/><br/>
				<span id = "successMessage"><b>${messages.success}</b></span>
			  </p>
			</form>
			<div id = "restaurantCreate"><a href="restaurantcreate"><h5>Create Restaurant</h5></a></div>
	</div>

	<br/>
	<div class="table">
			<table border="1" id ="keywords" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
	                <th><h5>Name</h5></th>
	                <th><h5>CreditCard</h5></th>
	                <th><h5>WIFI</h5></th>
	                <th><h5>PriceRange</h5></th>
	                <th><h5>Open</h5></th>
	                <th><h5>Close</h5></th>
	                <th><h5>Noise</h5></th>
	                <!--<th><h5>Neighborhood</h5></th>-->
	                <th><h5>Star</h5></th>
	                <th><h5>Parking</h5></th>
	                <th><h5>Street</h5></th>
	                <th><h5>City</h5></th>
	                <th><h5>State</h5></th>
	                <th><h5>ZipCode</h5></th>
	                <th><h5>Delete</h5></th>
	                <th><h5>Update</h5></th>
				</tr>
				</thead>
				<c:forEach items = "${restaurants}" var = "rest">
					<tbody>
					<tr>
						<td><c:out value="${rest.getName()}" /></td>
						<td><c:out value="${rest.getAcceptsCreditCard()}" /></td>
						<td><c:out value="${rest.getWIFI()}" /></td>
						<td><c:out value="${rest.getPriceRange()}" /></td>
						<td><fmt:formatDate value="${rest.getOpen()}" pattern="hh:mm:ss"/></td>
						<td><fmt:formatDate value="${rest.getClose()}" pattern="hh:mm:ss"/></td>
						<td><c:out value="${rest.getNoiseLevel()}" /></td>
						<!--  <td><c:out value="${rest.getNeighborhood()}" /></td>-->
						<td><c:out value="${rest.getStar()}" /></td>
						<td><c:out value="${rest.getParking()}" /></td>
						<td><c:out value="${rest.getStreet()}" /></td>
						<td><c:out value="${rest.getCity()}" /></td>
						<td><c:out value="${rest.getState()}" /></td>
						<td><c:out value="${rest.getZipCode()}" /></td>
	                    <td><a href="restaurantdelete?restaurantId=<c:out value="${rest.getRestaurantId()}"/>">Delete</a></td>
	                    <td><a href="restaurantupdate?restaurantId=<c:out value="${rest.getRestaurantId()}"/>">Update</a></td>
					</tr>
					</tbody>
				</c:forEach>
			</table> 
		</div> 
		<div class="footer">
     <p><h5>Copyright &copy; 2015 Gourmet. All Rights Reserved | Design by BEE</h5></a></p>
</div> 
</div>
</body>
</html>