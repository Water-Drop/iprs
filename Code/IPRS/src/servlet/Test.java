package servlet;

import util.HttpHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import servlet 

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xml = "<POST>"
				+ "<Operation-set>"
				+ "<Target>this.Username</Target>"
				+ "<Value>test3</Value>"
				+ "</Operation-set><Operation-set><Target>this.Password</Target><Value>testpas3</Value></Operation-set></POST>";
		String url="http://59.78.3.25:8080/RMP/Entity/iprs/User/";
		HttpHelper ph = new HttpHelper();
		//System.out.println(ph.SendHttpRequest("POST", url, xml));
		//System.out.println(ph.SendHttpRequest("GET", url, xml));
		String url2="http://59.78.3.25:8080/RMP/Entity/iprs/User/42356549783851";
		String xm2 = "<PUT>"
				+ "<Operation-set>"
				+ "<Target>this.Username</Target>"
				+ "<Value>Mtest</Value>"
				+ "</Operation-set><Operation-set><Target>this.Password</Target><Value>Mpas</Value></Operation-set></PUT>";
		System.out.println(ph.SendHttpRequest("PUT", url2, xm2));
		System.out.println(ph.SendHttpRequest("GET", url, xml));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
