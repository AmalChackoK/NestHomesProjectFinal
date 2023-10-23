package com.edit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Dailyhelpbean;
import com.bean.Dailyhelpcategorybean;
import com.dao.DailyhelpDAO;
import com.dao.DailyhelpcategoryDAO;

public class DailyhelpEditOne {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
	PrintWriter pw=res.getWriter();
	
	int dhId=Integer.parseInt(req.getParameter("dhId"));
	ArrayList<Dailyhelpbean> help=null;
	
	try{
		help=DailyhelpDAO.fatchdailyhelp(dhId);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	pw.print("<html>"
		    +"<head>"
	        +"<title>Daily Help</title>"
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
			+"<div class='menu'>"
			+ "<form id='help' method='post' action='helpupdateaction'>"
	            +"<h2>Daily Help</h2>");
	
	for(Dailyhelpbean dh:help){
		pw.print("<input type='hidden' name='dhId' value='"+dh.getDhid()+"'/><br>"
					+"<label for='category' class='form-label'>Category</label>"
	                +"<select id='category' name='category' class='form-select'>"
	                +"<option value=''>select category</option>");
	}
	ArrayList<Dailyhelpcategorybean> cate=null;
	
	try{
		//FlatBean flatBean=new FlatBean();
		cate=DailyhelpcategoryDAO.listAlldcb();
	}catch(SQLException e){
		e.printStackTrace();
	}
	for(Dailyhelpcategorybean dhc:cate){
		pw.print("<option value='"+dhc.getDcid()+"'>"+dhc.getDccategory()+"</option>");
	}
	pw.print("</select><br>"
			+"<label>Names</label>"
            +"<input type='text' name='uname' placeholder='enter name'/><br>"
            +"<label>Address</label>"
            +"<textarea id='address' name='address' placeholder='enter address' columns='50' rows='4'></textarea><br>"
            +"<label>Contact</label>"
            +"<input type='tele' id='phon' name='phon' value='+91'/>"
           
+" <input type='submit' value='save'id='ownerbutten'>"
			+"</form>"
			+"</div>"
			+"<div class='table'>"
			+"<table>"
			+"<tr>"
				+"<th>SlNo</th>"
				+"<th>Category</th>"
				+"<th>Name</th>"
				+"<th>Address</th>"
				+"<th>Contact</th>"
				+"<th>Edit</th>"
				+"<th>Delete</th>"
			+"</tr>");
	
	int i=1;
	ArrayList<Dailyhelpbean> helps=null;
	
	try{
		helps=DailyhelpDAO.listAlldailyhelp();
	}catch(SQLException e){
		e.printStackTrace();
	}
	for(Dailyhelpbean dhc:helps){
		pw.print("<tr>"
				+ "<td>"+i+"</td>"
				+ "<td>"+dhc.getDccat()+"</td>"
				+ "<td>"+dhc.getDhname()+"</td>"
				+ "<td>"+dhc.getDhaddress()+"</td>"
				+ "<td>"+dhc.getDhphone()+"</td>"
				+ "<td><a href='helpedit?dhid="+dhc.getDhid()+"'>Edit</a></td>" //---------button 
				+ "<td><a href='DailyhelpDelete?dhId="+dhc.getDhid()+"'>Delete</a></td>"
				+"</tr>");
		i++;
	}
	pw.print("</table>"
			+"</div>"
			+"</body>"
			+"</html>");
}
}
