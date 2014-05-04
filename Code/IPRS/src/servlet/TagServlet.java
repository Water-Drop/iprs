package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ejb.PaperBean;
import ejb.TagBean;

import model.Paper;


/**
 * Servlet implementation class TagServlet
 */
@WebServlet("/Tag")
public class TagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
     private PaperBean pb;
	@EJB
	private TagBean tb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Paper> papers = pb.getAll();
		if (papers.size() != 0){
		for (int i = 0, j = papers.size()-1; i < j; i++){
			if (papers.get(i).getStatus() != null && papers.get(i).getStatus() < 0){
				papers.remove(i);
			}
			}
		}
		request.setAttribute("paperlist", papers);

		request.getRequestDispatcher("AddTag.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String tag1 = request.getParameter("tag1");
		String tag2 = request.getParameter("tag2");
		String tag3 = request.getParameter("tag3");
		if (null != tag1){
		tb.add(pid, tag1);
		} 
		if (null != tag2){
		tb.add(pid, tag2);
		}
		if (null != tag3){
		tb.add(pid, tag3);
		}
		response.sendRedirect("/iprs/Tag"); 
	}

}
