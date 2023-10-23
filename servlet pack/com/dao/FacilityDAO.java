package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Facilitybeen;


public class FacilityDAO {
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
	public static boolean insert(Facilitybeen facilitybeen)throws SQLException{
		con=getdbconnection();
		try{
			
			pst=con.prepareStatement("insert into facility(fm_name,fm_des,fm_duty,fm_phone)values(?,?,?,?)");
			pst.setString(1,facilitybeen.getFmname());
			pst.setString(2,facilitybeen.getFmdes());
			pst.setString(3,facilitybeen.getFmduty());
			pst.setString(4,facilitybeen.getFmphone());
			pst.executeUpdate();
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return flage;
		
	}
	//------------to view-----
		public static ArrayList<Facilitybeen> listAllfm()throws SQLException{
			ArrayList<Facilitybeen> facilitybeen=new ArrayList<Facilitybeen>();
			con=getdbconnection();
			try{
			pst=con.prepareStatement("SELECT * FROM facility;");
			rs=pst.executeQuery();
			while(rs.next()){
				Facilitybeen user=new Facilitybeen();
				user.setFmid(rs.getInt(1));
				user.setFmname(rs.getString(2));
				user.setFmdes(rs.getString(3));
				user.setFmduty(rs.getString(4));
				user.setFmphone(rs.getString(5));
				facilitybeen.add(user);
			}
			}catch(SQLException e){
			e.printStackTrace();
			}
			return facilitybeen;
			}

			//--------------END----------------------------------------------------------------
		public static ArrayList<Facilitybeen> fetchfmById(int fmid)throws SQLException{
			ArrayList<Facilitybeen> facilitybeen=new ArrayList<Facilitybeen>();
			con=getdbconnection();
			try{
			pst=con.prepareStatement("SELECT * FROM facility where fm_id="+fmid+" ");
			rs=pst.executeQuery();
			while(rs.next()){
				Facilitybeen cat=new Facilitybeen();
			cat.setFmid(rs.getInt(1));
			cat.setFmname(rs.getString(2));
			cat.setFmdes(rs.getString(3));
			cat.setFmduty(rs.getString(4));
			cat.setFmphone(rs.getString(5));
			facilitybeen.add(cat);
			}
			}catch(SQLException e){
			e.printStackTrace();
			}
			return facilitybeen;
			}
			//--------------END----------------------------------------------------------------
		//fm_id, fm_name, fm_des, fm_duty, fm_phone
		public static boolean updateuser(Facilitybeen facilitybeen)throws Exception{
			con=getdbconnection();
			boolean flag=false;
			try {
			pst=con.prepareStatement("update facility set fm_name=? ,fm_des=? ,fm_duty=?,fm_phone=? where fm_id =?");
			pst.setString(1,facilitybeen.getFmname());
			pst.setString(2,facilitybeen.getFmdes());
			pst.setString(3,facilitybeen.getFmduty());
			pst.setString(4, facilitybeen.getFmphone());
			pst.setInt(5,facilitybeen.getFmid());
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
		public static boolean deleteuser(Facilitybeen facilitybeen)throws Exception{
			con=getdbconnection();
			boolean flag=false;
			try {
				pst=con.prepareStatement("delete from facility where fm_id =?");
			
			pst.setInt(1, facilitybeen.getFmid());
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
