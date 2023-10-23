package com.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;
import com.bean.Socitybeen;
import com.dao.FlatDAO;
import com.dao.SocityDAO;

public class PaymentViewAction extends HttpServlet{

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
	      		+"<div class='div-2'>"
   				+"<form method='post' action='payadd' name='dues'>"
	                +"<h2>Payment</h2>"
	                +"<div class='date'>"
	                    +"<label>Date</label>"
	                    +"<input  type='date' name='date'/>"
	                +"</div>"
	                +"<label>Category</label>"
	                +"<select id='select' name='category'>");
	
	ArrayList<Socitybeen> socitybeen=null;
	try{
		socitybeen=SocityDAO.listAllcat();
	}catch(Exception e){
		e.printStackTrace();
	}
	for(Socitybeen sdc:socitybeen){
		out.print("<option value='"+sdc.getScid()+"'>"+sdc.getSccat()+"</option>");
	}
	out.print("</select><br>"
            +"<label>Flat</label>"
            +"<select id='select' name='flate'>");

	ArrayList<Flatbean> flat=null;
	try{
		flat=FlatDAO.listAllflats();
	}catch(Exception e){
		e.printStackTrace();
	}
	for(Flatbean fl:flat){
		out.print("<option value='"+fl.getFid()+"'>"+fl.getFnumber()+" "+fl.getFtower()+"</option>");
	}
	out.print("</select><br>"
            +"<label>Total dues</label>"
            +"<input type='button' name='button' class='button' value='check dues' onclick='checkdues()' />"
            +"<input type='number' id='amount' name='amount' readonly='true' /><br>"
           
            +"<label>Paid Amount</label>"
            +"<input type='text' name='paid'/><br>"
            +"<input type='submit' name='button' class='button' value='Save' />"
            +"</form>"
          +"</div>"
        +"</body>"
    +"</html>");
	                    
	              	               
   					
}
}
	

