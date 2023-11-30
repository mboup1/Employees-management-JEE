<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Employee"%>
<%@page import="model.EmployeeDb"%>
<%@page import="java.sql.SQLException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Liste des employés</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	</head>
	<body>
		<%@include file="NavBar.jsp" %>
		
		<%
		    EmployeeDb employeeDb = new EmployeeDb();
		    List<Employee> employees = null;
		    employees = employeeDb.getEmployees();
		%>
			
		 <div class="row">
		    <div class="col-12 col-lg-8 mx-auto">
		      <div class="bg-white shadow p-4 rounded">
		        <h1 class="display-6 text-dark text-center">Liste des employés</h1>
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
		            <% if (employees != null) {
		                for (Employee employee : employees) { %>
		                    <tr>
		                        <td><%= employee.getIdEmployee() %></td>
		                        <td><%= employee.getFirstName() %></td>
		                        <td><%= employee.getLastName() %></td>
		                        <td><%= employee.getEmail() %></td>
		                        <td>
		                            <a href="UpdateEmployee.jsp?id=<%= employee.getIdEmployee() %>" class="btn btn-primary">Modifier</a>
		                            <a href="DeleteEmployeeServlet?id=<%= employee.getIdEmployee() %>" class="btn btn-danger">Supprimer</a>
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
