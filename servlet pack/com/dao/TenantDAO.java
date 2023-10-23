package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.bean.Tenantbean;

public class TenantDAO {
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
	public static boolean insert(Tenantbean tenantbean){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into tenant(t_name,t_address,t_phone)values(?,?,?)");
			pst.setString(1,tenantbean.getTname());
			pst.setString(2, tenantbean.getTaddress());
			pst.setString(3, tenantbean.getTphone());
			pst.executeUpdate();
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flage;
		
	}
	//--------------END------insert details-------------------------
			//------------to view-----
		public static ArrayList<Tenantbean> listAlltenant()throws SQLException{
			ArrayList<Tenantbean> tenantbean=new ArrayList<Tenantbean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT * FROM tenant;");
				rs=pst.executeQuery();
				while(rs.next()){
					Tenantbean user=new Tenantbean();
					user.setTid(rs.getInt(1));
					user.setTname(rs.getString(2));
					user.setTaddress(rs.getString(3));
					user.setTphone(rs.getString(4));
					tenantbean.add(user);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			return tenantbean;
			
		}
		//--------------END----------------------------------------------------------------
		public static ArrayList<Tenantbean> fetchtenantById(int tid)throws SQLException{
			ArrayList<Tenantbean> tenantbean=new ArrayList<Tenantbean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT * FROM tenant where t_id="+tid+" ;");
				rs=pst.executeQuery();
				while(rs.next()){
					Tenantbean cat=new Tenantbean();
					cat.setTid(rs.getInt(1));
					cat.setTname(rs.getNString(2));
					cat.setTaddress(rs.getNString(3));
					cat.setTphone(rs.getNString(4));
					tenantbean.add(cat);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return tenantbean;
		}
		//--------------END----------------------------------------------------------------
		public static boolean updatetenant(Tenantbean tenantbean)throws Exception{
			con=getdbconnection();
			boolean flage=false;
			try{
				pst=con.prepareStatement("update tenant set t_name=? ,t_address=? ,t_phone=? where t_id =?");
				pst.setString(1, tenantbean.getTname());
				pst.setString(2, tenantbean.getTaddress());
				pst.setString(3, tenantbean.getTphone());
				pst.setInt(4, tenantbean.getTid());
				pst.executeUpdate();
				flage=true;
			}catch(SQLException sql){
				sql.printStackTrace();
			}finally {
				DBDAO.close();
			}
			
			return flage;
			
		}
		public static boolean deletetenant(Tenantbean tenantbean)throws Exception{
			con=getdbconnection();
			boolean flag=false;
			try {
				pst=con.prepareStatement("delete from tenant where t_id =?");
			
			pst.setInt(1, tenantbean.getTid());
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
