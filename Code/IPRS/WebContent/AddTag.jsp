<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Paper"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <%
 List<Paper> paperlist = (List<Paper>) request.getAttribute("paperlist");
 out.print("<p>Select Paper:</p><br/>");
 out.print("<select id=\"paperselect\">");
 if (paperlist != null) {
     for(int i=0, j=paperlist.size(); i<j; i++) {
     	Paper paper = (Paper) paperlist.get(i);
     	out.print("<option value =\"" + paper.getUri().replace("iprs/Papers/", "") + "\">"  + paper.getTitle() + "</option>");
     }
     }
 out.print("</select>");
 out.print("</br>");
 %>
  <input type="button" id="button2" value="Stored with RDF" onclick="store_RDF()"/>
  <p>Tag1:</p>
 <p><input type="text" id=tag1 size=25></p>
  <p>Tag2:</p>
 <p><input type="text" id=tag2 size=25></p>
  <p>Tag3:</p>
 <p><input type="text" id=tag3 size=25></p>
  <input type="button" id="button1" value="submit" onclick="add_tags()"/>
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
function add_tags(){
	var pid = document.getElementById('paperselect').value;
	var tag1 = document.getElementById('tag1').value;
	var tag2 = document.getElementById('tag1').value;
	var tag3 = document.getElementById('tag1').value;
	$.post("Tag",
		    {
	          pid: pid,
		      tag1: tag1,
		      tag2: tag2,
		      tag3: tag3
		    },
		    function(data,status){
			    alert("Add Tags success!");
			    });
};
function store_RDF(){
	var pid = document.getElementById('paperselect').value;
	$.post("RDF",
		    {
	          pid: pid
		    },
		    function(data,status){
			    alert("Stored in RDF success!");
			    });
};
</script>
</body>
</html>