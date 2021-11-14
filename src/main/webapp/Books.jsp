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
	
		<c:if test="${manager ne null && Dept eq 101}">
			<a class="btn btn-success" href="/issue/book" style="left: 400px;">Issue Book</a>
			<a class="btn btn-primary" href="/return/book" style="left: 390px;">Return Book</a>
			<a class="btn btn-primary" href="/updatebook" style="left: 380px;">Update Book</a>
		</c:if>
	
	<div class="list-group">
		<h1>
			<label style="color: green; position: relative; left: 45%;">Books
			</label>
		</h1>
		<form class="form-inline my-2 my-lg-0" action="/getbook" method="get" style="justify-content: center;">
			<input style="border:1px solid green; width:350px;" class="form-control mr-sm-2 " type="text"
				placeholder="Enter Book ID to be searched" aria-label="Search" style="width: 50%;"
				name="id">
			<button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
		</form>
		
		<c:if test="${manager ne null && Dept eq 101}">
			<br>
			<form class="form-inline my-2 my-lg-0" action="/deletebook" method="post" style="justify-content: center;">
			<input style="border:1px solid green; width:350px;" class="form-control mr-sm-2 " type="text"
				placeholder="Enter Book ID to be removed" aria-label="Delete" style="width: 50%;"
				name="id">
				<button class="btn btn-danger my-2 my-sm-0" type="submit">Delete</button>
			</form>
		</c:if>
		
		<br><br>
		
		<c:if test="${ error ne null }">
			<p>THIS BOOK DOESN'T EXIST</p>
		</c:if>
		
		<c:if test="${ qq ne null }">
			<div class="card border border-success">

				<div class="card-header   bg-info text-white" name="book name">
				
				<!--  <form action="/issue/book" method="GET">
					<input type="hidden" value="${q.bookid}" name="bookid"> <input
						type="submit" value="issue" name="submit"
						class="btn btn-success btn-lg"
						style="position: absolute; right: 0px; top: 0px; padding: 10px, 20px;">
				</form>
				
				-->
				</div>
				<div class="card-body">
					<h5 class="card-title">
						Book_ID: <span style="color: #6c757d !important">${qq.bookid }</span>
					</h5>
					<h5 class="card-title">
						Shelf_ID: <span style="color: #6c757d !important">${qq.shelfid}</span>
					</h5>
					<h5 class="card-title">
						Title: <span style="color: #6c757d !important">${qq.title}</span>
					</h5>
					<h5 class="card-title">
						Author: <span style="color: #6c757d !important">${qq.author }</span>
					</h5>
					<h5 class="card-title">
						Publications:<span style="color: #6c757d !important">
							${qq.publications }</span>
					</h5>
					<h5 class="card-title">
						Language:<span style="color: #6c757d !important">
							${qq.language }</span>
					</h5>
				</div>
				<div class="card-footer text-muted">
					<strong>Status:</strong>
					<c:if test="${manager ne null && Dept eq 101}">
						<c:if test="${qq.isissued == true }">
							<a href="/updatebook"> <input type="submit"
								value="available" name="submit" class="btn btn-success btn-lg">
							</a>
						</c:if>
						<c:if test="${qq.isissued==false }">
							<a href="/updatebook"> <input type="submit"
								value="Not available" name="submit"
								class="btn btn-danger btn-lg">
							</a>
						</c:if>
					</c:if>
					<c:if test="${student ne null || staff ne null}">
						<c:if test="${qq.isissued == false }"> Available  </c:if>
						<c:if test="${qq.isissued==true}"> Currently Not available</c:if>
					</c:if>
				</div>
			</div>
		</c:if>

		<c:forEach begin="0" end="${qresult.size()}" var="q" items="${qresult}">

			<div class="card border border-success">

				<div class="card-header   bg-info text-white" name="book name">
				
				<!--  <form action="/issue/book" method="GET">
					<input type="hidden" value="${q.bookid}" name="bookid"> <input
						type="submit" value="issue" name="submit"
						class="btn btn-success btn-lg"
						style="position: absolute; right: 0px; top: 0px; padding: 10px, 20px;">
				</form>
				
				-->
				</div>
				<div class="card-body">
					<h5 class="card-title">
						Book_ID: <span style="color: #6c757d !important">${q.bookid }</span>
					</h5>
					<h5 class="card-title">
						Shelf_ID: <span style="color: #6c757d !important">${q.shelfid}</span>
					</h5>
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
						Language:<span style="color: #6c757d !important">
							${q.language }</span>
					</h5>
				</div>
				<div class="card-footer text-muted">
					<strong>Status:</strong>
						<c:if test="${q.isissued == false }"> Available  </c:if>
						<c:if test="${q.isissued==true}"> Currently Not available</c:if>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>