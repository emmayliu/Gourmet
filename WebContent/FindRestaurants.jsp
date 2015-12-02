<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Restaurant of Gourmet</title>
</head>
<body>
	<form action="findrestaurants" method="post">
		<h1>Search for a Restaurant by attributes </h1>
		<p>
			<label for="restaurantId">RestaurantId</label>
			<input id="restaurantId" name= "restaurantId" value= "${fn:escapeXml(param.restaurantId)}">
		</p>
		<p>
			<label for="restaurantName">RestaurantName</label>
			<input id="restaurantName" name= "restaurantName" value= "${fn:escapeXml(param.restaurantName)}">
		</p>
		<p>
			<label for="zip">Zipcode</label>
			<input id="zip" name="zip" value="${fn:escapeXml(param.zip)}">
		</p>
		<p>
			<label for="priceRange">PriceRange</label>
			<input id="priceRange" name="priceRange" value="${fn:escapeXml(param.priceRange)}">
		</p>
		<p>
			<input type = "submit">
			<br/><br/><br/>
			<span id = "successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id = "restaurantCreate"><a href="restaurantcreate">Create Restaurant</a></div>
	<br/>
	<h1>Matching Restaurants</h1>
		<table border="1">
			<tr>
				<th>RestaurantId</th>
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
			<c:forEach items = "${restaurants}" var = "rest">
				<tr>
					<td><c:out value="${rest.getRestaurantId()}" /></td>
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
			</c:forEach>
		</table>   
</body>
</html>