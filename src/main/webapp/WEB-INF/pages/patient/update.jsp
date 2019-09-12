<%-- 
    Document   : update
    Created on : Aug 1, 2019, 11:31:11 PM
    Author     : Katarina Djordjevic
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/datepicker.css">
        <title>Patient update</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <%@include file = "../common/flash_message.jsp" %>
        <br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/patient/index">Patient</a> / Edit </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/patient/edit/${patient.patientID}" method="post" modelAttribute="patient" >
                <div class="form-group">
                    <form:label path="firstname">First name</form:label>
                    <form:input path="firstname" class="form-control" id="firstname" value="${patient.firstname}" disabled="false" />
                </div>
                <div class="form-group">
                    <form:label path="lastname">Last name</form:label>
                    <form:input path="lastname" class="form-control" id="lastname" value="${patient.lastname}" />
                </div>
                <div class="form-group">
                    <form:label path="jmbg">JMBG</form:label>
                    <form:input path="jmbg" class="form-control" id="jmbg" value="${patient.jmbg}" />
                </div>
                <div class="form-group">
                    <form:label path="number">Phone number</form:label>
                    <form:input path="number" class="form-control" value="${patient.number}" id="number" />
                </div>
                <div class="form-group">
                    <form:label path="address">Address</form:label>
                    <form:input path="address" class="form-control" value="${patient.address}" id="address" />
                </div>
                <div class="form-group">
                    <form:label path="dateofbirth">Date of Birth</form:label>
                    <fmt:formatDate value="${patient.dateofbirth}" var="formattedDate" type="date" pattern="yyyy/MM/dd" />           
                    <form:input path="dateofbirth" class="form-control datum" autocomplete="off" value="${formattedDate}" id="dateofbirth" />
                </div>
                <br>                        
                <div class='text-right'>
                    <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
                    <a href="/patient/index" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>

            </form:form>                

        </div>

        <div class="col-6 col-md-1"></div>
        <br><br>

    </body>
    <script src="../../resources/js/bootstrap-datepicker.js"></script> 
    <script>
        $('.datum').datepicker({
            format: 'yyyy/mm/dd'
        }).on('changeDate', function () {
            $(this).datepicker('hide');
        });
    </script>
</html>

