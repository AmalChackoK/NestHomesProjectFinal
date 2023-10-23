package com.rental.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;

import com.bean.Tenantbean;

import com.dao.RentalDAO;


public class RentalAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int tenant=Integer.parseInt(request.getParameter("tenant"));
			int number=Integer.parseInt(request.getParameter("flat"));
			
			Tenantbean tenantbean=new Tenantbean();
			Flatbean flatbean=new Flatbean();
			
			tenantbean.setTid(tenant);
			flatbean.setFid(number);
			boolean flage=RentalDAO.insert(tenantbean, flatbean);
			if(flage){ // if inserted
				response.sendRedirect("rentalview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


