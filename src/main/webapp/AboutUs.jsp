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
	<link href="../CSS/navbar.css" rel="stylesheet" type="text/css">
<html>
    <body>
        
        	<nav>
				<a class="logo" href="/">MyLibrary</a>
				<ul class="nav-bar">
					<li class="nav-bar_item"><a href="/getbooks">Books</a></li>
					<li class="nav-bar_item"><a href="/getstudents">Students</a></li>
					<li class="nav-bar_item"><a href="/getstaffs">Staff</a></li>
					<li class="nav-bar_item"><a href="/getmanagers">Managers</a></li>
					<li class="nav-bar_item"><a href="/getretailers">Retailer</a></li>
					<li class="nav-bar_item"><a href="/getshelves">Shelves</a></li>
					<li class="nav-bar_item"><a href="/getstocks">Stocks</a></li>
					<li class="nav-bar_item"><a href="/aboutus">About Us</a></li>
					<c:if test="${manager ne null || student ne null || staff ne null}">
						<li class="nav-bar_item"><a href="/logout">Logout</a></li>
					</c:if> 
				</ul>
			</nav>
            <img src="../images/aboutusimg.jpg" style="margin-left: 180px;margin-top: 140px;
            height: 500px;
            width: 500px;border-radius:10px;">
       
                
                <div style="margin-left: 780px;margin-top: -500px;">
                    <h1 style="font-size: 60px; font-weight: bold; color: darkblue;">About Us</h1><br>
                    <h5 style="font-size: 36px; font-weight: bold; color: darkslateblue;">MyLibrary</h5><br>
                    <p style="text-align: justify; width: 500px; font-size:22px;">
                    	This is a library management website which aims at securely storing and operating
                    	the information for a library database through a properly engineered user interface.
                    	It is built on Cutting edge backend technology; which includes Spring MVC along with MySQL
                    	database. Users can register and login as 3 different roles, namely student, staff and manager
                    	with appropriate permission of accessing and processing the data.
                    </p>
             
                </div>
                <br>
                <!--  
                <button style="border-radius: 8px; background-color: darkblue; border: none;
                color: white;
                padding: 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 20px;
                font-weight: bold;
                margin: 4px 2px;
                cursor: pointer;
                margin-left: 780px;">Home</button>-->
                
    </body>
</html>