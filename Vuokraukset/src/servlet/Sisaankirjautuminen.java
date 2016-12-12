package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.WebUser;
import dao.DAO;
import dao.DAOPoikkeus;






@WebServlet("/Sisaankirjautuminen")
public class Sisaankirjautuminen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public Sisaankirjautuminen() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		try {
	
			DAO dao = new DAO();
			WebUser kayttajaKannasta = dao.hae(username);
			
			boolean validiKayttaja = kayttajaKannasta.vertaaSalasanaa(password);
			
			if(validiKayttaja) {

				session.setAttribute (SiteServlet.SESSION_ATTR_WEBUSER, kayttajaKannasta);

				request.getRequestDispatcher(SiteServlet.SECOND_PAGE).forward(request, response);
				
			} else {
				request.setAttribute("error", "Käyttäjätunnus tai salasana väärin!");
				request.setAttribute("prev_login_username", username);
				request.getRequestDispatcher(SiteServlet.FRONT_PAGE).forward(request, response);
			}
		} catch (NoSuchAlgorithmException e) {
			throw new ServletException("Salausalgoritmia ei löydy.", e);
		} catch (SQLException e) {

			e.printStackTrace();
		}
			
			
			
	}
	private void takaisinVirheviestilla(String viesti, String username, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", viesti);
		request.setAttribute("prev_reg_username", username);
		request.getRequestDispatcher(SiteServlet.FRONT_PAGE).forward(request, response);
		
	}
}
