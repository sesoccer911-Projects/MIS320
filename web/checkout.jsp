<%-- 
    Document   : checkout
    Created on : Apr 29, 2017, 5:00:31 PM
    Author     : Brandon
--%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <title>Blockbuster Checkout</title>
    <head>
        <link rel="stylesheet" href="resources/css/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/sakila"
                           user="root"  password="nbuser"/>
        <%
            Object obj = request.getSession().getAttribute("username");
            
        %>

        <sql:query dataSource="${snapshot}" var="user_id">
            Select C.customer_id
            from cart as C
            join cartofitems as CI
            on CI.cart_id = C.cart_id
            join customer as CS
            on CS.customer_id = C.customer_id
            where username = ?
            limit 1;
            <sql:param value="${username}"/>
        </sql:query>
        <sql:query dataSource="${snapshot}" var="subtotal">
            Select sum(F.rental_rate * F.rental_duration)as Subtotal
            from cart as C
            join cartofitems as CI
            on CI.cart_id = C.cart_id
            join film as F
            on CI.film_id = F.film_id
            join customer as CS
            on CS.customer_id = C.customer_id
            Where username = ?;
            <sql:param value="${username}"/>
        </sql:query>
        <form method="post" action="CheckoutServlet">

            <div class="container">
                <label><b>Total Cost</b></label>
                <c:forEach items="${subtotal.rows}" var="subtotal">                    
                    <input type="text" value="${subtotal.Subtotal}" readonly="true"
                    </c:forEach>
                    <input id="output" hidden="true" name="${username}"/>
                <label><b>PayPal Username</b></label>
                <input type="text" placeholder="Enter PayPal Username" name="pUser" required>

                <label><b>PayPal Password</b></label>
                <input type="password" placeholder="Enter PayPal Password" name="pPass" required>

                <p>By checking out you agree to our <a href="#">Terms & Privacy</a>.</p>

                <!-- START CAPTCHA -->
                <br>
                <div class="capbox">

                    <div id="CaptchaDiv"></div>

                    <div class="capbox-inner">
                        Type the above number:<br>

                        <input type="hidden" id="txtCaptcha">
                        <input type="text" name="CaptchaInput" id="CaptchaInput" size="15"><br>

                    </div>
                </div>
                <br><br>
                <!-- END CAPTCHA -->
                <input id="username1" hidden="true" name="username1"/>
                <script type="text/javascript">
                    (function (global) {
                        document.getElementById("username1").value = global.localStorage.getItem("mySharedData");
                    }(window));
                </script>
                <button type="submit" class="checkoutbtn">Checkout</button>
            </div>
        </form>
        <div class="button-container">
            <form method="get" border="0" action="cart.jsp">
                <div>
                    <button type="submit"class="cancelbtn">Cancel</button>
                </div>
            </form>

            <script type="text/javascript">

                // Captcha Script

                function checkform(theform) {
                    var why = "";

                    if (theform.CaptchaInput.value == "") {
                        why += "- Please Enter CAPTCHA Code.\n";
                    }
                    if (theform.CaptchaInput.value != "") {
                        if (ValidCaptcha(theform.CaptchaInput.value) == false) {
                            why += "- The CAPTCHA Code Does Not Match.\n";
                        }
                    }
                    if (why != "") {
                        alert(why);
                        return false;
                    }
                }

                var a = Math.ceil(Math.random() * 9) + '';
                var b = Math.ceil(Math.random() * 9) + '';
                var c = Math.ceil(Math.random() * 9) + '';
                var d = Math.ceil(Math.random() * 9) + '';
                var e = Math.ceil(Math.random() * 9) + '';

                var code = a + b + c + d + e;
                document.getElementById("txtCaptcha").value = code;
                document.getElementById("CaptchaDiv").innerHTML = code;

                // Validate input against the generated number
                function ValidCaptcha() {
                    var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
                    var str2 = removeSpaces(document.getElementById('CaptchaInput').value);
                    if (str1 == str2) {
                        return true;
                    } else {
                        return false;
                    }
                }

                // Remove the spaces from the entered and generated code
                function removeSpaces(string) {
                    return string.split(' ').join('');
                }
            </script>

    </body>
</html>
