<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page = "/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>
<section id="content">
	<h2>Hello Spring</h2>
	<br>
	<h3>ajax통신하기</h3>
	<h4>
		<button class="btn btn-outline-primary" onclick="basicAjax();">기본 ajax처리</button>
	</h4>
	<h4>
		<button class="btn btn-outline-success" onclick="convertAjax();">json 받기</button>
	</h4>
	<h4>
		<button class="btn btn-outline-warning" onclick="basic2();">jsp 받기</button>
	</h4>
	<h4>
		<button class="btn btn-outline-danger" onclick="memberList();">전체 회원 조회</button>
	</h4>
	<h4>
		<button class="btn btn-outline-dark" onclick="insertData();">데이터 저장</button>
	</h4>
	<div id="ajaxContainer">
	
	</div>
	<script type="text/javascript">
		const insertData=()=>{
			const data={userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"};
			/* $.post("${pageContext.request.contextPath}/ajax/insertData.do",
					{userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"},
					data=>{
						console.log(data);
					}); */
			/* $.ajax({
				url:"${pageContext.request.contextPath}/ajax/insertData.do",
				type:"post",
				data:JSON.stringify(data),
				contentType:"application/json;charset=utf-8",
				success:data=>{
					console.log(data);
				}
			});			 */		
			
			//fetch함수를 제공함. -> 다른라이브러리가 필요없다
			//fetch("URL주소",{요청에 대한 옵션})
			//	.then(response=>response.json())//응답내용파싱,,에러처리
			//	.then(data=>{처리로직})//success함수
			/* fetch("${pageContext.request.contextPath}/ajax/memberAll.do")
			.then(response=>{
						console.log(response);
						if(!response.ok) throw new Error("요청실패!"); 
						return response.json()
					}
			).then(data=>{
				console.log(data)
				}
			).catch(e=>{
				alert(e);
			}); */
			
			fetch("${pageContext.request.contextPath}/ajax/insertData.do",{
				method:"post",
				headers:{
					"Content-type":"application/json"
				},body:JSON.stringify(data)
			}).then(response=>{
						if(!response.ok) new Error("입력실패"); 
						return response.json()//서버가 json으로 응답했을때 
						//일반문자를 반환했을때 response.text()
					}
			)
			.then(data=>{
				console.log(data);
			}).catch(e=>{
				
			});
		}
	
	
	
	
		const memberList=()=>{
			$.get('${path}/ajax/memberList',data=>{
				console.log(data);
				const table=$("<table>");
				const header=["아이디","이름","나이","성별","이메일","전화번호","주소","취미","가입일"];
					const tr=$("<tr>");
				header.forEach(e=>{
					const th=$("<th>").text(e);
					tr.append(th);
				});
				
				table.append(tr);
				
				data.forEach(e=>{
					const bodyTr=$("<tr>");
					const userId=$("<td>").text(e.userId);
					const userName=$("<td>").text(e.userName);
					const age=$("<td>").text(e.age);
					const gender=$("<td>").text(e.gender);
					const email=$("<td>").text(e.email);
					const phone=$("<td>").text(e.phone);
					const address=$("<td>").text(e.address);
					const hobby=$("<td>").text(e.hobby.toString());
					const enrollDate=$("<td>").text(new Date(e.enrollDate));
					
					bodyTr.append(userId).append(userName).append(age).append(gender)
					.append(email).append(phone).append(address).append(hobby).append(enrollDate);
					
					table.append(bodyTr);
				});
				$("#ajaxContainer").html(table);
			});
		}
	
		const basicAjax=()=>{
			$.get('${path}/ajax/basicTest.do',(data)=>{
				console.log(data);
				$("#ajaxContainer").html("<h2>"+data+"</h2>");
			});
		}
	
		const convertAjax=()=>{
			$.get('${path}/ajax/converter',(data)=>{
				console.log(data);
			});
		}
		
		const basic2=()=>{
			$.get("${path}/ajax/basic2",data=>{
				console.log(data);
				$("#ajaxContainer").html(data);
			});	
		}
	</script>
</section>
<jsp:include page = "/WEB-INF/views/common/footer.jsp"/>