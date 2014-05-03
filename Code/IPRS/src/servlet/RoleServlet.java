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
import model.User;
import ejb.RoleBean;
import ejb.UserBean;
import ejb.ConferenceBean;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/Role")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoleBean ro;
	@EJB
	private UserBean ub;
	@EJB
	private ConferenceBean conf;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> users = ub.getAllUsers();
		request.setAttribute("userlist", users);
		
		List<Conference> confs = conf.getConferences();
		request.setAttribute("conflist", confs);

		request.getRequestDispatcher("AddChairman.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String add_type = request.getParameter("type");
		if ("chairman".equalsIgnoreCase(add_type)){
			String uid = request.getParameter("uid").replace("iprs/User/", "");
			String cid = request.getParameter("cid").replace("iprs/Conference/", "");
			int type = 1;
			ro.add(uid, cid, type);
		} else if ("editor".equalsIgnoreCase(add_type)){
			String uid = request.getParameter("uid").replace("iprs/User/", "");;
			String cid = request.getParameter("cid").replace("iprs/Conference/", "");
			int type = 2;
			ro.add(uid, cid, type);
		} else {
		
		}
	}

}
