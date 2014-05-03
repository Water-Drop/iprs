<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户主页</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<style type="text/css">
body {
	background-image: url(imgs/mainBackground.jpg);
	background-repeat: repeat;
	background-position: top;
}
</style>
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	var bgc = "rgb(204, 156, 133)";
	var hlc = "#CCC";
	function doNothing(){}
	function mouseOnButton(x) {
			x.style.background = hlc;
		}
	function mouseOutButton(x) {
			x.style.background = bgc;
		}
	function mouseClick(x) {
		var actChair = document.getElementById("actChair"); 
		var actAssign = document.getElementById("actAssign"); 
		var actFinal = document.getElementById("actFinal"); 
		actChair.style.background = bgc;
		actAssign.style.background = bgc;
		actFinal.style.background = bgc;
		actChair.setAttribute("onmouseout", "mouseOutButton(this);");
		actAssign.setAttribute("onmouseout", "mouseOutButton(this);");
		actFinal.setAttribute("onmouseout", "mouseOutButton(this);");
		x.style.background = hlc;
		x.onmouseout=null;
		var pMain = document.getElementById("pMain");
		var cAssign = document.getElementById("cAssign");
		var fReview = document.getElementById("fReview");
		pMain.style.visibility = "hidden";
		cAssign.style.visibility = "hidden";
		fReview.style.visibility = "hidden";
		if (x.id == "actChair")
			{
				pMain.style.visibility = "visible";	
			}
		else if (x.id == "actAssign")
		{
			cAssign.style.visibility = "visible";
		}
		else if (x.id == "actFinal")
		{
			fReview.style.visibility = "visible";
		}		
		}
</script>
</head>
<body>
	<div class="mainArea">
		<div class="userActArea">
			<div class="pInfoArea">
				<div id="actChair" class="prAct-Title cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h2>个人主页</h2>
				</div>
			</div>
			<div class="paperActArea">				
				<div class="prAct-Title cBackground1">
					<h2>权限分配</h2>
				</div>
				<div id="actAssign" class="prAct-Op cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h3>分配审稿</h3>
				</div>
				<div id="actFinal" class="prAct-Op cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h3>最终审稿</h3>
				</div>
			</div>
		</div>
		<div class="actArea c5">
			<div id="pMain" class="pInfo">
				<h1>不论如何，欢迎您来到IRPS</h1>
				<h1>欢迎您，会议主席${user.username}</h1>
				<h1>您的Uri是 ${user.uri}</h1>
			</div>
		</div>
		<iframe src="AssignmentTask.jsp" id="cAssign" class="pIFrame isHidden"></iframe>
		<iframe src="paperReview.jsp" id="fReview" class="pIFrame isHidden"></iframe>
	</div>
</body>
</html>