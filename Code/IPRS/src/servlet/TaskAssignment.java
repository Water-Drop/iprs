/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: TaskAssignment.java
 * Date		: May 3, 2014
 */

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paper;

@WebServlet("/TaskAssignment")
public class TaskAssignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public TaskAssignment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paper task = new Task();
		String title = request.getParameter("title");
	}
}
