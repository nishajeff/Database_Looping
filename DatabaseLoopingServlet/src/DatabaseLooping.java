

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseLooping
 */
@WebServlet("/DatabaseLooping")
public class DatabaseLooping extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String message="";  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseLooping() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		 try{
			  message="";  
      	 String url = "jdbc:oracle:thin:testuser/password@localhost"; 
      	 Class.forName("oracle.jdbc.driver.OracleDriver");
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testdb");
	        props.setProperty("password", "password");
	        Connection conn = DriverManager.getConnection(url,props);
	        //creating connection to Oracle database using JDBC              

             PreparedStatement ps=conn.prepareStatement("select * from demo_customers  ");
            
             ResultSet rs=ps.executeQuery();                 

             /* Printing column names */

            // ResultSetMetaData rsmd=rs.getMetaData();
         message+="<div align=\"center\"><table style=\"border:2px solid black\">";
             message+="<th style=\" background-color:yellow;border:2px solid black\">Firstname</th><th style=\" background-color:yellow;border:2px solid black\">Lastname</th><th style=\" background-color:yellow;border:2px solid black\">Address</th>";

             while(rs.next())

                {

                   message+="<tr style=\"border:2px solid black\"><td style=\" background-color:yellow;border:2px solid black\">"+
                   "<a href=\"details.jsp\">"+rs.getString("cust_first_name")+"</a>"+
                		   "</td><td style=\"background-color:yellow;border:2px solid black\">" + 
                   rs.getString("cust_last_name")+"</td><td style=\"background-color:yellow;border:2px solid black\">" + 
                   rs.getString("cust_street_address1")+"</td></tr>" ;  

                  
             }
             

             message+="\n</tbody>\n</table></div>";
            // System.out.println("message="+message);
            
             request.setAttribute("message", message);
             getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);

      }catch (Exception e2)

        {

          System.out.println("No connection");

        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
