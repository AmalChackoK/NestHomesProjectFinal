package com.rate.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Gasratebean;

import com.dao.GasrateDAO;


public class RateAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int rate=Integer.parseInt(request.getParameter("rate"));
			
			
			Gasratebean gasratebean=new Gasratebean();
			gasratebean.setGrate(rate);
			
			boolean flage=GasrateDAO.insert(gasratebean);
			if(flage){ // if inserted
				response.sendRedirect("rateview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
