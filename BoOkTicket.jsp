<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.List" %>
<%@ page import="com.flightDetails" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BOOKTicket</title>
<style>
.navbar {
            background-color: #333;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 20px;
        }

        .navbar-brand {
            color: #fff;
            font-size: 24px;
            font-weight: bold;
        }

        .nav-links {
            display: flex;
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .nav-link {
            margin-left: 20px;
            color: #fff;
            text-decoration: none;
            font-size: 16px;
        }

        .nav-link:hover {
            color: #ff0000;
        }

        @media (max-width: 768px) {
            .nav-links {
                display: none;
            }

            .nav-links.active {
                display: flex;
                flex-direction: column;
            }

            .nav-link {
                margin: 10px 0;
            }

            .navbar-toggler {
                display: block;
                background: none;
                border: none;
                color: #fff;
                font-size: 24px;
                cursor: pointer;
            }
        }
        .form-container {
            /* Styles for the form container */
            max-width: 800px;
            width:90%
            margin: 0 auto;
            padding: 10px;
            background-color: pink;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0);
        }

        .form-container label {
            /* Styles for form labels */
            font-weight: 400;
            font-size: 30px;
        }

        .form-container input,
        .form-container select {
            /* Styles for form inputs and select */
            width: 100%;
            padding: 10px;
            border: 1px solid black;
            border-radius: 30px;
            font-size: 20px;
            margin-bottom: 10px;
        }

        .form-container button {
            /* Styles for the form submit button */
            background-color: green;
            color: #ffffff;
            border: none;
            border-radius:30px;
            padding: 10px 20px;
            font-size: 20px;
            cursor: pointer;
        }

        .form-container button:hover {
            /* Styles for the form submit button on hover */
            background-color: lime;
            }
            .date,.number,.trip{
            width: 20%;
            max-width: 200px;
            }

</style>
</head>
<body style="background: pink;">
<h1>BookTicket</h1>
<nav class="navbar">
        <a class="navbar-brand" href="#">AirLine</a>
        
        <ul class="nav-links" id="nav-links">
           
            <li class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
            <li class="nav-item"><a class="nav-link" href="#">About Us</a></li>
            <li class="nav-item"><a class="nav-link" href="#">Sign_In</a></li>
          
        </ul>
    </nav>
    <div class="form-container">
        <form action="SearchServletPage" method="post">
            <label for="source">Source:</label>
            <input type="text" id="source" name="source" placeholder="Enter Source" required>
            <br>
            <label for="destination">Destination:</label>
            <input type="text" id="destination" name="destination" placeholder="Enter Destination" required>
            <br>
            <label for="date">Date:</label>
            <input type="date" class="date" id="date" name="date" required>
            <br>
            
            <button type="submit">Search</button>
        </form>
        
    </div>
    <h2>${pageTitle}</h2>
<%-- Check if matchingFlights list is not empty and display flight details --%>
<% if (request.getAttribute("matchingFlights") != null) { %>
    <table>
        <tr>
            <th>Source</th>
            <th>Destination</th>
            <th>Date</th>
            <th>Flight Number</th>
            <th>Price</th>
        </tr>
        <% List<com.flightDetails> matchingFlights = (List<com.flightDetails>) request.getAttribute("matchingFlights"); %>
        <% for (com.flightDetails flight : matchingFlights) { %>
            <tr>
                <td><%= flight.getSource() %></td>
                <td><%= flight.getDestination() %></td>
                <td><%= flight.getDate() %></td>
                <td><%= flight.getFlightNumber() %></td>
                <td><%= flight.getPrice() %></td>
                <td>
    <form action="BOokingAndPaymentPage.jsp">
        <input type="hidden" name="flightNumber" value="<%= flight.getFlightNumber() %>">
        <input type="submit" value="Book">
    </form>
</td>
            </tr>
        <% } %>
    </table>
<% } else { %>
<p>No flights found for the given criteria.</p>
<% } %>






<%-- Display Flight Details Section --%>
<footer class="bg-dark text-center text-white py-3">
    <p>&copy; 2023 Airline flight team. All rights reserved.</p>
  </footer>

    
</body>
</html>