package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class FlightDetailstabledata {
    
    public static void main(String[] args) {
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobilefly", "root", "root");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO flightDetails VALUES(?,?,?,?,?,?)");
            

            Scanner sc = new Scanner(System.in);
            String choice = "";

            while (!choice.equals("2")) {
                System.out.println("Press 'i' to continue inserting data or '2' to exit:");
                choice = sc.next().toLowerCase();

                if (choice.equals("i")) {
                	System.out.println("Enter ID:");
                	int id=sc.nextInt();
                    System.out.println("Enter your source:");
                    String source = sc.next();
                    System.out.println("Enter your Destination:");
                    String destination = sc.next();
                    System.out.println("Enter your date (in yyyy-MM-dd format):");
                    String date = sc.next();
                    System.out.println("Enter your flight number:");
                    String flightNumber = sc.next();
                    System.out.println("Enter the price:");
                    String price = sc.next();
                    stmt.setInt(1, id);
                    stmt.setString(2, source);
                    stmt.setString(3, destination);
                    stmt.setString(4, date);
                    stmt.setString(5, flightNumber);
                    stmt.setString(6, price);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + " row(s) inserted successfully.");
                }
            }
            System.out.println("Thanks dude");

            con.close();
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
