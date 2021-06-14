<%--
  Created by IntelliJ IDEA.
  User: kim ji min
  Date: 2021-06-03
  Time: 오후 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <form action="list" id="frm"> <!-- method생략하면 get방식 -->
        <input type="hidden" name="page" value="${param.page == null ? 1 : param.page}">
            <select name="recordCnt"> <!-- onchange="getList();" / js에서 change 적었을 때 사용하는 건데 안적어도됨-->
                <c:forEach begin="5" end="20" step="5" var="cnt">
                    <c:choose>
                        <c:when test="${cnt eq param.recordCnt}">
                            <option selected>${cnt}</option>
                        </c:when>
                        <c:otherwise>
                            <option>${cnt}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
        </select>
    </form>
</div>

<h1>리스트</h1>
<table>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>작성일시</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="item">
    <tr class="record" onclick="moveToDetail(${item.iboard});">
        <td>${item.iboard}</td>
        <td>
            <c:choose>
                <c:when test="${param.searchType eq 1 || param.searchType eq 2}">
                    ${item.title.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                </c:when>
                <c:otherwise>
                    ${item.title}
                </c:otherwise>
            </c:choose>
            <!-- 좋아요 -->
            <c:if test="${not empty sessionScope.loginUser && item.isFav eq 1}">
                <i class="fas fa-heart"></i>
            </c:if>
        </td>

        <c:choose>
            <c:when test="${empty item.profileImg}">
                <c:set var="img" value="/res/img/noprofile.jpg"/>
            </c:when>
            <c:otherwise>
                <c:set var="img" value="/img/${item.iuser}/${item.profileImg}"/>
            </c:otherwise>
        </c:choose>
        <td>
            <c:choose>
                <c:when test="${param.searchType eq 4}">
                    ${item.writerNm.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                </c:when>
                <c:otherwise>
                    ${item.writerNm}
                </c:otherwise>
            </c:choose>
            <img src="${img}" class="profileImg">
        </td>
        <td>${item.regdt}</td>
    </tr>
    </c:forEach>
</table>
<div>
<c:forEach var="i" begin="1" end="${requestScope.maxPageVal}">
    <c:choose>
        <c:when test="${(empty param.page && i eq 1) || param.page eq i}">
            <span class="selected">${i}</span>
        </c:when>
        <c:otherwise>
    <span><a href="list?page=${i}&recordCnt=${requestScope.cPage.recordCnt}">${i}</a></span>
        </c:otherwise>
    </c:choose>
</c:forEach>
</div>

<!-- items : 자료 하나씩 넣어가면서 실행 (list나 배열을 받았을 때)
     begin, end : begin에서 end까지 step만큼 [값 안주면 기본 1, 숫자 주는만큼] 건너뛴다 -->