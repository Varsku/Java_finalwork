package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.SiteServlet;
/**
 * Servlet implementation class Uloskirjautuminen
 */
@WebServlet("/kirjaudu_ulos")
public class Uloskirjautuminen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uloskirjautuminen() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute(SiteServlet.SESSION_ATTR_WEBUSER);
		request.getSession().invalidate();
		response.sendRedirect(SiteServlet.FRONT_PAGE);
		
	}

}
