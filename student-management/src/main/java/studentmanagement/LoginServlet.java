package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		//Step-1
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					//Step-2
					String url="jdbc:mysql://localhost:3306/studentmanagement";
					String user="root";
					String pass="root";
					Connection con=DriverManager.getConnection(url, user, pass);
					String query="select * from student where username =? and password=? ";
					PreparedStatement ps=con.prepareStatement(query);
					
					ps.setString(1, username);
					ps.setString(2, password);
					ResultSet rs=ps.executeQuery();
					if(username.equalsIgnoreCase("Gokul15")&&password.equals("Gokul@123")) {
						HttpSession session = req.getSession();
						session.setAttribute("admin", username);
						resp.sendRedirect("admin.html");
						
					}
					else if(rs.next()) {
						HttpSession session = req.getSession();
					    session.setAttribute("username", username);
					    session.setAttribute("firstname", rs.getString("firstname"));
						resp.sendRedirect("homepage.html");
					}
					else {
						resp.setContentType("text/html"); // Ensure correct content type
					    PrintWriter out = resp.getWriter();
					    out.println("<center><p style='color:red;'>Invalid username or password</p></center>");
					    
					    RequestDispatcher rd = req.getRequestDispatcher("login.html");
					    rd.include(req, resp);
					
					}
					con.close();
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	

}
