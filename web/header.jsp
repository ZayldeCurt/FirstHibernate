<%--
  Created by IntelliJ IDEA.
  User: pllsym
  Date: 14 cze 2018
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Homepage - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">
</head>
<body>

<%
    Long userId=1L;
    String phrase = request.getParameter("phrase");
    if(phrase==null){
        phrase="";
    }
    pageContext.setAttribute("phrase",phrase);
    pageContext.setAttribute("userId",userId);
%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div>
            <form method="get" action="findProductByName.jsp">
                <input type="text" name="phrase">
                <button type="submit">Szukaj</button>
            </form>
        </div>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Strona Główna
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cart.jsp">Koszyk</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="order.jsp">Zamowienia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">NIC</a>
                </li>
                <C:if test="${userId.equals(1)}">
                    <a class="nav-link" href="productAdminPage.jsp">Panel Administratora</a>
                </C:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
