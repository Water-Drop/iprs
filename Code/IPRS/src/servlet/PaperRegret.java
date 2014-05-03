/*
 * Author	: Zhou Cheng, Su Ziyue
 * Project	: iprs
 * Filename	: PaperEdit.java
 * Date		: 2014-5-4
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

import ejb.PaperBean;

@WebServlet("/PaperRegret")
public class PaperRegret extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	PaperBean paperBean;
	
    public PaperRegret() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String pid = request.getParameter("pid");
    	System.out.println("Processing Regretion. PID = " + pid);
		if (0 == paperBean.withdraw(pid))
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
