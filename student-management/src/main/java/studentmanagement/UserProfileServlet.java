package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/viewStudentDetails")
public class UserProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Get username from session
        HttpSession session = req.getSession(false);
        String username = (String) session.getAttribute("username");

        if (username == null) {
            resp.sendRedirect("login.html");
            return;
        }

        out.println("<html><head><title>My Profile</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        out.println("</head><body><div class='container mt-5'>");
        out.println("<h2 class='mb-4 text-center'>Your Profile</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<table class='table table-bordered table-striped'>");
                out.println("<tr><th>First Name</th><td>" + rs.getString("firstname") + "</td></tr>");
                out.println("<tr><th>Last Name</th><td>" + rs.getString("lastname") + "</td></tr>");
                out.println("<tr><th>Username</th><td>" + rs.getString("username") + "</td></tr>");
                out.println("<tr><th>Email</th><td>" + rs.getString("email") + "</td></tr>");
                out.println("<tr><th>Phone Number</th><td>" + rs.getString("phonenumber") + "</td></tr>");
                out.println("<tr><th>College</th><td>" + rs.getString("collegename") + "</td></tr>");
                out.println("</table>");
            } else {
                out.println("<p class='text-danger'>No record found.</p>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<p class='text-danger'>Error: " + e.getMessage() + "</p>");
        }

        out.println("<a href='homepage.html' class='btn btn-primary'>Back to Home</a>");
        out.println("</div></body></html>");
    }
}
