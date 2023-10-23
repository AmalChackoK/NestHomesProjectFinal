package com.rental.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;

import com.bean.Rentalbean;
import com.bean.Tenantbean;

import com.dao.RentalDAO;

public class RentalEditAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			 String rental=request.getParameter("rtid");
			 String tenant=request.getParameter("tenant");
			 String flat=request.getParameter("flat");
			 int rtid=Integer.parseInt(rental);
			 int te=Integer.parseInt(tenant);
			 int fl=Integer.parseInt(flat);
			 boolean flag=false;
			 Flatbean flatbean = new Flatbean();
			 Tenantbean tenantbean=new Tenantbean();
			 Rentalbean rentalbean=new Rentalbean();
			 
			 rentalbean.setRtid(rtid);
			 tenantbean.setTid(te);
			 flatbean.setFid(fl);
			 flag=RentalDAO.updaterental(rentalbean, tenantbean, flatbean);
			 if(flag){
					response.sendRedirect("rentalview");
					}
					else{
					response.sendRedirect("error.html");
					}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
}
