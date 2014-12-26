// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

//Extend HttpServlet class
public class CartActions extends HttpServlet{
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //Set response content type
        response.setContentType("text/html");

        HttpSession mySession = request.getSession();


    String variId  = (String)mySession.getAttribute("loginVarified"); //taking user email as session varification - will be used for db.
    
    if (variId == null) {
        response.sendRedirect("/jakaria-ses/");
    }





        PrintWriter out = response.getWriter();
        String title = "Checkout Page";

        int totalPrice = 0;
        
        String addCartBtn = request.getParameter("add2cart");
		String delBtn	  = request.getParameter("cartDel");
		String checkBtn   = request.getParameter("checkOut");
		String confirmBtn = request.getParameter("checkOutConfirm");

    String docType =  "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

  // Invalidate the session
  // mySession.invalidate();

    out.println(docType+
        "<html>\n"+
        "<head><title>"+title+"</title></head>\n"+
        "<body>\n"+
            "<h1 align=\"center\">"+title+" - for you - "+variId+"</h1>\n"+
            "<center><form action='register' method='post'>\n"+
                "<tr><td><input name=\"logout\" type=\"submit\" value=\"Logout\"></td></tr>\n"+
            "</form></center>\n"

        );//println ends

    /*----- DATABASE OPERATIONS NECESSARY VARIABLES -----*/
    
        String url  = "jdbc:mysql://localhost/atp1db";
        String user = "root";
        String dpass= "j01911450873u";

        String dc  = "X"; //used for confirmation that data has been inserted 

        Connection con = null;

        //DB statement variable
        Statement stmt  = null;
        Statement stmt1 = null;
        Statement stmt2 = null;

	    
		
		//String f2Btn 	  = request.getParameter("form2");
		//String f3Btn 	  = request.getParameter("form3");
		
		// login form data receive
		//String loginBtn = request.getParameter("loginBtn");

		// logout button
		//String logoutBtn = request.getParameter("logout");

