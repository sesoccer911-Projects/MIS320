<%-- 
    Document   : RentalHistory
    Created on : Apr 30, 2017, 9:42:31 PM
    Author     : Evan
--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <title>Rental History</title>
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
        <sql:query dataSource="${snapshot}" var="customer_id">
            select c.customer_id, r.rental_date, i.inventory_id, title
            from rental as r
            join customer as c
            on c.customer_id = r.customer_id
            join inventory as i
            on i.inventory_id = r.inventory_id
            join film as f
            on f.film_id = i.film_id
            where c.customer_id = ?
            order by rental_date
            <sql:param value="${customerid}"/>
        </sql:query>
        
        
        <table width="850" border="1" class="cart">
                    <thead>
                        <tr>
                            <th>Movie Title</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result.rows}" var="rental">
                            <tr>
                                <td><c:out value="${rental.title}"/></td>
                            </tr>
                        </c:forEach>

                        </tr>
                        </tbody>
                </table>
        
        <p><form action="account.jsp" method="post">
                            <button type="submit" name="your_name" value="your_value" class="accloginbtn">Back To Account</button>
                        </form>
    </body>
</html>
