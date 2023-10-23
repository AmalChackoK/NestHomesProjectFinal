package com.payment.action;

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
		PrintWriter pw=response.getWriter();
		pw.print("<html>"
                +"<head>"
                +"<script>"
				+"var request;"
				+"function checkdues(){"
					+"var fId=document.dues.flate.value;"
					+"var url='totaldue?fId='+fId;"
					+"if(window.XMLHttpRequest){"
						+"request=new XMLHttpRequest();"
					+"}else if(window.ActiveXObject){"
						+"request=new ActiveXObject('Microsoft.XMLHTTP');"
					+"}"
					+"try{"
						+"request.onreadystatechange=getTotal;"
						+"request.open('GET',url,true);"
						+"request.send();"
					+"}catch(e){"
						+"alert('Unable to connect to server');"
					+"}"	
				+"}"
				+"function getTotal(){"
					+"if(request.readyState == 4){"
						+"var total=request.responseText;"
						+"document.getElementById('amount').value=total;"
					+"}"
				+"}"
				+"</script>"
                +"<title>payment details</title>"
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
			pw.print("<option value='"+sdc.getScid()+"'>"+sdc.getSccat()+"</option>");
		}
		pw.print("</select><br>"
                +"<label>Flat</label>"
                +"<select id='select' name='flate'>");

		ArrayList<Flatbean> flat=null;
		try{
			flat=FlatDAO.listAllflats();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(Flatbean fl:flat){
			pw.print("<option value='"+fl.getFid()+"'>"+fl.getFnumber()+" "+fl.getFtower()+"</option>");
		}
		pw.print("</select><br>"
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


