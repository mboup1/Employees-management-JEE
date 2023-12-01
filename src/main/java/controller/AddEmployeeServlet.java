package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import model.EmployeeDb;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Récupération des paramètres de la requête
   
	    
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String email = request.getParameter("email");

	    // Création de l'objet Employee
	    Employee employee = new Employee(firstName, lastName, email);
	    
	    // Instanciation de EmployeeDb et ajout de l'employé
	    EmployeeDb db = new EmployeeDb();
	    try {	    	
	        int result = db.addEmployee(employee);
	        if (result>0) {				
				ArrayList<Employee> employees= db.getEmployees();
				request.setAttribute("Employees", employees);

		        request.setAttribute("Success","L'employé " + employee.getLastName()+ " a été ajouté");
		        request.getRequestDispatcher("EmployeeList.jsp").forward(request, response);;
			} else {
		        request.setAttribute("Erreur", "Erreur lors de l'insertion");
		        request.getRequestDispatcher("EmployeeList.jsp").forward(request, response);;
			}
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        System.out.println("Pas d'insertion");
	    }
	}

}
