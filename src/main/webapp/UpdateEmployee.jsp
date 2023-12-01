<%@page import="model.Employee"%>
<%@page import="model.EmployeeDb"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mettre à jour un employé</title>
</head>
<body>
    <%@include file="NavBar.jsp" %>
        <%
        String employeeId = request.getParameter("id");
        EmployeeDb employeeDb = new EmployeeDb();
        Employee employee = employeeDb.getEmployeeById(Long.parseLong(employeeId));
    	%>

    <div class="row m-5">
        <div class="col-12 col-md-10 col-lg-8 col-xl-6 mx-auto">
            <div class="bg-white shadow rounded p-4">
                <h1 class="display-6 text-dark text-center">Mettre à jour un employé</h1>
                <hr class="bg-dark mb-5" />
                <form action="UpdateEmployeeServlet" method="post">
                    <input type="hidden" name="id" value="<%= employee.getIdEmployee() %>" />
                    <div class="form-group mb-3">
                        <label for="firstName">Prénom</label>
                        <input type="text" id="firstName" name="firstName" value="<%= employee.getFirstName() %>" class="form-control" required />
                    </div>
                    <div class="form-group mb-3">
                        <label for="lastName">Nom</label>
                        <input type="text" id="lastName" name="lastName" value="<%= employee.getLastName() %>" class="form-control" required />
                    </div>
                    <div class="form-group mb-3">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" value="<%= employee.getEmail() %>" class="form-control" required />
                    </div>
                    <div class="d-flex">
                        <button class="btn btn-success mx-auto" type="submit">Mettre à jour</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>