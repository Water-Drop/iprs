/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: PaperEdit.java
 * Date		: May 3, 2014
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paper;
import ejb.PaperBean;

@WebServlet("/PaperEdit")
public class PaperEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	PaperBean paperBean;
	
    public PaperEdit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		Paper paper = paperBean.get(pid);
		RequestDispatcher rd = request.getRequestDispatcher("paperEdit.jsp");
		request.setAttribute("paper", paper);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Paper
		Paper paper = new Paper();
		String title = request.getParameter("title");
		if (title == null) {
			PrintWriter out = response.getWriter();
			out.write("Error : Title is empty.");
       		out.flush();
        	out.close();
        	return ;
		}
		paper.setTitle(title);
		String abstra = request.getParameter("abstract");
		if (abstra == null) {
			PrintWriter out = response.getWriter();
			out.write("Error : Abstract is empty.");
       		out.flush();
        	out.close();
        	return ;
		}
		paper.setAbstract(abstra);
		String cid = request.getParameter("cid");
		if (cid == null) {
			PrintWriter out = response.getWriter();
			out.write("Error : Cid is empty.");
       		out.flush();
        	out.close();
        	return ;
		}
		paper.setCid(cid);
		String status = request.getParameter("status");
		if (status == null) {
			PrintWriter out = response.getWriter();
			out.write("Error : Status is empty.");
       		out.flush();
        	out.close();
        	return ;
		}
		paper.setStatus(Integer.parseInt(status));
		Date lmTime = new Date();
		paper.setLMTime(lmTime);
		String uid = request.getParameter("uid");
		if (uid == null) {
			PrintWriter out = response.getWriter();
			out.write("Error : Uid is empty.");
       		out.flush();
        	out.close();
        	return ;
		}
		paper.setUid(uid);
		String location = request.getParameter("location");
		if (location == null) {
			PrintWriter out = response.getWriter();
			out.write("Error : Uid is empty.");
       		out.flush();
        	out.close();
        	return ;
		}
		paper.setLocation(location);
		if (0 == paperBean.edit(paper))
		{
			PrintWriter out = response.getWriter();
			out.write("Success.");
       		out.flush();
        	out.close();
        	return ;
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.write("Unknow error, please refresh and try again.");
       		out.flush();
        	out.close();
        	return ;
		}
	}
}
