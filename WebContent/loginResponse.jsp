<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Response</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap2.min.css">
    <link rel="stylesheet" href="css/styles2.min.css">
</head>
<body>
    <div class="login-card"><div id="div-second"><h4 id="p-first"></h4></div><img src="img/avatar_2x.png" class="profile-img-card">
        <p class="profile-name-card"> </p>
        <form class="form-signin" action="Auth" method="post"><span class="reauth-email"> </span><input class="form-control" type="email" placeholder="Email address" required autofocus id="inputEmail" name="useremail"><input class="form-control" type="password" required autofocus placeholder="Password" id="inputPassword" name="userpassword">
        <button class="btn btn-primary btn-block btn-lg btn-signin" type="submit">Sign in</button></form><a href="Register.html">Create an account</a>
    </div>
    <script src="js/jquery2.min.js"></script>
    <script src="bootstrap/js/bootstrap2.min.js"></script>
        <script>
    $(document).ready(function(){
    $("#p-first").html(" <%= (String)request.getAttribute("lr")  %>");
    });
    $("#p-first").fadeOut(5000);
    </script>
</body>
</html>