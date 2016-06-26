package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.IDepartmentDao;
import com.sports.entity.Department;
import com.sports.util.DatabaseConnection;

public class DepartmentDaoImpl implements IDepartmentDao {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private int result=0;
	
	public DepartmentDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public int doCreate(Department department) throws Exception {

		String sql="insert into department(departmentName) values (?)";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, department.getDepartmentName());
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
		
		String sql="delete from department where id=?";
		
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
	public int doUpdate(int id, Department department) throws Exception {
		
		String sql="update department set departmentName=? where id=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, department.getDepartmentName());
			pstmt.setInt(2, id);
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
	public List<Department> findAll() throws Exception {
		
		List<Department> departmentList=new ArrayList<Department>();
		Department department=null;
		
		String sql="select * from department";
				
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				department=new Department();
				department.setId(rs.getInt(1));
				department.setDepartmentName(rs.getString(2));
				departmentList.add(department);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return departmentList;
	}

	@Override
	public Department findById(int id) throws Exception {
		
		Department department=null;
		
		String sql="select * from department where id=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				department=new Department();
				department.setId(rs.getInt(1));
				department.setDepartmentName(rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return department;
	}
	
	//此类方法都没有关闭，为的是执行后续的操作
	@Override
	public int getIdByName(String departmentName) throws Exception {

		int id=0;
		
		String sql="select id from department where departmentName=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, departmentName);
			rs=pstmt.executeQuery();
			while(rs.next()){
				id=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return id;
	}

}
