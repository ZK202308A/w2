<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>

<h1>Edit Page</h1>

${todo}

<form action="/todo/remove" method="post">
    <input type="hidden" name="tno"  value="${todo.tno}" >
    <button>REMOVE</button>
</form>

<%@include file="../includes/footer.jsp"%>
