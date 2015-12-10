<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Gourmet Restaurant Review</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath} css/bootstrap.css">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery.min.js"></script>
<link href='//fonts.googleapis.com/css?family=Droid+Serif:400,400italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>

<script>
$(document).ready(function(c) {
	$('.alert-close').on('click', function(c){
		$('.vlcone').fadeOut('slow', function(c){
			$('.vlcone').remove();
		});
	});	  
});
</script>
<!-- //script for close -->
</head>
<body>
<div class="main" >
	<h1>GOURMET RESTAURANT</h1>
	
	<div class="hotel-right  vlcone">
		<div class="alert-close"> </div>
		<div class="pay-form">
		<img src="${pageContext.request.contextPath} images/body-bg.jpg" />	
			<form>
				<h3>Restaurants on Gourmet</h3>
				<h5>RESTAURANT ID</h5>
				<input type="text" value="James Thompson" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'James Thompson';}" required="">

				<h5>NAME</h5>
				<input class="card_logo" type="text" value="2525 2525 2525 2525" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '2525 2525 2525 2525';}" required="">

				<h5>ZIP CODE</h5>
				<select id="country" onchange="change_country(this.value)" class="frm-field required">
					<option value="null">January</option>
					<option value="null">February</option>         
					<option value="AX">March</option>
					<option value="AX">April</option>
					<option value="AX">May</option>
					<option value="AX">June</option>
					<option value="AX">July</option>
					<option value="AX">August</option>
					<option value="AX">September</option>
					<option value="AX">October</option>
					<option value="AX">November</option>
					<option value="AX">December</option>
				</select>
				<div class="clear"></div>
				<h5>PRICE RANGE</h5>
				<input type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" required="">
				<p>
				</p>
				<input type="submit" value="FIND RESTAURANT">
			</form>
			<p><span></span>Your information is encrypted.</p>
		</div>
	</div>
	<div class="hotel-left">
		<div class="hotel-text">
			<h2>ROYAL PALACE</h2>
			<h3> $250.00 / <span>night</span></h3>
			<p>Entire Room for 5 members.</p>
			<p>Thursday, Dec 10, 2014 to Thursday, Dec 12, 2014.</p>
		</div>
	</div>
	<div class="clear"></div>
	<p class="footer">&copy; 2015 Hotel Checkout Form. All Rights Reserved | Design by BEE</a></p>
</div>
</body>
</html>