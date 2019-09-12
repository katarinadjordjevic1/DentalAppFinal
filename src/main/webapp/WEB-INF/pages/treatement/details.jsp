<%-- 
    Document   : details
    Created on : Aug 6, 2019, 5:32:59 PM
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
        <title>Treatement details</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <%@include file = "../common/flash_message.jsp" %>
        <br><br><br>
        <jsp:include page="../common/flash_message.jsp">
            <jsp:param name="value" value="${value}"/>
            <jsp:param name="type" value="${type}"/>
        </jsp:include>
        <div class="col-md-10 col-md-offset-1">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/treatement/index">Treatement</a> / Details </font></b>
                </div>
            </div>
            <div class='text-right'>
                <a href="/treatement/index" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
            </div>

            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-md-2'>Date of treatement:</label>
                    <div class='col-md-6'>
                        <p class='form-control-static'>
                            <fmt:formatDate value="${treatement.date}" var="formattedDate" type="date" pattern="dd.MM.yyyy. HH:mm" />   
                            ${formattedDate}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-md-2'>Cost</label>
                    <div class='col-md-6'>
                        <p class="form-control-static">
                            ${treatement.cost}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-md-2'>Doctor:</label>
                    <div class='col-md-6'>
                        <p class="form-control-static">
                            ${treatement.dentist}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-md-2'>Patient</label>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class='control-label col-md-6'>Name:</label>
                        <div class='col-md-6'>
                            <p class="form-control-static">
                                ${treatement.patient}
                            </p>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class='control-label col-md-4'>JMBG</label>
                        <div class='col-md-6'>
                            <p class="form-control-static">
                                ${treatement.patient.jmbg}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class='control-label col-md-6'>Address:</label>
                        <div class='col-md-6'>
                            <p class="form-control-static">
                                ${treatement.patient.address}
                            </p>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class='control-label col-md-4'>Phone number</label>
                        <div class='col-md-6'>
                            <p class="form-control-static">
                                ${treatement.patient.number}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="panel-heading" align="left">
                    <h4><b><font color="black" style="font-family: sans-serif;">List of interventions</font> </b></h4>
                </div>
                <c:forEach items="${treatement.interventionList}" var="intervention">
                    <div class ="row">
                        <div class="form-group col-md-4">
                            <label class='control-label col-md-4'>Tooth:</label>
                            <div class='col-md-6'>
                                <p class="form-control-static">
                                    ${intervention.tooth}
                                </p>
                            </div>
                        </div>
                        <div class="form-group col-md-4">
                            <label class='control-label col-md-3'>Service:</label>
                            <div class='col-md-9'>
                                <p class="form-control-static">
                                    ${intervention.dentalService}
                                </p>
                            </div>
                        </div>
                        <c:if test="${not empty intervention.description}">
                            <div class="form-group col-md-4">
                                <label class='control-label col-md-4'>Description:</label>
                                <div class='col-md-6'>
                                    <p class="form-control-static">
                                        ${intervention.description}
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
