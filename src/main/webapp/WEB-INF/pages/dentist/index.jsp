<%-- 
    Document   : insert
    Created on : Aug 1, 2019, 11:31:49 PM
    Author     : Katarina Djordjevic
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Dentists</title>
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
                    <b><font size="4">Dentists</font></b>
                </div>
            </div>
            <br><br>

            <table class='table table-condensed table-align'>
                <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Username</th>
                        <th>Gender</th>
                        <th>Phone number</th>
                        <th>ID card number</th>
                        <th class='text-right'><a href="/dentist/insert" class="btn btn-primary"><span class='fa fa-fw fa-plus'></span></a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dentistList}" var="dentist" >
                        <tr>
                            <td>
                                <p class='form-control-static'>
                                    ${dentist.firstname}
                                </p>
                            </td>   
                            <td>
                                <p class='form-control-static'>
                                    ${dentist.lastname}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${dentist.username}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${dentist.sex}
                                </p>
                            </td>  
                            <td>
                                <p class='form-control-static'>
                                    ${dentist.phonenumber}
                                </p>
                            </td> 
                            <td>
                                <p class='form-control-static'>
                                    ${dentist.cardnumber}
                                </p>
                            </td> 
                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/dentist/details/${dentist.dentistID}" class="btn btn-default"><span class='fa fa-fw fa-info'></span></a>
                                        <a href="/dentist/edit/${dentist.dentistID}" class="btn btn-warning"><span class='fa fa-fw fa-file'></span></a>
                                        <a  href="/dentist/delete/${dentist.dentistID}" class="btn btn-danger"><span class='fa fa-fw fa-trash'></span></a>
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
