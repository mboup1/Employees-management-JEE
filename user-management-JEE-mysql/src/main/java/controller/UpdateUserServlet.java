package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserDb;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class UpdateUserServlet
 */
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // Récupération des paramètres de la requête
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");

        // Mise à jour de l'objet user
        User user = new User(idUser, firstname, lastname, email);
        UserDb db = new UserDb();

        try {
            int result = db.updateUser(user);
            if (result > 0) {
                request.setAttribute("Success", "La personne " + user.getLastname() + " a été mise à jour");
            } else {
                request.setAttribute("Erreur", "Erreur lors de la mise à jour");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }
        request.getRequestDispatcher("UserList.jsp").forward(request, response);
	}
	
	

}
