<%-- 
    Document   : details
    Created on : Aug 1, 2019, 11:31:04 PM
    Author     : Katarina Djordjevic
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${not empty param.value}">
    <div class="flash-message">
        <p class="alert alert-${param.type}">${param.value}
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        </p>
    </div>
</c:if>