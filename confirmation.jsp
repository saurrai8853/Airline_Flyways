<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flight Booking Confirmation</title>
</head>
<body>
    <h1>Flight Booking Confirmation</h1>
    <p>Name: <%= request.getAttribute("name") %></p>
    <p>Mobile Number: <%= request.getAttribute("mobile") %></p>
    <p>Email: <%= request.getAttribute("email") %></p>
    <p>Trip Type:
