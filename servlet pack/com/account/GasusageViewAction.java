package com.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;
import com.dao.FlatDAO;

public class GasusageViewAction extends HttpServlet{

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
				+"<link rel='stylesheet' type='text/css' href='./css/dailyhelp.css'>"
				+"<link rel='stylesheet' type='text/css' href='./css/admin.css'>"
				+"</head>"
				+"<body>"
				
				
+"<div class='navbar'>"

				+" <a href='account.html'>Home</a>"
				+"<div class='dropdown'>"
	            +"<button class='dropbtn'>Society Dues"
	              +"<i class='fa fa-caret-down'></i>"
	            +"</button>"
	            +"<div class='dropdown-content'>"
	              +"<a href='accview'>Monthly Gas Usage</a>"
	              +"<a href='accpay'>Payment</a>"
	            +"</div>"
	          +"</div>"
	          +"<a href='#' class='split'>Logout</a>"
	      +"</div>"
	      	 
	      	     +"<form action='usage' method='post'>"
	      	    +"<div class='div-2'>"
	      	           +"<h4>gas usage</h4>"
	      	            
	      	                +"flate"
	      	                +"<select  name='flate'>"
	      	                +"<option >select flate</option>");
	ArrayList<Flatbean>  category=null;    	                
	      try{
	    	  category=FlatDAO.listAllflats();
	      }catch (SQLException sql) {
			sql.printStackTrace();
		}
	     for(Flatbean ob:category){
	    	 out.print("<option value='"+ob.getFid()+"'>"+ob.getFnumber()+""+ob.getFtower()+"</option>");
	     }
	      	                
	      	                
	      	        out.print( "</select><br>"
	      	        		+" unit<input type='text' name='unit'><br>"
	      	               
	      	             +"<input type='submit' id='ownerbutten' value='save'/>"
	      	        +"</div>"
	      	        
	      	   
					+"</form>");
	      	        
	      	      out.print("</table>"
	      				+"</div>"
	      				+"</body>"
	      				+"</html>");
	}
	
}
