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

<div class="p-3 mb-2 bg-primary text-white" style="text-align: center;">
	<h3>Register As Manager!</h3>
</div>
<div class="container">



	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<article class="card-body">
					<form id="form" action="/registermanager" method="post" modelAttribute="Manager">
						<div class="form-row">
							<div class="col form-group">
								<label>Department ID</label> <input name="deptid" type="text"
									class="form-control" placeholder="" required>
									<small class="form-text text-muted">Enter the ID of the department
									you would like to work for. <a href="/getdepts">To check the list click here</a></small>
								
							</div>

							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						
						<div class="form-row">
							<div class="col form-group">
								<label>First Name </label> <input name="fname" type="text"
									class="form-control" placeholder="" required>
							</div>

							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						
						<div class="form-row">
							<div class="col form-group">
								<label>Last Name </label> <input name="lname" type="text"
									class="form-control" placeholder="" required>
							</div>

							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						
						<div class="form-group">
							<label>Create password</label> <input class="form-control"
								name="password" type="password" required>
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
						
						<div class="form-group">
							<label>Contact-Number</label> <input type="text"
								name="contact" class="form-control" placeholder="" >
						</div>
						
						<div class="form-group">
							<label>Email address</label> <input name="email" type="email"
								class="form-control" placeholder="" required>
						</div>


						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								Register</button>
						</div>



						<!-- form-group// -->
						<small class="text-muted">By clicking the 'Sign Up'
							button, you confirm that you accept our <br> Terms of use
							and Privacy Policy.
						</small>
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