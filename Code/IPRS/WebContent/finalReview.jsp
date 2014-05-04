<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>审阅论文</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	function allReview() {
		$.post("PaperReview",{type:"cReview"},function(data) {
			var x = document.getElementById("reviewInfo");
			x.innerHTML = data.trim();
		});
	}
	function review(tid) {
		document.getElementById("cTid").value = tid;
		document.getElementById("myComment").style.visibility = 'visible';
		document.getElementById("reviewInfo").style.visibility = 'hidden';
	}
	function cSubmit(){
		var cTid = document.getElementById("cTid");
		var cContent = document.getElementById("cContent");
		$.post("Comment",
				{
			type:"add",
			tid:cTid.value,
			content:cContent.value,
			},
			function(data){
				alert("success");
				document.getElementById("myComment").style.visibility = 'hidden';
				document.getElementById("reviewInfo").style.visibility = 'visible';
				})
		}
</script>
</head>
<body onload="allReview();">
	<div id="reviewInfo" class="position:absolute;top:0px;"></div>
	<div id="myComment" class="commentArea isHidden">
		<form id="paperForm">
			<input type="hidden" id="cTid">
			<table>
				<tr>
					<td>内容：<textarea id="cContent" rows="5"
							class="pSubmit-Abstract"></textarea></td>
				</tr>
				<tr>
					<td><input type="button" value="通过" onclick="cSubmit();"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>