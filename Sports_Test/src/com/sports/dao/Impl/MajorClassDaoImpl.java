package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.IMajorClassDao;
import com.sports.dao.proxy.DepartmentDaoProxy;
import com.sports.entity.Department;
import com.sports.entity.MajorClass;
import com.sports.entity.MajorClassView;
import com.sports.util.DatabaseConnection;

public class MajorClassDaoImpl implements IMajorClassDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result = 0;

	public MajorClassDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int doCreate(MajorClass majorClass) throws Exception {

		String sql = "insert into majorclass(departmentId,majorClassName) values(?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, majorClass.getDepartmentId());
			pstmt.setString(2, majorClass.getMajorClassName());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {

		String sql = "delete from majorclass where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int doUpdate(int id, MajorClass majorClass) throws Exception {

		String sql = "update majorclass set departmentId=?,majorClassName=? where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, majorClass.getDepartmentId());
			pstmt.setString(2, majorClass.getMajorClassName());
			pstmt.setInt(3, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<MajorClass> findAll() throws Exception {

		List<MajorClass> majorClassList = new ArrayList<MajorClass>();
		MajorClass majorClass = null;
		String sql = "select * from majorclass";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				majorClass = new MajorClass();
				majorClass.setId(rs.getInt(1));
				majorClass.setDepartmentId(rs.getInt(2));
				majorClass.setMajorClassName(rs.getString(3));
				majorClassList.add(majorClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return majorClassList;
	}

	@Override
	public MajorClass findById(int id) throws Exception {

		MajorClass majorClass = null;
		String sql = "select * from majorclass where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				majorClass = new MajorClass();
				majorClass.setId(rs.getInt(1));
				majorClass.setMajorClassName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return majorClass;
	}

	@Override
	public MajorClassView findByIdV(int id) throws Exception {
		MajorClassView majorClassView = null;
		Department department = null;
		String sql = "select * from majorclass where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				majorClassView = new MajorClassView();
				majorClassView.setId(rs.getInt(1));
				// 根据id查找部门
				int depId = rs.getInt(2);
				DepartmentDaoProxy ddp = new DepartmentDaoProxy();
				department = ddp.findById(depId);
				majorClassView
						.setDepartmentName(department.getDepartmentName());

				majorClassView.setMajorClassName(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return majorClassView;
	}

	@Override
	public List<MajorClassView> findAllV() throws Exception {
		List<MajorClassView> majorClassViewList = new ArrayList<MajorClassView>();
		MajorClassView majorClassView = null;
		Department department = null;
		String sql = "select * from majorclass";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				majorClassView = new MajorClassView();
				majorClassView.setId(rs.getInt(1));
				// 根据id查找部门
				int depId = rs.getInt(2);
				DepartmentDaoProxy ddp = new DepartmentDaoProxy();
				department = ddp.findById(depId);
				
				majorClassView
						.setDepartmentName(department.getDepartmentName());

				majorClassView.setMajorClassName(rs.getString(3));
				majorClassViewList.add(majorClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return majorClassViewList;
	}

	@Override
	public List<MajorClassView> findByDepNameV(String depName) throws Exception {
		List<MajorClassView> majorClassViewList=new ArrayList<MajorClassView>();
		MajorClassView majorClassView = null;
		Department department = null;
		String sql = "select * from majorclass where departmentId=?";

		try {
			DepartmentDaoProxy ddp1=new DepartmentDaoProxy();
			int depId=ddp1.getIdByName(depName);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				majorClassView = new MajorClassView();
				majorClassView.setId(rs.getInt(1));
				// 根据id查找部门获取具体的名称
				int DepId = rs.getInt(2);
				DepartmentDaoProxy ddp2 = new DepartmentDaoProxy();
				department = ddp2.findById(DepId);
				majorClassView
						.setDepartmentName(department.getDepartmentName());

				majorClassView.setMajorClassName(rs.getString(3));
				majorClassViewList.add(majorClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return majorClassViewList;
	}

	@Override
	public int getIdByName(String majorClassName) throws Exception {
		
		MajorClass majorClass = null;
		String sql = "select * from majorclass where majorClassName=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, majorClassName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				majorClass = new MajorClass();
				majorClass.setId(rs.getInt(1));
				majorClass.setDepartmentId(rs.getInt(2));
				majorClass.setMajorClassName(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return majorClass.getId();
	}
}
