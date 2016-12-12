package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Vuokraus;
import dao.DAO;
import dao.DAOPoikkeus;





@WebServlet("/site")
public class SiteServlet extends HttpServlet {

	public static final String FRONT_PAGE = "index.jsp";
	public static final String SECOND_PAGE = "etusivu.jsp";
	public static final String THIRD_PAGE= "vuokraukset.jsp";

	public static final String SESSION_ATTR_WEBUSER = "kayttajatiedot";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SiteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
	RequestDispatcher disp;

	if (action == null) {
		request.getRequestDispatcher(SiteServlet.SECOND_PAGE).forward(request, response);
	}
	else if (action.equalsIgnoreCase("hae kaikki vuokraukset")){
		System.out.println("KAIKKI");
		haeKaikkiVuokraukset(request, response);
	}
	else if (action.equalsIgnoreCase("hae tietyn pvm:n vuokraukset")){
		System.out.println("PÄIVÄ");
		haePaivanVuokraukset(request,response);
	}

}
	
	
	private void haeKaikkiVuokraukset(HttpServletRequest req, 
			HttpServletResponse res) throws IOException, ServletException
	{
		DAO dao = new DAO();
		RequestDispatcher disp;
		try {
			List<Vuokraus> lista = dao.haeVuokraukset();
			System.out.println(lista);
			if (lista != null) {
			req.setAttribute("lista",lista);
			disp = req.getRequestDispatcher(THIRD_PAGE);
			disp.forward(req,  res);
			}
			else
			{
				req.setAttribute("EI_LOYDY", true);
				disp = req.getRequestDispatcher(SECOND_PAGE);
				disp.forward(req,  res);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("TK_VIRHE", true);
			disp = req.getRequestDispatcher(FRONT_PAGE);
			disp.forward(req,  res);
		}
		
	}
	private void haePaivanVuokraukset(HttpServletRequest request, HttpServletResponse response) 
			 throws IOException, ServletException
{
	DAO dao = new DAO();
	RequestDispatcher disp;
	String date = request.getParameter("date");
	try {
		List<Vuokraus> lista = dao.haeVuokraukset(date);
		
		if (lista != null ) {
			request.setAttribute("lista", lista);
		
			disp = request.getRequestDispatcher(THIRD_PAGE);
			disp.forward(request, response);
		}
		else {
			request.setAttribute("tyhja", true);
			request.setAttribute("date", date);
			disp = request.getRequestDispatcher(SECOND_PAGE);
			disp.forward(request, response);
		}
			
	} catch (Exception e) {
		request.setAttribute("TK_VIRHE", true);
		disp = request.getRequestDispatcher(FRONT_PAGE);
		disp.forward(request,  response);
	}
}
	
	
}

