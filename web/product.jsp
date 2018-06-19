<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: pllsym
  Date: 14 cze 2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>

<%
    Long productId = Long.parseLong(request.getParameter("productId"));
    Optional<Product> productOptional = ProductRepository.findProduct(productId);
    if (productOptional.isPresent()){
        Product product = productOptional.get();
        pageContext.setAttribute("newProduct",product);
    }


%>


<!DOCTYPE html>
<html lang="en">

<head>

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

<%@include file="header.jsp"%>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <%@include file="leftMenu.jsp"%>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div class="row">

                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <a target="_blank" href="/productImage?productId=${newProduct.id}"><img class="card-img-top" src="/productImage?productId=${newProduct.id}" onerror="this.src='http://placehold.it/700x400'"></a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="#">${newProduct.name}</a>
                                </h4>
                                <h5>${newProduct.price.priceNet}</h5>
                                <p class="card-text">${newProduct.color}</p>
                                <p class="card-text">Ilosc: ${newProduct.sumStockForSale}</p>

                            </div>
                            <div class="card-footer">
                                <%--<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>--%>
                                <form action="/AddProductToCart?productId=${newProduct.id}" method="post">
                                    <label for="productAmount">Liczba produktów : </label>
                                    <input name="productAmount" id="productAmount" value="1" type="number">
                                    <button type="submit">Dodaj do koszyka</button>
                                </form>
                            </div>
                        </div>
                    </div>


            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<%@include file="footer.jsp"%>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

