package com.action.tenant;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Tenantbean;

import com.dao.TenantDAO;

public class TenantAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			String Name=request.getParameter("name");
			String Address=request.getParameter("address");
			String Phone=request.getParameter("phone");
			
			Tenantbean tenantbean=new Tenantbean();
			tenantbean.setTname(Name.toUpperCase().trim());
			tenantbean.setTaddress(Address.toLowerCase().trim());
			tenantbean.setTphone(Phone);
			boolean flage=TenantDAO.insert(tenantbean);
			if(flage){ // if inserted
				response.sendRedirect("tenantview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
