<%-- 
    Document   : details
    Created on : Aug 1, 2019, 11:31:04 PM
    Author     : Katarina Djordjevic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Dentist details</title>
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
                    <b><font size="4"><a href="/dentist/index">Dentist</a> / Details </font></b>
                </div>
            </div>

            <div class='text-right'>
                <a href="/dentist/edit/${dentist.dentistID}" class="btn btn-warning"><span class="fa fa-fw fa-file"></span></a>
                <a href="/dentist/index" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
            </div>

            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>First name:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${dentist.firstname}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Last name:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${dentist.lastname}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Username:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${dentist.username}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Gender:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${dentist.sex}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-xs-4'>Phone number:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${dentist.phonenumber}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-xs-4'>ID card number:</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${dentist.cardnumber}
                        </p>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-6 col-md-1"></div>

    </body>
</html>
