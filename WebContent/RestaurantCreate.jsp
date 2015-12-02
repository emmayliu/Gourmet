<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Restaurant of Gourmet</title>
</head>
<body>
	<h1>Create a Restaurant of Gourmet</h1>
	<form action="restaurantcreate" method="post">
		<p>
			<label for="restaurantId">RestaurantId</label>
			<input id="restaurantId" name="restaurantId" value="">
		</p>
		<p>
			<label for="restaurantname">RestaurantName</label>
			<input id="restaurantname" name="restaurantname" value="">
		</p>
		<p>
			<label for="acceptCreditCard">AcceptsCreditCard</label>
			<input id="acceptCreditCard" name="acceptCreditCard" value="">
		</p>
		<p>
			<label for="wifi">WIFI</label>
			<input id="wifi" name="wifi" value="">
		</p>
		<p>
			<label for="priceRange">PriceRange</label>
			<input id="priceRange" name="priceRange" value="">
		</p>
		<p>
			<label for="openTime">OpenTime</label>
			<input id="openTime" name="openTime" value="">
		</p>
		<p>
			<label for="closeTime">CloseTime</label>
			<input id="closeTime" name="closeTime" value="">
		</p>
		<p>
			<label for="noiseLevel">NoiseLevel</label>
			<input id="noiseLevel" name="noiseLevel" value="">
		</p>
		<p>
			<label for="neighborhood">Neighborhood</label>
			<input id="neighborhood" name="neighborhood" value="">
		</p>
		<p>
			<label for="star">Star</label>
			<input id="star" name="star" value="">
		</p>
		<p>
			<label for="parking">Parking</label>
			<input id="parking" name="parking" value="">
		</p>
		<p>
			<label for="street">Street</label>
			<input id="street" name="street" value="">
		</p>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="zip">ZipCode</label>
			<input id="zip" name="zip" value="">
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