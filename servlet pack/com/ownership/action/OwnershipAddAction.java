package com.ownership.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;
import com.bean.Ownerbean;

import com.dao.OwnershipDAO;


public class OwnershipAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int owner=Integer.parseInt(request.getParameter("owner"));
			int number=Integer.parseInt(request.getParameter("flat"));
			
			Ownerbean ownerbean=new Ownerbean();
			Flatbean flatbean=new Flatbean();
			
			ownerbean.setOid(owner);
			flatbean.setFid(number);
			boolean flage=OwnershipDAO.insert(ownerbean, flatbean);
			if(flage){ // if inserted
				response.sendRedirect("ownershipview"); //redirect to
				}else{
				response.sendRedirect("error.htmll");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
