package com.fashionstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fashionstore.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Visit /db-test to verify MySQL (faisal connection / faisaldb) from Tomcat */
@WebServlet("/db-test")
public class DbTestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family:sans-serif;padding:20px'>");
        out.println("<h2>Aura DB Test (faisal → faisaldb)</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("<p style='color:green'>MySQL driver loaded OK</p>");
        } catch (ClassNotFoundException e) {
            out.println("<p style='color:red'>MySQL driver NOT found. "
                    + "Put mysql-connector-j.jar in WEB-INF/lib and restart Tomcat.</p>");
            out.println("</body></html>");
            return;
        }

        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            out.println("<p style='color:red'>Connection failed. Check MySQL is running "
                    + "and password for root on 127.0.0.1:3306</p>");
            out.println("</body></html>");
            return;
        }

        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(*) AS c FROM products")) {
            rs.next();
            int count = rs.getInt("c");
            out.println("<p style='color:green'>Connected! Products in faisaldb: <strong>"
                    + count + "</strong></p>");
            out.println("<p><a href='" + request.getContextPath()
                    + "/products'>Go to Products</a></p>");
        } catch (Exception e) {
            out.println("<p style='color:red'>Query error: " + e.getMessage() + "</p>");
        } finally {
            DBConnection.closeQuietly(conn);
        }

        out.println("</body></html>");
    }
}
