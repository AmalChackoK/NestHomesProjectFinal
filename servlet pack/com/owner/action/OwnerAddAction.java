package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Ownerbean;

import com.dao.OwnerDAO;

public class OwnerAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			String Name=request.getParameter("name");
			String Address=request.getParameter("address");
			String Phone=request.getParameter("phone");
			
			Ownerbean ownerbean=new Ownerbean();
			ownerbean.setOname(Name.toUpperCase().trim());
			ownerbean.setOaddress(Address.toLowerCase().trim());
			ownerbean.setOphone(Phone);
			boolean flage=OwnerDAO.insert(ownerbean);
			if(flage){ // if inserted
				response.sendRedirect("ownerview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
