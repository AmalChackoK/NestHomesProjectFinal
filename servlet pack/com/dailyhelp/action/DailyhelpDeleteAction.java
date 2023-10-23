package com.dailyhelp.action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Dailyhelpbean;
import com.bean.Dailyhelpcategorybean;
import com.dao.DailyhelpDAO;

public class DailyhelpDeleteAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			 
			 int dhid=Integer.parseInt(request.getParameter("dhid"));
		
			 String ow=request.getParameter("category"); 
			 String name=request.getParameter("name");
			 String address=request.getParameter("address");
			 String phone=request.getParameter("phone");
			 boolean flag=false;
			 Dailyhelpcategorybean dailyhelpcategorybean = new Dailyhelpcategorybean();
			 Dailyhelpbean dailyhelpbean=new Dailyhelpbean();
			 dailyhelpbean.setDhid(dhid);
			 dailyhelpcategorybean.setDccategory(ow);
			 dailyhelpbean.setDhname(name);
			 dailyhelpbean.setDhaddress(address);
			 dailyhelpbean.setDhphone(phone);
			 flag=DailyhelpDAO.deletedailyhelp(dailyhelpbean);
			 if(flag){ // if inserted
					response.sendRedirect("helpview"); //redirect to
					}else{
					response.sendRedirect("error.html");
					}
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

