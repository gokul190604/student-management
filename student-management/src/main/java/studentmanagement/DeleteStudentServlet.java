package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Session check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect("login.html");
            return;
        }

        String username = req.getParameter("username");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentmanagement", "root", "root");

            String sql = "DELETE FROM student WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            int rows = ps.executeUpdate();

            out.println("<html><head><title>Delete Result</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head><body class='container mt-5'>");

            if (rows > 0) {
                out.println("<div class='alert alert-success'>Student deleted successfully.</div>");
            } else {
                out.println("<div class='alert alert-warning'>No student found with the given username.</div>");
            }

            out.println("<a href='admin.html' class='btn btn-primary mt-3'>Back to Dashboard</a>");
            out.println("</body></html>");

            con.close();
        } catch (Exception e) {
            out.println("<div class='alert alert-danger'>Error: " + e.getMessage() + "</div>");
        }
    }
}
