package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.INationDao;
import com.sports.entity.Nation;
import com.sports.util.DatabaseConnection;

public class NationDaoImpl implements INationDao {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	public NationDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public String getNameById(int id) {
		String sql="select * from nation where id=?";
		Nation nation=null;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				nation=new Nation();
				nation.setId(rs.getInt(1));
				nation.setNationName(rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return nation.getNationName();
	}

	@Override
	public int getIdByName(String name) {
		
		String sql="select * from nation where nationName=?";
		Nation nation=null;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			while(rs.next()){
				nation=new Nation();
				nation.setId(rs.getInt(1));
				nation.setNationName(rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		
		return nation.getId();
	}
	@Override
	public List<Nation> findAll() throws Exception {
		
		String sql="select * from nation";
		Nation nation=null;
		List<Nation> nationList=new ArrayList<Nation>();
		
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				nation=new Nation();
				nation.setId(rs.getInt(1));
				nation.setNationName(rs.getString(2));
				nationList.add(nation);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return nationList;
	}

}
