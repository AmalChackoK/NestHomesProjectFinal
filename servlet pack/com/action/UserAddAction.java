package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Userbean;
import com.dao.UserDAO;

public class UserAddAction  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
		String uName=request.getParameter("username");
		String uPass=request.getParameter("password");
		String uType=request.getParameter("type");
		
		Userbean userbean=new Userbean();
		userbean.setUname(uName.toUpperCase().trim());
		userbean.setUpass(uPass);
		userbean.setUtype(uType.toUpperCase().trim());
		
		boolean flage=UserDAO.insert(userbean);
		if(flage){ // if inserted
			response.sendRedirect("Userview"); //redirect to
			}else{
			response.sendRedirect("error.html");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


