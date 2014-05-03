<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>