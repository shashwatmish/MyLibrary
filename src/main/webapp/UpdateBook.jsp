<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<div class="p-3 mb-2 bg-primary text-white"  style="text-align:center;"><h3>Update A Book</h3></div>
<div class="container">



	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<article class="card-body">
					<form action="/updatebook" method="post" modelAttribute="book">
						
						<div class="form-row">
							<div class="col form-group">
								<label>Book-ID (of the book to be updated) </label> <input name="bookid" type="text"
									class="form-control" placeholder="" required>
							</div>
						</div>
						
						<div class="form-row">
							<div class="col form-group">
								<label>Shelf-ID </label> <input name="shelfid" type="text"
									class="form-control" placeholder="" required>
							</div>
						</div>
						
						
						<div class="form-group">
							<label>Language</label> <input class="form-control"
								name="language" type="text" required>
						</div>
						
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								Update</button>
						</div>
					</form>
				</article>
				<!-- card-body end .// -->
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
