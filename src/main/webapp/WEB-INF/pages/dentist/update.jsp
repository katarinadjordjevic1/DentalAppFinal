<%-- 
    Document   : update
    Created on : Aug 1, 2019, 11:31:11 PM
    Author     : Katarina Djordjevic
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Dentist edit</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br>
      
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/dentist/index">Dentist</a> / Edit </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/dentist/edit/${dentist.dentistID}" method="post" modelAttribute="dentist" >
                <div class="form-group">
                    <form:label path="firstname">First name:</form:label>
                    <form:input path="firstname" class="form-control" id="firstname" value="${dentist.firstname}" disabled="false" />
                </div>
                <div class="form-group">
                    <form:label path="lastname">Last name:</form:label>
                    <form:input path="lastname" class="form-control" id="lastname" value="${dentist.lastname}" />
                </div>
                <div class="form-group">
                    <form:label path="sex">Gender</form:label>
                    <form:select class="form-control" path="sex">   
                        <form:option value="muski">male</form:option>
                        <form:option value="zenski">female</form:option>
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label path="phonenumber">Phone number:</form:label>
                    <form:input path="phonenumber" class="form-control" value="${dentist.phonenumber}" id="phonenumber" />
                </div>
                <div class="form-group">
                    <form:label path="cardnumber">ID card number:</form:label>
                    <form:input path="cardnumber" class="form-control" value="${dentist.cardnumber}" id="cardnumber" />
                </div>
                <div class="form-group">
                    <form:label path="username">Username:</form:label>
                    <form:input path="username" class="form-control"  value="${dentist.username}" id="username" />
                </div>
                <div class="form-group">
                    <form:label path="password">Password:</form:label>
                    <form:password path="password" class="form-control" value="${dentist.password}" id="password" />
                </div>
                <br>                        
                <div class='text-right'>
                    <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
                    <a href="/dentist/index" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>

            </form:form>                

        </div>

        <div class="col-6 col-md-1"></div>

    </body>
</html>

