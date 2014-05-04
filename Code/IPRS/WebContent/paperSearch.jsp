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
	function pSearch() {
		var pTitle = document.getElementById("title");
		var pAbs = document.getElementById("abstra");
		var pAuthor = document.getElementById("author");
		document.getElementById("AllPaper").innerHTML = "搜索中...";
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
		<h1>搜索：</h1>
		标题：<input id="title">概要：<input id="abstra">作者：<input
			id="author"> <input type="button" onclick="pSearch();"value="搜索">
	</form>
	<div id="AllPaper"></div>
</body>
</html>