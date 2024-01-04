<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter un produit</title>
</head>
<body>
    <%@include file="NavBar.jsp" %>
    <div class="row m-5">
        <div class="col-12 col-md-10 col-lg-8 col-xl-6 mx-auto">
            <div class="bg-white shadow rounded p-4">
                <h1 class="display-6 text-dark text-center">Ajouter un produit</h1>
                <hr class="bg-dark mb-5" />
                            <!-- Add Product Form -->
            <form action="ProductServlet" method="post">
                <!-- Hidden input field to specify the action as "add" -->
                <input type="hidden" name="action" value="add">

                <div class="form-group mb-3">
                    <label for="name">Nom</label>
                    <input type="text" id="name" name="name" class="form-control" required />
                </div>
                <div class="form-group mb-3">
                    <label for="description">Description</label>
                    <input type="text" id="description" name="description" class="form-control" required />
                </div>
                <div class="form-group mb-3">
                    <label for="price">Prix</label>
                    <input type="text" id="price" name="price" class="form-control" required />
                </div>
                <div class="d-flex">
                    <button class="btn btn-success mx-auto" type="submit">Enregistrer</button>
                </div>
            </form>
            </div>
        </div>
    </div>
</body>
</html>