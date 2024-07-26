<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>


<h1>Todo List Page</h1>

<h1>${pageInfo.start}</h1>

<h1>${pageInfo.end}</h1>

<h1>${pageInfo.prev}</h1>

<h1>${pageInfo.next}</h1>


<ul class="pagination">
    <c:if test="${pageInfo.prev}">
        <li class="page-item"><a class="page-link" href="/todo/list?page=${pageInfo.start - 1 }">Previous</a></li>
    </c:if>

    <c:forEach begin="${pageInfo.start}" end="${pageInfo.end}" var="num">
        <li class="page-item ${pageInfo.page == num ?'active':''}"><a class="page-link" href="/todo/list?page=${num}">${num}</a></li>
    </c:forEach>

    <c:if test="${pageInfo.next}">
        <li class="page-item"><a class="page-link" href="/todo/list?page=${pageInfo.end + 1 }">Next</a></li>
    </c:if>
</ul>

<%@include file="../includes/footer.jsp"%>


