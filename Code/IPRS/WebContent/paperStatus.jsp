<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>论文查看</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var uId =document.getElementById("uri").value.replace("iprs/User/", "");
		alert(1);
		$.post("PaperSearch",
				{
				type:"getUid",
				uid:uId,
				},
				function(data,status){
					var x = document.getElementById("testX");
					x.innerHTML = data.trim();
					})
		}
</script>
</head>
<body>
<input type="hidden" id="uri" value="${user.uri}">
<div id = "testX">
</div>
</body>
</html>