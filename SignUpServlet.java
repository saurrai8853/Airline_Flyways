package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobilefly", "root", "root");

            String query = "SELECT * FROM user_registration WHERE name=? AND email=? AND mobile=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, mobile);

            ResultSet rs = pstmt.executeQuery();
            boolean userExists = rs.next();

            // Close resources
            rs.close();
            pstmt.close();

            // Send response based on user existence
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Sign Up Result</title></head><body>");
            if (userExists) {
                out.println("<h1>Access Granted</h1>");
            } else {
                out.println("<h1>Access Denied</h1>");
            }
            out.println("</body></html>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Sign Up Result</title></head><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>An error occurred. Please try again later.</p>");
            out.println("</body></html>");
        } finally {
            // Close the database connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
