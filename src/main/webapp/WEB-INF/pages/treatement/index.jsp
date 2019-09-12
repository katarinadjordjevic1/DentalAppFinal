<%-- 
    Document   : index
    Created on : Aug 6, 2019, 5:33:09 PM
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
        <title>Treatements</title>
    </head>
    <br><br><br>
    <body>
        <%@include file = "../header.jsp" %>
        <%@include file = "../common/flash_message.jsp" %>
        <br><br><br>
        <jsp:include page="../common/flash_message.jsp">
            <jsp:param name="value" value="${value}"/>
            <jsp:param name="type" value="${type}"/>
        </jsp:include>
        <div class="col-6 col-md-1"></div>

        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4">Treatements</font></b>
                </div>
                <div class="panel-body">
                    <table class='table table-condensed table-align'>
                        <thead>
                            <tr>
                                <th>Date of treatement</th>
                                <th>Dentist</th>
                                <th>Patient</th>
                                <th>Patient JMBG</th>
                                <th>Note</th>
                                <th>Cost of treatement</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${treatementList}" var="treatement" >
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
                                            ${treatement.patient}
                                        </p>
                                    </td>
                                    <td>
                                        <p class='form-control-static'>
                                            ${treatement.patient.jmbg}
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

            <br><br>


        </div>

        <div class="col-6 col-md-1"></div>
    </body>
</html>

