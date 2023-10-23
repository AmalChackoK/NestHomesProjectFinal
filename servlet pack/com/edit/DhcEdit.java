package com.edit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Dailyhelpcategorybean;

import com.dao.DailyhelpcategoryDAO;


public class DhcEdit extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 PrintWriter out=response.getWriter();
		 ArrayList<Dailyhelpcategorybean> dhcedite=null;
		
		 int dcid=Integer.parseInt(request.getParameter("dhcid"));
		 try {
			 dhcedite=DailyhelpcategoryDAO.fetchodcrById(dcid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 out.print("<!DOCTYPE html>"
					+"<html>"
					+"<head>"
					+"<meta charset='ISO-8859-1'>"
					+"<title>Insert title here</title>"
					+"<link rel='stylesheet' type='text/css' href='./css/user.css'>"
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
	          		+"<form action='dhceditaction' method='Post'>"
	          		+"<div class='div-2'>"
	          			+"<h3>flates</h3> <hr>");
	          			for(Dailyhelpcategorybean ob:dhcedite){
	               			out.print("<input type='hidden' name='dhcid' value='"+ob.getDcid()+"'/>"
	               			+" Number<br><input type='text' name='Category' value='"+ob.getDccategory()+"' />"
	               			
	               			+" <input type='submit' value='edit'>");
	               		}
					out.print("</div>"
	          	   +"</form>"	
	    
			
					+"<div class='table'>"
						+"<table>"
						+"<tr>"
						+"<th>Sl.No</th>"
						+"<th>Category</th>"
						
						+"<th>Edit</th>"
						+"<th>Delete</th>"
						+"</tr>");
			int i=1;
			ArrayList<Dailyhelpcategorybean> user=null;
			
			try{

				
			//Userbean userbean = new Userbean();
				user=DailyhelpcategoryDAO.listAlldcb();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			for(Dailyhelpcategorybean ob:user){
				out.print("<tr>"
						+"<td>"+i+"</td>"
						+ "<td>"+ob.getDccategory()+"</td>"
						+ "<td><a href='dhcedit?dhcid="+ob.getDcid()+"'>Edit</a></td>"
						+ "<td><a href='dhcdelete?dhcid="+ob.getDcid()+"'>Delete</a></td>"
						+"</tr>");
				i++;
			}
			out.print("</table>"
					+"</div>"
					+"</body>"
					+"</html>");
		}			

		
	}

