<%-- 
    Document   : delete
    Created on : Aug 1, 2018, 11:30:48 PM
    Author     : Katarina Djordjevic
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Patient delete</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <%@include file = "../common/flash_message.jsp" %>

        <br><br><br>
        
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/patient/index">Patient</a> / Delete </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/patient/delete/${patient.patientID}" method="post">
                <div class="form-group">
                    <label for="firstname">First name:</label>
                    ${patient.firstname}
                </div>
                <div class="form-group">
                    <label for="lastname">Last name:</label>
                    ${patient.lastname}
                </div>
                <div class="form-group">
                    <label for="sex">JMBG:</label>
                    ${patient.jmbg}
                </div>
                <div class="form-group">
                    <label for="phonenumber">Phone number:</label>
                    ${patient.number}
                </div>
                <div class="form-group">
                    <label for="cardnumber">Address:</label>
                    ${patient.address}
                </div>
                <div class="form-group">
                    <label for="dateofbirth">Date of birth:</label>
                    <fmt:formatDate value="${patient.dateofbirth}" var="formattedDate" type="date" pattern="dd.MM.yyyy." />   
                    ${formattedDate}

                </div>
                <br>

                <div class='text-left'>
                    <button type="submit" class="btn btn-danger"><span class="fa fa-fw fa-trash"></span></button>
                    <a href="/patient/index" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>
            </form:form>

        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
