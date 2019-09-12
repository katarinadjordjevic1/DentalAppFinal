<%-- 
    Document   : index
    Created on : Aug 5, 2019, 9:16:50 PM
    Author     : Katarina Djordjevic
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index.css">
        <%@include file = "/resources/js/bootstrap.js" %>
    </head>
    <body>

        <div> 
            <div class="panel panel-success">
                <jsp:include page="common/flash_message.jsp">
                    <jsp:param name="value" value="${value}"/>
                    <jsp:param name="type" value="${type}"/>
                </jsp:include>
                <div class="panel-heading" align="center">
                    <h4><b><font color="black" style="font-family: sans-serif;">Dental system</font> </b></h4>
                </div>
                <div class="panel-body" align="center"  style="background-image: url(../../resources/images/dentalClinic.jpg)">

                    <div class="container " style="margin-top: 10%; margin-bottom: 10%;">

                        <div class="panel panel-success" style="max-width: 40%;" align="left">


                            <div class="panel-heading form-group">
                                <b><font color="white">
                                    Login Form</font> </b>
                            </div>

                            <div class="panel-body" >

                                <form action="/login" method="post" >
                                    <div class="form-group">
                                        <label for="username">User Name</label> <input
                                            type="text" class="form-control" name="username" id="username"
                                            placeholder="Enter User Name" required="required">

                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label> <input
                                            type="password" class="form-control" name="password" id="password"
                                            placeholder="Password" required="required">
                                    </div>
                                    <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b>Login</b></button>

                                </form>

                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>

    </body>
</html>