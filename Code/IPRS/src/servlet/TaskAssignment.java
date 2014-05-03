/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: TaskAssignment.java
 * Date		: May 3, 2014
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.TaskBean;
import ejb.PaperBean;
import ejb.UserBean;
import model.Task;
import model.Paper;
import model.User;

@WebServlet("/TaskAssignment")
public class TaskAssignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PaperBean pb;
	@EJB
	private UserBean ub;
	
    
    public TaskAssignment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = ub.getAllUsers();
		request.setAttribute("userlist", users);
		List<Paper> papers = pb.getAll();
		for (int i = 0, j = papers.size(); i < j; i++){
			if (papers.get(i).getStatus() < 0){
				papers.remove(i);
			}
			}
		request.setAttribute("paperlist", papers);

		request.getRequestDispatcher("AssignmentTask.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Task> ts = new ArrayList<Task>();
		String pid = request.getParameter("pid");
		for (int i = 1; i <= 5; i ++)
		{
			String para = "uid" + String.valueOf(i);
			String uid = request.getParameter(para);
			if (uid != null)
			{
				Task t = new Task();
				t.setPid(pid);
				t.setUid(uid);
				t.setStatus(0);
				t.setOrder(i);
				ts.add(t);
			}
		}
		TaskBean taskBean = new TaskBean();
		int result = taskBean.add(ts);
		if (0 == result)
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
		
}
