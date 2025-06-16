package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/editStudentForm")
public class EditStudentFormServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("student", rs);
                req.setAttribute("username", username);
                RequestDispatcher dispatcher = req.getRequestDispatcher("EditStudentDetails.jsp");
                dispatcher.forward(req, resp);
            } else {
                out.println("<p style='color:red;'>Student not found.</p>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}

