<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>欢迎来到国际论文审阅系统</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<style type="text/css">
body {
	background-image: url(imgs/welcome.jpg);
	background-repeat: repeat;
	background-position: top;
}
</style>
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	function loginToRegister() {
		var lWindow = document.getElementById("loginWindow");
		var rWindow = document.getElementById("registerWindow");
		lWindow.style.visibility = 'hidden';
		rWindow.style.visibility = 'visible';
		}
	function registerToLogin(){
		var lWindow = document.getElementById("loginWindow");
		var rWindow = document.getElementById("registerWindow");
		rWindow.style.visibility = 'hidden';
		lWindow.style.visibility = 'visible';
		}
	function login() {
		var usn = document.getElementById("lUsername");
		var psw = document.getElementById("lPassword");
		$.post("Register",
				{
				type:"login",
				username:usn.value,
				password:psw.value
				},
				function(data,status){
						window.location.href=data.trim();
					})
		}
	function isValidUsername(){
		var usn = document.getElementById("rUsername");
		var cMsg = document.getElementById("usnCheckMsg");
		$.post("Register",
				{
				type:"check",
				username:usn.value
				},
				function(data,status){
					if (data.trim() == "success"){
						cMsg.innerHTML = "用户名可用！";
						cMsg.style.visibility = 'visible';
						}
					else{
						cMsg.innerHTML = "<img src=\"icos/error.jpg\">用户名不可用!";
						cMsg.style.visibility = 'visible';
						}
					}
				)
		}
	function register(){
		var usn = document.getElementById("rUsername");
		var psw = document.getElementById("rPassword");
		var eml = document.getElementById("email");
		$.post("Register",
				{
				type:"register",
				username:usn.value,
				password:psw.value,
				email:eml.value
				},
				function(data,status){
					window.location.href=data.trim();
				})
		}

	function isSamePassword(){		
		var psw = document.getElementById("rPassword");
		var cpsw = document.getElementById("checkPassword");
		var cMsg = document.getElementById("pswCheckMsg");
		if ((psw.value == cpsw.value) && (psw.value != "")){
			cMsg.innerHTML = "密码正确！";
			cMsg.style.visibility = 'visible';
			}
		else{
			cMsg.innerHTML = "<img src=\"icos/error.jpg\">密码错误!";
			cMsg.style.visibility = 'visible';
			}
	}
</script>

</head>
<body>
	<div id="loginWindow" class="lWindow">
		<div class="loginTopics">
		<h1>欢迎来到国际论文审阅系统</h1>
		<h3>Welcome to the International Paper Review System</h3>
		</div>
		<div class="popLoginArea">
			<table>
				<tbody>
					<tr>
						<td>
							<div class="popLogin-inner">
								<div class="popLogin-inputText">用户名：</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="popLogin-inner">
								<div class="popLogin-inputText">密码：</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="padding-top: 13px;"></td>
					</tr>
				</tbody>
			</table>
			<form name="loginForm" method="post">
				<input id="lUsername" type="text" class="inputFieldStyle iField1"> 
				<input id="lPassword" type="password" class="inputFieldStyle iField2">
				<input type="button" class="lrButton leftButton bUrl1" value=" " onclick="login();">
				<input type="button" class="lrButton rightButton bUrl2"	onclick="loginToRegister();">
			</form>
		</div>
	</div>
	<div id="registerWindow" class="rWindow isHidden">
		<div class="loginTopics">
		<h1>欢迎加入国际论文审阅系统</h1>
		<h3>Welcome to join the International Paper Review System</h3>
		</div>
		<div class="popLoginArea">
			<table>
				<tbody>
					<tr>
						<td>
							<div class="popLogin-inner">
								<div class="popLogin-inputText">用户名：</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="popLogin-inner">
								<div class="popLogin-inputText">密码：</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="popLogin-inner">
								<div class="popLogin-inputText">确认密码：</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="popLogin-inner">
								<div class="popLogin-inputText">邮箱：</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="padding-top: 13px;"></td>
					</tr>
				</tbody>
			</table>
			<form name="registerForm" method="post">
				<input id="rUsername" type="text" class="inputFieldStyle iField1" onblur="isValidUsername();">
				<input id="rPassword" type="password" class="inputFieldStyle iField2" onblur="isSamePassword();"> 
				<input id="checkPassword" type="password" class="inputFieldStyle iField3" onblur="isSamePassword();">  
				<input id="email" type="text" class="inputFieldStyle iField4"> 
				<input type="button" class="lrButton leftButton bUrl3" value=" " onclick="register();"> 
				<input type="button" class="lrButton rightButton bUrl4"	onclick="registerToLogin();">
			</form>
			<div id="usnCheckMsg" class="usnCheckMsg isHidden">				
			</div>
			<div id="pswCheckMsg" class="pswCheckMsg isHidden">				
			</div>
		</div>
	</div>

</body>
</html>
