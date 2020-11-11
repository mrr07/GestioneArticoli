package it.gestionearticoli.web.servlet.utente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteGetUtenteServlet
 */
@WebServlet("/ExecuteGetUtenteServlet")
public class ExecuteGetUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteGetUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		if (user.isEmpty() || pass.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		try {
			Utente ut = new Utente();
			ut = MyServiceFactory.getUtenteServiceInstance().findByUserPass(user, pass);
			if( ut.getId() == null) {
				request.setAttribute("errorMessage", "Attenzione username e/o password sono errati");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Utente utente = new Utente();
		try {
			utente = MyServiceFactory.getUtenteServiceInstance().findByUserPass(user, pass);
			if(utente.getNome() == null) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("utente", utente);
			
			
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			
		}
		
		request.getRequestDispatcher("index.jsp").include(request, response);
		
	}

}
