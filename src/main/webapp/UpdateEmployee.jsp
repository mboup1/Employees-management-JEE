<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un employé</title>
</head>
<body>
    <%@include file="NavBar.jsp" %>
    <div class="row m-5">
        <div class="col-12 col-md-10 col-lg-8 col-xl-6 mx-auto">
            <div class="bg-white shadow rounded p-4">
                <h1 class="display-6 text-dark text-center">Ajouter un employé</h1>
                <hr class="bg-dark mb-5" />
                <form action="AddEmployeeServlet" method="post">
                    <div class="form-group mb-3">
                        <label for="firstName">Prénom</label>
                        <input type="text" id="firstName" name="firstName" class="form-control" required />
                    </div>
                    <div class="form-group mb-3">
                        <label for="lastName">Nom</label>
                        <input type="text" id="lastName" name="lastName" class="form-control" required />
                    </div>
                    <div class="form-group mb-3">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" class="form-control" required />
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
