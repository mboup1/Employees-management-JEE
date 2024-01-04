package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class ProductServlet
 */

@WebServlet("/AddProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDao productDb = new ProductDao();
        
        try {
        	productDb.deleteProduct(id);
            response.sendRedirect("ProductList.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("ProductList.jsp");
        }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");
	    
	    if (action != null) {
	        switch (action) {
	            case "add":
	                // Handle the product addition logic here
	                String name = request.getParameter("name");
	                String description = request.getParameter("description");
	                String price = request.getParameter("price");
	                
	                // Create a new Product object with the provided data
	                Product newProduct = new Product(name, description, price);
	                ProductDao productDb = new ProductDao();
	                
	                try {
	                    int result = productDb.addProduct(newProduct);
	                    if (result > 0) {
	                        request.setAttribute("Success", "Product added successfully");
	                    } else {
	                        request.setAttribute("Erreur", "Error adding the product");
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace(); // Handle the exception appropriately
	                }
	                break;
	                
	            case "update":
	                // Handle the product update logic here
	                int id = Integer.parseInt(request.getParameter("id"));
	                String updatedName = request.getParameter("name");
	                String updatedDescription = request.getParameter("description");
	                String updatedPrice = request.getParameter("price");
	                
	                // Create an updated Product object
	                Product updatedProduct = new Product(id, updatedName, updatedDescription, updatedPrice);
	                ProductDao db = new ProductDao();
	                
	                try {
	                    int result = db.updateProduct(updatedProduct);
	                    if (result > 0) {
	                        request.setAttribute("Success", "Product updated successfully");
	                    } else {
	                        request.setAttribute("Erreur", "Error updating the product");
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace(); // Handle the exception appropriately
	                }
	                break;
	                
	            default:
	                // Handle other cases or actions
	                break;
	        }
	    }
	    
	    // Forward the request to a JSP page (e.g., ProductList.jsp)
	    request.getRequestDispatcher("ProductList.jsp").forward(request, response);
	}


}
