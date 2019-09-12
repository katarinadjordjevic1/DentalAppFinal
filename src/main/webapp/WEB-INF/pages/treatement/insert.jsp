<%-- 
    Document   : insert
    Created on : Aug 1, 2019, 5:33:17 PM
    Author     : Katarina Djordjevic
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/ajax.js" %>
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="/resources/styles/datepicker.css">
        <script src="/resources/js/bootstrap-datepicker.js"></script>

        <title>Treatement insert</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <%@include file = "../common/flash_message.jsp" %>
        <br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group" align="center">
                <div class="list-group-item">
                    <b><font size="4"><a href="/patient/details/${patient.patientID}">Patient</a> / Insert treatement </font></b>
                </div>
            </div>
            <br><br>
            <div class="container-fluid">

                <form:form action="/patient/${patient.patientID}/treatement/insert" method="post" modelAttribute="treatement">

                    <div class='form-horizontal'>
                        <div class='form-group'>
                            <label class='control-label col-md-1'>Patient</label>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class='control-label col-md-4'>Name:</label>
                                <div class='col-md-6'>
                                    <p class="form-control-static">
                                        ${patient}
                                    </p>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class='control-label col-md-4'>JMBG</label>
                                <div class='col-md-6'>
                                    <p class="form-control-static">
                                        ${patient.jmbg}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="text" id="date" name="date" class="datum form-control" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="note">Note</label>
                        <input type="text" id="note" name="note" class="form-control">
                    </div>

                    <div class="container col-md-12">
                        <h3>New intervention</h3>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class='control-label col-md-2' for="description" >Description</label>
                                    <input type="text" class="form-control" id ="description"/>
                                </div>
                                <div class="form-group">
                                    <label class='control-label col-md-2' for="service">Dental service</label>
                                    <select class="form-control" id="service"
                                            name="service">
                                        <option value="-1">
                                            ---
                                        </option>
                                        <c:forEach var="service" items="${dentalServiceList}">
                                            <option value="${service.dentalserviceID}">${service.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label class='control-label col-md-2' for="tooth" >Tooth</label>
                                    <select class="form-control" id="tooth"
                                            name="tooth">
                                        <option value="-1">
                                            ---
                                        </option>
                                        <c:forEach var="tooth" items="${toothList}">
                                            <option value="${tooth.toothID}">${tooth.label}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <button type="button" id="add_intervention" class="btn btn-default" onclick="addRow('dataTable')">Add intervention</button>
                        <button type="button" id="edit_intervention" disabled="true" class="btn btn-default" onclick="updateRow()">Edit intervention</button>
                        <button type="button" id="cancel_intervention"  class="btn btn-default" onclick="cancel()">Cancel</button>
                        <input type="hidden" id="edit_row_id">
                    </div>

                    <div class="panel-body">
                        <table id="interventions" class='table table-condensed table-align'>
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Dental service</th>
                                    <th>Tooth</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody id="body">

                            </tbody>
                        </table>
                    </div>
                    <br>                        
                    <div class='text-right'>
                        <button type="submit" class="btn btn-primary"><span class="fa fa-fw fa-check"></span></button>
                        <a href="/patient/details/${patient.patientID}" class="btn btn-default"><span class="fa fa-fw fa-remove"></span></a>
                    </div>
                </form:form>

            </div>
        </div>
    </body>
    <script>
        var i = 0;
        function addRow(tableID) {

            description = document.getElementById("description").value;
            var toothSelect = document.getElementById("tooth");
            tooth = toothSelect.options[toothSelect.selectedIndex].value;
            var serviceSelect = document.getElementById("service");
            service = serviceSelect.options[serviceSelect.selectedIndex].value;
            i++;

            $.post("/ajax/intervention/insert",
                    {
                        description: description,
                        tooth: tooth,
                        service: service,
                        rbr: i
                    },
                    function (data) {
                        $("#body").append(data);
                        clearForm();

                    }).done(function () {
            }).fail(function (xhr, textStatus, errorThrown) {
                alert("You mast choose dental service in intervention!");
            });
        }
        function deleteRow(rbr) {
            $.post("/ajax/intervention/delete",
                    {
                        rbr: rbr
                    },
                    function (data) {
                        $("#" + rbr + "").remove();
                        alert(data);
                    }).done(function () {
            }).fail(function (xhr, textStatus, errorThrown) {
                alert(errorThrown.getMessage());
            });
        }
        function editRow(rbr) {
            var row = document.getElementById(rbr);
            $("#edit_intervention").prop("disabled", false);
            $("#add_intervention").prop("disabled", true);
            $("#tooth").val($("#toothID_" + rbr).val());
            $("#service").val($("#serviceID_" + rbr).val());
            $("#description").val($("#description_" + rbr).text());
            $('#edit_row_id').val(rbr);
        }

        function updateRow() {
            var rbr = $('#edit_row_id').val();
            description = document.getElementById("description").value;
            var toothSelect = document.getElementById("tooth");
            tooth = toothSelect.options[toothSelect.selectedIndex].value;
            var serviceSelect = document.getElementById("service");
            service = serviceSelect.options[serviceSelect.selectedIndex].value;
            $.post("/ajax/intervention/update",
                    {
                        description: description,
                        tooth: tooth,
                        service: service,
                        rbr: rbr
                    },
                    function (data) {
                        alert(data);
                        $('#interventions').find('#' + rbr).find('td:eq(0)').html(description);
                        $('#interventions').find('#' + rbr).find('td:eq(1)').html(serviceSelect.options[serviceSelect.selectedIndex].text);
                        $('#interventions').find('#' + rbr).find('td:eq(2)').html(toothSelect.options[toothSelect.selectedIndex].text);

                        $("#edit_intervention").prop("disabled", true);
                        $("#add_intervention").prop("disabled", false);

                        $("#toothID_" + rbr).val(tooth);
                        $("#serviceID_" + rbr).val(service);

                        clearForm();

                    }).done(function () {
            }).fail(function (xhr, textStatus, errorThrown) {
                alert("You mast choose dental service in intervention!");
            });
        }
        function clearForm() {
            $("#description").val("");
            $("#tooth").val("-1");
            $("#service").val("-1");
        }

        function cancel() {
            clearForm();
            $("#edit_intervention").prop("disabled", true);
            $("#add_intervention").prop("disabled", false);
        }


    </script>
    <script>
        $('.datum').datepicker({
            format: 'dd.mm.yyyy.'
        }).on('changeDate', function () {
            $(this).datepicker('hide');
        });
    </script>


</html>
