package com.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatetotalDAO extends HttpServlet{

	/**
	 * gd_id, f_id, gd_due
	 */
	private static final long serialVersionUID = 1L;
public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
	PrintWriter out=response.getWriter();
	int fid=Integer.parseInt(request.getParameter("fId"));
	System.out.println(fid);
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection dbcon=DriverManager.getConnection("jdbc:mysql://localhost:3306/nest","root","Luminar@2023");
		PreparedStatement pst=dbcon.prepareStatement("SELECT gd_due FROM gasdue WHERE f_id=?");
		pst.setInt(1, fid);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			out.print(rs.getDouble(1));
		}
		dbcon.close(); 
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
