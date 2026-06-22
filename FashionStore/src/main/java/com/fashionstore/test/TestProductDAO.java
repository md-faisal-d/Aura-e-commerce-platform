package com.fashionstore.test;

import java.util.List;

import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.interfaces.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;

public class TestProductDAO {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAOImpl();

        // =========================================
        // TEST 1 - GET ALL PRODUCTS
        // =========================================

        System.out.println("===== ALL PRODUCTS =====");

        List<Product> allProducts =
                productDAO.getAllProducts();

        for (Product product : allProducts) {

            System.out.println(
                    product.getId() + " | "
                    + product.getName() + " | "
                    + product.getPrice()
            );
        }

        // =========================================
        // TEST 2 - FEATURED PRODUCTS
        // =========================================

        System.out.println("\n===== FEATURED PRODUCTS =====");

        List<Product> featuredProducts =
                productDAO.getFeaturedProducts();

        for (Product product : featuredProducts) {

            System.out.println(
                    product.getName()
            );
        }

        // =========================================
        // TEST 3 - NEW ARRIVALS
        // =========================================

        System.out.println("\n===== NEW ARRIVALS =====");

        List<Product> newArrivals =
                productDAO.getNewArrivals();

        for (Product product : newArrivals) {

            System.out.println(
                    product.getName()
            );
        }

        // =========================================
        // TEST 4 - SEARCH PRODUCTS
        // =========================================

        System.out.println("\n===== SEARCH : Hoodie =====");

        List<Product> searchedProducts =
                productDAO.searchProducts("Hoodie");

        for (Product product : searchedProducts) {

            System.out.println(
                    product.getName()
            );
        }

        // =========================================
        // TEST 5 - PRODUCTS BY CATEGORY
        // =========================================

        System.out.println("\n===== CATEGORY : MEN =====");

        List<Product> categoryProducts =
                productDAO.getProductsByCategory(1);

        for (Product product : categoryProducts) {

            System.out.println(
                    product.getName()
            );
        }

        // =========================================
        // TEST 6 - FILTER PRODUCTS
        // =========================================

        System.out.println("\n===== FILTER PRODUCTS =====");

        List<Product> filteredProducts =
                productDAO.filterProducts(
                        1,
                        "M",
                        "Black",
                        500.0,
                        3000.0,
                        "H&M",
                        "priceLowToHigh"
                );

        for (Product product : filteredProducts) {

            System.out.println(
                    product.getName()
                    + " | "
                    + product.getPrice()
            );
        }

        // =========================================
        // TEST 7 - PRODUCT VARIANTS
        // =========================================

        System.out.println("\n===== PRODUCT VARIANTS =====");

        List<ProductVariant> variants =
                productDAO.getVariantsByProductId(1);

        for (ProductVariant variant : variants) {

            System.out.println(
                    variant.getSize()
                    + " | "
                    + variant.getColor()
                    + " | Stock : "
                    + variant.getStock()
            );
        }

        System.out.println("\n===== DAO TEST COMPLETED =====");
    }
}