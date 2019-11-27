<%-- 
    Document   : login
    Created on : Mar 28, 2017, 12:53:00 PM
    Author     : sgreen
--%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <link rel="stylesheet" href="resources/css/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="resources/scripts/jquery-1.8.2.min.js"></script>
        <title>Home Page</title>
    </head>
    <body> 
        <br>
        <br>
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
            where username like ?
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
            Where username like ?;
            <sql:param value="${username}"/>
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result">
            Select F.title, (F.rental_rate * F.rental_duration) as Price, cartItem_id
            from cart as C
            join cartofitems as CI
            on CI.cart_id = C.cart_id
            join film as F
            on CI.film_id = F.film_id
            join customer as CS
            on CS.customer_id = C.customer_id
            Where username like ?;
            <sql:param value="${username}"/>
        </sql:query>

        <div align="center">
            <table width="850" border="1">
                <tr>
                    <td align="center">
                        <img src="images/cart.png">
                    </td>
                </tr>
            </table>
        </div> 
        <div align="center">
            <form action="checkout.jsp" method="post" border="">
                <table width="850" border="1" class="cart">
                    <thead>
                        <tr>
                            <th>Action</th>
                            <th>Movie Title</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result.rows}" var="cart">
                            <tr>
                                <td><a href="CartServlet?action=delete&cartItem_id=<c:out value="${cart.cartItem_id}"/>">Delete</a></td>
                                <td><c:out value="${cart.title}" /></td>
                                <td class="price"><c:out value="${cart.Price}" /></td>
                            </tr>
                        </c:forEach>
                        <tr class="totalColumn">
                            <td  colspan="2" align="right" ><b>Subtotal: </b></td>
                    <form action="checkout.jsp" method="post" border="">
                        <c:forEach items="${subtotal.rows}" var="subtotal">
                            <td class="price"><c:out value="${subtotal.Subtotal}" /></td>
                        </c:forEach>

                        </tr>
                        </tbody>
                </table>
                <table width="850" border="1">
                    <tr>
                    <div style="width:650px;">
                        <button type="submit" name="your_name" value="Subtotal" class="loginbtn">Checkout Cart</button>
                        </form>
                        <p><form action="faces/index.xhtml" method="post">
                            <button type="submit" name="your_name" value="your_value" class="adminloginbtn">Back To Search</button>
                        </form>
                    </div>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
