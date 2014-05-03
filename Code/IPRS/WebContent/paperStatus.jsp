<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>论文查看</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var uId = document.getElementById("uri").value
				.replace("iprs/User/", "");
		$.post("PaperSearch", {
			type : "getUid",
			uid : uId,
		}, function(data, status) {
			var x = document.getElementById("paperInfo");
			x.innerHTML = data.trim();
		})
	}
	function modifyPaper(s,st) {
		var x = document.getElementById("paperInfo");
		var pModif = document.getElementById("modification");
		var pUri = document.getElementById("mUri");
		var pSta = document.getElementById("mSta");
		pSta.value = st;
		pUri.value = s;
		x.style.visibility = "hidden";
		pModif.style.visibility = "visible";
	}
	function mSubmit(){
		var mTitle = document.getElementById("mTitle");
		var mAbs = document.getElementById("mAbstract");
		var mSta = document.getElementById("mSta");
		var mUri = document.getElementById("mUri");
		$.post("PaperEdit",
				{
				title:mTitle.value,
				abstra:mAbs.value,
				status:mSta.value,
				uri:mUrs.value,
				loc:""
				},
				function(data){
					alert(data.trim());
					var x = document.getElementById("paperInfo");
					var pModif = document.getElementById("modification");
					x.style.visibility = "visible";
					pModif.style.visibility = "hidden";					
				});
		}
</script>
</head>
<body>
	<input type="hidden" id="uri" value="${user.uri}">
	<div id="paperInfo" class="position:absolute;top:0px;"></div>
	<div id="modification" class="pModification isHidden">
		<form id="paperForm">
			<input type ="hidden" id="mSta">
			<table>
			<tr>
			<td><h1>修改论文：</h1></td>
			</tr>
				<tr>
					<td>标题 ：<input id="mTitle" type="text" class="pSubmit-Title"></td>
				</tr>
				<tr>
					<td>概要 ：<textarea id="mAbstract" rows="5"
							class="pSubmit-Abstract"></textarea></td>
				</tr>
				<tr>
					<td><input type="hidden" id="mUri" value="0"><input
						type="button" value="提交" onclick="mSubmit();"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>