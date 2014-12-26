// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//Extend HttpServlet class
public class VarifyForm extends HttpServlet{
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //Set response content type
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String title = "Varify Form Data";
        
        String docType =  "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	    
		String f1Btn = request.getParameter("form1");
		String f2Btn = request.getParameter("form2");
		String f3Btn = request.getParameter("form3");
		
		// login form data receive
		String loginBtn = request.getParameter("loginBtn");

		// logout button
		String logoutBtn = request.getParameter("logout");

		// if-else for checking which form is posted and getting its data and taking it to session
		
		if(f1Btn != null){ //data from form one 

			String fName = request.getParameter("f_name");
			String lName = request.getParameter("l_name");
			String day   = request.getParameter("day");
			String month = request.getParameter("month");
			String year  = request.getParameter("year");
			
			if((fName == null) || (fName.trim().equals(""))){
				
				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Something Wrong With Your First Name Field!");
				
				response.sendRedirect("/jakaria-ses/");
				
			}else if((lName == null) || (lName.trim().equals(""))){
				
				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Something Wrong With Your Last Name Field!");
				
				response.sendRedirect("/jakaria-ses/f1");
			
			}else if((day == null) || (day.trim().equals(""))){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Something Wrong With Your Day Field of Death of Birth!");
				
				response.sendRedirect("/jakaria-ses/f1");
			
			}else if((month == null) || (month.trim().equals(""))){
				
				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Something Wrong With Your Month Field of Death of Birth!");
				
				response.sendRedirect("/jakaria-ses/f1");
				
			}else if((year == null) || (year.trim().equals(""))){
				
				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Something Wrong With Your Years Field of Death of Birth!");
				
				response.sendRedirect("/jakaria-ses/f1");
				
			}else {

				HttpSession mySession = request.getSession();
				        
				mySession.setAttribute("fName", fName);
		        mySession.setAttribute("lName", lName);
		        mySession.setAttribute("day", day);
		        mySession.setAttribute("month", month);
		        mySession.setAttribute("year", year);
				
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f2");
                dispatcher.forward(request,response);

			}

//f1btn if-ends bellow			
		}else if(f2Btn != null){ //data from second form

			String phone    = request.getParameter("phone");
			String email    = request.getParameter("email");
			int atPosition  = email.indexOf("@");
			int dotPosition = email.lastIndexOf(".");

			if((phone == null) || (phone.trim().equals(""))){

			    HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "You Have Not Provided Phone Number!");
				
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f2");
                dispatcher.forward(request,response);

			}else if((email == null) || (email.trim().equals(""))){
				
				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "You Have Not Provided Email Address!");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f2");
                dispatcher.forward(request,response);

			}else if (atPosition<1 || dotPosition-atPosition<2){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Email Address is Invalid!");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f2");
                dispatcher.forward(request,response);

			}else {

				HttpSession mySession = request.getSession();
				        
				mySession.setAttribute("phone", phone);
        		mySession.setAttribute("email", email);
				
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f3");
                dispatcher.forward(request,response);

			}

//f2btn if-ends bellow			
		}else if(f3Btn != null){ //data from form three

			String gender   = request.getParameter("gender");
			String pass     = request.getParameter("password");
			String cpass    = request.getParameter("password2");

			String myCaptcha= request.getParameter("captchaCheck");

			HttpSession cSession = request.getSession();
			String theCaptcha 	 = (String)cSession.getAttribute("captcha"); 

			if((gender == null) || (gender.trim().equals(""))){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "You Have Not Provided Gender!");
				
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f3");
                dispatcher.forward(request,response);

			}else if((pass == null) || (pass.trim().equals(""))){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "You Have Not Provided Password!");
				
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f3");
                dispatcher.forward(request,response);

			}else if(!pass.equals(cpass)){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Password Didn't Match!");
				
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f3");
                dispatcher.forward(request,response);

			}else if (!theCaptcha.equals(myCaptcha)){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("errMss", "Wrong In Captcha!");
				
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f3");
                dispatcher.forward(request,response);
				  
			} else {

				       
		        HttpSession mySession = request.getSession();
		        
   		        mySession.setAttribute("gender", gender);
		        mySession.setAttribute("pass", pass);
		        
				//response.sendRedirect("/jakaria-ses/f2");

				ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/all");
                dispatcher.forward(request,response);

			}
//f3btn if-ends bellow
		}else if(loginBtn != null){ //data from login form

			String loginEmail   = request.getParameter("email");
			int atPosition  	= loginEmail.indexOf("@");
			int dotPosition 	= loginEmail.lastIndexOf(".");

			String loginPass     = request.getParameter("password");

			if((loginEmail == null) || (loginEmail.trim().equals(""))){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("lgnERR", "You Have Not Provided Email Address!");
				
				response.sendRedirect("/jakaria-ses/");

				/*ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f3");
                dispatcher.forward(request,response);*/

			}else if (atPosition<1 || dotPosition-atPosition<2){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("lgnERR", "Email Address is Invalid!");

				response.sendRedirect("/jakaria-ses/");

				/*ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f2");
                dispatcher.forward(request,response);*/

			}else if((loginPass == null) || (loginPass.trim().equals(""))){

				HttpSession erSession = request.getSession();
				erSession.setAttribute("lgnERR", "You Have Not Provided Password!");
				
				response.sendRedirect("/jakaria-ses/");

				/*ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/f3");
                dispatcher.forward(request,response);*/

			}else {
				       
		        HttpSession mySession = request.getSession();
		        
   		       	mySession.setAttribute("lEmail", loginEmail);
		        mySession.setAttribute("lPass", loginPass);
		        
				response.sendRedirect("/jakaria-ses/login");

				
				/*ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/login");
                dispatcher.forward(request,response);*/

			}
//loginBtn if-ends bellow
		}else if(logoutBtn != null){ //for logout
			HttpSession mySession = request.getSession();
			mySession.invalidate();
			response.sendRedirect("/jakaria-ses/");
		}
        
    }//doPost ends
    
}//class ends