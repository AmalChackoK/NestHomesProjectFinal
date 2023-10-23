package com.facility.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Facilitybeen;

import com.dao.FacilityDAO;


public class FacilityAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException ,ServletException{
		try{
			String Name=request.getParameter("name");
			String des=request.getParameter("des");
			String Dauty=request.getParameter("Dauty");
			String phone=request.getParameter("phone");
		
			System.out.println(Name);
			
			Facilitybeen facilitybeen=new Facilitybeen();
			facilitybeen.setFmname(Name);
			facilitybeen.setFmdes(des);
			facilitybeen.setFmduty(Dauty);
			facilitybeen.setFmphone(phone);
			boolean flage=FacilityDAO.insert(facilitybeen);
			if(flage){ // if inserted
				response.sendRedirect("facilityview"); //redirect to
				}else{
				response.sendRedirect("error.html");
				}
			
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	

}
