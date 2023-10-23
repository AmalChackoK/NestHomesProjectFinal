package com.action.tenant;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Tenantbean;

import com.dao.TenantDAO;

public class TenantDeleteAction  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			int tid=Integer.parseInt(request.getParameter("tid"));
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String phone=request.getParameter("phone");
			boolean flage=false;
			Tenantbean tenantbean=new Tenantbean();
			tenantbean.setTid(tid);
			tenantbean.setTname(name.toUpperCase().trim());
			tenantbean.setTaddress(address.toLowerCase());
			tenantbean.setTphone(phone);
			
				flage=TenantDAO.deletetenant(tenantbean);
				if(flage){
					response.sendRedirect("tenantview");
					}
					else{
					response.sendRedirect("error.html");
					}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}


}
