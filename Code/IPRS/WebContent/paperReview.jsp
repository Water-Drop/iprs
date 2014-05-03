<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>иСтдбшнд</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
function allMyReview(){
	$.post("PaperReview", 
			function(data) {
				var x = document.getElementById("reviewInfo");
				x.innerHTML = data.trim();
				});	
}
</script>
</head>
<body onload="allMyReview();">
	<div id="reviewInfo" class="position:absolute;top:0px;"></div>
</body>
</html>