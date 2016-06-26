package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITeacherDao;
import com.sports.entity.Teacher;
import com.sports.util.DatabaseConnection;

public class TeacherDaoImpl implements ITeacherDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result = 0;

	public TeacherDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int doCreate(Teacher teacher) throws Exception {

		String sql = "insert into teacher(teaNumber,teaPassword,teaName,teaSex,teaPosition,teaPhone,teaEmail) values(?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeaNumber());
			pstmt.setString(2, teacher.getTeaPassword());
			pstmt.setString(3, teacher.getTeaName());
			pstmt.setString(4, teacher.getTeaSex());
			pstmt.setString(5, teacher.getTeaPosition());
			pstmt.setString(6, teacher.getTeaPhone());
			pstmt.setString(7, teacher.getTeaEmail());
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

		String sql = "delete from teacher where id=?";

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
	public int doUpdate(int id, Teacher teacher) throws Exception {

		String sql = "update teacher set teaNumber=?,teaName=?,teaSex=?,teaPosition=?,teaPhone=?,teaEmail=? where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeaNumber());
			pstmt.setString(2, teacher.getTeaName());
			pstmt.setString(3, teacher.getTeaSex());
			pstmt.setString(4, teacher.getTeaPosition());
			pstmt.setString(5, teacher.getTeaPhone());
			pstmt.setString(6, teacher.getTeaEmail());
			pstmt.setInt(7, id);
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
	public List<Teacher> findAll() throws Exception {

		List<Teacher> teacherList = new ArrayList<Teacher>();
		Teacher teacher = null;
		String sql = "select * from teacher";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setTeaNumber(rs.getString(2));
				teacher.setTeaPassword(rs.getString(3));
				teacher.setTeaName(rs.getString(4));
				teacher.setTeaSex(rs.getString(5));
				teacher.setTeaPosition(rs.getString(6));
				teacher.setTeaPhone(rs.getString(7));
				teacher.setTeaEmail(rs.getString(8));
				teacherList.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return teacherList;
	}

	@Override
	public Teacher findById(int id) throws Exception {

		Teacher teacher = null;
		String sql = "select * from teacher where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setTeaNumber(rs.getString(2));
				teacher.setTeaPassword(rs.getString(3));
				teacher.setTeaName(rs.getString(4));
				teacher.setTeaSex(rs.getString(5));
				teacher.setTeaPosition(rs.getString(6));
				teacher.setTeaPhone(rs.getString(7));
				teacher.setTeaEmail(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return teacher;
	}

	@Override
	public Teacher findByTeaNumber(String teaNumber) throws Exception {

		Teacher teacher = null;
		String sql = "select * from teacher where teaNumber=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teaNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setTeaNumber(rs.getString(2));
				teacher.setTeaPassword(rs.getString(3));
				teacher.setTeaName(rs.getString(4));
				teacher.setTeaSex(rs.getString(5));
				teacher.setTeaPosition(rs.getString(6));
				teacher.setTeaPhone(rs.getString(7));
				teacher.setTeaEmail(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return teacher;
	}

	@Override
	public Teacher findByTeaName(String teaName) throws Exception {

		Teacher teacher = null;
		String sql = "select * from teacher where teaName=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teaName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setTeaNumber(rs.getString(2));
				teacher.setTeaPassword(rs.getString(3));
				teacher.setTeaName(rs.getString(4));
				teacher.setTeaSex(rs.getString(5));
				teacher.setTeaPosition(rs.getString(6));
				teacher.setTeaPhone(rs.getString(7));
				teacher.setTeaEmail(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return teacher;
	}

	@Override
	public int doChangePassword(String teaNumber, String teaPassword)
			throws Exception {

		String sql = "update teacher set teaPassword=? where teaNumber=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teaPassword);
			pstmt.setString(2, teaNumber);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

}
