package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Userbean;
import com.dao.UserDAO;

public class UserDeleteAction  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
		int uid=Integer.parseInt(request.getParameter("uid"));	
		System.out.println(uid);
		String uName=request.getParameter("username");
		String uPass=request.getParameter("password");
		String uType=request.getParameter("type");
		boolean flag=false;
		Userbean userbean = new Userbean();
		userbean.setUid(uid);
		userbean.setUname(uName.toUpperCase().trim());
		userbean.setUpass(uPass);
		userbean.setUtype(uType.toUpperCase().trim());
		flag=UserDAO.deleteuser(userbean);

		if(flag){
		response.sendRedirect("Userview");
		}
		else{
		response.sendRedirect("error.html");
		}
		
		}catch( Exception e){
			e.printStackTrace();
		}
	}
}
