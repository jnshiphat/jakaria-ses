// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class Login extends HttpServlet{
        
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Set response content type
        response.setContentType("text/html");
        
        //variables for storing retrived data from DB
        String emailDb;
        String passDb;

        String dc  = "X"; //printing the errors 

        /* Getting all session data */

        HttpSession mySession = request.getSession();

        String lEmailA  = (String)mySession.getAttribute("lEmail");
        String lPassW   = (String)mySession.getAttribute("lPass");
                
        /* ------------------------ */
        
        /* Database Operations */

        String url  = "jdbc:mysql://localhost/atp1db";
        String user = "root";
        String dpass= "j01911450873u";

        //String dc  = "X"; //used for confirmation that data has been inserted 

        Connection con = null;

        //DB statement variable
        Statement stmt = null;

        try{
        
        //register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
        con = DriverManager.getConnection(url,user,dpass);
        //connectedR = "Connected Successfully!";

        //Execute SQL Query
        //dc = "SELECT email, pass FROM user WHERE email='"+lEmailA+"' AND pass='"+lPassW+"'";
        stmt = con.createStatement();
        String sql = "SELECT id, email, pass FROM user WHERE email='"+lEmailA+"' AND pass='"+lPassW+"'";
        
        ResultSet rs = stmt.executeQuery(sql);

        //Extrat data from resultset and checking if there is any data
        if (!rs.next()) {    
            mySession.setAttribute("lgnERR", "Email of Password is Wrong!");
            response.sendRedirect("/jakaria-ses/");
        }else{
            emailDb   = rs.getString("email");
            passDb    = rs.getString("pass");
            dc ="Logged In";
            mySession.setAttribute("loginVarified", emailDb);
            response.sendRedirect("/jakaria-ses/home"); //re-direct to product home page
        }    
        
    
        //clean up environment
        rs.close();
        stmt.close();
        con.close();

      } catch(SQLException se){

        dc = se.getMessage();
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

        /* ------------------------ */
      


    PrintWriter out = response.getWriter();
    String title = "Login Page";
    
    String docType =  "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

  // Invalidate the session
  // mySession.invalidate();

    out.println(docType+
        "<html>\n"+
        "<head><title>"+title+"</title></head>\n"+
        "<body>\n"+
            "<h1 align=\"center\">"+title+"</h1>\n"+
            "<table>\n"+
                    "<tr><td>confirmation:</td><td>"+dc+"</td></tr>\n"+
            "</table>\n"+
                  
        "</body></html>"
    );//println ends



    } //doPost ends
    
} //class ends