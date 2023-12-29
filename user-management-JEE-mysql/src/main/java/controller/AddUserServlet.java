package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserDb;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class AddUser
 */

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
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
		System.out.println("ajouter !!!");
		
		
	    String firstname = request.getParameter("firstname");
	    String lastname = request.getParameter("lastname");
	    String email = request.getParameter("email");

	    // Création de l'objet Employee
	    User user = new User(firstname, lastname, email);
	    
	    // Instanciation de EmployeeDb et ajout de l'employé
	    UserDb db = new UserDb();
	    try {	    	
	        int result = db.addUser(user);
	        if (result>0) {				
				ArrayList<User> users= db.getUsers();
				request.setAttribute("Users", users);

//		        request.setAttribute("Success","La personne " + user.getLastname()+ " a été ajoutée");
		        request.getRequestDispatcher("UserList.jsp").forward(request, response);;
			} else {
		        request.setAttribute("Erreur", "Erreur lors de l'insertion");
		        request.getRequestDispatcher("UserList.jsp").forward(request, response);;
			}
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        System.out.println("Pas d'insertion");
	    }
	}
		

}
