import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.util.*;

public class ShadowedText extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {


/* ----- Changes for random string starts ----- */

  char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
  StringBuilder sb = new StringBuilder();
  Random random = new Random();
  for (int i = 0; i < 10; i++) {
      char c = chars[random.nextInt(chars.length)];
      sb.append(c);
  }
  String outputCaptcha = sb.toString();


/* ----- Changes for random string ends   ----- */

	String message = outputCaptcha;
  String fontName =  "Serif";
  int fontSize  = 30;

  HttpSession cSession = request.getSession();       
  cSession.setAttribute("captcha", message);

      response.setContentType("image/jpeg");
      MessageImage.writeJPEG
        (MessageImage.makeMessageImage(message,
                                       fontName,
                                       fontSize),
         response.getOutputStream());
  }//doGet ends

}//class ends
