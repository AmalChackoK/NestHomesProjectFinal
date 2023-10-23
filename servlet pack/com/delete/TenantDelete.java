package com.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Tenantbean;

import com.dao.TenantDAO;


public class TenantDelete  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		 ArrayList<Tenantbean> tenantdelete=null;
		 String tenantId=request.getParameter("tid");
		 int tid=Integer.parseInt(tenantId);
		 try {
			 tenantdelete=TenantDAO.fetchtenantById(tid);
			
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
	         		+"<form action='tenantdeleteaction' method='Post'>"
	         		+"<div class='div-2'>"
	         			+"<h3>Owner Details</h3> <hr>");
	         			for(Tenantbean ob:tenantdelete){
	              			out.print("<input type='hidden' name='tid' value='"+ob.getTid()+"'/>"
	              			+" name<br><input type='text' name='name' value='"+ob.getTname()+"' readonly/><br>"
	              		 + "Address<br><textarea  name='address'  rows='4' cols='50'readonly/>"+ob.getTaddress()+"</textarea><br>"
	              			+ "contact<br><input type='text' name='type' value='"+ob.getTphone()+"' readonly/><br>"
	              			+" <input type='submit' value='save'id='ownerbutten'>");
	              		}
					out.print("</div>"
	         	   +"</form>"	
	   
			
					+"<div class='table'>"
						+"<table>"
						+"<tr>"
						+"<th>Sl.No</th>"
						+"<th>Name</th>"
						+ "<th>Address</th>"
						+"<th>Contact</th>"
						+"<th>Edit</th>"
						+"<th>Delete</th>"
						+"</tr>");
			int i=1;
			ArrayList<Tenantbean> user=null;
			
			try{

				
			//Userbean userbean = new Userbean();
				user=TenantDAO.listAlltenant();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			for(Tenantbean ob:user){
				out.print("<tr>"
						+"<td>"+i+"</td>"
						+ "<td>"+ob.getTname()+"</td>"
						+ "<td>"+ob.getTaddress()+"</td>"
						+ "<td>"+ob.getTphone()+"</td>"
						+ "<td><a href='tenantedit?tid="+ob.getTid()+"'>Edit</a></td>"
						+ "<td><a href='tenantdelete?tid="+ob.getTid()+"'>Delete</a></td>"
						+"</tr>");
				i++;
			}
			out.print("</table>"
					+"</div>"
					+"</body>"
					+"</html>");
		}			

}
