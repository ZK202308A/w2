<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>


<h1>Todo List Page</h1>

<h1>${pageInfo.start}</h1>

<h1>${pageInfo.end}</h1>

<h1>${pageInfo.prev}</h1>

<h1>${pageInfo.next}</h1>


<ul>
    <c:forEach begin="${pageInfo.start}" end="${pageInfo.end}" var="num">
        <li>${num}</li>
    </c:forEach>
</ul>

<%@include file="../includes/footer.jsp"%>


