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
		var actMain = document.getElementById("actMain"); 
		var actSubmit = document.getElementById("actSubmit"); 
		var actStatus = document.getElementById("actStatus"); 
		var actModify = document.getElementById("actModify"); 
		var actRegret = document.getElementById("actRegret"); 
		var actReview = document.getElementById("actReview");
		var actSeeAll = document.getElementById("actSeeAll"); 
		actMain.style.background = bgc;
		actSubmit.style.background = bgc;
		actStatus.style.background = bgc;
		actModify.style.background = bgc;
		actRegret.style.background = bgc;
		actReview.style.background = bgc;
		actSeeAll.style.background = bgc;
		actMain.setAttribute("onmouseout", "mouseOutButton(this);");
		actSubmit.setAttribute("onmouseout", "mouseOutButton(this);");
		actStatus.setAttribute("onmouseout", "mouseOutButton(this);");
		actModify.setAttribute("onmouseout", "mouseOutButton(this);");
		actRegret.setAttribute("onmouseout", "mouseOutButton(this);");
		actReview.setAttribute("onmouseout", "mouseOutButton(this);");
		actSeeAll.setAttribute("onmouseout", "mouseOutButton(this);");
		x.style.background = hlc;
		x.onmouseout=null;
		var pMain = document.getElementById("pMain");
		var pSubmit = document.getElementById("pSubmit");
		var pStatus = document.getElementById("pStatus");
		var pModify = document.getElementById("pModify");
		var pRegret = document.getElementById("pRegret");
		var pReview = document.getElementById("pReview");
		var pSeeAll = document.getElementById("pSeeAll");
		pMain.style.visibility = "hidden";
		pSubmit.style.visibility = "hidden";
		pStatus.style.visibility = "hidden";
		pModify.style.visibility = "hidden";
		pRegret.style.visibility = "hidden";
		pReview.style.visibility = "hidden";
		pSeeAll.style.visibility = "hidden";
		if (x.id == "actMain")
			{
				pMain.style.visibility = "visible";	
			}
		else if (x.id == "actSubmit")
		{
			pSubmit.style.visibility = "visible";
		}
		else if (x.id == "actStatus")
		{
			pStatus.style.visibility = "visible";
		}			
		else if (x.id == "actModify")
		{
			pModify.style.visibility = "visible";
		}			
		else if (x.id == "actRegret")
		{
			pRegret.style.visibility = "visible";
		}			
		else if (x.id == "actReview")
		{
			pReview.style.visibility = "visible";
		}			
		else if (x.id == "actSeeAll")
		{
			pSeeAll.style.visibility = "visible";
		}			
		}
</script>
</head>
<body>
	<div class="mainArea">
		<div class="userActArea">
			<div class="pInfoArea">
				<div id="actMain" class="prAct-Title cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h2>个人主页</h2>
				</div>
			</div>
			<div class="paperActArea">
				<div class="prAct-Title cBackground1">
					<h2>论文相关</h2>
				</div>
				<div id="actSubmit" class="prAct-Op cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h3>论文投稿</h3>
				</div>
				<div id="actStatus" class="prAct-Op cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h3>论文查看</h3>
				</div>
				<div id="actModify" class="prAct-Op cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h3>论文修改</h3>
				</div>
				<div id="actRegret" class="prAct-Op cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h3>论文撤回</h3>
				</div>
			</div>
			<div class="reviewActArea">
				<div class="prAct-Title cBackground1">
					<h2>审阅任务</h2>
				</div>
				<div id="actReview" class="prAct-Op cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
					<h3>审阅论文</h3>
				</div>
			</div>
			<div id="actSeeAll" class="prAct-Title cBackground1" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);" onclick="mouseClick(this);">
				<h2>所有论文</h2>
			</div>
			<div class="prAct-Title cBackground1 isHidden" onmouseover="mouseOnButton(this);" onmouseout="mouseOutButton(this);">
				<h2>主席任务?</h2>
			</div>
		</div>
		<div class="actArea c5">
			<div id="pMain" class="pInfo">
				<h1>此处应有个人信息</h1>
				<h1>但是论文投稿人的个人信息并不重要</h1>
				<h1>不论如何，欢迎来到IRPS</h1>
				<h1>欢迎你，用户${user.username}</h1>
				<h1>你的Uri是 ${user.uri}</h1>
			</div>
		</div>
		<iframe src="paperSubmit.jsp" id="pSubmit" class="pIFrame isHidden"></iframe>
		<iframe src="paperStatus.jsp" id="pStatus" class="pIFrame isHidden"></iframe>
		<iframe src="paperModify.jsp" id="pModify" class="pIFrame isHidden"></iframe>
		<iframe src="paperRegret.jsp" id="pRegret" class="pIFrame isHidden"></iframe>
		<iframe src="paperReview.jsp" id="pReview" class="pIFrame isHidden"></iframe>
		<iframe src="paperSeeAll.jsp" id="pSeeAll" class="pIFrame isHidden"></iframe>
	</div>
</body>
</html>