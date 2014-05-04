<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="model.Paper"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有论文!</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	$.post("PaperSearch", {
		type : "getAll",
	}, function(data) {
		document.getElementById("AllPaper").innerHTML = data.trim();
	});
</script>
</head>
<body>
	<input type="hidden" id="uri" value="${user.uri}">
	<div id="AllPaper"></div>
</body>
</html>