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
	
<link href="../CSS/navbar.css" rel="stylesheet" type="text/css">

<style>
p{
  border-radius:10px;
  background-color: #E7E5FC ;    
  text-align: center;
  height:40px;
  widht:300px;
  text-align:centre;
  font-size:22px;
  font-weight:bold;
  padding:50px 40px;
}
</style>
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
		crossorigin="anonymous">
	</script>
	
	<nav>
		<a class="logo" href="/">MyLibrary</a>
		<ul class="nav-bar">
			<li class="nav-bar_item"><a href="/getbooks">Books</a></li>
			<li class="nav-bar_item"><a href="/getstudents">Students</a></li>
			<li class="nav-bar_item"><a href="/getstaffs">Staff</a></li>
			<li class="nav-bar_item"><a href="/getmanagers">Managers</a></li>
			<li class="nav-bar_item"><a href="/getretailers">Retailer</a></li>
			<li class="nav-bar_item"><a href="/getshelves">Shelves</a></li>
			<li class="nav-bar_item"><a href="/getstocks">Stock</a></li>
			<li class="nav-bar_item"><a href="">About Us</a></li>
			<li class="nav-bar_item"><a href="/logout">Logout</a></li>
		</ul>
	</nav>
	<br><br>
	
		<c:if test="${manager ne null && Dept eq 101}">
			<a class="btn btn-success" href="/addstock" style="left: 400px;">Add Stock</a>
			<a class="btn btn-danger" href="/deletestock" style="left: 390px;">Delete Stock</a>
			<a class="btn btn-primary" href="/updatestock" style="left: 380px;">Update Stock</a>
		</c:if>
	
	<div class="list-group">
		<br><br>
		<h1>
			<label style="color: green; position: relative; left: 45%;">Stocks
			</label>
		</h1>

		<c:forEach begin="0" end="${qresult.size()}" var="q" items="${qresult}">

			<div class="card border border-success">

				</div>
				<div class="card-body">
					<h5 class="card-title">
						Title: <span style="color: #6c757d !important">${q.title}</span>
					</h5>
					<h5 class="card-title">
						Author: <span style="color: #6c757d !important">${q.author }</span>
					</h5>
					<h5 class="card-title">
						Publications:<span style="color: #6c757d !important">
							${q.publications }</span>
					</h5>
					<h5 class="card-title">
						No. of books available :<span style="color: #6c757d !important">
							${q.booksavailable }</span>
					</h5>
					<h5 class="card-title">
						No. of books issued :<span style="color: #6c757d !important">
							${q.booksissued }</span>
					</h5>
				</div>
		</c:forEach>
	</div>
</body>
</html>