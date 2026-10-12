<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>Student Management</title>
</head>

<body>

<h1>Student Management System</h1>

<h2>Add Student</h2>

<form action="/StudentManagement/students/add" method="post">

    Register Number:
    <input type="number"
           name="regno"
           required>

    <br><br>

    Name:
    <input type="text"
           name="name"
           required>

    <br><br>

    CGPA:
    <input type="number"
           name="cgpa"
           step="0.01"
           min="0"
           max="10"
           required>

    <br><br>

    <input type="submit" value="Add Student">

</form>

<br><br>

<a href="/StudentManagement/students/list">
    View All Students
</a>

<br><br>

<a href="/StudentManagement/students/search">
    Search Student
</a>

</body>

</html>