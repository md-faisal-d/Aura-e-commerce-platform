package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.interfaces.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();
        Connection connection = null;

        try {

            connection = DBConnection.getConnection();
            if (connection == null) {
                return categories;
            }

            String query = "SELECT * FROM categories ORDER BY name";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Category category = new Category();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                categories.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeQuietly(connection);
        }

        return categories;
    }

    @Override
    public Category getCategoryById(int categoryId) {

        Category category = null;
        Connection connection = null;

        try {

            connection = DBConnection.getConnection();
            if (connection == null) {
                return null;
            }

            String query = "SELECT * FROM categories WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                category = new Category();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeQuietly(connection);
        }

        return category;
    }
}
