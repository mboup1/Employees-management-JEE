<%@page import="model.Product"%>
<%@page import="model.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mettre à jour un produit</title>
</head>
<body>
    <%@include file="NavBar.jsp" %>
        <%
        String id = request.getParameter("id");
                ProductDao productDb = new ProductDao();
                Product product = productDb.getProductById(Integer.parseInt(id));
        %>

    <div class="row m-5">
        <div class="col-12 col-md-10 col-lg-8 col-xl-6 mx-auto">
            <div class="bg-white shadow rounded p-4">
                <h1 class="display-6 text-dark text-center">Mettre à jour un produit</h1>
                <hr class="bg-dark mb-5" />
                 <form action="ProductServlet" method="post">
                    <input type="hidden" name="id" value="<%= product.getId() %>" />
                    
                    <!-- Hidden input field for the "action" parameter set to "update" -->
                    <input type="hidden" name="action" value="update">
                    
                    <div class="form-group mb-3">
                        <label for="name">Nom</label>
                        <input type="text" id="name" name="name" value="<%= product.getName()%>" class="form-control" required />
                    </div>
                    <div class="form-group mb-3">
                        <label for="description">Description</label>
                        <input type="text" id="description" name="description" value="<%= product.getDescription() %>" class="form-control" required />
                    </div>
                    <div class="form-group mb-3">
                        <label for="price">Prix</label>
                        <input type="text" id="price" name="price" value="<%= product.getPrice() %>" class="form-control" required />
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