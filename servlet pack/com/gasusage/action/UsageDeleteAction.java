package com.gasusage.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Usagebean;
import com.dao.GasusageDAO;

public class UsageDeleteAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			 
			 int guid=Integer.parseInt(request.getParameter("guid"));
		
			// int ow=Integer.parseInt(request.getParameter("flate")); 
			 
			//float unit=Float.parseFloat(request.getParameter("unit"));
			 boolean flag=false;
			// Flatbean flatbean = new Flatbean();
			 Usagebean usagebean=new Usagebean();
			 usagebean.setGuid(guid);
			// flatbean.setFid(ow);
			// usagebean.setUsage(unit);
			
			 flag=GasusageDAO.deleteusage(usagebean);
			 if(flag){ // if inserted
					response.sendRedirect("usageview"); //redirect to
					}else{
					response.sendRedirect("error.html");
					}
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
