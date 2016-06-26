package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITestTermDao;
import com.sports.entity.TestTerm;
import com.sports.util.DatabaseConnection;

public class TestTermDaoImpl implements ITestTermDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;
	private int result = 0;

	public TestTermDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int doCreate(TestTerm testTerm) throws Exception {

		String sql = "insert into testterm(testTermName,isNow,isOpen) values (?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testTerm.getTestTermName());
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
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

		String sql = "delete from testterm where id=?";

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
	public int doUpdate(int id, TestTerm testTerm) throws Exception {

		String sql = "update testterm set testTermName=?,isNow=? where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testTerm.getTestTermName());
			pstmt.setInt(2, testTerm.getIsNow());
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
	public List<TestTerm> findAll() throws Exception {

		List<TestTerm> testTermList = new ArrayList<TestTerm>();
		TestTerm testTerm = null;
		String sql = "select * from testterm order by testTermName desc";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testTerm = new TestTerm();
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
				testTermList.add(testTerm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testTermList;
	}

	@Override
	public TestTerm findById(int id) throws Exception {
		TestTerm testTerm = null;
		String sql = "select * from testterm where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testTerm = new TestTerm();
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testTerm;
	}

	@Override
	public TestTerm findNow() throws Exception {

		TestTerm testTerm = new TestTerm();

		String sql = "select * from testterm where isNow=1";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testTerm;
	}

	@Override
	public int setNow(int id) throws Exception {

		String sql1 = "update testterm set isNow=1 where id=?";
		String sql2 = "update testterm set isNow=0 where id!=?";

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, id);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, id);
			result = pstmt.executeUpdate() * pstmt2.executeUpdate();
			if (result > 0) {
				conn.commit();
				conn.setAutoCommit(true);
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int OpenBook() throws Exception {
		TestTerm testTerm = new TestTerm();

		String sql = "select * from testterm where isNow=1";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int i = testTerm.getIsOpen();

		// 打开
		if (i == 0) {
			String sql1 = "update testterm set isOpen=1 where isNow=1";
			try {
				pstmt = conn.prepareStatement(sql1);
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closePreparedStatement(pstmt);
				DatabaseConnection.closeConnection(conn);
			}
		}
		return result;
	}

	@Override
	public int CloseBook() throws Exception {

		TestTerm testTerm = new TestTerm();

		String sql = "select * from testterm where isNow=1";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int i = testTerm.getIsOpen();

		// 关闭
		if (i == 1) {
			String sql1 = "update testterm set isOpen=0 where isNow=1";
			try {
				pstmt = conn.prepareStatement(sql1);
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DatabaseConnection.closePreparedStatement(pstmt);
				DatabaseConnection.closeConnection(conn);
			}
		}
		return result;
	}

	@Override
	public List<TestTerm> findByKeyWord(String key) throws Exception {

		List<TestTerm> testTermList = new ArrayList<TestTerm>();
		TestTerm testTerm = null;
		String sql = "select * from testterm where testTermName like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testTerm = new TestTerm();
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
				testTermList.add(testTerm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testTermList;
	}

	@Override
	public List<TestTerm> findByGrade(String stuGradeNow) throws Exception {

		List<TestTerm> testTermList = new ArrayList<TestTerm>();
		TestTerm testTerm = null;
		// 注意此sql语句的逻辑
		String sql = "select * from testterm order by testTermName desc limit 8";
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, stuGradeNow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testTerm = new TestTerm();
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
				testTermList.add(testTerm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testTermList;
	}

	@Override
	public TestTerm findByTestTermName(String testTermName) throws Exception {
		TestTerm testTerm = null;
		String sql = "select * from testterm where testTermName=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testTermName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testTerm = new TestTerm();
				testTerm.setId(rs.getInt(1));
				testTerm.setTestTermName(rs.getString(2));
				testTerm.setIsNow(rs.getInt(3));
				testTerm.setIsOpen(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testTerm;
	}

}
