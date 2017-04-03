<%-- 
    Document   : editorial
    Created on : 28/03/2017, 08:17:29 PM
    Author     : Usuario
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">WebSiteName</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.htm">Home</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuarios
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="home.htm">Usuarios</a></li>
                            <li><a href="autor.htm">Autores</a></li>
                            <li><a href="categoria.htm">Categorias</a></li>
                            <li><a href="editorial.htm">Editorial</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Page 2</a></li>
                    <li><a href="#">Page 3</a></li>
                </ul>
            </div>
        </nav>
    <body>
        <div class="container">
            <div class="row">
                <h1>Editoriales</h1>
                <p>
                    <a href="<c:url value="addEditorial.htm" />" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Agregar</a>
                </p>
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>CorreoContacto</th>
                            <th>TelefonoContacto</th>
                            <th>Direccion</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${datos}" var="dato">
                            <tr>
                                <td><c:out value="${dato.idEditorial}" /></td>
                                <td><c:out value="${dato.nombre}" /></td>
                                <td><c:out value="${dato.correoContacto}" /></td>
                                <td><c:out value="${dato.telefonoContacto}" /></td>
                                <td><c:out value="${dato.direccion}" /></td>
                                <td>
                                    <a href="<c:url value="editEditorial.htm?idEditorial=${dato.idEditorial}" />"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                    <a href="<c:url value="deleteEditorial.htm?idEditorial=${dato.idEditorial}" />"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </body>
</html>
