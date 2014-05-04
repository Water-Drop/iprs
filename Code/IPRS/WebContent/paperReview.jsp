<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>审阅论文</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	function allMyReview() {
		$.post("PaperReview",{type:"review"}, function(data) {
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
		var cRate = document.getElementById("cRate");
		var cConf = document.getElementById("cConf");
		$.post("Comment",
				{
			type:"add",
			tid:cTid.value,
			content:cContent.value,
			rate:cRate.value,
			confidence:cConf.value
			},
			function(data){
				alert("success");
				document.getElementById("myComment").style.visibility = 'hidden';
				document.getElementById("reviewInfo").style.visibility = 'visible';
				})
		}
</script>
</head>
<body onload="allMyReview();">
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
					<td>评分：<select id="cRate">
							<option value=1>1</option>
							<option value=2>2</option>
							<option value=3>3</option>
							<option value=4>4</option>
							<option value=5>5</option>
					</select></td>
				</tr>
				<tr>
					<td>信心：<select id="cConf">
							<option value=1>1</option>
							<option value=2>2</option>
							<option value=3>3</option>
							<option value=4>4</option>
							<option value=5>5</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="button" value="提交" onclick="cSubmit();"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>