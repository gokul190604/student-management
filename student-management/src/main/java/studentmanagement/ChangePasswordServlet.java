package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.html");
            return;
        }

        String username = session.getAttribute("username").toString();
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            out.println("<div class='alert alert-danger'>New passwords do not match!</div>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentmanagement", "root", "root");

            // Check current password
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM student WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, currentPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Update password
                PreparedStatement update = con.prepareStatement(
                    "UPDATE student SET password=? WHERE username=?");
                update.setString(1, newPassword);
                update.setString(2, username);
                update.executeUpdate();

                out.println("<html><head><title>Password Changed</title>");
                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("</head><body class='container mt-5'>");

                out.println("<div class='alert alert-success'>Password changed successfully!</div>");
                out.println("<a href='homepage.html' class='btn btn-primary'>Go to Dashboard</a>");

                out.println("</body></html>");

            } else {
                out.println("<html><body class='container mt-5'>");
                out.println("<div class='alert alert-danger'>Current password is incorrect!</div>");
                out.println("<a href='changePassword.html' class='btn btn-warning'>Try Again</a>");
                out.println("</body></html>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Error: " + e.getMessage() + "</div>");
        }
    }
}
