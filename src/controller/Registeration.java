package controller;

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
@WebServlet("/Registeration")
public class Registeration extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=request.getParameter("username");
		String userpassword=request.getParameter("password");
		String useremail=request.getParameter("useremail");
		PrintWriter out=response.getWriter();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			   String url="jdbc:mysql://localhost:3306/mydb";
			   String user="root";
			   String password="";
			Connection conn=DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt=conn.prepareStatement("insert into users (username,useremail,userpassword) values (?,?,?)");
			pstmt.setString(1,username);
			pstmt.setString(2,useremail);
			pstmt.setString(3,userpassword);
			Boolean b=pstmt.execute();
			if(!b)
			{
				RequestDispatcher rd=request.getRequestDispatcher("loginResponse.jsp");
                String s="Congrates! you are registered now . Please login now.";
                request.setAttribute("lr",s);
                rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("Register.html");
				rd.include(request, response);
				out.println("<center><p><font color=\"red\"><h4>Something went wrong! Please try again.</h4></p></center>");
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
