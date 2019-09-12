<%-- 
    Document   : index
    Created on : Aug 6, 2019, 12:13:19 AM
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
        <title>Patients</title>
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
                    <b><font size="4">Patients</font></b>
                </div>
            </div>
            <br><br>

            <table class='table table-condensed table-align'>
                <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>JMBG</th>
                        <th>Phone number</th>
                        <th>Address</th>
                        <th>Date of Birth</th>
                        <th class='text-right'><a href="/patient/insert" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${patientList}" var="patient" >
                        <tr>
                            <td>
                                <p class='form-control-static'>
                                    ${patient.firstname}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${patient.lastname}
                                </p>
                            </td>    

                            <td>
                                <p class='form-control-static'>
                                    ${patient.jmbg}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${patient.number}
                                </p>
                            </td> 
                            <td>
                                <p class='form-control-static'>
                                    ${patient.address}
                                </p>
                            </td> 
                            <td>
                                <p class='form-control-static'>
                                    <fmt:formatDate value="${patient.dateofbirth}" var="formattedDate" type="date" pattern="dd.MM.yyyy." />   
                                    ${formattedDate}
                                </p>
                            </td> 
                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/patient/details/${patient.patientID}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                                        <a href="/patient/edit/${patient.patientID}" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                                        <a href="/patient/delete/${patient.patientID}" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
                                    </div>
                                </div>
                            </td>               
                        </tr>
                    </c:forEach>
                </tbody>
            </table>      
        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
