package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/studentUpdate")
public class StudentUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            resp.sendRedirect("login.html");
            return;
        }

        // Get form inputs (username will come from session for security)
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String college = req.getParameter("college");

        // Optional: Basic validation (can be expanded)
        if (firstname == null || email == null || phone == null || college == null) {
            out.println("<div class='alert alert-warning'>All fields are required!</div>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentmanagement", "root", "root");
                 PreparedStatement ps = con.prepareStatement(
                         "UPDATE student SET firstname=?, lastname=?, email=?, phonenumber=?, collegename=? WHERE username=?")) {

                ps.setString(1, firstname);
                ps.setString(2, lastname);
                ps.setString(3, email);
                ps.setString(4, phone);
                ps.setString(5, college);
                ps.setString(6, username);

                int rows = ps.executeUpdate();

                out.println("<html><head><title>Update Result</title>");
                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("</head><body class='container mt-5'>");

                if (rows > 0) {
                    out.println("<div class='alert alert-success'>Your details have been updated successfully!</div>");
                } else {
                    out.println("<div class='alert alert-warning'>Update failed. Please try again.</div>");
                }

                out.println("<a href='homepage.html' class='btn btn-primary mt-3'>Back to Dashboard</a>");
                out.println("</body></html>");
            }

        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Error: " + e.getMessage() + "</div>");
            e.printStackTrace();
        }
    }
}


