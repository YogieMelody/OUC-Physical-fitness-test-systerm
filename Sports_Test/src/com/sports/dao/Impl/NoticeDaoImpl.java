package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sports.dao.INoticeDao;
import com.sports.entity.Notice;
import com.sports.util.DatabaseConnection;

public class NoticeDaoImpl implements INoticeDao {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private int result=0;
	
	public NoticeDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public int doCreate(Notice notice) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat();
		Date date=new Date();
		String formatDate=sdf.format(date);
		
		String sql="insert into notice(time,title,content) values(?,?,?)";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, formatDate);
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();;
		}finally{
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {
		
		String sql="delete from notice where id=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();;
		}finally{
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int doUpdate(int id, Notice notice) throws Exception {
		
		String sql="update notice set title=?,content=? where id=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, id);
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
	public List<Notice> findAll() throws Exception {
		
		List<Notice> noticeList=new ArrayList<Notice>();
		String sql="select * from notice order by time desc";
		Notice notice=null;
				
		try{
		    pstmt=conn.prepareStatement(sql);
		    rs=pstmt.executeQuery();
		    while(rs.next()){
		    	notice=new Notice();
		    	notice.setId(rs.getInt(1));
		    	notice.setTime(rs.getString(2));
		    	notice.setTitle(rs.getString(3));
		    	notice.setContent(rs.getString(4));
		    	noticeList.add(notice);
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return noticeList;
	}

	@Override
	public Notice findById(int id) throws Exception {
		
		Notice notice=null;
		String sql="select * from notice where id=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				notice=new Notice();
				notice.setId(rs.getInt(1));
				notice.setTime(rs.getString(2));
				notice.setTitle(rs.getString(3));
				notice.setContent(rs.getString(4));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return notice;
	}

	@Override
	public List<Notice> findByKeyWord(String key) throws Exception {
		List<Notice> noticeList=new ArrayList<Notice>();
		Notice notice=null;
		String sql="select * from notice where title like ?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+key+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				notice=new Notice();
				notice.setId(rs.getInt(1));
				notice.setTime(rs.getString(2));
				notice.setTitle(rs.getString(3));
				notice.setContent(rs.getString(4));
				noticeList.add(notice);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return noticeList;
	}

}
