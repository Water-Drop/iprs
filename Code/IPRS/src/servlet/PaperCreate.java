/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: PaperCreate.java
 * Date		: May 3, 2014
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paper;
import model.Authors;
import model.Keywords;
import ejb.PaperBean;

@WebServlet("/PaperServlet")
public class PaperCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PaperCreate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String abstra = request.getParameter("abstra");
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
		paper.setStatus(0);
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
		System.out.println("WATER DROP IS HERE: ->" + location);
		if (location == null) {
			PrintWriter out = response.getWriter();
			out.write("Error : Uid is empty.");
       		out.flush();
        	out.close();
        	return ;
		}
		paper.setLocation(location);
		// Authors 0-3
		List<Authors> as = new ArrayList<Authors>();
		for (int i = 0; i < 4; i ++)
		{
			String para = "author" + String.valueOf(i);
			String author = request.getParameter(para);
			if (author != null)
			{
				Authors au = new Authors();
				au.setName(author);
				au.setIdentity(i);
				as.add(au);
			}
		}
		// Keywords 0-2
		List<Keywords> ks = new ArrayList<Keywords>();
		for (int i = 0; i < 3; i ++)
		{
			String para = "keyword" + String.valueOf(i);
			String keyword = request.getParameter(para);
			if (keyword != null)
			{
				Keywords k = new Keywords();
				k.setKeyword(keyword);
				ks.add(k);
			}
		}
		PaperBean paperBean = new PaperBean();
		int result = paperBean.add(paper, as, ks);
		if (0 == result)
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