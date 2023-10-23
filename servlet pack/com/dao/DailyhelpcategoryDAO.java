package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Dailyhelpcategorybean;


public class DailyhelpcategoryDAO {
	static Connection con;
	static PreparedStatement pst;
	static ResultSet rs;
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
	//------------------END-------------database connection---------dc_id, dc_category
	public static boolean insert(Dailyhelpcategorybean dcb){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into dailyhelpcategory(dc_category)values(?)");
			pst.setString(1,dcb.getDccategory());
			
			pst.executeUpdate();
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flage;
		
	}
	//--------------END------insert details-------------------------dc_id, dc_category
			//------------to view-----
	public static ArrayList<Dailyhelpcategorybean> listAlldcb()throws SQLException{
		ArrayList<Dailyhelpcategorybean> dcb=new ArrayList<Dailyhelpcategorybean>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT * FROM dailyhelpcategory;");
			rs=pst.executeQuery();
			while(rs.next()){
				Dailyhelpcategorybean user=new Dailyhelpcategorybean();
				user.setDcid(rs.getInt(1));
				user.setDccategory(rs.getString(2));
				dcb.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dcb;
		
	}
	//--------------END----------------------------------------------------------------dc_id, dc_category,dailyhelpcategory
	public static ArrayList<Dailyhelpcategorybean> fetchodcrById(int dcid)throws SQLException{
	ArrayList<Dailyhelpcategorybean> dcb=new ArrayList<Dailyhelpcategorybean>();
	con=getdbconnection();
	try{
		pst=con.prepareStatement("SELECT * FROM dailyhelpcategory where dc_id="+dcid+" ");
		rs=pst.executeQuery();
		while(rs.next()){
			Dailyhelpcategorybean cat=new Dailyhelpcategorybean();
			cat.setDcid(rs.getInt(1));
			cat.setDccategory(rs.getString(2));
			
			dcb.add(cat);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	return dcb;
	}
//--------------END----------------------------------------------------------------dc_id, dc_category,dailyhelpcategory
	public static boolean updateowner(Dailyhelpcategorybean dcb)throws Exception{
		con=getdbconnection();
		boolean flage=false;
		try{
			pst=con.prepareStatement("update dailyhelpcategory set dc_category=?  where dc_id =?");
			pst.setString(1, dcb.getDccategory());
			pst.setInt(2, dcb.getDcid());
			pst.executeUpdate();
			flage=true;
		}catch(SQLException sql){
			sql.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flage;
		
	}
	public static boolean deletedc(Dailyhelpcategorybean dcb)throws Exception{
		con=getdbconnection();
		boolean flag=false;
		try {
			pst=con.prepareStatement("delete from dailyhelpcategory where dc_id =?");
		
		pst.setInt(1, dcb.getDcid());
		pst.executeUpdate();
		flag=true;
		}catch(SQLException sql){
		sql.printStackTrace();
		}finally{
		DBDAO.close();
		}
		return flag;
		}
}
