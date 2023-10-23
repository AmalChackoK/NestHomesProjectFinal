package com.flats.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;

import com.dao.FlatDAO;

public class FlateEditAction  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int fid=Integer.parseInt(request.getParameter("fid"));	
			System.out.println(fid);
			String fName=request.getParameter("Number");
			String fTower=request.getParameter("Tower");
			String ftype=request.getParameter("type");
			boolean flag=false;
			Flatbean flatbean = new Flatbean();
			flatbean.setFid(fid);
			flatbean.setFnumber(fName);
			flatbean.setFtower(fTower);
			flatbean.setFtype(ftype);
			flag=FlatDAO.updateflat(flatbean);
			if(flag){
				response.sendRedirect("flateview");
				}
				else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}


