package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Dailyhelpbean;
import com.bean.Dailyhelpcategorybean;



public class DailyhelpDAO {
	static Connection con=null;
	static PreparedStatement pst=null;
	static ResultSet rs=null;
	static boolean flage=false;
	//--------------START------get connection---------------dh_id, dc_id, dh_name, dh_address, dh_phone
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
		public static boolean insert(Dailyhelpcategorybean dailyhelpcategorybean,Dailyhelpbean dailyhelpbean){
			try{
				con=getdbconnection();
				pst=con.prepareStatement("insert into dailyhelp( dc_id, dh_name, dh_address, dh_phone)values(?,?,?,?)");
				pst.setInt(1, dailyhelpcategorybean.getDcid());
				pst.setString(2, dailyhelpbean.getDhname());
				pst.setString(3,dailyhelpbean.getDhaddress());
				pst.setString(4,dailyhelpbean.getDhphone());
				pst.executeUpdate();
				flage=true;
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return flage;
			
		}
		//--------------END------insert details-------------------------
		//------------to view-----
		public static ArrayList<Dailyhelpcategorybean> listAllcatogory()throws SQLException{
			ArrayList<Dailyhelpcategorybean> dailyhelpcategorybean=new ArrayList<Dailyhelpcategorybean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT dc_id, dc_category FROM dailyhelpcategory;");
				rs=pst.executeQuery();
				while(rs.next()){
					Dailyhelpcategorybean user=new Dailyhelpcategorybean();
					user.setDcid(rs.getInt(1));
					user.setDccategory(rs.getString(2));
					
					dailyhelpcategorybean.add(user);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			return dailyhelpcategorybean;
			
		}
		//dailyhelpcategory   dc_id, dc_category   dailyhelp  dh_id, dc_id, dh_name, dh_address, dh_phone
		public static ArrayList<Dailyhelpbean> listAlldailyhelp()throws SQLException{
			ArrayList<Dailyhelpbean> dailyhelpbean=new ArrayList<Dailyhelpbean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT dailyhelp.dh_id, dailyhelpcategory.dc_category, dailyhelp.dh_name, dailyhelp.dh_address,dailyhelp.dh_phone FROM dailyhelpcategory, dailyhelp WHERE dailyhelpcategory.dc_id=dailyhelp.dc_id ");
				rs=pst.executeQuery();
				while(rs.next()){
					Dailyhelpbean user=new Dailyhelpbean();
					user.setDhid(rs.getInt(1));
					user.setDccat(rs.getString(2));
					user.setDhname(rs.getString(3));
					user.setDhaddress(rs.getString(4));
					user.setDhphone(rs.getString(5));
					dailyhelpbean.add(user);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			return dailyhelpbean;
			
		}
		public static ArrayList<Dailyhelpbean> fatchdailyhelp(int dhid)throws SQLException{
			ArrayList<Dailyhelpbean> dailyhelpbean=new ArrayList<Dailyhelpbean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT dailyhelp.dh_id, dailyhelpcategory.dc_category, dailyhelp.dh_name, dailyhelp.dh_address,dailyhelp.dh_phone FROM dailyhelp, dailyhelpcategory WHERE dailyhelpcategory.dc_id=dailyhelp.dc_id AND dailyhelp.dh_id="+dhid+" ");
				
	
				rs=pst.executeQuery();
				while(rs.next()){
					Dailyhelpbean user=new Dailyhelpbean();
					user.setDhid(rs.getInt(1));
					user.setDccat(rs.getString(2));
					user.setDhname(rs.getString(3));
					user.setDhaddress(rs.getString(4));
					user.setDhphone(rs.getString(5));
					dailyhelpbean.add(user);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			return dailyhelpbean;
			
		}
		//--------------END----------------------------------------------------------------
		//dailyhelpcategory   dc_id, dc_category   dailyhelp  dh_id, dc_id, dh_name, dh_address, dh_phone
		public static boolean updatedailyhelp(Dailyhelpcategorybean dailyhelpcategorybean,Dailyhelpbean dailyhelpbean)throws Exception{
			con=getdbconnection();
			boolean flage=false;
			try{
				pst=con.prepareStatement("update dailyhelp set dc_id=? ,dh_name=?, dh_address=?, dh_phone=? where dh_id =?");
				
				pst.setInt(1, dailyhelpcategorybean.getDcid());
				pst.setString(2, dailyhelpbean.getDhname());
				pst.setString(3, dailyhelpbean.getDhaddress());
				pst.setString(4, dailyhelpbean.getDhphone());
				pst.setInt(5, dailyhelpbean.getDhid());
				pst.executeUpdate();
				flage=true;
			}catch(SQLException sql){
				sql.printStackTrace();
			}finally {
				DBDAO.close();
			}
			
			return flage;
			
		}
		public static boolean deletedailyhelp(Dailyhelpbean dailyhelpbean)throws Exception{
			con=getdbconnection();
			boolean flag=false;
			try {
				pst=con.prepareStatement("delete from dailyhelp where dh_id =?");
			
			pst.setInt(1, dailyhelpbean.getDhid());
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


