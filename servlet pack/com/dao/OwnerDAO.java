package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.bean.Ownerbean;


public class OwnerDAO {
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
	public static boolean insert(Ownerbean ownerbean){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into owner(o_name,o_address,o_phone)values(?,?,?)");
			pst.setString(1,ownerbean.getOname());
			pst.setString(2, ownerbean.getOaddress());
			pst.setString(3, ownerbean.getOphone());
			pst.executeUpdate();
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flage;
		
	}
	//--------------END------insert details-------------------------
			//------------to view-----
		public static ArrayList<Ownerbean> listAllowner()throws SQLException{
			ArrayList<Ownerbean> ownerbean=new ArrayList<Ownerbean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT * FROM owner;");
				rs=pst.executeQuery();
				while(rs.next()){
					Ownerbean user=new Ownerbean();
					user.setOid(rs.getInt(1));
					user.setOname(rs.getString(2));
					user.setOaddress(rs.getString(3));
					user.setOphone(rs.getString(4));
					ownerbean.add(user);
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			return ownerbean;
			
		}
		//--------------END----------------------------------------------------------------
		public static ArrayList<Ownerbean> fetchownerById(int oid)throws SQLException{
			ArrayList<Ownerbean> ownerbean=new ArrayList<Ownerbean>();
			con=getdbconnection();
			try{
				pst=con.prepareStatement("SELECT * FROM owner where o_id="+oid+" ");
				rs=pst.executeQuery();
				while(rs.next()){
					Ownerbean cat=new Ownerbean();
					cat.setOid(rs.getInt(1));
					cat.setOname(rs.getString(2));
					cat.setOaddress(rs.getString(3));
					cat.setOphone(rs.getString(4));
					ownerbean.add(cat);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return ownerbean;
		}
		//--------------END----------------------------------------------------------------
		public static boolean updateowner(Ownerbean ownerbean)throws Exception{
			con=getdbconnection();
			boolean flage=false;
			try{
				pst=con.prepareStatement("update owner set o_name=? ,o_address=? ,o_phone=? where o_id =?");
				pst.setString(1, ownerbean.getOname());
				pst.setString(2, ownerbean.getOaddress());
				pst.setString(3, ownerbean.getOphone());
				pst.setInt(4, ownerbean.getOid());
				pst.executeUpdate();
				flage=true;
			}catch(SQLException sql){
				sql.printStackTrace();
			}finally {
				DBDAO.close();
			}
			
			return flage;
			
		}
		public static boolean deleteowner(Ownerbean ownerbean)throws Exception{
			con=getdbconnection();
			boolean flag=false;
			try {
				pst=con.prepareStatement("delete from owner where o_id =?");
			
			pst.setInt(1, ownerbean.getOid());
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
