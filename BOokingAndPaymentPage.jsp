<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Booking and Payment</title>
    <!-- Add your CSS styles here if needed -->
</head>
<body style="background: pink;">
    <h1>Booking and Payment</h1>
    <div class="form-container">
        <form action="BOokingAndPaymentPage" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter Name" required>
            <br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter Email" required>
            <br>
            <label for="mobile">Mobile Number:</label>
            <input type="tel" id="mobile" name="mobile" placeholder="Enter Mobile Number" required>
            <br>
            <table>
                <tr>
                    <th>Source</th>
                    <th>Destination</th>
                    <th>Date</th>
                    <th>Flight Number</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="flight" items="${matchingFlights}">
                    <tr>
                        <td>${flight.source}</td>
                        <td>${flight.destination}</td>
                        <td>${flight.date}</td>
                        <td>${flight.flightNumber}</td>
                        <td>${flight.price}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Proceed to Payment">
        </form>
    </div>
    <footer class="bg-dark text-center text-white py-3">
    <p>&copy; 2023 Airline flight team. All rights reserved.</p>
  </footer>
    
</body>
</html>
