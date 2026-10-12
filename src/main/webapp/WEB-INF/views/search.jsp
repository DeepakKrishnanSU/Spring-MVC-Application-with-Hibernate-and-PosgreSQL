<!DOCTYPE html>
<html>

<head>
    <title>Search Student</title>
</head>

<body>

<h1>Search Result</h1>

<%
    Object student = request.getAttribute("student");
%>

<% if (student != null) { %>

    <p>
        Student Found
    </p>

    <p>
        Register Number:
        ${student.regno}
    </p>

    <p>
        Name:
        ${student.name}
    </p>

    <p>
        CGPA:
        ${student.cgpa}
    </p>

<% } else { %>

    <p>
        Student not found.
    </p>

<% } %>

<br>

<a href="${pageContext.request.contextPath}/students/">
    Back
</a>

</body>

</html>