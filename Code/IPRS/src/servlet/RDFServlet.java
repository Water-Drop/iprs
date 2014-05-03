package servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.PaperBean;
import model.Paper;
import util.RDFHelper;
/**
 * Servlet implementation class RDFServlet
 */
@WebServlet("/RDF")
public class RDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    private PaperBean pb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RDFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		Paper p = pb.get(pid);
		RDFHelper rh = new RDFHelper();
		rh.initRDF();
		rh.setDescription(p.getUri());
		rh.setTarget("Title", p.getTitle());
		rh.setTarget("Abstract", p.getAbstract());
		rh.setTarget("LMTime", p.getLMTime().toString());
		rh.setTarget("Location", p.getLocation());
		rh.endRDF();
		File f = new File("E:\\" + pid.toString() + ".txt");
		FileWriter fw = new FileWriter(f);  
		fw.write(rh.getRDF());
		fw.close();
	}

}
