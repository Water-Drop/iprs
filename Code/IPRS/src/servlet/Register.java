/*
 * Author	: Zhou Cheng & Su Ziyue
 * Project	: iprs
 * Filename	: Register.java
 * Date		: May 3, 2014
 * Data		: 2014-5-3
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import ejb.AccountBean;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	AccountBean account;

	public Register() {
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
		if ("check".equals(type)) {
			String username = request.getParameter("username");
			StringBuffer sb = new StringBuffer("");
			if (!account.isExist(username)) {
				sb.append("success");
			} else {
				sb.append("fail");
			}
			PrintWriter out = response.getWriter();
			out.write(sb.toString());
			out.close();
		} else if ("login".equals(type)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = account.check(username, password);
			if (user != null) {
				HttpSession ses = request.getSession(true);
				ses.setAttribute("user", user);
				PrintWriter out = response.getWriter();
				out.write("success");
				out.close();
			}			
		} else if ("register".equals(type)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			if (0 == account.add(username, password, email)) {
				User user = account.check(username, password);
				if (user != null) {
					HttpSession ses = request.getSession(true);
					ses.setAttribute("username", username);
				}
				PrintWriter out = response.getWriter();
				out.println("Register success");
				out.close();
			} else {
				PrintWriter out = response.getWriter();
				out.println("Register fail");
				out.close();
			}
		}
		// other condition, do nothing
	}
}
