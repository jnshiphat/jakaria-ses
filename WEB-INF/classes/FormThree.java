// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//Extend HttpServlet class
public class FormThree extends HttpServlet{
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Set response content type
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String title = "Fill up The Third Form";
        
        String docType =  "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";

        String erMe;
        HttpSession erSession = request.getSession();       
        String errorMessage = (String)erSession.getAttribute("errMss");
        
        if(errorMessage == null){
            erMe = " ";
        }else{
            erMe = errorMessage;
        }
        
        //erSession.invalidate();
        erSession.removeAttribute("errMss");


        out.println(docType+
            "<html>\n"+
            "<head><title>"+title+"</title></head>\n"+
            "<body>\n"+
            "<h1 align=\"center\">"+title+"</h1>\n"+
            
            "<b>"+erMe+"</b>\n"+

            "<form action='register' method='post'>\n"+
                "<table>\n"+
                        "<tr><td>Gender</td><td><input name=\"gender\" type=\"radio\" value=\"male\">Male</td></tr>\n"+
                                "<tr><td></td><td><input name=\"gender\" type=\"radio\" value=\"female\">Female</td></tr>\n"+
                        "<tr><td>Password </td><td><input name=\"password\" type=\"password\"></td></tr>\n"+
                        "<tr><td>Confirm Password </td><td><input name=\"password2\" type=\"password\"></td></tr>\n"+
                        "<tr><td>Human Check:</td><td><input name=\"captchaCheck\" type=\"text\"></td></tr>\n"+
                        "<tr><td>            </td><td><img src=\"http://localhost:8080/jakaria-ses/st\" border=\"0\" alt=\"Captcha image\"/></td></tr>\n"+
                        "<tr><td><input name=\"form3\" type=\"submit\" value=\"Next\"></td></tr>\n"+
                        
                "</table>\n"+                   
            "</form>\n"+
            
            
            
            "</body></html>"
        );//println ends

    } //doPost ends
    
} //class ends