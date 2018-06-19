<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="sda.pl.domain.ProductType" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 15.06.2018
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductType[] values = ProductType.values();
    pageContext.setAttribute("categories", values);
%>
<html>
<div class="col-lg-3">

    <h1 class="my-4">Shop Name</h1>
    <div class="list-group">
        <c:forEach var="category" items="${categories}">
            <a href="productCategory.jsp?category=${category}" class="list-group-item">${category}</a>
        </c:forEach>
    </div>

</div>
</html>
<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: pllsym--%>
  <%--Date: 14 cze 2018--%>
  <%--Time: 20:24--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="col-lg-3">--%>

    <%--<h1 class="my-4">Shop Name</h1>--%>
    <%--<div class="list-group">--%>
        <%--<a href="#" class="list-group-item">Category 1</a>--%>
        <%--<a href="#" class="list-group-item">Category 2</a>--%>
        <%--<a href="#" class="list-group-item">Category 3</a>--%>
    <%--</div>--%>

<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
