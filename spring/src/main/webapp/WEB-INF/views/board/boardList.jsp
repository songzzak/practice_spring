<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
   <jsp:param name="title" value="Board List"/>
</jsp:include>
<section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        <button class="btn btn-outline-success" onclick="location.assign('${path}/board/insertBoard.do')">글 쓰기</button>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty boards }">
            	<c:forEach var="b" items="${boards }" varStatus="vs">
            		<tr>
		                <td>${b.boardNo }</td>
		                <td>
		                	<a href="${path }/board/boardView.do?no=${b.boardNo}">
		                ${b.boardTitle }
		                	</a>
		                </td>
		                <td>${b.boardWriter }</td>
		                <td>${b.boardDate }</td>
		                <td></td>
		                <td>${b.boardReadCount }</td>
		            </tr>
            	</c:forEach>
            </c:if>
        </table> 
        <div id="pageBar">
        ${pageBar }
        </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>