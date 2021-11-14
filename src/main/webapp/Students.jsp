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
  height:20px;
  width:600px;
  text-align:centre;
  font-size:22px;
  font-weight:bold;
  padding:50px 150px;
  margin-left: 30%; 
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
			<li class="nav-bar_item"><a href="/aboutus">About Us</a></li>
			<li class="nav-bar_item"><a href="/logout">Logout</a></li>
		</ul>
	</nav>
	<br><br>
	
		<c:if test="${manager ne null && Dept eq 103}">
			<a class="btn btn-primary" href="/updatestudent" style="left: 380px;">Update Student</a>
		</c:if>
	
	<div class="list-group">
		<h1>
			<label style="color: green; position: relative; left: 45%;">Students
			</label>
		</h1>
		<form class="form-inline my-2 my-lg-0" action="/getstudent" method="get" style="justify-content: center;">
			<input style="border:1px solid green; width:350px;" class="form-control mr-sm-2 " type="text"
				placeholder="Enter Student ID to be searched" aria-label="Search" style="width: 50%;"
				name="id">
			<button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
		</form>
		
		<c:if test="${manager ne null && Dept eq 103}">
			<br>
			<form class="form-inline my-2 my-lg-0" action="/deletestudent" method="post" style="justify-content: center;">
			<input style="border:1px solid green; width:350px;" class="form-control mr-sm-2 " type="text"
				placeholder="Enter Student ID to be removed" aria-label="Delete" style="width: 50%;"
				name="id">
				<button class="btn btn-danger my-2 my-sm-0" type="submit">Delete</button>
			</form>
		</c:if>
		
		<br><br>
		
		<c:if test="${ error ne null }">
			<p>THIS STUDENT DOESN'T EXIST</p>
		</c:if>
		
		<c:if test="${ qq ne null }">
			<div class="card border border-success">

				<div class="card-header   bg-info text-white" name="book name">
				
				</div>
				<div class="card-body">
					<h5 class="card-title">
						Student ID: <span style="color: #6c757d !important">${qq.studentid}</span>
					</h5>
					<h5 class="card-title">
						First Name: <span style="color: #6c757d !important">${qq.fname}</span>
					</h5>
					<h5 class="card-title">
						Last Name: <span style="color: #6c757d !important">${qq.lname}</span>
					</h5>
					<h5 class="card-title">
						Age: <span style="color: #6c757d !important">${qq.age}</span>
					</h5>
					<h5 class="card-title">
						Gender:<span style="color: #6c757d !important">${qq.gender }</span>
					</h5>
					<h5 class="card-title">
						Contact :<span style="color: #6c757d !important">${qq.contact }</span>
					</h5>
					<h5 class="card-title">
						Email :<span style="color: #6c757d !important">${qq.email }</span>
					</h5>
					<h5 class="card-title">
						Street :<span style="color: #6c757d !important">${qq.street }</span>
					</h5>
					<h5 class="card-title">
						Area :<span style="color: #6c757d !important">${qq.area }</span>
					</h5>
					<h5 class="card-title">
						Pincode :<span style="color: #6c757d !important">${qq.pincode }</span>
					</h5>
					<h5 class="card-title">
						No. of books issued :<span style="color: #6c757d !important">${qq.booksissued }</span>
					</h5>
				</div>
			</div>
			
		</c:if>

		<c:forEach begin="0" end="${qresult.size()}" var="q" items="${qresult}">

			<div class="card border border-success">

				<div class="card-header   bg-info text-white" name="book name">
				
				</div>
				<div class="card-body">
					<h5 class="card-title">
						Student ID: <span style="color: #6c757d !important">${q.studentid}</span>
					</h5>
					<h5 class="card-title">
						First Name: <span style="color: #6c757d !important">${q.fname}</span>
					</h5>
					<h5 class="card-title">
						Last Name: <span style="color: #6c757d !important">${q.lname}</span>
					</h5>
					<h5 class="card-title">
						Age: <span style="color: #6c757d !important">${q.age}</span>
					</h5>
					<h5 class="card-title">
						Gender:<span style="color: #6c757d !important">${q.gender }</span>
					</h5>
					<h5 class="card-title">
						Contact :<span style="color: #6c757d !important">${q.contact }</span>
					</h5>
					<h5 class="card-title">
						Email :<span style="color: #6c757d !important">${q.email }</span>
					</h5>
					<h5 class="card-title">
						Street :<span style="color: #6c757d !important">${q.street }</span>
					</h5>
					<h5 class="card-title">
						Area :<span style="color: #6c757d !important">${q.area }</span>
					</h5>
					<h5 class="card-title">
						Pincode :<span style="color: #6c757d !important">${q.pincode }</span>
					</h5>
					<h5 class="card-title">
						No. of books issued :<span style="color: #6c757d !important">${q.booksissued }</span>
					</h5>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>