		if(addCartBtn != null){ //if add to cart is clicked 
			
			String prName 	= request.getParameter("prName");
			String prPrice 	= request.getParameter("prPrice");
			int incPrice	= Integer.parseInt(prPrice);
			String prId 	= request.getParameter("prId");
			int i = 1;

			String userId 	= (String)mySession.getAttribute("userId");
		
		    /*----- DATABASE OPERATIONS NECESSARY VARIABLES -----*/
    
	        //String url  = "jdbc:mysql://localhost/atp1db";
	        //String user = "root";
	        //String dpass= "j01911450873u";

	        //String dc  = "X"; //used for confirmation that data has been inserted 

	        //Connection con = null;

	        //DB statement variable
	        //Statement stmt  = null;
	        //Statement stmt1 = null;
	        //Statement stmt2 = null;

             /* UPDATE CART TABLE */


		    try{
		        
		        //register JDBC driver
		        Class.forName("com.mysql.jdbc.Driver");

		        // Open a connection
		        con = DriverManager.getConnection(url,user,dpass);
		        
		        //Execute SQL Query
		        stmt = con.createStatement();
		        String sql = "SELECT * FROM cart WHERE userId='"+userId+"' AND productId='"+prId+"'";

		        ResultSet rs = stmt.executeQuery(sql);

		        if(!rs.next()){

		        	//insert pid, uid, price, quantity=1 into cart
		        	stmt1 = con.createStatement();
			        String sqlx = 	"INSERT INTO cart(userId, productId, quantity, price)" + 
                     				"VALUES('"+userId+"', '"+prId+"', '"+i+"', '"+prPrice+"')";
			        stmt1.executeUpdate(sqlx);
			        stmt1.close();

		        }else{

		        	//update cart table - quantity+1, price+price-from-form
		        	int cartId = rs.getInt("cartId");
		            int	userIdInt  = rs.getInt("userId");
		            int productId = rs.getInt("productId");
		            int quantity = rs.getInt("quantity");
		            int cPrice = rs.getInt("price");
		            
		            cPrice = cPrice+incPrice;
		            quantity = quantity+1;

		            stmt1 = con.createStatement();
			        String sqlx = "UPDATE cart SET price='"+cPrice+"', quantity='"+quantity+"' WHERE userId='"+userIdInt+"' AND productId='"+prId+"'";
			        stmt1.executeUpdate(sqlx);
			        stmt1.close();
		        }
		        
		    
		        //clean up environment
		        rs.close();
		        stmt.close();
		        con.close();

		      } catch(SQLException se){

		        dc = se.getMessage();
		        out.println(dc);
		        //Handle errors for JDBC
		         se.printStackTrace();

		      } catch(Exception e){
		        System.out.println(e.getMessage());
		        //Handle errors for Class.forName
		         e.printStackTrace();

		      }finally{

		        //finally block used to close resources
		         try{
		            if(stmt!=null)
		               stmt.close();
		         }catch(SQLException se2){
		         }// nothing we can do
		         try{
		            if(con!=null)
		            con.close();
		         }catch(SQLException se){
		            se.printStackTrace();
		         }//end finally try

		      }//end try

			response.sendRedirect("/jakaria-ses/home");
			/* --------------------------- */

		//IF ENDS BELLOW
		}else if (delBtn!=null) {
			
			String cartId 	= request.getParameter("cartId");

		    /*----- DATABASE OPERATIONS NECESSARY VARIABLES -----*/
    
	        //String url  = "jdbc:mysql://localhost/atp1db";
	        //String user = "root";
	        //String dpass= "j01911450873u";

	        //String dc  = "X"; //used for confirmation that data has been inserted 

	       // Connection con = null;

	        //DB statement variable
	        //Statement stmt  = null;
	        //Statement stmt1 = null;
	        //Statement stmt2 = null;

             /* UPDATE CART TABLE */


		    try{
		        
		        //register JDBC driver
		        Class.forName("com.mysql.jdbc.Driver");

		        // Open a connection
		        con = DriverManager.getConnection(url,user,dpass);
		        
		        //Execute SQL Query
		        stmt = con.createStatement();
		        String sql = "DELETE FROM cart WHERE cartId='" + cartId + "'";

		        stmt.executeUpdate(sql);     
		    
		        //clean up environment
		        //rs.close();
		        stmt.close();
		        con.close();

		      } catch(SQLException se){

		        dc = se.getMessage();
		        out.println(dc);
		        //Handle errors for JDBC
		         se.printStackTrace();

		      } catch(Exception e){
		        System.out.println(e.getMessage());
		        //Handle errors for Class.forName
		         e.printStackTrace();

		      }finally{

		        //finally block used to close resources
		         try{
		            if(stmt!=null)
		               stmt.close();
		         }catch(SQLException se2){
		         }// nothing we can do
		         try{
		            if(con!=null)
		            con.close();
		         }catch(SQLException se){
		            se.printStackTrace();
		         }//end finally try

		      }//end try

			response.sendRedirect("/jakaria-ses/home");
			/* --------------------------- */

		//IF ENDS BELLOW
		}else if (checkBtn!=null) {

			String userId 	= (String)mySession.getAttribute("userId");
			//out.println(userId);

		    try{
        
		        //register JDBC driver
		        Class.forName("com.mysql.jdbc.Driver");

		        // Open a connection
		        con = DriverManager.getConnection(url,user,dpass);
		        
		        //Execute SQL Query
		        stmt = con.createStatement();
		        String sqlx = "SELECT cartId, userId, productId, quantity, price FROM cart WHERE userId='"+userId+"'";

		        ResultSet rsx = stmt.executeQuery(sqlx);


		        out.println("<br /><center><table border=\"1px\"><br /><tr><th>Product Name</th><th>Quantity</th><th>Price</th></tr>");    
		        
		        while(rsx.next()){
		            //Retrieve by column name
		            int cartId = rsx.getInt("cartId");
		            int userIdx  = rsx.getInt("userId");
		            int productId = rsx.getInt("productId");
		            int quantity = rsx.getInt("quantity");
		            int cPrice = rsx.getInt("price");
		            //dc ="Data Extrated<br/>";
		            //out.println(dc);
		            totalPrice = totalPrice+cPrice;
		            stmt1 = con.createStatement();
		            String sqlPN = "SELECT pid, pname FROM product WHERE pid='"+productId+"'";

		            //out.println(sqlPN);

		            ResultSet pn = stmt1.executeQuery(sqlPN);
		            String proName = "";
		            int proId = 0;
		            if(pn.next()) {
		                proName = pn.getString("pname");
		                proId = pn.getInt("pid");   
		            
		            //Display values
		            
		            out.println(
		                "<form action=\"ca\" method=\"post\">\n" +
		                "<tr>" +
		                "<td>" + proName + "<input type=\"hidden\" name=\"proId\" value=" + proId + "></td>" + 
		                "<td>" + quantity + "</td>" +
		                "<td>" + cPrice + "</td>" +
		                "</tr>\n" +
		                "</form>\n"
		                );
		            }
		            //out.println(", Price " + dc + "<br>");
		            stmt1.close();
		         }
		        out.println("<br /><tr><td></td><td>Total Price</td><td>" + totalPrice + "</td></tr><br /></table><br />" +
		        	"<form action=\"ca\" method=\"post\"><tr>Confirm Checkout & Back to Home - <input type=\"submit\" name=\"checkOutConfirm\" value=\"Confirm\" ></tr></form><br />" +
		        	"</center></body></html>"); 
		    
		        //clean up environment
		        rsx.close();
		        stmt.close();
		        con.close();

		      } catch(SQLException se){

		        dc = se.getMessage();
		        out.println(dc);
		        //Handle errors for JDBC
		         se.printStackTrace();

		      } catch(Exception e){
		        System.out.println(e.getMessage());
		        //Handle errors for Class.forName
		         e.printStackTrace();

		      }finally{

		        //finally block used to close resources
		         try{
		            if(stmt!=null)
		               stmt.close();
		         }catch(SQLException se2){
		         }// nothing we can do
		         try{
		            if(con!=null)
		            con.close();
		         }catch(SQLException se){
		            se.printStackTrace();
		         }//end finally try

		      }//end try
		//check out if ends			   
		}else if (confirmBtn!=null) {

			 try{
		        
		        String userId 	= (String)mySession.getAttribute("userId");

		        //register JDBC driver
		        Class.forName("com.mysql.jdbc.Driver");

		        // Open a connection
		        con = DriverManager.getConnection(url,user,dpass);
		        
		        //Execute SQL Query
		        stmt = con.createStatement();
		        String sql = "DELETE FROM cart WHERE userId='" + userId + "'";

		        stmt.executeUpdate(sql);     
		    
		        //clean up environment
		        //rs.close();
		        stmt.close();
		        con.close();

		      } catch(SQLException se){

		        dc = se.getMessage();
		        out.println(dc);
		        //Handle errors for JDBC
		         se.printStackTrace();

		      } catch(Exception e){
		        System.out.println(e.getMessage());
		        //Handle errors for Class.forName
		         e.printStackTrace();

		      }finally{

		        //finally block used to close resources
		         try{
		            if(stmt!=null)
		               stmt.close();
		         }catch(SQLException se2){
		         }// nothing we can do
		         try{
		            if(con!=null)
		            con.close();
		         }catch(SQLException se){
		            se.printStackTrace();
		         }//end finally try

		      }//end try

			response.sendRedirect("/jakaria-ses/home");
			/* --------------------------- */
		}



        }//doPost ends 

   }//class ends