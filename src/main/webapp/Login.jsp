<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<title>Home page</title>

<style>
body {
	background-image: url('../images/bg.jpg') ;
}
</style>
</head>
<body>
	
	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
		
		<script>
		function myFunction(var error) {
		  alert(error);
		}
		</script>

	<!-- Option 2: jQuery, Popper.js, and Bootstrap JS
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    -->

		<c:if test="${ error ne null }">
			<div class="alert alert-warning alert-dismissible" role="alert">
				<span type="button" class="close" data-dismiss="alert"
					aria-label="Close"><span aria-hidden="true">&times;</span></span> <strong>
					${error }</strong>
			</div>
			<!-- 
			<script>
				  myFunction(error);
			</script>
			-->
		</c:if>
	
	<!-- <p
		style="color: #fff; font-size: xxx-large; left: 40%; position: absolute; top: 50%;">Libro-Pro</p>
 -->


	<header class="masthead" style="height: 500px;">
		<div class="container ">
			<br> <br>
			<div
				class="row h-600 align-items-center justify-content-center text-center">
				<div class="col-lg-10 align-self-end">
					<a href="/">
					<h1 class="text-uppercase font-weight-bold" style="font-size: 100px; color:#00008B;">MyLibrary</h1>
					</a>
				</div>
				<div class="col-lg-9 align-self-baseline">
					<h3 class="text-white-75 font-weight-light mb-5"
						style="color: blue;">Login</h3>
				</div>
			</div>
		</div>
	</header>


		<a href="/student/login" class="btn btn-primary" style="position: absolute;top: 35%;left: 40%;width:300px;height:50px;font-size:22px;border-radius:8px;"><h3>Student</h3></a>
		<a href="/staff/login" class="btn btn-primary" style="position: absolute;top: 55%;left: 40%;width:300px;height:50px;font-size:22px;border-radius:8px;"><h3>Staff</h3></a>
		<a href="/manager/login" class="btn btn-primary" style="position: absolute;top: 75%;left: 40%;width:300px;height:50px;font-size:22px;border-radius:8px;"><h3>Manager</h3></a>

</body>
</html>