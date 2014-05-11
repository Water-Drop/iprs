package servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.PaperBean;
import ejb.TagBean;
import model.Paper;
import model.Tag;
import util.RDFHelper;

/**
 * Servlet implementation class RDFServlet
 */
@WebServlet("/RDF")
public class RDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PaperBean pb;
	@EJB
	private TagBean tb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RDFServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String domain = "http://59.78.3.25:8080/RMP/Entity/";
		String pid = request.getParameter("pid");
		Paper p = pb.get(pid);
		List<Tag> ts = tb.getTagbyPaper(pid);
		RDFHelper rh = new RDFHelper();
		rh.initRDF();
		rh.setDescription(domain + p.getUri());
		rh.setTarget("Title", p.getTitle());
		rh.setTarget("Abstract", p.getAbstract());
		rh.setTarget("LMTime", p.getLMTime().toString());
		rh.setTarget("Location", p.getLocation());
		//System.out.println(ts);
		if (ts.size() != 0) {
			String targetname = "Tag";
			for (int i = 0; i < ts.size(); i++) {
				rh.setTarget(targetname + String.valueOf(i), ts.get(i).getTag());
			}
		}
		rh.endRDF();
		File f = new File("E:\\" + pid.toString() + ".txt");
		FileWriter fw = new FileWriter(f);
		fw.write(rh.getRDF());
		fw.close();
		//System.out.println(rh.getRDF());
	}

}
