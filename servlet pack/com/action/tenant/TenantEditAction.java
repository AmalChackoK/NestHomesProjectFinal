package com.action.tenant;

import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bean.Tenantbean;

import com.dao.TenantDAO;

public class TenantEditAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int tid=Integer.parseInt(request.getParameter("tid"));
			System.out.println(tid);
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String phone=request.getParameter("phone");
			boolean flage=false;
			Tenantbean tenantedit=new Tenantbean();
			tenantedit.setTid(tid);
			tenantedit.setTname(name.toUpperCase().trim());
			tenantedit.setTaddress(address.toLowerCase());
			tenantedit.setTphone(phone);
			flage=TenantDAO.updatetenant(tenantedit);
			if(flage){
				response.sendRedirect("tenantview");
				}
				else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
