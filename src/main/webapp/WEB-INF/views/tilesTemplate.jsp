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
    <!-- 이 위치에, 이름이 "res"인 put-attribute를 String으로 가져와라 (tiles.xml확인하기) -->
    <script defer src="/res/js/common.js"></script>
    <script defer src="/res/js/<tiles:getAsString name="res"/>.js"></script>
    <!-- 해당페이지에서만 쓰는 파일을 import 할 때 쓸거 -->
</head>
<body>
    <div id="container">
        <tiles:insertAttribute name="header"/>
        <!-- name이 header인 attribute의 value를 여기에 넣는다. [해당하는 jsp파일을 가져와서 넣어주는 것] -->
        <section>
        <tiles:insertAttribute name="content"/>  <!-- ~.jsp가 이 위치로 들어온다. -->
        </section>
    <footer>
        @Copyright 2021.
    </footer>
    </div>
</body>
</html>