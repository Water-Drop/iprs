/*
 * Author	: Lirian Su
 * Project	: iprs
 * Filename	: PaperSearch.java
 * Date		: 2014-5-3
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paper;
import ejb.PaperBean;

@WebServlet("/TaskView")
public class TaskView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TaskView() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if ("getUid".equals(type)) {
			PaperBean paperBean = new PaperBean();
			String uid = request.getParameter("uid");
			List<Paper> pList = paperBean.getByUid(uid);
			PrintWriter out = response.getWriter();
			out.println("<table>");
			for (int i = 0; i < pList.size(); i++)
				out.println("<tr><td>" + pList.get(i).getUid() + "</td><td>"
						+ pList.get(i).getTitle() + "</td></tr>");
			if (0 == pList.size())
				out.println("Sad :( <br> No more paper");

			out.println("</table>");
			out.close();
		} else if ("getAll".equals(type)) {
			PaperBean paperBean = new PaperBean();
			List<Paper> pList = paperBean.getAll();
			PrintWriter out = response.getWriter();
			out.println("<table>");
			for (int i = 0; i < pList.size(); i++)
				out.println("<tr><td>User:" + pList.get(i).getUid()
						+ "</td><td>Paper:" + pList.get(i).getTitle()
						+ "</td></tr>");
			if (0 == pList.size())
				out.println("Sad :( <br> No more paper");
			out.println("</table>");
			out.close();
		}
	}
}
