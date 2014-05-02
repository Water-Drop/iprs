//  Author  : Zhou Cheng
//  Date    : May 1, 2014
//  Register servlet

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.AccountBean;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	AccountBean account;
	
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type.equals("check"))
		{
			String username = request.getParameter("username");
			StringBuffer sb = new StringBuffer("");
			if (!account.isExist(username))
			{
		        sb.append("success");
			}
			else
			{
				sb.append("fail");
			}
			PrintWriter out = response.getWriter();
	        out.write(sb.toString());
	        out.close();
		}
		else if (type.equals("login"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (account.check(username, password))
			{
		        // TODO 登陆成功后的处理
			}
			else
			{
				// TODO 登陆失败后的处理
			}
		}
		else if (type.equals("register"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (account.add(username, password))
			{
		        // TODO 登陆成功后的处理
			}
			else
			{
				// TODO 登陆失败后的处理
			}
		}
		// other condition, do nothing
	}
}
