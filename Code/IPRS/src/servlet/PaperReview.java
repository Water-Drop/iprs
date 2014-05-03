/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: PaperReview.java
 * Date		: May 4, 2014
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.PaperBean;
import ejb.TaskBean;
import model.Paper;
import model.Task;
import model.TaskDTO;
import model.User;

@WebServlet("/PaperReview")
public class PaperReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PaperReview() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getUri().replaceAll("iprs/User/", "");
		TaskBean taskBean = new TaskBean();
		PaperBean paperBean = new PaperBean();
		List<Task> ats = taskBean.getTaskbyUser(uid);
		List<TaskDTO> dts = new ArrayList<TaskDTO>();
		for (int i = 0, j = ats.size(); i < j; i ++)
		{
			Task t = ats.get(i);
			Paper p = paperBean.get(t.getPid());
			TaskDTO td = new TaskDTO();
			td.setTid(t.getUri().replaceAll("iprs/Task/", ""));
			td.setTitle(p.getTitle());
			td.setDownUrl("http://test.ldsink.com/Files/" + p.getLocation());
			if (p.getStatus() + 1 == t.getOrder())
				td.setIsEdit(0);
			else
				td.setIsEdit(1);
			dts.add(td);
		}
		
		// dts is the list!
	}
}
