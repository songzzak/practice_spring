<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" demo List "/>
</jsp:include>
<section id="content">
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
			<th scope="col">수정</th>
		</tr>
		<c:if test="${empty demoList}">
			<tr ><tdcolspan="7">조회 내역이 없습니다.</td></tr>
		</c:if>
		<c:if test="${not empty demoList }">
			<c:forEach var="d" items="${demoList }">
				<tr>
					<td>${d.devNo }</td>
					<td>${d.devName }</td>
					<td>${d.devAge }</td>
					<td>${d.devEmail }</td>
					<td>${d.devGender }</td>
					<td>
						<c:if test="${not empty d.devLang }">
							<c:forEach var="lang" items="${d.devLang }">
								${lang }
							</c:forEach>
						</c:if>
					</td>
					<td>
						<button onclick="fn_updateDemo(${d.devNo });">수정</button>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>	
</section>
<script type="text/javascript">
const fn_updateDemo=(no)={
	location.replace("${path}/demo/updateDemo.do?no="+no);
}

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>