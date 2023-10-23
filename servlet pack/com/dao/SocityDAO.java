package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.bean.Socitybeen;

public class SocityDAO {
	static Connection con=null;
	static PreparedStatement pst=null;
	static ResultSet rs=null;
	static boolean flage=false;
	//--------------START------get connection---------------
	public static Connection getdbconnection() throws SQLException{
		try{
			DBDAO.connection();
			con=DBDAO.getDbcon();
		}catch(SQLException s){
			s.printStackTrace();
		}
		return con;
	}
	//------------------END-------------database connection---------
	public static ArrayList<Socitybeen> listAllcat()throws SQLException{
		ArrayList<Socitybeen> socitybeen=new ArrayList<Socitybeen>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT * FROM socityduescat;");
			rs=pst.executeQuery();
			while(rs.next()){
				Socitybeen user=new Socitybeen();
				user.setScid(rs.getInt(1));
				user.setSccat(rs.getString(2));
			
				socitybeen.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return socitybeen;
		
	}
}
