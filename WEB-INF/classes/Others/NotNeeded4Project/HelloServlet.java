import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*; */

public class HelloServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";

      	out.println(docType + 
      		"<html>\n" +
      		"<head><title>Hello</title></head>\n" +
      		"<body bgcolor=\"#fdf5e6\">\n" +
      		"<h1>Hello</h1>\n" +
      		"</body></html>");		
	}
}