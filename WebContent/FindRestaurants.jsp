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
			  <p>
				<div class="user"><label for="restaurantId"><h5>RESTAURANT ID</h5></label>
				<input type="text" class="user" id="restaurantId" name= "restaurantId" value= "${fn:escapeXml(param.restaurantId)}" ></div>
			  </p>
		      <p>
				<div class="pass"><label for="restaurantName"><h5>NAME</h5></label>
				<input type="text" class="pass" id="restaurantName" name= "restaurantName" value= "${fn:escapeXml(param.restaurantName)}"
				      ></div>
		      </p>
		      <p>
				<div class="user"><label for="priceRange"><h5>PRICE RANGE</h5></label>
				<input type="text" class="user" id="priceRange" name="priceRange" value="${fn:escapeXml(param.priceRange)}"
				      ></div>
		      </p>
		      <p>
				<div class="user"><label for="star"><h5>STAR</h5></label>
				<input type="text" class="user" id="star" name="star" value="${fn:escapeXml(param.star)}"
				      ></div>
		      </p>
			<p>
			<div class="pass" style= "color: #ffffff"><label for="city"><h5>CITY</h5></label>
			</br>
			<select class="custom-select" name="city">
				<option value="" disabled selected>Choose City</option>
				<option value="Phoenix"><h5>Phoenix</h5></option>
				<option value="Edinburgh"><h5>Edinburgh</h5></option>
				<option value="Charlotte"><h5>Charlotte</h5></option>
				<option value="Las Vegas"><h5>Las Vegas</h5></option>
				<option value="Pittsburgh"><h5>Pittsburgh</h5></option>
				<option value="Madison"><h5>Madison</h5></option>
				<option value="Montreal"><h5>Montreal</h5></option>
				<option value="Mesa"><h5>Mesa</h5></option>
				<option value="Gilbert"><h5>Gilbert</h5></option>
				<option value="Scottsdale"><h5>Scottsdale</h5></option>
				<option value="Musselburgh"><h5>Musselburgh</h5></option>
				<option value="Karlsruhe"><h5>Karlsruhe</h5></option>
				<option value="Tempe"><h5>Tempe</h5></option>
				<option value="Henderson"><h5>Henderson</h5></option>
				<option value="Buckeye"><h5>Buckeye</h5></option>
				<option value="Verdun"><h5>Verdun</h5></option>
			</select>
		    </div>
		    </p>
		    </br>
		    <p>
			<div class="pass" style= "color: #ffffff"><label for="cuisineType"><h5>CUISINE TYPE</h5></label>
			</br>
			<select class="custom-select" name="cuisineType">
				<option value="" disabled selected>Choose Cuisine Type</option>
				<option value="Bars"><h5>Bars</h5></option>
				<option value="Mexican"><h5>Mexican</h5></option>
				<option value="Chinese"><h5>Chinese</h5></option>
				<option value="AmericanNew"><h5>AmericanNew</h5></option>
				<option value="AmericanTraditional"><h5>AmericanTraditional</h5></option>
				<option value="Sandwiches"><h5>Sandwiches</h5></option>
				<option value="Seafood"><h5>Seafood</h5></option>
				<option value="Buffets"><h5>Buffets</h5></option>
				<option value="Greek"><h5>Greek</h5></option>
				<option value="Indian"><h5>Indian</h5></option>
				<option value="SteakHouse"><h5>SteakHouse</h5></option>
				<option value="Nightlife"><h5>Nightlife</h5></option>
				<option value="Korean"><h5>Korean</h5></option>
				<option value="Pizza"><h5>Pizza</h5></option>
				<option value="Thai"><h5>Thai</h5></option>
				<option value="Italian"><h5>Italian</h5></option>
			</select>
		    </div>
		    </p>
		    </br>
		    <p>
			<div class="pass" style= "color: #ffffff"><label for="goodFor"><h5>GOOD FOR</h5></label>
			</br>
			<select class="custom-select" name="goodFor">
				<option value="" disabled selected>Choose Good For</option>
				<option value="GoodForBrunch"><h5>Brunch</h5></option>
				<option value="GoodForDinner"><h5>Dinner</h5></option>
				<option value="GoodForBreakfast"><h5>Breakfast</h5></option>
				<option value="GoodForLunch"><h5>Lunch</h5></option>
				<option value="GoodForDessert"><h5>Dessert</h5></option>
				<option value="GoodForLateNight"><h5>LateNight</h5></option>
				<option value="GoodForKids"><h5>Kids</h5></option>
				<option value="GoodForGroups"><h5>Groups</h5></option>
			</select>
		    </div>
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
		<div class="footer" style= "color: #ffffff;">
     <p><h5>Copyright &copy; 2015 Gourmet. All Rights Reserved | Design by BEE</h5></a></p>
</div> 
</div>
</body>
</html>