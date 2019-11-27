<%-- 
    Document   : login
    Created on : Mar 30, 2017, 12:53:00 PM
    Author     : sgreen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US">
    <html>
        <head>
            <link rel="stylesheet" href="resources/css/main.css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Admin Login Page</title>
        </head>
        <form method="post" action="AdminLoginServlet">
            <div class="imgcontainer">
                <img src="images/admin.png" alt="Avatar" class="avatar">
            </div>
            <div class="container">
                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required>
                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>
                <input type="submit" value="Login" />
                
                <a href="login.jsp" style="margin-left:30px;">Customer Login</a>
    </div>
</form>
</html>
