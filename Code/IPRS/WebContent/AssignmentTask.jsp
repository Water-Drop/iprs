<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User"%>
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
	List<User> userlist = (List<User>) request.getAttribute("userlist");
    List<Paper> paperlist = (List<Paper>) request.getAttribute("paperlist");
    out.print("<select id=\"paperselect\">");
    if (paperlist != null) {
        for(int i=0, j=paperlist.size(); i<j; i++) {
        	Paper paper = (Paper) paperlist.get(i);
        	out.print("<option value =\"" + paper.getUri().replace("iprs/Papers/", "") + "\">"  + paper.getTitle() + "</option>");
        }
        }
    out.print("</select>");
    out.print("</br>");
    out.print("<table>");
    out.print("<tr><td>First Reviewer</td>");
    out.print("<td><select id=\"userselect1\">");
    out.print("<option value = \"empty\">" + "</option>");
    if (userlist != null) {
        for(int i=0, j=userlist.size(); i<j; i++) {
        	User user = (User) userlist.get(i);
        	out.print("<option value =\"" + user.getUri().replace("iprs/User/", "") + "\">"  + user.getUsername() + "</option>");
        }
       
        }
    out.print("</select></td></tr>");
    out.print("<tr><td>Second Reviewer</td>");
    out.print("<td><select id=\"userselect2\">");
    out.print("<option value = \"empty\">" + "</option>");
    if (userlist != null) {
        for(int i=0, j=userlist.size(); i<j; i++) {
        	User user = (User) userlist.get(i);
        	out.print("<option value =\"" + user.getUri().replace("iprs/User/", "") + "\">"  + user.getUsername() + "</option>");
        }
       
        }
    out.print("</select></td></tr>");
    out.print("<tr><td>Third Reviewer</td>");
    out.print("<td><select id=\"userselect3\">");
    out.print("<option value = \"empty\">" + "</option>");
    if (userlist != null) {
        for(int i=0, j=userlist.size(); i<j; i++) {
        	User user = (User) userlist.get(i);
        	out.print("<option value =\"" + user.getUri().replace("iprs/User/", "") + "\">"  + user.getUsername() + "</option>");
        }
       
        }
    out.print("</select></td></tr>");
    out.print("<tr><td>Fourth Reviewer</td>");
    out.print("<td><select id=\"userselect4\">");
    out.print("<option value = \"empty\">" + "</option>");
    if (userlist != null) {
        for(int i=0, j=userlist.size(); i<j; i++) {
        	User user = (User) userlist.get(i);
        	out.print("<option value =\"" + user.getUri().replace("iprs/User/", "") + "\">"  + user.getUsername() + "</option>");
        }
        }
    out.print("</select></td></tr>");
    out.print("<tr><td>Fifth Reviewer</td>");
    out.print("<td><select id=\"userselect5\">");
    out.print("<option value = \"empty\">" + "</option>");
    if (userlist != null) {
        for(int i=0, j=userlist.size(); i<j; i++) {
        	User user = (User) userlist.get(i);
        	out.print("<option value =\"" + user.getUri().replace("iprs/User/", "") + "\">"  + user.getUsername() + "</option>");
        }
        }
    out.print("</select></td></tr>");
    out.print("</table>");
 %>
<input type="button" id="button1" value="submit" onclick="add_tasks()"/>
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
 function add_tasks(){
	var pid = document.getElementById('paperselect').value;
	var uid1 = document.getElementById('userselect1').value;
	var uid2 = document.getElementById('userselect2').value;
	var uid3 = document.getElementById('userselect3').value;
	var uid4 = document.getElementById('userselect4').value;
	var uid5 = document.getElementById('userselect5').value;
	if (uid1 =="empty"){
		uid1 = null;
			}
	if (uid2 =="empty"){
		uid2 = null;
		} 
	if (uid3 =="empty"){
		uid3 = null;
		}
	if (uid4 =="empty"){
		uid4 = null;
		}
	if (uid5 =="empty"){
		uid5 = null;
		}
	$.post("TaskAssignment",
		    {
	          pid: pid,
		      uid1: uid1,
		      uid2: uid2,
		      uid3: uid3,
		      uid4: uid4,
		      uid5: uid5
		    },
		    function(data,status){
			    alert("Assignment Tasks success!");
			    });
};
</script>
</body>
</html>