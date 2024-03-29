/*
 * Author	: Zhou Cheng
 * Project	: iprs
 * Filename	: PaperReview.java
 * Date		: May 4, 2014
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paper;
import model.Task;
import model.TaskDTO;
import model.User;
import ejb.PaperBean;
import ejb.TaskBean;

@WebServlet("/PaperReview")
public class PaperReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaperReview() {
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
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		if ("review".equals(type)) {
			User user = (User) request.getSession().getAttribute("user");
			String uid = user.getUri().replaceAll("iprs/User/", "");
			TaskBean taskBean = new TaskBean();
			PaperBean paperBean = new PaperBean();
			List<Task> ats = taskBean.getTaskbyUser(uid);
			List<TaskDTO> dts = new ArrayList<TaskDTO>();
			for (int i = 0, j = ats.size(); i < j; i++) {
				Task t = ats.get(i);
				System.out.println("i= " + i + " t=" + t.getPid());
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
			System.out.println("Printing.!");
			// dts is the list!
			// <input type=button value=下载 onclick="window.location.href='文件名'">
			PrintWriter out = response.getWriter();
			if (0 == dts.size()) {
				out.println("Sad :( <br> No more paper");
			} else {
				out.println("<table>");
				out.println("<tr><td>审阅任务ID</td><td>论文题目</td><td>审阅</td><td>下载</td></tr>");
				for (int i = 0; i < dts.size(); i++) {
					out.print("<tr>");
					out.print("<td>" + dts.get(i).getTid() + "</td>");
					out.print("<td>" + dts.get(i).getTitle() + "</td>");
					if (0 == dts.get(i).getIsEdit())
						out.print("<td><input type='button' value='审阅' onclick='review(\""
								+ dts.get(i).getTid() + "\");'></td>");
					else
						out.print("<td>不可审阅</td>");
					out.print("<td><input type='button' value='下载' onclick='window.location.href=\""
							+ dts.get(i).getDownUrl() + "\"'></td>");
					out.print("</tr>");
				}
				out.println("</table>");
			}
			out.close();
		} else if ("cReview".equals(type)) {
			System.out.println("所有审稿进行中！");
			TaskBean taskBean = new TaskBean();
			PaperBean paperBean = new PaperBean();
			List<Task> ats = taskBean.getAllTask();
			List<TaskDTO> dts = new ArrayList<TaskDTO>();
			for (int i = 0, j = ats.size(); i < j; i++) {
				Task t = ats.get(i);
				System.out.println("i= " + i + " t=" + t.getPid());
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
			System.out.println("Printing.!");
			// dts is the list!
			// <input type=button value=下载 onclick="window.location.href='文件名'">
			PrintWriter out = response.getWriter();
			if (0 == dts.size()) {
				out.println("Sad :( <br> No more paper");
			} else {
				out.println("<table>");
				out.println("<tr><td>审阅任务ID</td><td>论文题目</td><td>审阅</td><td>下载</td></tr>");
				for (int i = 0; i < dts.size(); i++) {
					out.print("<tr>");
					out.print("<td>" + dts.get(i).getTid() + "</td>");
					out.print("<td>" + dts.get(i).getTitle() + "</td>");
					if (0 == dts.get(i).getIsEdit())
						out.print("<td><input type='button' value='审阅' onclick='review(\""
								+ dts.get(i).getTid() + "\");'></td>");
					else
						out.print("<td>不可审阅</td>");
					out.print("<td><input type='button' value='下载' onclick='window.location.href=\""
							+ dts.get(i).getDownUrl() + "\"'></td>");
					out.print("</tr>");
				}
				out.println("</table>");
			}
			out.close();
		}
	}
}
