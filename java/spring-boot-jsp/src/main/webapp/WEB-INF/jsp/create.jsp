<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <h2>Add New Customer</h2>

    <form action="/create" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>
        <br>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>
        <br>
        <label for="street">Street:</label>
        <input type="text" id="street" name="street">
        <br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address">
        <br>
        <label for="city">City:</label>
        <input type="text" id="city" name="city">
        <br>
        <label for="state">State:</label>
        <input type="text" id="state" name="state">
        <br>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email">
        <br>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone">
        <br>
        <button type="submit">Create</button>
    </form>

    <br>

    <a href="/home">Back to Customer List</a>
</body>
</html>
