/*
 * Author	: Lirian Su
 * Project	: iprs
 * Filename	: PaperSearch.java
 * Date		: 2014-5-3
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Paper;
import ejb.PaperBean;

@WebServlet("/PaperSearch")
public class PaperSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaperSearch() {
		super();
	}

	private String pStatus(int s) {
		if (0 == s)
			return "���Ĵ����";
		else if (-10 == s)
			return "�����ѳ���";
		else if (0 > s)
			return "�����ڵ�" + (0-s) + "����˺󶳽ᣬ�ȴ��û��޸�";
		else if (10 == s)
			return "��ͨ��";
		else
			return "���ĵȴ���" + (s + 1) + "�����";
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
		response.setContentType("text/html;charset=UTF-8");
		if ("getUid".equals(type)) {
			PaperBean paperBean = new PaperBean();
			String uid = request.getParameter("uid");
			List<Paper> pList = paperBean.getByUid(uid);
			PrintWriter out = response.getWriter();
			if (0 == pList.size()) {
				out.println("Sad :( <br> No more paper");
			} else {

				out.println("<table>");
				out.println("<tr><td>�û�ID</td><td>������Ŀ</td><td>���ĸ�Ҫ</td><td>����״̬</td><td>�޸�����</td><td>��������</td></tr>");
				for (int i = 0; i < pList.size(); i++)
					out.println("<tr><td>"
							+ pList.get(i).getUid()
							+ "</td><td>"
							+ pList.get(i).getTitle()
							+ "</td><td>"
							+ pList.get(i).getAbstract()
							+ "</td><td>"
							+ pStatus(pList.get(i).getStatus())
							+ "</td><td><input type=\"button\" onclick=\"modifyPaper('"
							+ pList.get(i).getUri() + "', '"
							+ pList.get(i).getStatus()
							+ "')\" value=\"�޸�\"></td><td>"
							+ "<input type=\"button\" onclick=\"regretPaper('"
							+ pList.get(i).getUri() + "');\" value=\"����\">"
							+ "</td></tr>");
				out.println("</table>");
			}
			out.close();

		} else if ("getAll".equals(type)) {
			PaperBean paperBean = new PaperBean();
			List<Paper> pList = paperBean.getAll();
			PrintWriter out = response.getWriter();
			if (0 == pList.size()) {
				out.println("Sad :( <br> No more paper");
			} else {

				out.println("<table>");
				out.println("<tr><td>�û�ID</td><td>������Ŀ</td><td>���ĸ�Ҫ</td><td>����״̬</td></tr>");
				for (int i = 0; i < pList.size(); i++)
					out.println("<tr><td>" + pList.get(i).getUid()
							+ "</td><td>" + pList.get(i).getTitle()
							+ "</td><td>" + pList.get(i).getAbstract()
							+ "</td><td>" + pStatus(pList.get(i).getStatus())
							+ "</td></tr>");
				out.println("</table>");
			}
			out.close();

		}
	}
}
