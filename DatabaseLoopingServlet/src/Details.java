

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
 * Servlet implementation class Details
 */
@WebServlet("/details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private  String message1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("CustID");
		int cust_id=Integer.parseInt(id);
		
		 try{
			  message1="";  
     	 String url = "jdbc:oracle:thin:testuser/password@localhost"; 
     	 Class.forName("oracle.jdbc.driver.OracleDriver");
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testdb");
	        props.setProperty("password", "password");
	        Connection conn = DriverManager.getConnection(url,props);
	        //creating connection to Oracle database using JDBC   
	      
	        	PreparedStatement ps=conn.prepareStatement("select * from demo_customers where customer_id=  "+cust_id);
           
	        	ResultSet rs=ps.executeQuery();   
	        
	       
            /* Printing column names */

           // ResultSetMetaData rsmd=rs.getMetaData();
       

            while(rs.next())

               {

                  message1+="<br>First Name: "+rs.getString("cust_first_name")+ "</br><br>Last Name: "+
                  rs.getString("cust_last_name")+"</br><br>Address: "+
                  rs.getString("cust_street_address1") +"</br><br>state: "+rs.getString("cust_state")
                  +"</br><br>City: "+rs.getString("cust_city")+"</br><br>Postal Code: "+rs.getString("cust_postal_code")+
                  "</br><br>Phone Number 1: "+rs.getString("phone_number1")+"</br><br>Credit Limit: "+rs.getString("credit_limit")+"</br>";  

                 
            }
            

          
           System.out.println(message1);
            request.setAttribute("message", message1);
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
