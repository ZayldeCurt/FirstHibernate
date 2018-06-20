<%@ page import="sda.pl.domain.ProductType" %>
<%@ page import="sda.pl.domain.Color" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="java.util.Optional" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: lbacic_adm
  Date: 14.06.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%
    // TODO dodac user z sesji albo ciastka
    Long USER_ID = 1L;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
    <%--<meta name="description" content="">--%>
    <%--<meta name="author" content="">--%>
    <%--<title>Shop Homepage - Start Bootstrap Template</title>--%>
    <%--<!-- Bootstrap core CSS -->--%>
    <%--<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<!-- Custom styles for this template -->--%>
    <%--<link href="css/shop-homepage.css" rel="stylesheet">--%>
    <%@include file="header.jsp"%>
</head>
<body>

<!-- Page Content -->
<%
//    ProductType[] values = ProductType.values();
    Color[] colors = Color.values();

//    pageContext.setAttribute("categories",values);
    pageContext.setAttribute("colors",colors);

    String productId = request.getParameter("productId");
    Product product = new Product();
    if (productId != null && productId.trim().length() > 0) {
        Optional<Product> productOptional = ProductRepository.findProduct(Long.valueOf(productId));
        if (productOptional.isPresent()) {
            product = productOptional.get();
        }
    }
    pageContext.setAttribute("product", product);
%>

<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <%@include file="leftMenu.jsp"%>
        </div>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9">
            <div class="row">
                <form action="/productAdmin" method="post">
                    <div class="form-group">
                        <input type="hidden" value="${product.id}" name="productId">
                        <label for="name">Nazwa produktu</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Nazwa produktu" value="${product.name}">
                    </div>
                    <div class = "form-group">
                        <label for="netPrice">cena netto</label>
                        <input type="text" class="form-control" id="netPrice" name="netPrice" placeholder="cena netto" value="${product.price.priceNet}">
                    </div>
                    <div class = "form-group">
                        <label for="grossPrice">cena brutto</label>
                        <input type="text" class="form-control" id="grossPrice" name="grossPrice" placeholder="cena brutto" value="${product.price.priceGross}">
                    </div>
                    <%--<div class = "form-group">--%>
                        <%--<label for="Color">kolor</label>--%>
                        <%--<select name="color" id = "color">--%>
                            <%--<option>--%>
                                <%--<c:forEach var="color" items="${colors}">--%>
                                    <%--<option>${color}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label for="color">Kolor</label>
                        <select name="color" id="color" class="form-control">
                            <c:forEach var="color" items="${colors}">
                                <c:if test="${product.color.equals(color)}">
                                    <option selected="selected">${color}</option>
                                </c:if>
                                <c:if test="${! product.color.equals(color)}">
                                    <option>${color}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <%--<div class = "form-group">--%>
                        <%--<label for="productType">typ</label>--%>
                        <%--<select name="productType" id = "productType">--%>
                            <%--<c:forEach var="type" items="${categories}">--%>
                                <%--<option>${type}</option>--%>
                            <%--</c:forEach>--%>

                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label for="productType">Typ</label>
                        <select name="productType" id="productType" class="form-control">
                            <c:forEach var="category" items="${categories}">
                                <c:if test="${product.productType.equals(category)}">
                                    <option selected="selected">${category}</option>
                                </c:if>
                                <c:if test="${! product.productType.equals(category)}">
                                    <option>${category}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class = "form-group">
                        <label for="photo">zdjecie</label>
                        <input type="file" class="form-control" id="photo" placeholder="zdjecie">
                    </div>
                    <button type="submit">OK!</button>
                </form>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.col-lg-9 -->
    </div>
    <!-- /.row -->
</div>
<!-- /.container -->
<%@include file="footer.jsp"%>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
