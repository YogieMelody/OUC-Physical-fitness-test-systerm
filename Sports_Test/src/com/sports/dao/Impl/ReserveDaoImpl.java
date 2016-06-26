package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.IReserveDao;
import com.sports.entity.Reserve;
import com.sports.util.DatabaseConnection;

public class ReserveDaoImpl implements IReserveDao {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private int result=0;
	
	public ReserveDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public int doCreate(Reserve reserve) throws Exception {
		
		String sql="insert into reserve(testTermId,testClassId,stuId) values (?,?,?)";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, reserve.getTestTermId());
			pstmt.setInt(2, reserve.getTestClassId());
			pstmt.setInt(3, reserve.getStuId());
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {
		
		String sql="delete from reserve where id=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public Reserve findNowReserve(int testTermId, int stuId) throws Exception {
		
		Reserve reserve=null;
		String sql="select * from reserve where testTermId=? and stuId=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, testTermId);
			pstmt.setInt(2, stuId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				reserve=new Reserve();
				reserve.setId(rs.getInt(1));
				reserve.setTestTermId(rs.getInt(2));
				reserve.setTestClassId(rs.getInt(3));
				reserve.setStuId(rs.getInt(4));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return reserve;
	}
	@Override
	public List<Reserve> findByTestTermIdAndTestClassId(int testTermId,
			int testClassId) throws Exception {
		
		List<Reserve> rList=new ArrayList<Reserve>();
		Reserve reserve=null;
		String sql="select * from reserve where testTermId=? and testClassId=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, testTermId);
			pstmt.setInt(2, testClassId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				reserve=new Reserve();
				reserve.setId(rs.getInt(1));
				reserve.setTestTermId(rs.getInt(2));
				reserve.setTestClassId(rs.getInt(3));
				reserve.setStuId(rs.getInt(4));
				rList.add(reserve);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return rList;
	}

}
