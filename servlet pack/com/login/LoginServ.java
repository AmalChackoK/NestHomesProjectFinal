package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServ extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String driver="com.mysql.cj.jdbc.Driver";
	final String url="jdbc:mysql://localhost:3306/nest";
	final String user="root";
	final String password="Luminar@2023";
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	RequestDispatcher dis=null;
	//lo_id, lo_username, lo_password, lo_type
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		try{
			Class.forName(driver);   
			conn=DriverManager.getConnection(url,user,password);
			String usrnm=req.getParameter("lousername"); 
			String psw=req.getParameter("lopassword");  
			String type=req.getParameter("type");
			boolean check=false;
			
			pst=conn.prepareStatement("SELECT * FROM login WHERE lo_username=? and lo_password=?");
			pst.setString(1,usrnm);
			pst.setString(2,psw);
			
			rs=pst.executeQuery();
			
			while(rs.next()){
				type=rs.getString(4);
				check=true;  
				
			}
			
			if(check){    
				if(type.equalsIgnoreCase("ADMIN")){
					res.sendRedirect("adminpage?name='"+usrnm+"'");
				}else if(type.equalsIgnoreCase("ACCOUNT")){
					res.sendRedirect("accpage?name='"+usrnm+"'");
				}
			}else{   
				dis=req.getRequestDispatcher("index.html");
				dis.forward(req, res);
			}
			
			conn.close();  
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException sql) {
			
			sql.printStackTrace();
		}
	}
}
