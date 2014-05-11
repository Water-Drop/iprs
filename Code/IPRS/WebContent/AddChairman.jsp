<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User"%>
<%@ page import="model.Conference"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Chairman</title>
<style type="text/css">
body {
	background-color : #CCC;
}
</style>
</head>
<body>
<%
    List<User> userlist = (List<User>) request.getAttribute("userlist");
    List<Conference> conflist = (List<Conference>) request.getAttribute("conflist");
    out.print("<p>Select User:</p><br/>");
    out.print("<select id=\"userselect\">");
    if (userlist != null) {
        for(int i=0, j=userlist.size(); i<j; i++) {
        	User user = (User) userlist.get(i);
        	out.print("<option value =\"" + user.getUri() + "\">"  + user.getUsername() + "</option>");
        }
       
        }
    out.print("</select>");
    out.print("<p>Select Conference:</p><br/>");
    out.print("<select id=\"confselect\">");
    if (conflist != null) {
        for(int i=0, j=conflist.size(); i<j; i++) {
        	Conference conf = (Conference) conflist.get(i);
        	out.print("<option value =\"" + conf.getUri() + "\">"  + conf.getName() + "</option>");
        }
        }
    out.print("</select>");
%>
<input type="button" id="button1" value="submit" onclick="add_chairman()"/>
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
function add_chairman(){
	var uid = document.getElementById('userselect').value;
	var cid = document.getElementById('confselect').value;
	$.post("Role",
		    {
	    	  type: "chairman",
	          uid: uid,
		      cid: cid
		    },
		    function(data,status){
			    alert("Add chairman success!");
			    });
};
</script>
</body>
</html>