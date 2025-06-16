package studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname=req.getParameter("firstname");
		String lastname=req.getParameter("lastname");
		String username=req.getParameter("username");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String strphno=req.getParameter("phonenumber");
		String clgname=req.getParameter("college");
		
		long phno=Long.parseLong(strphno);
		
		//Step-1
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Step-2
			String url="jdbc:mysql://localhost:3306/studentmanagement";
			String user="root";
			String pass="root";
			Connection con=DriverManager.getConnection(url, user, pass);
			String query="insert into student values(?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.setLong(6, phno);
			ps.setString(7, clgname);
			
			ps.execute();
			PrintWriter pw=resp.getWriter();
			pw.print("<html><body>");
			pw.print("<h2>Registration was Sucessful<h2>");
			pw.print("</body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.forward(req, resp);
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
