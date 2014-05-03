package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import ejb.CommentBean;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private CommentBean com;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if ("getUserComment".equalsIgnoreCase(type)){
			String uid = request.getParameter("uid");
			List<Comment> comms = com.getUserComment(uid);
			request.setAttribute("comlist", comms);
		} else if ("getPaperComment".equalsIgnoreCase(type)){
			String pid = request.getParameter("pid");
			List<Comment> comms = com.getPaperComment(pid);
			request.setAttribute("comlist", comms);
		} else if ("getTaskComment".equalsIgnoreCase(type)){
			String tid = request.getParameter("tid");
			List<Comment> comms = com.getTaskComment(tid);
			request.setAttribute("comlist", comms);
		}else {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if("add".equalsIgnoreCase(type)){
			String tid = request.getParameter("tid");
			String content = request.getParameter("content");
			int rate = Integer.parseInt(request.getParameter("rate"));
			int confidence = Integer.parseInt(request.getParameter("confidence"));
			com.add(tid, content, rate, confidence);
		}
		else if ("modify".equalsIgnoreCase(type)){
			String uri = request.getParameter("uri");
			String content = request.getParameter("content");
			int rate = Integer.parseInt(request.getParameter("rate"));
			int confidence = Integer.parseInt(request.getParameter("confidence"));
			com.modify(uri, content, rate, confidence);
		} else {
			
		}
	}

}
