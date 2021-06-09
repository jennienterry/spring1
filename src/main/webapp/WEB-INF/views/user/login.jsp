<%--
  Created by IntelliJ IDEA.
  User: kim ji min
  Date: 2021-06-01
  Time: 오후 5:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Login</title>

    <div>로그인</div>
    <div>${requestScope.errMsg}</div>
    <form action="login" method="post">
        <div><input type="text" name="uid" placeholder="id"></div>
        <div><input type="password" name="upw" placeholder="password"></div>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>