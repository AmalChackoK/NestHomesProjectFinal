package com.gasusage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Flatbean;
import com.bean.Usagebean;

import com.dao.FlatDAO;
import com.dao.GasusageDAO;

public class UsageViewAction extends HttpServlet{

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
+" <a href='adminpage'>Home</a>"
	+"<div class='dropdown'>"
    +"<button class='dropbtn'>Settings"
    +"<i class='fa fa-caret-down'></i>"
    +"</button>"
+"<div class='dropdown-content'>"
	+"<a href='Userview'>User</a>"
	+"<a href='flateview'>Flats</a>"
	+"<a href='dhcview'>Daily Help Category</a>"
	+"<a href='rateview'>Gas Unit Rate</a>"
+"</div>"
+"</div>"

+"<div class='dropdown'>"
	+"<button class='dropbtn'>Owners"
	+" <i class='fa fa-caret-down'></i>"
	+"</button>"
+"<div class='dropdown-content'>"
	+"<a href='ownerview'>Owner</a>"
	+"<a href='ownershipview'>Ownership</a>"
+"</div>"
+"</div>"

	+"<div class='dropdown'>"
		+ "<button class='dropbtn'>Tenants"
		+  "<i class='fa fa-caret-down'></i>"
		+"</button>"
	+"<div class='dropdown-content'>"
		+ "<a href='tenantview'>Tenant</a>"
		+"<a href='rentalview'>Rental</a>"
	+"</div>"
	+"</div>"

		+"<a href='facilityview'>Facility Manager</a>"
		+"<a href='helpview'>Daily Help</a>"

	+"<div class='dropdown'>"
		+ "<button class='dropbtn'>Society Dues"
		+  "<i class='fa fa-caret-down'></i>"
		+"</button>"
	+"<div class='dropdown-content'>"
		+"<a href='usageview'>Monthly Gas Usage</a>"
		+"<a href='payview'>Payment</a>"
	+"</div>"
	+ "</div>"
		+"<a href='logout.html' class='split'>Logout</a>"
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
	      	        
	      	   
					+"</form>"
	      	   	

		
				+"<Div class=table>"
					+"<table>"
					+"<tr>"
					+"<th>Sl.No</th>"
					+"<th>number</th>"
					+"<th>tower</th>"
					+"<th>unit</th>"
					
					+"<th>Edit</th>"
					+"<th>Delete</th>"
					+"</tr>");
		int i=1;
		ArrayList<Usagebean> user=null;
		
		try{

			
		//Userbean userbean = new Userbean();
			user=GasusageDAO.listAllusage();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		for(Usagebean obj:user){
			out.print("<tr>"
					+"<td>"+i+"</td>"
					+ "<td>"+obj.getFnumber()+"</td>"
					+ "<td>"+obj.getFtower()+"</td>"
					+ "<td>"+obj.getUsage()+"</td>"
					
					+ "<td><a href='usageedit?guid="+obj.getGuid()+"'>Edit</a></td>"
					+ "<td><a href='usagedelete?guid="+obj.getGuid()+"'>Delete</a></td>"
					+"</tr>");
			i++;
		}
		out.print("</table>"
				+"</div>"
				+"</body>"
				+"</html>");
	}			


	}


