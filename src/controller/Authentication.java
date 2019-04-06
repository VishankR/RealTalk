package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Auth")
public class Authentication extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static Set<HttpSession> sessions=Collections.synchronizedSet(new HashSet<HttpSession>());
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String ue=request.getParameter("useremail");
		String up=request.getParameter("userpassword");
		PrintWriter out=response.getWriter();
		try 
		{
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   String url="jdbc:oracle:thin:@localhost:1521:orcl";
		   String user="vishank";
		   String password="vishank";
		   ResultSet rs=null;
		   String s1,s3=null;
		   Connection conn=DriverManager.getConnection(url,user,password);
		   try
		   {
		   PreparedStatement pstmt=conn.prepareStatement("select * from users where useremail=?");
		   pstmt.setString(1,ue);
		   
		   rs=pstmt.executeQuery();
		   if(rs.next())
		   {
			   s1=rs.getString(1);
			   s3=rs.getString(3);
			   if(s3.equals(up))
			   {
				   HttpSession session=request.getSession();
				   if(session!=null)
				   {
					   sessions.add(session);
					   session.setAttribute("username", s1);
					   session.setAttribute("userpasswod",s3);
					   Iterator<HttpSession> itr=sessions.iterator();
					   while(itr.hasNext())
					   {
						   HttpSession sess=itr.next();
					   System.out.println("Sessions :"+sess.getAttribute("username"));
					   }
				   }
				   else
				   {
					   
				   }
			   RequestDispatcher rd=request.getRequestDispatcher("BroadcastChatPage.jsp");
			   rd.forward(request, response);
			   }
			   else
			   {
				   RequestDispatcher rd=request.getRequestDispatcher("login.html");
				   rd.include(request, response); 
				   out.println("<p><br>Hey '"+s1+"'<br><br>Password is incorrect.<br></p>");
			   }
		   }
		   else
		   {
				request.setAttribute("st","You are not Registered Please create an account first.");
				RequestDispatcher rd=request.getRequestDispatcher("registrationResponse.jsp");
				rd.forward(request, response);
		   }
		   }
			catch(Exception e)
			{
				e.printStackTrace();
			}
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		}
}


