<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des produits</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script>
		 function confirmDelete() {
	         return confirm("Etes-vous sûr de vouloir supprimer cette personne ?");
	     }
    </script>
</head>
<body>
    <%@include file="NavBar.jsp" %>		
		<%
				ProductDao productDb = new ProductDao();
						    List<Product> products = null;
						    products = productDb.getProducts();
				%>
			
		 <div class="row">
		    <div class="col-12 col-lg-8 mx-auto">
		      <div class="bg-white shadow p-4 rounded">
		        <h1 class="display-6 text-dark text-center">Liste des personnes</h1>
		        <hr class="bg-dark text-dark">
		        <table class="table">
		          <thead>
		            <tr>
		              <th scope="col">Id</th>
		              <th scope="col">Prénom</th>
		              <th scope="col">Nom</th>
		              <th scope="col">Email</th>
		              <th>Actions</th>
		            </tr>
		          </thead>
		          <tbody>
		            <% if (products != null) {
		                for (Product product : products) { %>
		                    <tr>
		                        <td><%= product.getId() %></td>
		                        <td><%= product.getName() %></td>
		                        <td><%= product.getPrice() %></td>
		                        <td><%= product.getDescription() %></td>
		                        <td>
		                            <a href="UpdateProduct.jsp?id=<%= product.getId() %>" class="btn btn-primary">Modifier</a>
		                            <a href="ProductServlet?id=<%= product.getId() %>" 
			                           class="btn btn-danger" 
			                           onclick="return confirmDelete();">
			                           Supprimer
			                        </a>
		                        </td>
		                    </tr>
		            <%  }
		              }
		            %>
		          </tbody>
		        </table>
		      </div>
		    </div>
		</div>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</body>
</html>