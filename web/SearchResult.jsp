<%-- 
    Document   : SearchResult
    Created on : Apr 27, 2017, 12:22:20 AM
    Author     : Ben
--%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <link rel="stylesheet" href="resources/css/cssLayout.css">
        <link rel="stylesheet" href="resources/css/styles.css">
        <link rel="stylesheet" href="resources/css/default.css"
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
    </head>
    <top/>
    <body>

        <table id="j_idt10:panel" columns ="2">

            <tr>
                <td>Title:</td> <td><c:out value="${title}"/></td>
            </tr>
            <tr>
                <td>Description::</td> <td><c:out value="${description}"/></td>
            </tr>
            <tr>
                <td>Genre:</td> <td><c:out value="${genres}"/></td>
            </tr>
            <tr>
                <td>Cast:</td> <td><c:out value="${actors}"/></td>
            </tr>
            <tr>
                <td>Film Length:</td> <td><c:out value="${length}"/> min</td>
            </tr>
            <tr>
                <td>Language:</td> <td><c:out value="${language}"/></td>
            </tr>
            <tr>
                <td>Title:</td> <td><c:out value="${title}"/></td>
            </tr>
            <tr>
                <td>Release Year:</td> <td><c:out value="${releaseYear}"/></td>
            </tr>
            <tr>
                <td>Rental Duration:</td> <td><c:out value="${rentalDuration}"/></td>
            </tr>
            <tr>
                <td>Rental Rate:</td> <td><c:out value="${rentalRate}"/></td>
            </tr>
            <tr>
                <td>Replacement Cost:</td> <td><c:out value="${replacementCost}"/></td>
            </tr>
            <tr>
                <td>Rating:</td> <td><c:out value="${rating}"/></td>
            </tr>
            <tr>
                <td>Special Features:</td> <td><c:out value="${specialFeatures}"/></td>
            </tr>
            <tr>
                <td>Last Update:</td> <td><c:out value="${lastUpdate}"/></td>
            </tr>
        </table>
        <a href="index.xhtml">View All List</a>
    </body>
</html>
