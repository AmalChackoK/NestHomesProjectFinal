package com.flats.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Flatbean;
import com.dao.FlatDAO;

public class FlatsAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			String Number=request.getParameter("Number");
			String Tower=request.getParameter("Tower");
			String type=request.getParameter("type");
			
			Flatbean flatbean=new Flatbean();
			flatbean.setFnumber(Number.toUpperCase().trim());
			flatbean.setFtower(Tower.toUpperCase().trim());
			flatbean.setFtype(type.toUpperCase().trim());
			boolean flage=FlatDAO.insert(flatbean);
			if(flage){ // if inserted
				response.sendRedirect("flateview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
