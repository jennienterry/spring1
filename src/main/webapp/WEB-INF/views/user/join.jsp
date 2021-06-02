<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <h1>회원가입</h1>
<form id="frm" action="join" method="post">
    <div>
        <input type="text" name="uid" placeholder="아이디">
        <input type="button" name="btnChkId" placeholder="중복ID체크">
    </div>
    <div id="chkUidResult"></div>
    <div><input type="password" name="upw" placeholder="비밀번호"></div>
    <div><input type="password" name="chkUpw" placeholder="비밀번호확인"></div>
    </div>
    <div>
        <input type="text" name="unm" placeholder="이름">
    </div>
    <div>
        <label>남<input type="radio" name="gender" value="1" checked></label>
        <label>여<input type="radio" name="gender" value="0"></label>
    </div>
    <input type="submit" value="join">
</form>
</body>
</html>