package com.dhc.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Dailyhelpcategorybean;
import com.dao.DailyhelpcategoryDAO;

public class DhcDeleteAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int dhcid=Integer.parseInt(request.getParameter("dhcid"));
			String Category=request.getParameter("Category");
			
			boolean flage=false;
			Dailyhelpcategorybean dhcbean=new Dailyhelpcategorybean();
			dhcbean.setDcid(dhcid);
			dhcbean.setDccategory(Category);
			
			flage=DailyhelpcategoryDAO.deletedc(dhcbean);
			if(flage){
				response.sendRedirect("dhcview");
				}
				else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
