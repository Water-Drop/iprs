<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新会议</title>
<style type="text/css">
body {
	background-color : #CCC;
}
</style>
</head>
<body>
<h1>It's page for add a new conference.</h1>
<form method=post action="Conference">
	<p>Name:</p>
	<p><input title="Name" type="text" name="name" size="25"></p>
    <p>BeginDate:</p>
    <p><input title="Begin" type="text" name="begin" size="25"></p>
    <p>EndDate:</p>
    <p><input title="End" type="text" name="end" size="25"></p>
    <p>Field:</p>
    <p><input title="Field" type="text" name="field" size="25"></p>
    <p>Description:</p>
    <p><textarea rows="10" cols="25" name="description"></textarea></p>
    <input type="submit" value="Submit">  
</form>
<a href = AdminPage.jsp>Back</a>
</body>
</html>