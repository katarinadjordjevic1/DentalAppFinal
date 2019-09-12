<%-- 
    Document   : details
    Created on : Aug 1, 2019, 11:31:04 PM
    Author     : Katarina Djordjevic
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Patient details</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>

        <br><br><br>
        <jsp:include page="../common/flash_message.jsp">
            <jsp:param name="value" value="${value}"/>
            <jsp:param name="type" value="${type}"/>
        </jsp:include>
        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/patient/index">Patient</a> / Details </font></b>
                </div>
            </div>

            <div class='text-right'>
                <a href="/patient/edit/${patient.patientID}" class="btn btn-warning"><span class="fa fa-fw fa-file"></span></a>
                <a href="/patient/index" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
            </div>

            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>First name:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${patient.firstname}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Last name:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${patient.lastname}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>JMBG:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${patient.jmbg}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-xs-4'>Phone number:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${patient.number}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-xs-4'>Address:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${patient.address}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-xs-4'>Date of Birth:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            <fmt:formatDate value="${patient.dateofbirth}" var="formattedDate" type="date" pattern="dd.MM.yyyy." />   
                            ${formattedDate}
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel-heading" align="left">
                <h4><b><font color="black" style="font-family: sans-serif;">List of treatements</font> </b></h4>
            </div>
            <div class="panel-body">
                <table class='table table-condensed table-align'>
                    <thead>
                        <tr>
                            <th>Date of treatement</th>
                            <th>Dentist</th>
                            <th>Note</th>
                            <th>Cost of treatement</th>
                            <th class='text-right'><a href="/patient/${patient.patientID}/treatement/insert" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${patient.treatementList}" var="treatement" >
                            <tr>
                                <td>
                                    <p class='form-control-static'>
                                        <fmt:formatDate value="${treatement.date}" var="formattedDate" type="date" pattern="dd.MM.yyyy." />   
                                        ${formattedDate}
                                    </p>
                                </td> 
                                <td>
                                    <p class='form-control-static'>
                                        ${treatement.dentist}
                                    </p>
                                </td>    

                                <td>
                                    <p class='form-control-static'>
                                        ${treatement.note}
                                    </p>
                                </td>
                                <td>
                                    <p class='form-control-static'>
                                        ${treatement.cost}
                                    </p>
                                </td> 
                                <td>
                                    <div class='text-right'>
                                        <div class='btn-group'>
                                            <a href="/treatement/details/${treatement.treatementID}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                                        </div>
                                    </div>
                                </td>               
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>      
            </div>
        </div>
    </body>
</html>
