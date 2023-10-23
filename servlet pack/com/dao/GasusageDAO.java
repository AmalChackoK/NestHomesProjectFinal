package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.bean.Flatbean;
import com.bean.Gasratebean;
import com.bean.Usagebean;

public class GasusageDAO {
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
	//------------------END-------------database connection---------gasusage  gu_id, f_id, gu_unit
		public static boolean insert(Usagebean usagebean,Flatbean flatbean){
			try{
				con=getdbconnection();
				pst=con.prepareStatement("insert into gasusage(f_id, gu_unit)values(?,?)");
				pst.setInt(1, flatbean.getFid());
				pst.setDouble(2, usagebean.getUsage());
				
				pst.executeUpdate();
				calculatedue(flatbean);
				flage=true;
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return flage;
			
		}
		//--------------END------insert details-------------------------flats
				//------------to view-----f_id, f_number, f_tower, f_type 
				public static ArrayList<Flatbean> listAllflat()throws SQLException{
					ArrayList<Flatbean> flatbean=new ArrayList<Flatbean>();
					con=getdbconnection();
					try{
						pst=con.prepareStatement("SELECT f_id, f_number,f_tower FROM flats;");
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
				//flats  f_id, f_number, f_tower, f_type   gasusage gu_id, f_id, gu_unit
				public static ArrayList<Usagebean> listAllusage()throws SQLException{
					ArrayList<Usagebean> usagebean=new ArrayList<Usagebean>();
					con=getdbconnection();
					try{
						pst=con.prepareStatement("SELECT gasusage.gu_id, flats.f_number, flats.f_tower, gasusage.gu_unit FROM gasusage, flats WHERE gasusage.f_id=flats.f_id ");
						rs=pst.executeQuery();
						while(rs.next()){
							Usagebean user=new Usagebean();
							user.setGuid(rs.getInt(1));
							user.setFnumber(rs.getString(2));
							user.setFtower(rs.getString(3));
							user.setUsage(rs.getFloat(4));
						
							usagebean.add(user);
						}
						
					}catch(SQLException e){
						e.printStackTrace();
					}
					return usagebean;
					
				}
				public static ArrayList<Usagebean> fatchusage(int guid)throws SQLException{
					ArrayList<Usagebean> usagebean=new ArrayList<Usagebean>();
					con=getdbconnection();
					try{
						pst=con.prepareStatement("SELECT gasusage.gu_id, flats.f_number, flats.f_tower, gasusage.gu_unit FROM gasusage, flats WHERE gasusage.f_id=flats.f_id AND gasusage.gu_id="+guid+" ");
						rs=pst.executeQuery();
						while(rs.next()){
							Usagebean user=new Usagebean();
							user.setGuid(rs.getInt(1));
							user.setFnumber(rs.getString(2));
							user.setFtower(rs.getString(3));
							user.setUsage(rs.getFloat(4));
							usagebean.add(user);
						}
						
					}catch(SQLException e){
						e.printStackTrace();
					}
					return usagebean;
				}
					public static boolean updateusage(Usagebean usagebean,Flatbean flatbean)throws Exception{
						con=getdbconnection();
						boolean flage=false;
						try{
							pst=con.prepareStatement("update gasusage set f_id=? ,gu_unit=? where gu_id =?");
							
							pst.setInt(1,flatbean.getFid());
							pst.setDouble(2, usagebean.getUsage());
							
							pst.setInt(3, usagebean.getGuid());
							pst.executeUpdate();
							flage=true;
						}catch(SQLException sql){
							sql.printStackTrace();
						}finally {
							DBDAO.close();
						}
						
						return flage;
						
					}
					public static boolean deleteusage(Usagebean usagebean)throws Exception{
						con=getdbconnection();
						boolean flag=false;
						try {
							pst=con.prepareStatement("delete from gasusage where gu_id =?");
						
						pst.setInt(1,usagebean.getGuid());
						pst.executeUpdate();
						flag=true;
						}catch(SQLException sql){
						sql.printStackTrace();
						}finally{
						DBDAO.close();
						}
						return flag;
						}
					//gasdue  gd_id, f_id, gd_due
					public static Usagebean fetchUsage(Flatbean flatbean)throws IOException{
						Usagebean usagebean=new Usagebean();
						try {
							con=getdbconnection();
							pst=con.prepareStatement("SELECT gu_unit FROM gasusage where f_id="+flatbean.getFid()+" ");
							rs=pst.executeQuery();
							while(rs.next()){
								usagebean.setUsage(rs.getDouble(1));
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
						return usagebean;
						
					}
					//g_id, g_rate  gasrate
						public static Gasratebean fatchrate() throws SQLException{
							Gasratebean gasratebean=new Gasratebean();
							con=getdbconnection();
							try{
								pst=con.prepareStatement("SELECT g_rate FROM gasrate");
								rs=pst.executeQuery();
								while(rs.next()){
									gasratebean.setGrate(rs.getInt(1));
									
								}
								
							}catch(SQLException e){
								e.printStackTrace();
							}
							return gasratebean;
							
						}
						//gasdue  gd_id, f_id, gd_due
						public static void calculatedue(Flatbean flatbean) throws IOException, SQLException{
							Usagebean usagebean=null;
							Gasratebean gasratebean=null;
							
							usagebean=fetchUsage(flatbean);
							gasratebean=fatchrate();
							
							double gasdues=usagebean.getUsage()*gasratebean.getGrate();
							System.out.println("dues"+gasdues);
							
							con=getdbconnection();
							try{
								pst=con.prepareStatement("INSERT INTO gasdue(f_id, gd_due) VALUES(?, ?)");
								pst.setInt(1, flatbean.getFid());
								pst.setDouble(2, gasdues);
								pst.executeUpdate();
							}catch(Exception e){
								e.printStackTrace();
								
							}finally {
								DBDAO.close();
							}
							
							
							
						}
						//gd_id, f_id, gd_due, gd_amont  gasdue
						public static void updateGasusageamount(Flatbean flatbean) throws IOException, SQLException{
							Usagebean usagebean=null;
							Gasratebean gasratebean=null;
							
							usagebean=fetchUsage(flatbean);
							gasratebean=fatchrate();
							
							double gasdues=usagebean.getUsage()*gasratebean.getGrate();
							
							
							con=getdbconnection();
							try{
								pst=con.prepareStatement("update gasdue set gd_amont=? where f_id ="+flatbean.getFid()+"");
							
								pst.setDouble(1, gasdues);
								pst.executeQuery();
							}catch(Exception e){
								e.printStackTrace();
								
							}finally {
								DBDAO.close();
							}
							
							
							
						}
				}

