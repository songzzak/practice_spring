<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h3>채팅방</h3>
	<div class=container-fluid>
		<div id="chattingcontainer">
		
		</div>
		<div class="row">
			<div class="col-xm-10">
				<input type="text" id="msg" class="form-controller">
			</div>
			<div class="com-xm-2">
				<button class="btn btn-outline-danger" id="send">전송</button>
			</div>
		</div>	
	</div>
	<script type="text/javascript">
		const server = new WebSocket("ws://localhost:8080/spring/chatting");
		const me='${loginMember.userId}';
		server.onopen=data=>{
			server.send(JSON.stringify(new Message("open",me)))
		}
		server.onmessage=data=>{
			const msg = JSON.parse(data.data);
			console.log(msg);
			switch(msg.type){
			case "msg": printMessage(msg);break;
			case "system":systemMessage(msg.msg);break;
			}
		}
		server.onclose=data=>{
			
		}	
		
		$("#send").click(e=>{
			const msg=$("#msg").val();
			server.send(JSON.stringify(new Message("msg",me,"",msg,"")));
		})
		
		function printMessage(msg){
			const msgContainer=$("<div>")
			const content=$("<h5>").text(msg.msg).css("text-align",msg.sender==me?"right":"left");
			msgContainer.append(content);
			$("#chattingcontainer").append(msgContainer);
		}
		
		
		
		class Message{
			constructor(type="",sender="",reciever="",msg="",room=""){
				this.type=type;
				this.sender=sender;
				this.reciever=reciever;
				this.msg=msg;
				this.room=room;
			}
		}
		
	</script>
</body>
</html>