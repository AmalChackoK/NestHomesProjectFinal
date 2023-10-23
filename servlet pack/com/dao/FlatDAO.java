package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Flatbean;






public class FlatDAO {
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
	public static boolean insert(Flatbean flatbean){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into flats(f_number,f_tower,f_type)values(?,?,?);");
			pst.setString(1,flatbean.getFnumber());
			pst.setString(2, flatbean.getFtower());
			pst.setString(3,flatbean.getFtype());
			pst.executeUpdate();
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flage;
		
	}
	//--------------END------insert details-------------------------
		//------------to view-----
	public static ArrayList<Flatbean> listAllflats()throws SQLException{
		ArrayList<Flatbean> flatbean=new ArrayList<Flatbean>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT * FROM flats;");
			rs=pst.executeQuery();
			while(rs.next()){
				Flatbean user=new Flatbean();
				user.setFid(rs.getInt(1));
				user.setFnumber(rs.getString(2));
				user.setFtower(rs.getString(3));
				user.setFtype(rs.getString(4));
				flatbean.add(user);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flatbean;
		
	}
	//--------------END----------------------------------------------------------------
	public static ArrayList<Flatbean> fetchflatById(int fid)throws SQLException{
		ArrayList<Flatbean> flatbean=new ArrayList<Flatbean>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT * FROM flats where f_id="+fid+" ");
			rs=pst.executeQuery();
			while(rs.next()){
				Flatbean cat=new Flatbean();
			cat.setFid(rs.getInt(1));
			cat.setFnumber(rs.getString(2));
			cat.setFtower(rs.getString(3));
			cat.setFtype(rs.getString(4));
			flatbean.add(cat);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flatbean;
		
	}
	//--------------END----------------------------------------------------------------
		public static boolean updateflat(Flatbean flatbean)throws Exception{
			con=getdbconnection();
			boolean flage=false;
			try{
				pst=con.prepareStatement("update flats set f_number=? ,f_tower=? ,f_type=? where f_id =?");
				pst.setString(1, flatbean.getFnumber());
				pst.setString(2, flatbean.getFtower());
				pst.setString(3, flatbean.getFtype());
				pst.setInt(4, flatbean.getFid());
				pst.executeUpdate();
				flage=true;
			}catch(SQLException sql){
				sql.printStackTrace();
			}finally {
				DBDAO.close();
			}
			System.out.println(flage);
			return flage;
			
		}
		public static boolean deleteflate(Flatbean flatbean)throws Exception{
			con=getdbconnection();
			boolean flag=false;
			try {
				pst=con.prepareStatement("delete from flats where f_id =?");
			
			pst.setInt(1, flatbean.getFid());
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
