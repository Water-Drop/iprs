package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.ConferenceBean;

/**
 * Servlet implementation class Conference
 */
@WebServlet("/Conference")
public class Conference extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private ConferenceBean conf;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Conference() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String name = "IEEE";
		String begin = "2014-03-25";
		String end = "2014-05-29";
		String field = "SE";
		String desc = "It's a test conference";
		conf.add(name, begin, end, field, desc);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
