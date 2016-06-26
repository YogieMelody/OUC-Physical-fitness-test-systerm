package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sports.dao.IUserDao;
import com.sports.entity.ManagerLogin;
import com.sports.entity.Student;
import com.sports.entity.Teacher;

public class UserDaoImpl implements IUserDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean findLoginStudent(Student student) throws Exception {
		boolean flag = false;
		try {
			String sql = "select stuNumber from student where stuNumber=? and stuPassword=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, student.getStuNumber());
			this.pstmt.setString(2, student.getStuPassword());
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				student.setStuNumber(rs.getString(1));
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (this.pstmt != null) {
				try {
					this.pstmt.close();
				} catch (Exception e) {
					throw e;
				}
			}
			if (this.conn != null) {
				try {
					this.conn.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public boolean findLoginTeacher(Teacher teacher) throws Exception {
		boolean flag = false;
		try {
			String sql = "select teaNumber from teacher where teaNumber=? and teaPassword=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, teacher.getTeaNumber());
			this.pstmt.setString(2, teacher.getTeaPassword());
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				teacher.setTeaNumber(rs.getString(1));
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (this.pstmt != null) {
				try {
					this.pstmt.close();
				} catch (Exception e) {
					throw e;
				}
			}
			if (this.conn != null) {
				try {
					this.conn.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public boolean findLoginManager(ManagerLogin managerLogin) throws Exception {
		boolean flag = false;
		try {
			String sql = "select * from manager where managerId=? and managerPwd=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, managerLogin.getManagerId());
			this.pstmt.setString(2, managerLogin.getManagerPwd());
			ResultSet rs = this.pstmt.executeQuery();
			if (rs.next()) {
				managerLogin.setManagerId(rs.getString(1));
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (this.pstmt != null) {
				try {
					this.pstmt.close();
				} catch (Exception e) {
					throw e;
				}
			}
			if (this.conn != null) {
				try {
					this.conn.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

}
