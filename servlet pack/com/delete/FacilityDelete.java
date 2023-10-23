package com.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Facilitybeen;
import com.dao.FacilityDAO;

public class FacilityDelete  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		 ArrayList<Facilitybeen> facilityedit=null;
		 String ownerId=request.getParameter("fmid");
		 int fmid=Integer.parseInt(ownerId);
		 try {
			 facilityedit=FacilityDAO.fetchfmById(fmid);
			
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
	          		+"<form action='facilitydeleteaction' method='Post'>"
	          		+"<div class='div-2'>"
	          			+"<h3>Owner Details</h3> <hr>");
	          			for(Facilitybeen ob:facilityedit){
	               			out.print("<input type='hidden' name='fmid' value='"+ob.getFmid()+"'/>"
	               					+" name<br><input type='text' name='name' value='"+ob.getFmname()+"' readonly/><br>"
	               					+"Designation<br><input typ='text' name='des' value='"+ob.getFmdes()+"' readonly><br>"
	               					+ "Duty<br><input type='text' name='Dauty' value='"+ob.getFmduty()+"' readonly/><br>"
	               					+ "contact<br><input type='text' name='phone' value='"+ob.getFmphone()+"' readonly/><br>"
	               					+" <input type='submit' value='save'id='ownerbutten'>");
	               		}
					out.print("</div>"
	          	   +"</form>"	
	    
			
					+"<div class='table'>"
						+"<table>"
						+"<tr>"
						+"<th>Sl.No</th>"
						+"<th>Name</th>"
						+ "<th>Designation</th>"
						+"<th>Dauty</th>"
						+"<th>contact</th>"
						+"<th>Edit</th>"
						+"<th>Delete</th>"
						+"</tr>");
			int i=1;
			ArrayList<Facilitybeen> user=null;
			
			try{

				
			//Userbean userbean = new Userbean();
				user=FacilityDAO.listAllfm();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			for(Facilitybeen ob:user){
				out.print("<tr>"
						+"<td>"+i+"</td>"
						+ "<td>"+ob.getFmname()+"</td>"
						+ "<td>"+ob.getFmdes()+"</td>"
						+ "<td>"+ob.getFmduty()+"</td>"
						+ "<td>"+ob.getFmphone()+"</td>"
						+ "<td><a href='facilityedit?fmid="+ob.getFmid()+"'>Edit</a></td>"
						+ "<td><a href='facilitydelete?fmid="+ob.getFmid()+"'>Delete</a></td>"
						+"</tr>");
				i++;
			}
			out.print("</table>"
					+"</div>"
					+"</body>"
					+"</html>");
		}			

		
	}



