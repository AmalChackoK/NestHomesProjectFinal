package com.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Dailyhelpbean;
import com.bean.Dailyhelpcategorybean;
import com.dao.DailyhelpDAO;
import com.dao.DailyhelpcategoryDAO;

public class DailyhelpDelete  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		 ArrayList<Dailyhelpbean> dailyhelpbeans=null;
		
		 int dhid=Integer.parseInt(request.getParameter("dhid"));
		 System.out.println(dhid);
		 System.out.println(request.getParameter("dhid"));
		 try {
			 dailyhelpbeans=DailyhelpDAO.fatchdailyhelp(dhid);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		 out.print("<!DOCTYPE html>"
					+"<html>"
					+"<head>"
					+"<meta charset='ISO-8859-1'>"
					+"<title>Insert title here</title>"
					+"<link rel='stylesheet' type='text/css' href='./css/dailyhelpedit.css'>"
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
		      	 
		      	     +" <form action='helpdeleteaction' method='post'>"
		      	    +"<div class='div-2'>"
		      	           +" <h4>daily help edit</h4>");
		      	           for(Dailyhelpbean ow:dailyhelpbeans) {
		      	        	   
		      	           
		      	             out.print("<input type='hidden' name='dhid' value='"+ow.getDhid()+"'/>"
		      	            		 +"Owner"
		      	                +"<select  name='category'>"
		      	                +"<option >select category</option>");
		ArrayList<Dailyhelpcategorybean>  owner=null;    	                
		      try{
		    	  owner=DailyhelpcategoryDAO.listAlldcb();
		      }catch (SQLException sql) {
				sql.printStackTrace();
			}
		     for(Dailyhelpcategorybean ob:owner){
		    	 out.print("<option value='"+ob.getDcid()+"'>"+ob.getDccategory()+"</option><br>");
		     }
		      	                
		 	         
		     out.print("</selcet><br>"
            			+" name<br><input type='text' name='name' value='"+ow.getDhname()+"' /><br>"
            		 + "Address<br><textarea  name='address'  rows='4' cols='50'/>"+ow.getDhaddress()+"</textarea><br>"
            			+ "contact<br><input type='text' name='phone' value='"+ow.getDhphone()+"' /><br>"
            			+" <input type='submit' value='save'id='ownerbutten'>");

		      	           }  
		      	           
					out.print("<Div class=table>"
						+"<table>"
						+"<tr>"
						+"<th>Sl.No</th>"
						+"<th>category</th>"
						+"<th>Name</th>"
						+"<th>Address</th>"
						+"<th>phone</th>"
						+"<th>Edit</th>"
						+"<th>Delete</th>"
						+"</tr>");
			int i=1;
			ArrayList<Dailyhelpbean> user=null;
			
			try{

				
			//Userbean userbean = new Userbean();
				user=DailyhelpDAO.listAlldailyhelp();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			for(Dailyhelpbean obj:user){
				out.print("<tr>"
						+"<td>"+i+"</td>"
						+ "<td>"+obj.getDccat()+"</td>"
						+ "<td>"+obj.getDhname()+"</td>"
						+ "<td>"+obj.getDhaddress()+"</td>"
						+ "<td>"+obj.getDhphone()+"</td>"
						+ "<td><a href='helpedit?dhid='"+obj.getDhid()+"'>Edit</a></td>"
						+ "<td><a href='helpdelete?dhid="+obj.getDhid()+"'>Delete</a></td>"
						+"</tr>");
				i++;
			}
			out.print("</table>"
					+"</div>"
					+"</body>"
					+"</html>");
		}			


}
