<%--
  Created by IntelliJ IDEA.
  User: kim ji min
  Date: 2021-06-09
  Time: 오전 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title>${requestScope.title}</title>
    <link rel="stylesheet" href="/res/css/common.css">
    <link rel="stylesheet" href="/res/css/<tiles:getAsString name="res"/>.css">
    <script defer src="/res/js/common.js"></script>
    <script defer src="/res/js/<tiles:getAsString name="res"/>.js"></script>
    <!-- 해당페이지에서만 쓰는 파일을 import 할 때 쓸거 -->
</head>
<body>
    <div id="container">
        <tiles:insertAttribute name="header"/>
        <section>
            <tiles:insertAttribute name="content"/>  <!-- login.jsp가 이 위치로 들어온다. -->
        </section>

    <footer>
        @Copyright 2021.
    </footer>
    </div>
</body>
</html>