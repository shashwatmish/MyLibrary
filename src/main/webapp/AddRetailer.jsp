<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	function validateForm() {
		var form = document.getElementById("form");
	}
</script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<div class="p-3 mb-2 bg-primary text-white"  style="text-align:center;"><h3>Add a Retailer</h3></div>
<div class="container">

	<br>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<article class="card-body">
					<form id="form" action="/registerretailer" method="post" modelAttribute="retailer">
						<div class="form-row">
							<div class="col form-group">
								<label>First Name </label> <input name="fname" type="text"
									class="form-control" placeholder="" required>
							</div>
						</div>
						
						<div class="form-row">
							<div class="col form-group">
								<label>Last Name </label> <input name="lname" type="text"
									class="form-control" placeholder="" required>
							</div>
						</div>
						
						<div class="form-group">
							<label>Contact-number</label> <input type="tel"
								title="input 10 digit mobile number, Don't put +91" name="contact" class="form-control" placeholder="" pattern="[0-9]{10}"required>
						</div>
						
						<div class="form-group">
							<label>Email address</label> <input name="email" type="email"
								class="form-control" placeholder="" required> 
						</div>						
						
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								Add</button>
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
