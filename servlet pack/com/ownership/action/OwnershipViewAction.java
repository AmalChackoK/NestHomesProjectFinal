package com.ownership.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;
import com.bean.Ownerbean;
import com.bean.Ownershipbean;
import com.dao.FlatDAO;
import com.dao.OwnerDAO;
import com.dao.OwnershipDAO;

public class OwnershipViewAction extends HttpServlet{

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
				+"<link rel='stylesheet' type='text/css' href='./css/owner.css'>"
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
	      	 
	      	     +" <form action='' method='post'>"
	      	    +"<div class='div-3'>"
	      	           +" <h4>Ownership</h4>"
	      	            
	      	                +"Owner"
	      	                +"<select  name='owner'>"
	      	                +"<option >select owner</option>");
	ArrayList<Ownerbean>  owner=null;    	                
	      try{
	    	  owner=OwnerDAO.listAllowner();
	      }catch (SQLException sql) {
			sql.printStackTrace();
		}
	     for(Ownerbean ob:owner){
	    	 out.print("<option value='"+ob.getOid()+"'>"+ob.getOname()+"</option>");
	     }
	      	                
	      	                
	      	        out.print( "</select><br>"
	      	        			+"Flat"
	      	                +"<select  name='flat' >"
	      	                +"<option >select flat</option>");
	      	  ArrayList<Flatbean>flats=null;
	      	  try{
	      		flats=FlatDAO.listAllflats();
	      	  }catch(SQLException e){
	      		  e.printStackTrace();
	      	  }
	      	        
	      	    for(Flatbean fb:flats){
	      	    	out.print("<option value='"+fb.getFid()+"'>"+fb.getFnumber()+""+fb.getFtower()+"</option>");
	      	    }
	      	        
	      	              out.print(   "</select>"
	      	           + "<input type='submit' value='save'/>"
	      	        +"</div>"
	      	        
	      	   
					+"</form>"
	      	   	

		
				+"<Div class=table>"
					+"<table>"
					+"<tr>"
					+"<th>Sl.No</th>"
					+"<th>Name</th>"
					+"<th>Flatnumber</th>"
					+"<th>tower</th>"
					+"<th>Edit</th>"
					+"<th>Delete</th>"
					+"</tr>");
		int i=1;
		ArrayList<Ownershipbean> user=null;
		
		try{

			
		//Userbean userbean = new Userbean();
			user=OwnershipDAO.listAllownership();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		for(Ownershipbean obj:user){
			out.print("<tr>"
					+"<td>"+i+"</td>"
					+ "<td>"+obj.getOname()+"</td>"
					+ "<td>"+obj.getFnumber()+"</td>"
					+ "<td>"+obj.getFtower()+"</td>"
					+ "<td><a href='ownershipedit?osid="+obj.getOsid()+"'>Edit</a></td>"
					+ "<td><a href='ownershipdelete?osid="+obj.getOsid()+"'>Delete</a></td>"
					+"</tr>");
			i++;
		}
		out.print("</table>"
				+"</div>"
				+"</body>"
				+"</html>");
	}			


	}

