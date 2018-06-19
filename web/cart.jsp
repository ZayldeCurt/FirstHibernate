<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import="java.util.Optional" %>
<%@ page import="sda.pl.repository.CartRepository" %>
<%@ page import="sda.pl.domain.Cart" %><%--
  Created by IntelliJ IDEA.
  User: pllsym
  Date: 14 cze 2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>

<%
    Long USER_ID = 1L;
    Optional<Cart> cartByUserID = CartRepository.findCartByUserID(USER_ID);
    Cart cart =new Cart();
    if(cartByUserID.isPresent()){
        cart = cartByUserID.get();
        pageContext.setAttribute("myCart",cart);
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

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nazwa produktu</th>
                            <th scope="col">Ilość</th>
                            <th scope="col">Cana netto</th>
                            <th scope="col">Cana brutto</th>
                            <th scope="col">Usuń</th>
                        </tr>
                    </thead>
                        <tbody>
                            <c:forEach items="${myCart.cartDetailSet}" var="cd" varStatus="i">
                                <tr>
                                    <th scope="row">${i.index+1}</th>
                                    <td><a href="product.jsp?productId=${cd.product.id}">${cd.product.name}</a></td>
                                    <td>${cd.amount}</td>
                                    <td>${cd.price.priceNet}</td>
                                    <td>${cd.price.priceGross}</td>
                                    <td><a href="removeProductFromCart?productId=${cd.product.id}">Usuń</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="3"></td>
                                <c:set var="priceN" value="0" />
                                <c:set var="priceG" value="0" />
                                    <c:forEach items="${myCart.cartDetailSet}" var="cd" varStatus="i">
                                        ${priceN+=cd.price.priceNet}
                                        ${priceG+=cd.price.priceGross}
                                    </c:forEach>
                                <td>${priceN}</td>
                                <td>${priceG}</td>
                            </tr>
                        </tfoot>
                </table>

                <a href="/createOrder" class="btn-success btn"> Kup i zapłać</a>
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

