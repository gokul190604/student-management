package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/viewstudents")
public class ViewStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // HTML & Bootstrap Head
        out.println("<!DOCTYPE html><html><head><title>Student List</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        out.println("</head><body class='bg-light'>");

        out.println("<div class='container mt-5'>");
        out.println("<h2 class='mb-4 text-center'>Student Details</h2>");
        out.println("<table class='table table-bordered table-striped shadow'>");
        out.println("<thead class='table-dark'><tr>");
        out.println("<th>First Name</th><th>Last Name</th><th>Username</th><th>Email</th>");
        out.println("<th>Phone Number</th><th>College Name</th>");
        out.println("</tr></thead><tbody>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/studentmanagement";
            String user = "root";
            String password = "root";

            Connection con = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM student";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("firstname") + "</td>");
                out.println("<td>" + rs.getString("lastname") + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("phonenumber") + "</td>");
                out.println("<td>" + rs.getString("collegename") + "</td>");
                out.println("</tr>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<tr><td colspan='6' class='text-danger text-center'>Error: " + e.getMessage() + "</td></tr>");
        }

        out.println("</tbody></table>");
        out.println("<a href='admin.html' class='btn btn-primary'>Back to Home</a>");
        out.println("</div></body></html>");
    }
}


