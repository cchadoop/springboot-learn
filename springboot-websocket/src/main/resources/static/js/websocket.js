var websocket = null
if('WebSocket' in window){
	websocket = new WebSocket("ws://localhost:8080/ws");
}else{
	alert("not support WebSocket");
}

websocket.onerror = function(){
	appendMessage("error");
}

websocket.onopen = function(){
	appendMessage("open");
}

websocket.onclose = function(){
	appendMessage("close");
}

//onmessage事件接收从服务端返回的数据
websocket.onmessage=function(event){
	appendMessage(event.data);
}

window.onbeforeunload = function(){
	websocket.close();
}

function appendMessage(message){
	var context = $("#context").html()+"<br/>"+message;
	$("#context").html(context);
}

function closeWebsocket(){
	websocket.close();
}

function sendMessage(){
	var message = $("#message").val();
	//send()方法向服务器发送数据
	websocket.send(message);
}