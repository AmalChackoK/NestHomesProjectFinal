package com.dhc.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Dailyhelpcategorybean;

import com.dao.DailyhelpcategoryDAO;


public class DhcAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			String Category=request.getParameter("Category");
			
			
			Dailyhelpcategorybean dcb=new Dailyhelpcategorybean();
			dcb.setDccategory(Category.toUpperCase().trim());
			
			boolean flage=DailyhelpcategoryDAO.insert(dcb);
			if(flage){ // if inserted
				response.sendRedirect("dhcview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
