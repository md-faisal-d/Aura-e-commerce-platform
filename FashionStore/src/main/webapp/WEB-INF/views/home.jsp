<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Product" %>
<%@ page import="com.fashionstore.model.Category" %>
<%@ page import="com.fashionstore.util.ImageUtil" %>
<%
String ctx = request.getContextPath();
List<Product> featuredProducts = (List<Product>) request.getAttribute("featuredProducts");
List<Product> newArrivals = (List<Product>) request.getAttribute("newArrivals");
List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aura - Fashion Store</title>
    <link rel="stylesheet" href="<%=ctx%>/assets/css/style.css">
</head>
<body class="page-bg">
    <jsp:include page="/components/navbar.jsp"/>

    <section class="hero">
        <div class="hero-left animate-in">
            <h1>Discover Your <span>Fashion</span> Style</h1>
            <p>Explore trendy outfits, modern fashion, and premium collections crafted for every style.</p>
            <a href="<%=ctx%>/products" class="hero-btn">Shop Now</a>
        </div>
        <div class="hero-right animate-in">
            <img src="<%= ImageUtil.toUrl(ctx, "assets/images/products/1.png") %>" alt="Fashion">
        </div>
    </section>

    <section class="section container">
        <h2 class="section-title">Shop by Category</h2>
        <div class="category-grid">
            <% if (categories != null) {
                for (Category cat : categories) { %>
            <a href="<%=ctx%>/products?categoryId=<%=cat.getId()%>" class="category-card animate-in">
                <span><%=cat.getName()%></span>
            </a>
            <% }} %>
        </div>
    </section>

    <section class="section container">
        <h2 class="section-title">Featured Products</h2>
        <div class="product-grid">
            <% if (featuredProducts != null) {
                for (Product product : featuredProducts) { %>
            <article class="product-card animate-in" onclick="location.href='<%=ctx%>/product?id=<%=product.getId()%>'">
                <div class="product-image-wrap">
                    <img src="<%= ImageUtil.toUrl(ctx, product.getImageUrl()) %>" alt="<%=product.getName()%>">
                </div>
                <div class="product-info">
                    <p class="product-brand"><%=product.getBrand()%></p>
                    <h3 class="product-name"><%=product.getName()%></h3>
                    <p class="product-price">₹<%=product.getPrice()%></p>
                </div>
            </article>
            <% }} %>
        </div>
    </section>

    <section class="section container">
        <h2 class="section-title">New Arrivals</h2>
        <div class="product-grid">
            <% if (newArrivals != null) {
                for (Product product : newArrivals) { %>
            <article class="product-card animate-in" onclick="location.href='<%=ctx%>/product?id=<%=product.getId()%>'">
                <div class="product-image-wrap">
                    <img src="<%= ImageUtil.toUrl(ctx, product.getImageUrl()) %>" alt="<%=product.getName()%>">
                </div>
                <div class="product-info">
                    <p class="product-brand"><%=product.getBrand()%></p>
                    <h3 class="product-name"><%=product.getName()%></h3>
                    <p class="product-price">₹<%=product.getPrice()%></p>
                </div>
            </article>
            <% }} %>
        </div>
    </section>

    <jsp:include page="/components/footer.jsp"/>
    <script>window.APP_CONTEXT = '<%=ctx%>';</script>
    <script src="<%=ctx%>/assets/js/search.js"></script>
</body>
</html>
