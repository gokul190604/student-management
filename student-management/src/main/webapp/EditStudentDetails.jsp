<%@ page import="java.sql.*" %>
<%
    String username = request.getParameter("username");
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "root");
    ps = con.prepareStatement("SELECT * FROM student WHERE username=?");
    ps.setString(1, username);
    rs = ps.executeQuery();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Student Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="text-center mb-4">Edit Student Details</h2>

        <% if (rs.next()) { %>
        <form action="updateStudent" method="post" class="card p-4 shadow-lg bg-white">
            <input type="hidden" name="username" value="<%=rs.getString("username")%>">
            
            <div class="mb-3">
                <label class="form-label">First Name</label>
                <input type="text" class="form-control" name="firstname" value="<%=rs.getString("firstname")%>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Last Name</label>
                <input type="text" class="form-control" name="lastname" value="<%=rs.getString("lastname")%>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email" value="<%=rs.getString("email")%>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Phone Number</label>
                <input type="text" class="form-control" name="phonenumber" value="<%=rs.getString("phonenumber")%>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">College Name</label>
                <input type="text" class="form-control" name="collegename" value="<%=rs.getString("collegename")%>" required>
            </div>
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success">Update Student</button>
                <a href="adminDashboard.html" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
        <% } else { %>
            <div class="alert alert-danger text-center">Student not found!</div>
        <% } %>
    </div>
</body>
</html>
