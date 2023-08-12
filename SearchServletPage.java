package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServletPage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchServletPage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JAR file
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobilefly", "root", "root");

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM flightDetails WHERE source = ? AND destination = ? AND date = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, source);
            pstmt.setString(2, destination);
            pstmt.setString(3, date);

            ResultSet rs = pstmt.executeQuery();

            PrintWriter out = response.getWriter();
            if (rs.next()) {
                // Display all matching flight details
            	List<flightDetails> matchingFlights = new ArrayList<>();
                do {
                    flightDetails flight = new flightDetails();
                    flight.setSource(rs.getString("source"));
                    flight.setDestination(rs.getString("destination"));
                    flight.setDate(rs.getString("date"));
                    flight.setFlightNumber(rs.getString("flightNumber"));
                    flight.setPrice(rs.getDouble("price"));
                    // Set other flight details as needed
                    matchingFlights.add(flight);
                } while (rs.next());

                // Set the list of matching flights in the request attribute
                request.setAttribute("matchingFlights", matchingFlights);

                // Set a title attribute to be displayed in the JSP
                request.setAttribute("pageTitle", "Flight Details:");

            } else {
                // Set a title attribute to indicate no flights were found
                request.setAttribute("pageTitle", "No flights found for the given criteria.");
            }

            pstmt.close();
            con.close();

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("BoOkTicket.jsp");
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
