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

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<title>Hello, world!</title>
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
	</c:if>


<div class="p-3 mb-2 bg-primary text-white"  style="text-align:center;"><h3>STUDENT LOGIN</h3></div>
<br><br><br>

	<div class="container">


		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<header class="card-header">

						<!-- <a href="register"
							class="float-right btn btn-outline-primary mt-1">Sign Up</a>-->
						<h4 class="card-title mt-2">Log In</h4>
					</header>
					<article class="card-body">
						<form action="/student/login" method="post">
						
							<div class="form-group">
								<label>Student-ID</label> <input name="id" type="id"
									class="form-control" placeholder="",style="height:80px;"> 
							</div>


							<div class="form-group">
								<label>Password</label> <input class="form-control"
									name="password" type="password">
							</div>
							<!-- form-group end.// -->


							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block" >
									Login</button>
							</div>


						</form>
					</article>
					<!-- card-body end .// -->
					<!--<div class="border-top card-body text-center">
						Don't Have an account? <a href="">Sign Up</a>
					</div>-->
				</div>
				<!-- card.// -->
			</div>
			<!-- col.//-->

		</div>
		<!-- row.//-->


	</div>
	<!--container end.//-->

	<br>
	<br>
	<br>
	<footer class="bg-secondary mb-3" style="height:150px;">
		<div class="card-body text-center" style="color:white;">
			<h4>&copy;MyLibrary 2021/22</h4>
		</div>
	</footer>
	</body>
</html>