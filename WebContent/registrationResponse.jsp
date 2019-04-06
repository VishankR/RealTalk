<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap3.min.css">
    <link rel="stylesheet" href="css/styles3.min.css">
</head>
<body>
    <div class="login-card">
    <div id="div-second"><h4 id="p-first"></h4></div>
        <form class="form-signin" action="Registration" method="post"> <span class="reauth-email"> </span><input class="form-control" type="text" required placeholder="User Name" autofocus id="inputname" name="username"><input class="form-control" type="email" required placeholder="Email address" autofocus id="inputEmail" name="useremail">
            <input class="form-control" type="password" required placeholder="Password" id="inputPassword" name="password">
            <button class="btn btn-primary btn-block btn-lg btn-signin" type="submit">Sign up</button></form>
        <p class="profile-name-card"> </p>
    </div>
    <script src="js/jquery3.min.js"></script>
    <script src="bootstrap/js/bootstrap3.min.js"></script>
        <script src="jquery/JQuery.js"></script>
    <script>
    $(document).ready(function(){
    $("#p-first").html(" <%= (String)request.getAttribute("st")  %>");
    });
    $("#p-first").fadeOut(5000);
    $(".btn btn-primary btn-block btn-lg btn-signin").click(function(){
    	<%
    	String s="Congrates! you are registered now Please Sign in now";
    	request.setAttribute("st2",s);  %>
    });
    </script>
</body>
</html>