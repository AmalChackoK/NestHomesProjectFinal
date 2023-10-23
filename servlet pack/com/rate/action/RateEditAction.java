package com.rate.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Gasratebean;

import com.dao.GasrateDAO;


public class RateEditAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int gid=Integer.parseInt(request.getParameter("gid"));
		
			int rate=Integer.parseInt(request.getParameter("rate"));
			boolean flage=false;
			Gasratebean gasratebean=new Gasratebean();
			gasratebean.setGid(gid);
			gasratebean.setGrate(rate);
			
			flage=GasrateDAO.updaterate(gasratebean);
			if(flage){
				response.sendRedirect("rateview");
				}
				else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
