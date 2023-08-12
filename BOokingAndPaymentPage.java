package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BOokingAndPaymentPage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        // Retrieve flight details from the session or request attribute
        // We assume you have already set this attribute in the previous servlet
        // For example, request.getAttribute("matchingFlights")

        // Simulate a payment process
        boolean paymentSuccessful = performPaymentProcess();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Payment Confirmation</title></head><body>");

        if (paymentSuccessful) {
            out.println("<h1>Payment Successful!</h1>");
            out.println("<p>Thank you, " + name + ", for booking with us.</p>");
            out.println("<p>We have sent a confirmation to your email: " + email + "</p>");
        } else {
            out.println("<h1>Payment Failed</h1>");
            out.println("<p>We are sorry, but there was an issue with the payment process.</p>");
        }

        out.println("</body></html>");
    }

    // Simulate the payment process (always returns true in this example)
    private boolean performPaymentProcess() {
        // In a real scenario, this method would interact with a payment gateway
        // and return true for a successful payment, or false for a failed payment.
        // Here, we simply assume the payment is successful for demonstration purposes.
        return true;
    }
}
