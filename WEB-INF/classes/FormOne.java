// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//Extend HttpServlet class
public class FormOne extends HttpServlet{
        
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Set response content type
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String title = "Register or Login";
        
        String docType =  "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
        
    	String erMe;
        String erLg;
    	HttpSession erSession = request.getSession();	    
    	String errorMessage = (String)erSession.getAttribute("errMss");
        String loginError   = (String)erSession.getAttribute("lgnERR");
    	
    	if(errorMessage == null){
    		erMe = " ";
    	}else{
    		erMe = errorMessage;
    	}

        if(loginError == null){
            erLg = " ";
        }else{
            erLg = loginError;
        }
    	
    	//erSession.invalidate();
        erSession.removeAttribute("errMss");
        erSession.removeAttribute("lgnERR");

	
        out.println(docType+
            "<html>\n"+
            "<head><title>"+title+"</title></head>\n"+
            "<body>\n"+

            "<h1 align=\"center\">"+title+"</h1>\n"+
            
            "<div style=\"width: 27%; float: left;\" id=\"registerForm\">\n"+

                "<h3 align=\"center\">Register Here (3 step)</h3>\n"+

    	        "<b>"+erMe+"</b>\n"+
    	
                "<form action='register' method='post'>\n"+
                    "<table>\n"+
                            "<tr><td>First Name</td><td><input name=\"f_name\" type=\"text\"></td></tr>\n"+
                            
                            "<tr><td>Last Name</td><td><input name=\"l_name\" type=\"text\"></td></tr>\n"+
                            
                            "<tr><td>DOB</td><td><input name=\"day\" type=\"text\" placeholder=\"day\"></td></tr>\n"+
                                            "<tr><td></td><td><input name=\"month\" type=\"text\" placeholder=\"month\"></td></tr>\n"+
                                            "<tr><td></td><td><input name=\"year\" type=\"text\" placeholder=\"year\"></td></tr>\n"+
                            "<tr><td><input name=\"form1\" type=\"submit\" value=\"Next\"></td></tr>\n"+
                    "</table>\n"+                   
                "</form>\n"+

            "</div>\n"+    


            "<div style=\"width: 33%; float: left; padding: 10px; margin-top: 50px; margin-left:100px; border: 1px solid;\" id=\"loginForm\">\n"+

                "<h3 align=\"center\">If you are already registered - Login</h3>\n"+

                "<b>"+erLg+"</b>\n"+

                "<form action='register' method='post'>\n"+
                    "<table>\n"+
                        "<tr><td>Email </td><td><input name=\"email\" type=\"text\"></td></tr>\n"+
                        "<tr><td>Password </td><td><input name=\"password\" type=\"password\"></td></tr>\n"+
                        "<tr><td><input name=\"loginBtn\" type=\"submit\" value=\"Login\"></td></tr>\n"+
                    "</table>\n"+                   
                "</form>\n"+
            "</div>\n"+
            
            "</body></html>"
        );//println ends

    } //doPost ends
    
} //class ends