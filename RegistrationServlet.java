package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/mobilefly";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String gender = request.getParameter("gender");
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	        String insertQuery = "insert into user_registration(gender, name, email, phone, address) values(?,?,?,?,?)";
	        PreparedStatement stmt = con.prepareStatement(insertQuery);
	        stmt.setString(1, gender);
	        stmt.setString(2, name);
	        stmt.setString(3, email);
	        stmt.setString(4, phone);
	        stmt.setString(5, address);

	        stmt.executeUpdate();
	        con.close();
	       response.sendRedirect("Success.jsp");
	    } catch (Exception e) {
	        System.out.println(e);
	        response.sendRedirect("error.jsp");
	    }
	}
}
