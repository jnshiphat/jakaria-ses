// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

//Extend HttpServlet class
public class AllSessionData extends HttpServlet{
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        //Set response content type
        response.setContentType("text/html");
        
        /* Getting all session data */

        HttpSession mySession = request.getSession();

        String fname = (String)mySession.getAttribute("fName");
        String lname = (String)mySession.getAttribute("lName");
        String bDay = (String)mySession.getAttribute("day");
        String bMonth = (String)mySession.getAttribute("month");
        String bYear = (String)mySession.getAttribute("year");
        String mPhone = (String)mySession.getAttribute("phone");
        String mEmail = (String)mySession.getAttribute("email");
        String mGender = (String)mySession.getAttribute("gender");
        String pass = (String)mySession.getAttribute("pass");
        String visit= "0";
        
        /* ------------------------ */
        
        /* Database Operations */

        String url  = "jdbc:mysql://localhost/atp1db";
        String user = "root";
        String dpass= "j01911450873u";

        String dc  = "X"; //used for confirmation that data has been inserted 

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
        dc = "INSERT INTO user(id, fname, lname, bday, bmonth, byear, phone, email, gender, pass, visit) VALUES ('', '"+fname+"', '"+lname+"', '"+bDay+"', '"+bMonth+"', '"+bYear+"', '"+mPhone+"', '"+mEmail+"', '"+mGender+"', '"+pass+"', '"+visit+"')";
        stmt = con.createStatement();
        String sql = "INSERT INTO user(fname, lname, bday, bmonth, byear, phone, email, gender, pass, visit) "+ 
                     "VALUES('"+fname+"', '"+lname+"', '"+bDay+"', '"+bMonth+"', '"+bYear+"', '"+mPhone+"', '"+mEmail+"', '"+mGender+"', '"+pass+"', '"+visit+"')";
        stmt.executeUpdate(sql);
        dc = "Data Inserted";

        //clean up environment
        //rs.close();
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
        String title = "Showing All Session Data and Inserting It in Database";
        
        String docType =  "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

      // Invalidate the session
        mySession.invalidate();

        out.println(docType+
            "<html>\n"+
            "<head><title>"+title+"</title></head>\n"+
            "<body>\n"+
                "<h1 align=\"center\">"+title+"</h1>\n"+
                "<table>\n"+
                        "<tr><td>First Name: </td><td>"+fname+"</td></tr>\n"+
                        "<tr><td>Last Name:  </td><td>"+lname+"</td></tr>\n"+
                        "<tr><td>DOB:        </td><td>"+bDay+"-"+bMonth+"-"+bYear+"</td></tr>\n"+
                        "<tr><td>Phone:      </td><td>"+mPhone+"</td></tr>\n"+
                        "<tr><td>Email:      </td><td>"+mEmail+"</td></tr>\n"+
                        "<tr><td>Gender:     </td><td>"+mGender+"</td></tr>\n"+
                        "<tr><td>Password:     </td><td>"+pass+"</td></tr>\n"+
                        "<tr><td>confirmation:     </td><td>"+dc+"</td></tr>\n"+
                "</table>\n"+
                "<h3 align=\"center\"><a href=\"/jakaria-ses/\">Click To Go To Register Page/Login</h3>\n"+      
            "</body></html>"
        );//println ends

    } //doPost ends
    
} //class ends