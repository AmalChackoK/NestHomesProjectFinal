package com.payment.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Flatbean;
import com.bean.Socitybeen;
import com.bean.Socitydauebean;

import com.dao.SocityDaueDAO;

public class PaymentAddAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int flId=Integer.parseInt(request.getParameter("flate"));
			int scat=Integer.parseInt(request.getParameter("category"));
			String date=request.getParameter("date");
			Double total=Double.parseDouble(request.getParameter("paid"));
			Double amount=Double.parseDouble(request.getParameter("amount"));
			
			Flatbean flatbean=new Flatbean();
			Socitybeen socitybeen=new Socitybeen();
			Socitydauebean socitydauebean=new Socitydauebean();
			
			flatbean.setFid(flId);
			socitybeen.setScid(scat);
			socitydauebean.setDate(date);
			socitydauebean.setDaue(total);
			socitydauebean.setPayment(amount);
			
			boolean flage=SocityDaueDAO.insertpayment(flatbean, socitybeen, socitydauebean);
			if(flage){ // if inserted
				response.sendRedirect("payview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}



