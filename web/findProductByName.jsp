<%@ page import="sda.pl.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String product  = request.getParameter("phrase");
    List<Product> all = ProductRepository.findAllByName(product);
    pageContext.setAttribute("productAll", all);
%>
<head>
    <%@include file="header.jsp"%>
</head>
<body>
<div class="container">
    <div class="row">
        <%@include file="leftMenu.jsp" %>
        <div class="col-lg-9">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Cena Netto</th>
                    <th scope="col">Cena Brutto</th>
                    <th scope="col">Kolor</th>
                    <th scope="col">Typ</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productAll}" var="product" >
                    <tr>
                        <th scope="row">1</th>
                        <td>${product.name}</td>
                        <td>${product.price.priceNet}</td>
                        <td>${product.price.priceGross}</td>
                        <td>${product.color}</td>
                        <td>${product.productType}</td>
                        <%--<td><a class="btn" href="orderDetails.jsp?orderId=${order.id}">Szczegoly zamowienia</a>  </td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%@include file="footer.jsp"%>
</body>

