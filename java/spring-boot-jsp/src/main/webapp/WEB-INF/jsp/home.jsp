<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h2>Customer List</h2>
    <table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Street</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <form action="/update" method="post" style="display: inline;">
                    <td><input type="text" name="firstName" value="${customer.getFirstName()}" contenteditable="true"></td>
                    <td><input type="text" name="lastName" value="${customer.getLastName()}" contenteditable="true"></td>
                    <td><input type="text" name="street" value="${customer.getStreet()}" contenteditable="true"></td>
                    <td><input type="text" name="address" value="${customer.getAddress()}" contenteditable="true"></td>
                    <td><input type="text" name="city" value="${customer.getCity()}" contenteditable="true"></td>
                    <td><input type="text" name="state" value="${customer.getState()}" contenteditable="true"></td>
                    <td><input type="text" name="email" value="${customer.getEmail()}" contenteditable="true"></td>
                    <td><input type="text" name="phone" value="${customer.getPhone()}" contenteditable="true"></td>
                    <td>
                        <input type="hidden" name="customerId" value="${customer.getUuid()}">
                        <button type="submit">Update</button>
                    </td>
                </form>

                <form action="/delete" method="post" style="display: inline;">
                    <td><input type="hidden" name="customerId" value="${customer.getUuid()}"></td>
                    <td><button type="submit">Delete</button></td>
                </form>
            </tr>
        </c:forEach>
    </table>

    <br>

    <a href="/create">Create Customer</a>
</body>
</html>
