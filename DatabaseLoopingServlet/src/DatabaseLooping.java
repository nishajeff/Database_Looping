

import java.io.IOException;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.DemoCustomer;



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
		
		doPost(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			message="";
			message+="<h3 align=\"center\">List Of Customers:</h3>";
			 message+="<div align=\"center\"><table style=\"border:2px solid black\">";
             message+="<th style=\" background-color:white;border:2px solid black\">CustID</th><th style=\" background-color:white;border:2px solid black\">Firstname</th><th style=\" background-color:white;border:2px solid black\">Lastname</th><th style=\" background-color:white;border:2px solid black\">Address</th>";
			
					
			EntityManager em=DBUtil.getEmFactory().createEntityManager();
			String q="select d from DemoCustomer d ";
			TypedQuery<DemoCustomer>bq =em.createQuery(q,DemoCustomer.class);
			List<DemoCustomer> list=bq.getResultList();
			for(DemoCustomer temp:list){
				 message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+
                   "<a href=\"details?CustID=" +temp.getCustomerId()+"\">"+temp.getCustomerId()+"</a>"+
                		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getCustFirstName()+
                		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getCustLastName()+
                		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getCustStreetAddress1()+"</td></tr>" ;  
			}
			 message+="\n</tbody>\n</table></div>";
			request.setAttribute("message", message);
    		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
