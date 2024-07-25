<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>

${nums}

<h1>Todo List Page</h1>

<c:out value="${tag}"/>

<ul>
    <c:forEach items="${nums}" var="num" >
        <li class="button ${num % 2 == 0 ?'EVEN':'ODD'}">AAA ${num} </li>
    </c:forEach>
</ul>


<%@include file="../includes/footer.jsp"%>


