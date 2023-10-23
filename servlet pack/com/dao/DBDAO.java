package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDAO {
	
		private static Connection dbcon;
		private static String dburl;
		private static String dbdriver;
		private static String user;
		private static String password;
		//--------------START------database initialization---------------
		/**
		*
		* @author: Amal K C
		* @Date: 03.14.2023
		* @version : 1.0
		* @purpose :Initialize database connection values
		* @param : Nothing
		 * @return 
		* @see : Nothing
		* @return: Nothing
		*/
		private static void dbInit(){
			try{
				dbdriver="com.mysql.cj.jdbc.Driver";
				dburl="jdbc:mysql://localhost:3306/nest";
				user="root";
				password="Luminar@2023";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//-------------END---------database initialization---------------

		//--------------START----------database connection ------------
		/**
		*
		* @author: Amal K C
		* @Date: 03.14.2023
		* @version : 1.0
		* @purpose : database connection
		* @param : Nothing
		* @throws : Exception in case of missing driver class
		* @return: Connection
		 * @throws SQLException 
		*/
		
		public static void connection() throws SQLException{
			dbInit();
			try{
				Class.forName(dbdriver);
				dbcon=DriverManager.getConnection(dburl, user, password);
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}
		}
		//--------------START----------get db connection ------------
		/**
		*
		* @author : Amal K C
		* @date : 03.14.2023
		* @version: 1.0
		* @purpose: Get Connection
		* @param : Nothing
		* @return : Connection
		*/
		public static Connection getDbcon(){
			return dbcon;
		}
		//------------------END-------------get connection---------

		//--------------START----------set db connection ------------

		/**
		*
		* @author : Amal K C
		* @date : 03.14.2023
		* @version: 1.0
		* @purpose: Set Connection
		* @param : Connection
		* @return : Nothing
		*/
		public static void setDbcon(Connection con){
			dbcon=con;
		}
		//--------------START----------close connection ------------

		/**
		*
		* @author : Amal K C
		* @date : 03.14.2023
		* @version: 1.0
		* @purpose: Close connection
		* @param : Nothing
		* @throws :Exception in case of closing connection
		* @return : Nothing
		*/
		public static void close() throws SQLException{
			dbcon.close();
		}
	}

