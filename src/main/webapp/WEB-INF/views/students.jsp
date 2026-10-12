<%@ page import="java.util.List" %>
<%@ page import="com.student.entity.Student" %>

<!DOCTYPE html>
<html>

<head>
    <title>All Students</title>
</head>

<body>

<h1>Student List</h1>

<table border="1" cellpadding="10">

    <tr>
        <th>Register Number</th>
        <th>Name</th>
        <th>CGPA</th>
        <th>Actions</th>
    </tr>

<%
    List<Student> students =
        (List<Student>) request.getAttribute("students");

    if (students != null) {

        for (Student student : students) {
%>

    <tr>

        <td>
            <%= student.getRegno() %>
        </td>

        <td>
            <%= student.getName() %>
        </td>

        <td>
            <%= student.getCgpa() %>
        </td>

        <td>

            <a href="/StudentManagement/students/edit/<%= student.getRegno() %>">
                Edit
            </a>

            &nbsp; | &nbsp;

            <a href="/StudentManagement/students/delete/<%= student.getRegno() %>">
                Delete
            </a>

        </td>

    </tr>

<%
        }
    }
%>

</table>

<br>

<a href="/StudentManagement/">
    Back
</a>

</body>

</html>