<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gourmet Restaurant Review</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/table.css" type="text/css" media="all" />
</head>
<body>
<div class="container" >
	<h1>FIND RESTAURANTS</h1>
	<div class="signin">
			<form action="findrestaurants" method="post">
			 <!--   <p>
				<div class="user"><label for="restaurantId">RESTAURANT ID</label>
				<input type="text" class="user" id="restaurantId" name= "restaurantId" value= "${fn:escapeXml(param.restaurantId)}" 
				       onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'XYZ';}"></div>
			  </p>-->
		      <p>
				<div class="pass"><label for="restaurantName">NAME</label>
				<input type="text" class="pass" id="restaurantName" name= "restaurantName" value= "${fn:escapeXml(param.restaurantName)}"
				       onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'McDonald\'s';}"></div>
		      </p>
		      <p>
				<div class="pass"><label for="zip">ZIP CODE</label>
				<input type="text" class="pass" id="zip" name="zip" value="${fn:escapeXml(param.zip)}"
				       onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '89109';}"></div>
				<div class="clear"></div>
			  </p>
		      <p>
				<div class="user"><label for="priceRange">PRICE RANGE</label>
				<input type="text" class="user" id="priceRange" name="priceRange" value="${fn:escapeXml(param.priceRange)}"
				       onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '3';}"></div>
		      </p>
		      <p>
				<input type = "submit">
				<br/><br/>
				<span id = "successMessage"><b>${messages.success}</b></span>
			  </p>
			</form>
			<div id = "restaurantCreate"><a href="restaurantcreate">Create Restaurant</a></div>
	</div>

	
	<div class="table">
			<table border="1" id ="keywords" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
	                <th>RestaurantName</th>
	                <th>CreditCard</th>
	                <th>WIFI</th>
	                <th>PriceRange</th>
	                <th>OpenTime</th>
	                <th>CloseTime</th>
	                <th>NoiseLevel</th>
	                <th>Neighborhood</th>
	                <th>Star</th>
	                <th>Parking</th>
	                <th>Street</th>
	                <th>City</th>
	                <th>State</th>
	                <th>ZipCode</th>
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
						<td><c:out value="${rest.getNeighborhood()}" /></td>
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
     <p>Copyright &copy; 2015 Gourmet. All Rights Reserved | Design by BEE</a></p>
</div> 
</div>
</body>
</html>