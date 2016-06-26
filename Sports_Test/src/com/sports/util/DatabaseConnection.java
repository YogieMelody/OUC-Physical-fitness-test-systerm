package com.sports.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/sports_test";
	private static final String userName="root";
	private static final String password="1234";
	private Connection conn=null;
	public DatabaseConnection() throws Exception{//在构造方法中进行数据库连接
		try{
			Class.forName(driver);
			this.conn=DriverManager.getConnection(url,userName,password);
		}catch(Exception e){
			throw e;
		}
	}
	
	public  Connection getConnection(){
		return this.conn;
	}
	
//	public void closeConnection() throws Exception{
//		if(this.conn!=null){
//			try{
//				this.conn.close();
//			}catch(Exception e){
//				throw e;
//			}
//		}
//	}
	
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static void closePreparedStatement(PreparedStatement pstmt){
		if(pstmt!=null){
			try {
				pstmt.close();
		    } catch (SQLException e) {
			e.printStackTrace();
		    }
		}
	}
	
	public static void closeResultSet(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
