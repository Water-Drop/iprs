<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="model.Paper"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>��������!</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
	function pSearch() {
		var pTitle = document.getElementById("title");
		var pAbs = document.getElementById("abstra");
		var pAuthor = document.getElementById("author");
		document.getElementById("AllPaper").innerHTML = "������...";
		$.post("PaperSearch", {
			type : "search",
			title : pTitle.value,
			abst : pAbs.value,
			author : pAuthor.value
		}, function(data) {
			document.getElementById("AllPaper").innerHTML = data.trim();
		});
	}
</script>
</head>
<body>
	<input type="hidden" id="uri" value="${user.uri}">
	<form>
		<h1>������</h1>
		���⣺<input id="title">��Ҫ��<input id="abstra">���ߣ�<input
			id="author"> <input type="button" onclick="pSearch();"value="����">
	</form>
	<div id="AllPaper"></div>
</body>
</html>