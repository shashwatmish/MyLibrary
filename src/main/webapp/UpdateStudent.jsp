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
<script>
	function validateForm() {
		var form = document.getElementById("form");
	}
</script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<div class="p-3 mb-2 bg-primary text-white"  style="text-align:center;"><h3>Update Student Details</h3></div>
<div class="container">



	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<article class="card-body">
					<form id="form" action="/updatestudent" method="post" modelAttribute="student">
					
					<div class="form-row">
							<div class="col form-group">
								<label>Student ID (of the student whose details have to be updated)</label> <input name="studentid" type="text"
									class="form-control" placeholder="" required>
							</div>
						</div>
						
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
							<label>Change password</label> <input class="form-control"
								name="password" type="password" required>
						</div>
						
						<div class="form-group">
							<label>Age</label> <input class="form-control"
								name="age" type="text" required>
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
							<label>Street</label> <input type="text" name="street"
								class="form-control" placeholder="" required>
						</div>
						
						<div class="form-group">
							<label>Area</label> <input type="text" name="area"
								class="form-control" placeholder="" required>
						</div>
						
						<div class="form-group">
							<label>Pin-code</label> <input type="text" name="pincode"
								class="form-control" placeholder="" required>
						</div>
						
						<!-- form-group end.// -->
						<div class="form-group">
							<label class="form-check form-check-inline"> <input
								class="form-check-input" type="radio" name="gender" value="M" required>
								<span class="form-check-label"> Male </span>
							</label> <label class="form-check form-check-inline"> <input
								class="form-check-input" type="radio" name="gender" value="F">
								<span class="form-check-label"> Female</span>
							</label>
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
