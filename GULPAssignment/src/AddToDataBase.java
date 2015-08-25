
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Review
 */
@WebServlet("/Review")
public class AddToDataBase extends HttpServlet 
{
	static Connection conn;
	static String name, output="";
	private static final long serialVersionUID = 1L;
	
	private static int restaurant_id = 0,review_id = 0;
       
	  public AddToDataBase(){
		  super();
	  }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
			// TODO Auto-generated method stub
			response.setContentType("text/html");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String url = "jdbc:oracle:thin:testuser/password@localhost"; 
			//properties for creating connection to Oracle database
			Properties props = new Properties();
			
			props.setProperty("user", "testdb");
			props.setProperty("password", "password");
			
			//creating connection to Oracle database using JDBC
			Connection conn;
			
			PreparedStatement preStatement;
			String restaurant_sql = "insert into Restaurants values (?,?,?,?)";
			try{
				conn = DriverManager.getConnection(url,props);
				preStatement = conn.prepareStatement(restaurant_sql);
				
				
				preStatement.setInt(1,restaurant_id++);
				preStatement.setString(2,request.getParameter("name"));
				preStatement.setString(3,request.getParameter("Address"));
				preStatement.setString(4,request.getParameter("Description"));

				ResultSet result = preStatement.executeQuery();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String reviews_sql = "insert into Reviews values (?,?,?,?)";
			try{
				conn = DriverManager.getConnection(url,props);
				preStatement = conn.prepareStatement(reviews_sql);
				
				
				preStatement.setInt(1,review_id++);
				preStatement.setString(2,request.getParameter("Rating"));
				preStatement.setString(3,request.getParameter("RatingDate"));
				preStatement.setString(4,request.getParameter("reviews"));


				ResultSet result = preStatement.executeQuery();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	            
	            
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
