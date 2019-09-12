<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Dentist delete</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <%@include file = "../common/flash_message.jsp" %>
        <br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/dentist/index">Dentist</a> / Delete </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/dentist/delete/${dentist.dentistID}" method="post">
                <div class="form-group">
                    <label for="firstname">First name:</label>
                    ${dentist.firstname}
                </div>
                <div class="form-group">
                    <label for="lastname">Last name:</label>
                    ${dentist.lastname}
                </div>
                <div class="form-group">
                    <label for="lastname">Username:</label>
                    ${dentist.username}
                </div>
                <div class="form-group">
                    <label for="sex">Gender:</label>
                    ${dentist.sex}
                </div>
                <div class="form-group">
                    <label for="phonenumber">Phone number:</label>
                    ${dentist.phonenumber}
                </div>
                <div class="form-group">
                    <label for="cardnumber">ID card number:</label>
                    ${dentist.cardnumber}
                </div>
                <br>

                <div class='text-left'>
                    <button type="submit" class="btn btn-danger"><span class="fa fa-fw fa-trash"></span></button>
                    <a href="/dentist/index" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                </div>
            </form:form>

        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
