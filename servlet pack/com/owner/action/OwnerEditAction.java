package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Ownerbean;
import com.dao.OwnerDAO;

public class OwnerEditAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int oid=Integer.parseInt(request.getParameter("oid"));
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String phone=request.getParameter("phone");
			boolean flage=false;
			Ownerbean ownerbean=new Ownerbean();
			ownerbean.setOid(oid);
			ownerbean.setOname(name.toUpperCase().trim());
			ownerbean.setOaddress(address.toLowerCase());
			ownerbean.setOphone(phone);
			flage=OwnerDAO.updateowner(ownerbean);
			if(flage){
				response.sendRedirect("ownerview");
				}
				else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
