<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" charset="ISO-8859-1">
<script src="jquery/JQuery.js"></script>
<title>Broadcastchat Page</title>
<style>
.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}

.button1 {background-color: #008CBA;} 
</style>
</head>
<body>
<form>
<input type="text" id="textMessage" placeholder=" Write Message....">
<button class="button button1" type="button"  onclick="sendMessage();">Send</button>
<a href="logout.jsp"><button class="button button1" type="button"  onclick="closemessage();">Close</button></a><br>
</form>
<div id="users"></div>
Users : <textarea id="textUsersArea" rows="25" cols="25" readonly="readonly"></textarea>
Messages : <textarea id="textMessageArea" rows="10" cols="50" readonly="readonly"></textarea>
<script type="text/javascript">
 var websocket=new WebSocket("ws://localhost:8088/RealTalk/serverendpoint");
  var username="<%= request.getAttribute("username")%>";
 var MessageTextArea=document.getElementById("textMessageArea");
 websocket.onopen=function(message1){processOpen(message1);}
 websocket.onclose=function(message2){processClose(message2);}
 websocket.onmessage=function(message3){processMessage(message3);}
 websocket.onerror=function(message4){processError(message4);}
 function processOpen(message)
 {
	 MessageTextArea.value+=username+" connected.."+"\n";
 }
 function processClose(message)
 {
	 MessageTextArea.value+=username+"disconnected.."+"\n";
 }
 function processMessage(message)
 {
	 var json=JSON.parse(message.data);
	 if(json!=null)
		 {
		  if(json.message!=null)
		   {
	       MessageTextArea.value +=json.name +" -"+json.message+"\n";
		   textUsersArea.value="";
		   var i=0;
		   while(i<json.users.length)
			{
               textUsersArea.value+=json.users[i]+"\n";
              i++;	                
            }
	        }
		  else
			  {
			   textUsersArea.value="";
			   var i=0;
			   while(i<json.users.length)
				{
	               textUsersArea.value+=json.users[i]+"\n";
	              i++;	                
	            }
			  }
		 }
  }
 function processError(message)
 {
	 MessageTextArea.value +="error!"+"\n";
	 console.log(message.data);
 }
 function sendMessage(){
	 if(textMessage.value!="close")
	{
			 var mes={"message":textMessage.value};
			 console.log("json stringify :"+JSON.stringify(mes));
		     websocket.send(JSON.stringify(mes));
		     textMessage.value="";

	}
	 else{
		 websocket.close();
		 }
 }
 function closemessage(){
	
 }
</script>
</body>
</html>
