<%-- 
    Document   : login
    Created on : Mar 30, 2017, 12:53:00 PM
    Author     : sgreen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US">
    <html>
        <title>Blockbuster Customer Login</title>
        <head>
            <link rel="stylesheet" href="resources/css/main.css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Login Page</title>
        </head>
        <form method="post" action="LoginServlet">
            <div class="imgcontainer">
                <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
            </div>
            <div class="container">
                <label><b>Username</b></label>
                <input id="username" type="text" placeholder="Enter Username" name="username" >
                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>
                <input id="Login" type="submit" value="Login" />

                <a href="register.jsp" style="margin-left:30px;">Register Here</a>
            </div>
        </form>
        <script>
            /*jslint sub: true, maxerr: 50, indent: 4, browser: true */

            (function (global) {
                document.getElementById("Login").addEventListener("click", function () {
                    global.localStorage.setItem("mySharedData", document.getElementById("username").value);
                }, false);
            }(window));
        </script>
    </html>
