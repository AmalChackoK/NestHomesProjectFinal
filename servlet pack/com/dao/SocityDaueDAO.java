package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bean.Flatbean;
import com.bean.Socitybeen;
import com.bean.Socitydauebean;




public class SocityDaueDAO {
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
	//------------------END-------------database connection---------sd_id, sdc_id, f_id, sd_date, sd_total, sd_payed socitydaue
	public static boolean insertpayment(Flatbean flatbean,Socitybeen socitybeen,Socitydauebean socitydauebean) throws SQLException{
		con=getdbconnection();
		try{
			String pattern="yyyy-MM-dd";
			String inputpattern="yyyy-MM-dd";
			SimpleDateFormat inputformate=new SimpleDateFormat(inputpattern);
			SimpleDateFormat outputformate=new SimpleDateFormat(pattern);
			String inputdate=socitydauebean.getDate();
			Date datepare =inputformate.parse(inputdate);
			String date =outputformate.format(datepare);
			
			pst=con.prepareStatement("insert into socitydaue(sdc_id,f_id,sd_date,sd_total,sd_payed)values(?,?,?,?,?)");
			pst.setInt(1,socitybeen.getScid());
			pst.setInt(2, flatbean.getFid());
			pst.setString(3,date);
			pst.setDouble(4, socitydauebean.getDaue());
			pst.setDouble(5, socitydauebean.getPayment());
			pst.executeUpdate();
			
			SocityDaueDAO.updatedaue(socitydauebean, flatbean);
			
			flage=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flage;
		
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
	public static ArrayList<Socitybeen> listAllSC()throws SQLException{
		ArrayList<Socitybeen> socitybeen=new ArrayList<Socitybeen>();
		con=getdbconnection();
		try{
			pst=con.prepareStatement("SELECT sdc_id, sdc_cat from socityduescat ;");
			rs=pst.executeQuery();
			while(rs.next()){
				Socitybeen user=new Socitybeen();
				user.setScid(rs.getInt(1));
				user.setSccat(rs.getString(2));
				
				
				socitybeen.add(user);
			}
			
		}catch(SQLException e){  
			e.printStackTrace();
		}
		return socitybeen;
		
	}
	//sd_id, sdc_id, f_id, sd_date, sd_total, sd_payed socitydaue gd_id, f_id, gd_due
	public static void updatedaue(Socitydauebean socitydauebean,Flatbean flatbean) throws SQLException{
		con=getdbconnection();
		try{
			pst=con.prepareStatement("update gasdue set gd_due=? where f_id ="+flatbean.getFid()+" ");
			double gasdaue=socitydauebean.getPayment()-socitydauebean.getDaue();
			pst.setDouble(1,gasdaue );
			pst.executeUpdate();
			flage=true;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBDAO.close();
		}
	}
}
