package com.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;
import com.dao.FlatDAO;



public class FlatDelete extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 PrintWriter out=response.getWriter();
		 ArrayList<Flatbean> flatedite=null;
		 int fid=Integer.parseInt(request.getParameter("fid"));
			
		 try {
			 flatedite=FlatDAO.fetchflatById(fid);
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
	          		+"<form action='flateditaction' method='Post'>"
	          		+"<div class='div-2'>"
	          			+"<h3>flates</h3> <hr>");
	          			for(Flatbean ob:flatedite){
	               			out.print("<input type='hidden' name='fid' value='"+ob.getFid()+"'/>"
	               			+" Number<br><input type='text' name='Number' value='"+ob.getFnumber()+"' readonly/><br>"
	               			+"Tower<br><input type='text' name='Tower' value='"+ob.getFtower()+"'readonly/><br>"
	               			+ "type<br><input type='text' name='type' value='"+ob.getFtype()+"' readonly/><br>"
	               			+" <input type='submit' value='save'id='ownerbutten'>");
	               		}
					out.print("</div>"
	          	   +"</form>"	
	    
			
					+"<div class='table'>"
						+"<table>"
						+"<tr>"
						+"<th>Sl.No</th>"
						+"<th>usernamr</th>"
						+ "<th>password</th>"
						+"<th>type</th>"
						+"<th>Edit</th>"
						+"<th>Delete</th>"
						+"</tr>");
			int i=1;
			ArrayList<Flatbean> user=null;
			
			try{

				
			//Userbean userbean = new Userbean();
				user=FlatDAO.listAllflats();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			for(Flatbean ob:user){
				out.print("<tr>"
						+"<td>"+i+"</td>"
						+ "<td>"+ob.getFnumber()+"</td>"
						+ "<td>"+ob.getFtower()+"</td>"
						+ "<td>"+ob.getFtype()+"</td>"
						+ "<td><a href='flatedit?fid="+ob.getFid()+"'>Edit</a></td>"
						+ "<td><a href='UserDelete?fid="+ob.getFid()+"'>Delete</a></td>"
						+"</tr>");
				i++;
			}
			out.print("</table>"
					+"</div>"
					+"</body>"
					+"</html>");
		}			

		
	}

