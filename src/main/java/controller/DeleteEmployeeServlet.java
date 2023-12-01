package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EmployeeDb;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteEmployeeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EmployeeDb employeeDb = new EmployeeDb();
        
        try {
            employeeDb.deleteEmployee(id);
            response.sendRedirect("EmployeeList.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("EmployeeList.jsp");
        }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
