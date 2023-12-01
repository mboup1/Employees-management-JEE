package controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import model.EmployeeDb;

public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateEmployeeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Généralement, vous pouvez utiliser doGet pour afficher le formulaire de mise à jour pré-rempli.
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Récupération des paramètres de la requête
        int idEmployee = Integer.parseInt(request.getParameter("idEmployee"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // Mise à jour de l'objet Employee
        Employee employee = new Employee(idEmployee, firstName, lastName, email);
        EmployeeDb db = new EmployeeDb();

        try {
            int result = db.updateEmployee(employee); // Supposons que cette méthode existe dans EmployeeDb
            if (result > 0) {
                request.setAttribute("Success", "L'employé " + employee.getLastName() + " a été mis à jour");
            } else {
                request.setAttribute("Erreur", "Erreur lors de la mise à jour");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }
        request.getRequestDispatcher("EmployeeList.jsp").forward(request, response);
    }
}
