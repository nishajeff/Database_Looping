

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
 * Servlet implementation class Details
 */
@WebServlet("/details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
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
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message="";
		String id=request.getParameter("CustID");
		int cust_id=Integer.parseInt(id);
		try{
			message="";
			message+="<h3>Customer Information requested :</h3>";
					
			EntityManager em=DBUtil.getEmFactory().createEntityManager();
			String q="select d from DemoCustomer d where d.customerId="+(long)cust_id;
			TypedQuery<DemoCustomer>bq =em.createQuery(q,DemoCustomer.class);
			List<DemoCustomer> list=bq.getResultList();
			for(DemoCustomer temp:list){
				message+="<br>First Name: "+temp.getCustFirstName()+ "</br><br>Last Name: "+
		                 temp.getCustLastName()+"</br><br>Address: "+
		                  temp.getCustStreetAddress1() +"</br><br>state: "+temp.getCustState()
		                  +"</br><br>City: "+temp.getCustCity()+"</br><br>Postal Code: "+temp.getCustPostalCode()+
		                  "</br><br>Phone Number 1: "+temp.getPhoneNumber1()+"</br><br>Credit Limit: "+temp.getCreditLimit()+"</br>";   
			}
			 message+="\n</tbody>\n</table></div>";
			request.setAttribute("message", message);
    		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}
