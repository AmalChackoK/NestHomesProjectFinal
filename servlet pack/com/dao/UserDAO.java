package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Userbean;


public class UserDAO {
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
	
	public static boolean insert(Userbean userbean){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into login(lo_username,lo_password,lo_type)values(?,?,?);");
			pst.setString(1,userbean.getUname());
			pst.setString(2,userbean.getUpass());
			pst.setString(3,userbean.getUtype());
			pst.executeUpdate();
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return flage;
		
	}
	//--------------END------insert details-------------------------
	//------------to view-----
	public static ArrayList<Userbean> listAlluser()throws SQLException{
		ArrayList<Userbean> userbean=new ArrayList<Userbean>();
		con=getdbconnection();
		try{
		pst=con.prepareStatement("SELECT * FROM login;");
		rs=pst.executeQuery();
		while(rs.next()){
			Userbean user=new Userbean();
			user.setUid(rs.getInt(1));
			user.setUname(rs.getString(2));
			user.setUpass(rs.getString(3));
			user.setUtype(rs.getString(4));
			userbean.add(user);
		}
		}catch(SQLException e){
		e.printStackTrace();
		}
		return userbean;
		}

		//--------------END----------------------------------------------------------------
	public static ArrayList<Userbean> fetchuserById(int uid)throws SQLException{
		ArrayList<Userbean> userbean=new ArrayList<Userbean>();
		con=getdbconnection();
		try{
		pst=con.prepareStatement("SELECT * FROM login where lo_id="+uid+" ");
		rs=pst.executeQuery();
		while(rs.next()){
			Userbean cat=new Userbean();
		cat.setUid(rs.getInt(1));
		cat.setUname(rs.getString(2));
		cat.setUpass(rs.getString(3));
		cat.setUtype(rs.getString(4));
		userbean.add(cat);
		}
		}catch(SQLException e){
		e.printStackTrace();
		}
		return userbean;
		}
		//--------------END----------------------------------------------------------------
	public static boolean updateuser(Userbean userbean)throws Exception{
		con=getdbconnection();
		boolean flag=false;
		try {
		pst=con.prepareStatement("update login set lo_username=? ,lo_password=? ,lo_type=? where lo_id =?");
		pst.setString(1,userbean.getUname());
		pst.setString(2,userbean.getUpass());
		pst.setString(3,userbean.getUtype());
		pst.setInt(4, userbean.getUid());
		pst.executeUpdate();
		flag=true;
		}
		catch(SQLException sql){
		sql.printStackTrace();
		}
		finally{
		DBDAO.close();
		}
		return flag;
		}
		//---------------------------------------end-------------------------------
	public static boolean deleteuser(Userbean userbean)throws Exception{
		con=getdbconnection();
		boolean flag=false;
		try {
			pst=con.prepareStatement("delete from login where lo_id =?");
		
		pst.setInt(1, userbean.getUid());
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
