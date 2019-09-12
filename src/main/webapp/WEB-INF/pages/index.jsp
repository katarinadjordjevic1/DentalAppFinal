<%-- 
    Document   : index
    Created on : Aug 5, 2019, 9:11:56 PM
    Author     : Katarina Djordjevic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index.css">
        <%@include file = "/resources/js/bootstrap.js" %>
        <title>Dental system</title>        
    </head>
    <body>
        <%@include file = "header.jsp" %>
        <br><br><br>
        <jsp:include page="common/flash_message.jsp">
            <jsp:param name="value" value="${value}"/>
            <jsp:param name="type" value="${type}"/>
        </jsp:include>

    </body>
</html>
