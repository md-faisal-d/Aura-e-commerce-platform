package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.interfaces.ProductDAO;
import com.fashionstore.model.Category;
import com.fashionstore.model.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Featured Products
        List<Product> featuredProducts =
                productDAO.getFeaturedProducts();

        // New Arrivals
        List<Product> newArrivals =
                productDAO.getNewArrivals();

        // Set data to request scope
        request.setAttribute(
                "featuredProducts",
                featuredProducts);

        request.setAttribute(
                "newArrivals",
                newArrivals);

        List<Category> categories =
                new CategoryDAOImpl().getAllCategories();

        request.setAttribute("categories", categories);

        // Forward to JSP
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "/WEB-INF/views/home.jsp");

        dispatcher.forward(request, response);
    }
}