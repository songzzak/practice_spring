<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page = "/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="Demo 페이지"/>
</jsp:include>
<section id="content">
	<div id="demo-container">
		<form id="devFrm" method="post">
			<div class="form-group row">
				<label for="devName" class="col-sm-2 col-form-label">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="devName" name="devName" >
				</div>
			</div>
			<div class="form-group row">
				<label for="devAge" class="col-sm-2 col-form-label">나이</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="devAge" name="devAge">
				</div>
			</div>
			<div class="form-group row">
				<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="devEmail" name="devEmail">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="devGender" id="devGender0" value="M">
						<label class="form-check-label" for="devGender0">남</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="devGender" id="devGender1" value="F">
						<label class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang" id="devLang0" value="Java">
						<label class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang" id="devLang1" value="C">
						<label class="form-check-label" for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang" id="devLang2" value="Javascript">
						<label class="form-check-label" for="devLang2">Javascript</label>
					</div>
				</div>
			</div>
<!-- 			<div class="form-group row">
				<label for="birthday" class="col-sm-2 col-form-label">생년월일</label>
				<div class="col-sm-10">
					<input type="date" class="form-control" id="birthday" name="birthday">
				</div>
			</div> -->
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo1.do')">
						spring메소드 서블릿처럼 이용하기	
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo2.do')">
						1:1로 매칭해서 데이터 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo3.do')">
						@RequestParam이용해서 데이터 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo4.do')">
						vo(command)이용해서 데이터 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo5.do')">
						Map 이용해서 데이터 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo6.do')">
						추가 데이터 확인하기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo7.do')">
						model and view
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo8.do')">
						@ResponseBody 이용하기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSand('demo/demo9.do')">
						method방식에 따라 요청처리
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="insertDemo();">
						insertDemo
					</button>
				</div>
			</div>
		</form>
	</div>
</section>
<script type="text/javascript">
	const insertDemo=()=>{
		$("#devFrm").attr("action","${path}/demo/insertDemo.do");
		$("#devFrm").submit();
	}

	const requestSand=(url)=>{
		$("#devFrm").attr("action","${path}/"+url);
		$("#devFrm").submit();
	}
</script>
<style>
	div#demo-container{
			width:50%;
			padding:15px;
			margin:0 auto;
			border:1px solid lightgray;
			border-radius:10px;
		}
</style>
<jsp:include page = "/WEB-INF/views/common/footer.jsp"/>