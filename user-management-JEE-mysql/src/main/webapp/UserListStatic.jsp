<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Liste des personnes</title>
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
    // Create a list to hold static employee data
    List<User> users = new ArrayList<>();

    // Add three employees to the list
    users.add(new User(1, "John", "Doe", "john.doe@example.com"));
    users.add(new User(2, "Jane", "Smith", "jane.smith@example.com"));
    users.add(new User(3, "Alice", "Johnson", "alice.johnson@example.com"));
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
		            <% if (users != null) {
		                for (User user : users) { %>
		                    <tr>
		                        <td><%= user.getIdUser() %></td>
		                        <td><%= user.getFirstname() %></td>
		                        <td><%= user.getLastname() %></td>
		                        <td><%= user.getEmail() %></td>
		                        <td>
		                            <a href="UpdateUser.jsp?id=<%= user.getIdUser() %>" class="btn btn-primary">Modifier</a>
		                            <a href="DeleteUserServlet?id=<%= user.getIdUser() %>" 
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
