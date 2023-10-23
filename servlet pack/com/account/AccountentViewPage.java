package com.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountentViewPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>"
+"<html>"
+"<head>"
+"<meta charset='ISO-8859-1'>"
+"<title>Insert title here</title>"
 +"<link rel='stylesheet' type='text/css' href='css/user.css'>"
+	"<link rel='stylesheet' type='text/css' href='css/admin.css'>"
+"</head>"
+"<body>");

	out.print(" <div class='navbar'>"
        +" <a href='account.html'>Home</a>"
        
          +"<div class='dropdown'>"
            +"<button class='dropbtn'>Society Dues"
             +" <i class='fa fa-caret-down'></i>"
            +"</button>"
            +"<div class='dropdown-content'>"
              +"<a href='accview'>Monthly Gas Usage</a>"
             +" <a href='accpay'>Payment</a>"
            +"</div>"
          +"</div>"
          +"<a href='#' class='split'>Logout</a>"
 +     "</div>");
 String name=request.getParameter("name");
          out.print("<h2>welcome"+name+"</h2>");
          out.print("</body>"
+"</html>");
	
	
	}
}
