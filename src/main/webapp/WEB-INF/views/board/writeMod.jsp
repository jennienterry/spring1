<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>글쓰기</title>
<h1>글쓰기</h1>
<%--<c:choose>--%>
<%--    <c:when test="${empty requestScope.data}">--%>
<%--        <c:set var="iboard" value="0"/>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <c:set var="iboard" value="${requestScope.data.iboard}"/>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>
<form action="writeMod" method="post">
    <input type="hidden" name="iboard" value="${requestScope.data == null ?0 : requestScope.data.iboard}">
    <%-- 위에 c:set으로 줬기 때문에 requestScope. 안하고 ${iboard} 해도 된다. --%>
    <div><input type="text" name="title" placeholder="제목" value="${requestScope.data.title}"></div>
    <div><textarea name="ctnt" placeholder="내용">${requestScope.data.ctnt}</textarea></div>
    <div>
        <input type="submit" value="등록">
        <input type="reset" value="새로작성">
    </div>
</form>
