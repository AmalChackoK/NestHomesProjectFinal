package com.facility.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Facilitybeen;

import com.dao.FacilityDAO;


public class FacilityEditAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			int fmid=Integer.parseInt(request.getParameter("fmid"));
			String name=request.getParameter("name");
			String des=request.getParameter("des");
			String Dauty=request.getParameter("Dauty");
			String phone=request.getParameter("phone");
			boolean flage=false;
			Facilitybeen facilitybeen=new Facilitybeen();
			facilitybeen.setFmid(fmid);
			facilitybeen.setFmname(name);
			facilitybeen.setFmdes(des);
			facilitybeen.setFmduty(Dauty);
			facilitybeen.setFmphone(phone);
			flage=FacilityDAO.updateuser(facilitybeen);
			if(flage){
				response.sendRedirect("facilityview");
				}
				else{
				response.sendRedirect("error.html");
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}



