// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class ShopHome extends HttpServlet{
        
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    //Set response content type
    response.setContentType("text/html");
    
    HttpSession mySession = request.getSession();

    String variId  = (String)mySession.getAttribute("loginVarified"); //taking user email as session varification - will be used for db.
    
    if (variId == null) {
        response.sendRedirect("/jakaria-ses/");
    }


    PrintWriter out = response.getWriter();
    String title = "Product Page";


    
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

        int userIdIn = 0;


// -----------------------------------------------------



try{
        
        //register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
        con = DriverManager.getConnection(url,user,dpass);
        
        //Execute SQL Query
        //stmt = con.createStatement();
            
        /* ---- */ stmt = con.createStatement();
        /* ---- */ String sqlT = "SELECT * FROM user WHERE email='"+variId+"'";
        /* ---- */ ResultSet ui = stmt.executeQuery(sqlT);
        /* ---- */ if(ui.next()){userIdIn = ui.getInt("id");}

        mySession.setAttribute("userId", String.valueOf(userIdIn));

        ui.close();
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





// -----------------------------------------------------




    /*----- DATABASE OPERATIONS AND SHOWING PRODUCTS -----*/    

        try{
        
        //register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
        con = DriverManager.getConnection(url,user,dpass);
        
        //Execute SQL Query
        stmt = con.createStatement();
        String sql = "SELECT * FROM product";
        
        ResultSet rs = stmt.executeQuery(sql);        

        out.println("<center><table border=\"1px\"><tr><th>Product Name</th><th>Price</th><th>Action</th></tr>\n");    
        while(rs.next()){
            //Retrieve by column name
            int pId  = rs.getInt("pid");
            int pPrice = rs.getInt("price");
            String pName = rs.getString("pname");
            dc ="Data Extrated";
            //Display values

            out.println(
                "<form action=\"ca\" method=\"post\">\n"+
                "<tr>"+
                "<td>" + pName + "<input type=\"hidden\" name=\"prName\" value=" + pName + "></td>" + 
                "<td>" +pPrice+ "<input type=\"hidden\" name=\"prPrice\" value=" + pPrice + "></td>" +
                "<td><input type=\"submit\" name=\"add2cart\" value=\"Add to Cart\" > <input type=\"hidden\" name=\"prId\" value=" + pId + "></td>" +
                "</tr>\n" +
                "</form>\n"
                );
            
            //out.println(", Price " + dc + "<br>");
         }
        out.println("</table></center>"); 
    
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

    /*----- ---------------------------------------- -----*/ 

    /*----- DATABASE OPERATIONS AND SHOWING THE CART -----*/   

    String cartExist = (String)mySession.getAttribute("cartExist");

    //if (cartExist!=null) {

        try{
        
        //register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
        con = DriverManager.getConnection(url,user,dpass);
        
        //Execute SQL Query
        stmt = con.createStatement();
        String sqlx = "SELECT * FROM cart WHERE userId=(SELECT id FROM user WHERE email='"+variId+"')";

        ResultSet rsx = stmt.executeQuery(sqlx);

        out.println("<br /><center><table border=\"1px\"><br /><tr><th>Product Name</th><th>Quantity</th><th>Price</th><th>Action</th></tr>");    
        
        while(rsx.next()){
            //Retrieve by column name
            int cartId = rsx.getInt("cartId");
            int userIdx  = rsx.getInt("userId");
            int productId = rsx.getInt("productId");
            int quantity = rsx.getInt("quantity");
            int cPrice = rsx.getInt("price");
            //dc ="Data Extrated<br/>";
            //out.println(dc);
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
                "<td><input type=\"submit\" name=\"cartDel\" value=\"Delete\" > <input type=\"hidden\" name=\"cartId\" value=" + cartId + "></td>" +
                "</tr>\n" +
                "</form>\n"
                );
            }
            //out.println(", Price " + dc + "<br>");
            stmt1.close();
         }
        out.println("<br /><form action=\"ca\" method=\"post\"><tr>Cart Check Out - <input type=\"submit\" name=\"checkOut\" value=\"Check Out\" ></tr></form><br /></table></center>"); 
    
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

   // }//IF CARTEXIST BLOCK ENDS

    /*----- ----------------------------------------- -----*/
    out.println(
        
        "</body></html>"

        );//println ends



    } //doPost ends
    
} //class ends