package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Flatbean;
import com.bean.Rentalbean;
import com.bean.Tenantbean;

public class RentalDAO {
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
	public static boolean insert(Tenantbean tenantbean,Flatbean flatbean){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into rental(t_id,f_id)values(?,?)");
			pst.setInt(1, tenantbean.getTid());
			pst.setInt(2, flatbean.getFid());
			
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
			pst=con.prepareStatement("SELECT t_id, t_name FROM tenant;");
			rs=pst.executeQuery();
			while(rs.next()){
				Tenantbean user=new Tenantbean();
				user.setTid(rs.getInt(1));
				user.setTname(rs.getString(2));
				
				tenantbean.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tenantbean;
		
	}

	public static ArrayList<Flatbean> listAllfalts()throws SQLException{
		ArrayList<Flatbean> flatbean=new ArrayList<Flatbean>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT f_id, f_number,f_tower from flats ;");
			rs=pst.executeQuery();
			while(rs.next()){
				Flatbean user=new Flatbean();
				user.setFid(rs.getInt(1));
				user.setFnumber(rs.getString(2));
				user.setFtower(rs.getString(3));
				
				flatbean.add(user);
			}
			
		}catch(SQLException e){  
			e.printStackTrace();
		}
		return flatbean;
		
	}
	public static ArrayList<Rentalbean> listAllrental()throws SQLException{
		ArrayList<Rentalbean> rentalbean=new ArrayList<Rentalbean>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT rental.r_id, tenant.t_name, flats.f_number, flats.f_tower FROM rental, tenant, flats WHERE rental.t_id=tenant.t_id AND rental.f_id=flats.f_id");
			rs=pst.executeQuery();
			while(rs.next()){
				Rentalbean user=new Rentalbean();
				user.setRtid(rs.getInt(1));
				user.setTname(rs.getString(2));
				user.setFnumber(rs.getString(3));
				user.setFtower(rs.getString(4));
				rentalbean.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rentalbean;
		
	}
	public static ArrayList<Rentalbean> fatchrental(int rtid)throws SQLException{
		ArrayList<Rentalbean> rentalbean=new ArrayList<Rentalbean>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT rental.r_id, tenant.t_name, flats.f_number, flats.f_tower FROM rental, tenant, flats WHERE rental.t_id=tenant.t_id AND rental.f_id=flats.f_id AND rental.r_id="+rtid+" ");
			rs=pst.executeQuery();
			while(rs.next()){
				Rentalbean user=new Rentalbean();
				user.setRtid(rs.getInt(1));
				user.setTname(rs.getString(2));
				user.setFnumber(rs.getString(3));
				user.setFtower(rs.getString(4));
				rentalbean.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rentalbean;
		
	}
	//--------------END----------------------------------------------------------------
	public static boolean updaterental(Rentalbean rentalbean,Tenantbean tenantbean,Flatbean flatbean)throws Exception{
		con=getdbconnection();
		boolean flage=false;
		try{
			pst=con.prepareStatement("update rental set t_id=? ,f_id=? where r_id =?");
			
			pst.setInt(1, tenantbean.getTid());
			pst.setInt(2, flatbean.getFid());
			pst.setInt(3, rentalbean.getRtid());
			pst.executeUpdate();
			flage=true;
		}catch(SQLException sql){
			sql.printStackTrace();
		}finally {
			DBDAO.close();
		}
		
		return flage;
		
	}
	public static boolean deleterental(Rentalbean rentalbean)throws Exception{
		con=getdbconnection();
		boolean flag=false;
		try {
			pst=con.prepareStatement("delete from rental where r_id =?");
		
		pst.setInt(1, rentalbean.getRtid());
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
