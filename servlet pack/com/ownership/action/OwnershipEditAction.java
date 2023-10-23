package com.ownership.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;
import com.bean.Ownerbean;
import com.bean.Ownershipbean;

import com.dao.OwnershipDAO;

public class OwnershipEditAction  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			 String ownership=request.getParameter("osid");
			 String owner=request.getParameter("owner");
			 String flat=request.getParameter("flat");
			 int osid=Integer.parseInt(ownership);
			 int ow=Integer.parseInt(owner);
			 int fl=Integer.parseInt(flat);
			 boolean flag=false;
			 Flatbean flatbean = new Flatbean();
			 Ownerbean ownerbean=new Ownerbean();
			 Ownershipbean ownershipbean=new Ownershipbean();
			 
			 ownershipbean.setOsid(osid);
			 ownerbean.setOid(ow);
			 flatbean.setFid(fl);
			 flag=OwnershipDAO.updateownership(ownershipbean,ownerbean, flatbean);
			 if(flag){
					response.sendRedirect("ownershipview");
					}
					else{
					response.sendRedirect("error.html");
					}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
}
