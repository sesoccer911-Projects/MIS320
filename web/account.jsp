<%-- 
    Document   : account
    Created on : Apr 16, 2017, 8:17:07 PM
    Author     : ewaremickens
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <title> Customer Account</title>
    </head>
    <body>     
        Please input any information you want to change to your information.

        <form method="post" action="CustomerUpdateServlet">
            Username: <input name="output" id="output" type="readonly"/> <br/>
            <script type="text/javascript">
                /*jslint sub: true, maxerr: 50, indent: 4, browser: true */

                (function (global) {
                    document.getElementById("output").value = global.localStorage.getItem("mySharedData");
                }(window));
            </script>
            First Name : <input
                type="text" name="firstName"/> <br /> 
            Last Name : <input
                type="text" name="lastName"/> <br /> 
            Password : <input
                type="text" name="password"/> <br /> 
            Email : <input type="text" name="email"/><br /> 
            <input type="submit" value="Submit Changes"/>
        </form> 

        <a href="RentalHistory.jsp" style="margin-left:30px;">View Rental History</a>
        <a href="WishList.jsp" style="margin-left:30px;">View Wish List</a>
        <a href="index.xhtml" style="margin-left:30px;">Return to browsing</a>

    </body>
</html> 
