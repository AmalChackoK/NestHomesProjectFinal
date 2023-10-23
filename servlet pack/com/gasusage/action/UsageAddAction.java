package com.gasusage.action;

import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Flatbean;
import com.bean.Usagebean;

import com.dao.GasusageDAO;

public class UsageAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int cat=Integer.parseInt(request.getParameter("flate"));
			
			float unit=Float.parseFloat(request.getParameter("unit"));
			 System.out.println(cat);
			Flatbean flatbean=new Flatbean();
			Usagebean usagebean=new Usagebean();
			
			flatbean.setFid(cat);
			usagebean.setUsage(unit);
			
			
			boolean flage=GasusageDAO.insert(usagebean, flatbean);
			if(flage){ // if inserted
				response.sendRedirect("usageview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
