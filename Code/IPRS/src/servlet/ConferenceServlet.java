package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conference;
import ejb.ConferenceBean;

/**
 * Servlet implementation class Conference
 */
@WebServlet("/Conference")
public class ConferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private ConferenceBean conf;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConferenceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Conference> confs = conf.getConferences();
		request.setAttribute("conflist", confs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String field = request.getParameter("field");
		String description = request.getParameter("description");
		conf.add(name, begin, end, field, description);
		request.getRequestDispatcher("AdminPage.jsp").forward(request,response);
	}

}
