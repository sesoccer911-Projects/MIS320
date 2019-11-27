<%-- 
    Document   : home
    Created on : Apr 4, 2017, 5:35:48 PM
    Author     : Brandon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Blockbuster Home</title>
    <head>
        <link rel="stylesheet" href="resources/css/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home Page</title>
    </head>
    <form>
        <div class="imgcontainer">
            <img src="images/Blockbuster_logo.png" alt="Avatar" class="avatar">
        </div>
        <h1>
            <center>Video Rental</center>
        </h1>
    </form>
    <div class="button-container">
        <form method="get" action="login.jsp">
            <div>
                <button type="submit"class="loginbtn">Login</button>
            </div>
        </form>
        <form method="get" action="adminlogin.jsp">
            <div>
                <button type="submit"class="adminloginbtn">Admin Login</button>
            </div>
        </form>
    </div>


</form>
</html>
