package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.bean.Gasratebean;



public class GasrateDAO {
	static Connection con=null;
	static PreparedStatement pst=null;
	static ResultSet rs=null;
	static boolean flage=false;
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
	public static boolean insert(Gasratebean gasratebean){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into gasrate( g_rate)values(?)");
			pst.setInt(1, gasratebean.getGrate());
			
			pst.executeUpdate();
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flage;
		
	}
	//--------------END------insert details-------------------------
			//------------to view-----
		public static ArrayList<Gasratebean> listAllrate()throws SQLException{
			ArrayList<Gasratebean> gasratebean=new ArrayList<Gasratebean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT * FROM gasrate;");
				rs=pst.executeQuery();
				while(rs.next()){
					Gasratebean user=new Gasratebean();
					user.setGid(rs.getInt(1));
					user.setGrate(rs.getInt(2));
				
					gasratebean.add(user);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			return gasratebean;
			
		}
		//--------------END----------------------------------------------------------------
		public static ArrayList<Gasratebean> fetchgrateById(int gid)throws SQLException{
			ArrayList<Gasratebean> gasratebean=new ArrayList<Gasratebean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT * FROM gasrate where g_id="+gid+" ");
				rs=pst.executeQuery();
				while(rs.next()){
					Gasratebean cat=new Gasratebean();
					cat.setGid(rs.getInt(1));
					cat.setGrate(rs.getInt(2));
					
					gasratebean.add(cat);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return gasratebean;
		}
		//--------------END----------------------------------------------------------------
		public static boolean updaterate(Gasratebean gasratebean)throws Exception{
			con=getdbconnection();
			boolean flage=false;
			try{
				pst=con.prepareStatement("update gasrate set g_rate=? where g_id =?");
				pst.setInt(1, gasratebean.getGrate());
				pst.setInt(2, gasratebean.getGid());
				pst.executeUpdate();
				flage=true;
			}catch(SQLException sql){
				sql.printStackTrace();
			}finally {
				DBDAO.close();
			}
			
			return flage;
			
		}
		public static boolean deleterate(Gasratebean gasratebean)throws Exception{
			con=getdbconnection();
			boolean flag=false;
			try {
				pst=con.prepareStatement("delete from gasrate where g_id =?");
			
			pst.setInt(1, gasratebean.getGid());
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


