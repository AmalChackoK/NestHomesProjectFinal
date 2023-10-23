package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Userbean;
import com.dao.UserDAO;

public class UserViewAction  extends HttpServlet{

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
          		+"<form action='User' method='Post'>"
          		+"<div class='div-2'>"
          			+"<h3>User</h3> <hr>"
          			+" Username<br><input type='text' name='username' /><br>"
          			+"Password<br><input type='text' name='password' /><br>"
          			+ "Type<br><input type='text' name='type' /><br>"
          			+" <input type='submit' value='save'id='ownerbutten'>"
          	   +"</div>"
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
		ArrayList<Userbean> user=null;
		
		try{

			
		//Userbean userbean = new Userbean();
			user=UserDAO.listAlluser();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		for(Userbean ob:user){
			out.print("<tr>"
					+"<td>"+i+"</td>"
					+ "<td>"+ob.getUname()+"</td>"
					+ "<td>"+ob.getUpass()+"</td>"
					+ "<td>"+ob.getUtype()+"</td>"
					+ "<td><a href='useredit?uid="+ob.getUid()+"'>Edit</a></td>"
					+ "<td><a href='UserDelete?uid="+ob.getUid()+"'>Delete</a></td>"
					+"</tr>");
			i++;
		}
		out.print("</table>"
				+"</div>"
				+"</body>"
				+"</html>");
	}			
}



