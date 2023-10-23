package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Flatbean;
import com.bean.Ownerbean;
import com.bean.Ownershipbean;




public class OwnershipDAO {
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
	public static boolean insert(Ownerbean ownerbean,Flatbean flatbean){
		try{
			con=getdbconnection();
			pst=con.prepareStatement("insert into ownership(o_id,f_id)values(?,?)");
			pst.setInt(1, ownerbean.getOid());
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
public static ArrayList<Ownerbean> listAllowner()throws SQLException{
	ArrayList<Ownerbean> ownerbean=new ArrayList<Ownerbean>();
	con=getdbconnection();
	try{
		pst=con.prepareStatement("SELECT o_id, o_name FROM owner;");
		rs=pst.executeQuery();
		while(rs.next()){
			Ownerbean user=new Ownerbean();
			user.setOid(rs.getInt(1));
			user.setOname(rs.getString(2));
			
			ownerbean.add(user);
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	return ownerbean;
	
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
public static ArrayList<Ownershipbean> listAllownership()throws SQLException{
	ArrayList<Ownershipbean> Ownershipbean=new ArrayList<Ownershipbean>();
	con=getdbconnection();
	try{
		pst=con.prepareStatement("SELECT ownership.os_id, owner.o_name, flats.f_number, flats.f_tower FROM ownership, owner, flats WHERE ownership.o_id=owner.o_id AND ownership.f_id=flats.f_id");
		rs=pst.executeQuery();
		while(rs.next()){
			Ownershipbean user=new Ownershipbean();
			user.setOsid(rs.getInt(1));
			user.setOname(rs.getString(2));
			user.setFnumber(rs.getString(3));
			user.setFtower(rs.getString(4));
			Ownershipbean.add(user);
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	return Ownershipbean;
	
}

public static ArrayList<Ownershipbean> fatchownership(int osid)throws SQLException{
	ArrayList<Ownershipbean> Ownershipbean=new ArrayList<Ownershipbean>();
	con=getdbconnection();
	try{
		pst=con.prepareStatement("SELECT ownership.os_id, owner.o_name, flats.f_number, flats.f_tower FROM ownership, owner, flats WHERE ownership.o_id=owner.o_id AND ownership.f_id=flats.f_id AND ownership.os_id="+osid+" ");
		rs=pst.executeQuery();
		while(rs.next()){
			Ownershipbean user=new Ownershipbean();
			user.setOsid(rs.getInt(1));
			user.setOname(rs.getString(2));
			user.setFnumber(rs.getString(3));
			user.setFtower(rs.getString(4));
			Ownershipbean.add(user);
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	return Ownershipbean;
	
}
//--------------END----------------------------------------------------------------
public static boolean updateownership(Ownershipbean ownershipbean,Ownerbean ownerbean,Flatbean flatbean)throws Exception{
	con=getdbconnection();
	boolean flage=false;
	try{
		pst=con.prepareStatement("update ownership set o_id=? ,f_id=? where os_id =?");
		
		pst.setInt(1, ownerbean.getOid());
		pst.setInt(2, flatbean.getFid());
		pst.setInt(3, ownershipbean.getOsid());
		pst.executeUpdate();
		flage=true;
	}catch(SQLException sql){
		sql.printStackTrace();
	}finally {
		DBDAO.close();
	}
	
	return flage;
	
}
public static boolean deleteOwnership(Ownershipbean ownershipbean)throws Exception{
	con=getdbconnection();
	boolean flag=false;
	try {
		pst=con.prepareStatement("delete from ownership where os_id =?");
	
	pst.setInt(1, ownershipbean.getOsid());
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
