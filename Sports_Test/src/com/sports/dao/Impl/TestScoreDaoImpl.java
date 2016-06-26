package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITestScoreDao;
import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.dao.proxy.TestClassDaoProxy;
import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.TestScore;
import com.sports.entity.TestScoreView;
import com.sports.entity.TestScoreViewNew;
import com.sports.util.DatabaseConnection;

public class TestScoreDaoImpl implements ITestScoreDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result = 0;

	public TestScoreDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int doCreate(TestScore testScore) throws Exception {

		String sql = "insert into testscore(testClassId,testTermId,stuNumber,height,weight,vitalCapacity,fiftyRun,jump,sitAndReach,eightHundredsRun,oneThousandRun,situp,pullup) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testScore.getTestClassId());
			pstmt.setInt(2, testScore.getTestTermId());
			pstmt.setString(3, testScore.getStuNumber());
			pstmt.setDouble(4, testScore.getHeight());
			pstmt.setDouble(5, testScore.getWeight());
			pstmt.setDouble(6, testScore.getVitalCapacity());
			pstmt.setDouble(7, testScore.getFiftyRun());
			pstmt.setDouble(8, testScore.getJump());
			pstmt.setDouble(9, testScore.getSitAndReach());
			pstmt.setString(10, testScore.getEightHundredsRun());
			pstmt.setString(11, testScore.getOneThousandRun());
			pstmt.setInt(12, testScore.getSitup());
			pstmt.setInt(13, testScore.getPullup());
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(int testTermId, String stuNumber, TestScore testScore)
			throws Exception {

		String sql = "update testscore set height=?,weight=?,vitalCapacity=?,fiftyRun=?,jump=?,sitAndReach=?,eightHundredsRun=?,oneThousandRun=?,situp=?,pullup=? where testTermId=? and stuNumber=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, testScore.getHeight());
			pstmt.setDouble(2, testScore.getWeight());
			pstmt.setDouble(3, testScore.getVitalCapacity());
			pstmt.setDouble(4, testScore.getFiftyRun());
			pstmt.setDouble(5, testScore.getJump());
			pstmt.setDouble(6, testScore.getSitAndReach());
			pstmt.setString(7, testScore.getEightHundredsRun());
			pstmt.setString(8, testScore.getOneThousandRun());
			pstmt.setInt(9, testScore.getSitup());
			pstmt.setInt(10, testScore.getPullup());
			pstmt.setInt(11, testTermId);
			pstmt.setString(12, stuNumber);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TestScore> findAll() throws Exception {
		List<TestScore> tsList = new ArrayList<TestScore>();
		TestScore ts = null;
		String sql = "select * from testscore";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ts = new TestScore();
				ts.setId(rs.getInt(1));
				ts.setTestClassId(rs.getInt(2));
				ts.setTestTermId(rs.getInt(3));
				ts.setStuNumber(rs.getString(4));
				ts.setHeight(rs.getDouble(5));
				ts.setWeight(rs.getDouble(6));
				ts.setVitalCapacity(rs.getDouble(7));
				ts.setFiftyRun(rs.getDouble(8));
				ts.setJump(rs.getDouble(9));
				ts.setSitAndReach(rs.getDouble(10));
				ts.setEightHundredsRun(rs.getString(11));
				ts.setOneThousandRun(rs.getString(12));
				ts.setSitup(rs.getInt(13));
				ts.setPullup(rs.getInt(14));
				tsList.add(ts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return tsList;
	}

	@Override
	public TestScore findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestScoreView findByTestTermIdAndStuNumber(int testTermId,
			String stuNumber) throws Exception {

		TestScoreView tsv = null;
		String sql = "select * from testscore where testTermId=? and stuNumber=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testTermId);
			pstmt.setString(2, stuNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tsv = new TestScoreView();
				tsv.setId(rs.getInt(1));

				// 返回测试班级名称
				int testClassId = rs.getInt(2);
				TestClassDaoProxy tcdp = new TestClassDaoProxy();
				tsv.setTestClassName(tcdp.findById(testClassId)
						.getTestClassName());

				// 返回测试学期名称
				int ttId = rs.getInt(3);
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				tsv.setTestTermName(ttdp.findById(ttId).getTestTermName());

				tsv.setStuNumber(rs.getString(4));
				tsv.setHeight(rs.getDouble(5));
				tsv.setWeight(rs.getDouble(6));
				tsv.setVitalCapacity(rs.getDouble(7));
				tsv.setFiftyRun(rs.getDouble(8));
				tsv.setJump(rs.getDouble(9));
				tsv.setSitAndReach(rs.getDouble(10));
				tsv.setEightHundredsRun(rs.getString(11));
				tsv.setOneThousandRun(rs.getString(12));
				tsv.setSitup(rs.getInt(13));
				tsv.setPullup(rs.getInt(14));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return tsv;
	}

	@Override
	public TestScore findByTestTermIdAndStuNumberC(int testTermId,
			String stuNumber) throws Exception {
		TestScore ts = null;
		String sql = "select * from testscore where testTermId=? and stuNumber=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testTermId);
			pstmt.setString(2, stuNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ts = new TestScore();
				ts.setId(rs.getInt(1));
				ts.setTestClassId(rs.getInt(2));
				ts.setTestTermId(rs.getInt(3));
				ts.setStuNumber(rs.getString(4));
				ts.setHeight(rs.getDouble(5));
				ts.setWeight(rs.getDouble(6));
				ts.setVitalCapacity(rs.getDouble(7));
				ts.setFiftyRun(rs.getDouble(8));
				ts.setJump(rs.getDouble(9));
				ts.setSitAndReach(rs.getDouble(10));
				ts.setEightHundredsRun(rs.getString(11));
				ts.setOneThousandRun(rs.getString(12));
				ts.setSitup(rs.getInt(13));
				ts.setPullup(rs.getInt(14));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return ts;
	}

	@Override
	public List<TestScoreViewNew> findByTestClassId(int testClassId)
			throws Exception {
		List<TestScoreViewNew> tsvnList = new ArrayList<TestScoreViewNew>();
		TestScoreViewNew tsvn = null;
		String sql = "select * from testscore where testClassId=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testClassId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tsvn = new TestScoreViewNew();
				tsvn.setId(rs.getInt(1));

				// 返回测试班级名称
				int testClassId1 = rs.getInt(2);
				TestClassDaoProxy tcdp = new TestClassDaoProxy();
				tsvn.setTestClassName(tcdp.findById(testClassId1)
						.getTestClassName());

				// 返回测试学期名称
				int ttId = rs.getInt(3);
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				tsvn.setTestTermName(ttdp.findById(ttId).getTestTermName());

				// 返回学生姓名
				String stuNumber = rs.getString(4);
				StudentDaoProxy sdp = new StudentDaoProxy();
				tsvn.setStuName(sdp.findByStuNumber(stuNumber).getStuName());

				tsvn.setHeight(rs.getDouble(5));
				tsvn.setWeight(rs.getDouble(6));
				tsvn.setVitalCapacity(rs.getDouble(7));
				tsvn.setFiftyRun(rs.getDouble(8));
				tsvn.setJump(rs.getDouble(9));
				tsvn.setSitAndReach(rs.getDouble(10));
				tsvn.setEightHundredsRun(rs.getString(11));
				tsvn.setOneThousandRun(rs.getString(12));
				tsvn.setSitup(rs.getInt(13));
				tsvn.setPullup(rs.getInt(14));
				tsvnList.add(tsvn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return tsvnList;
	}

	@Override
	public int doPartUpdate(int testTermId, String stuNumber,
			TestScore testScore) throws Exception {
		if (testScore.getHeight() != 0.0D) {
			String sql = "update testscore set height=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getHeight());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result = this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 预防身高为空的情况
			result = 1;
		}
		if (testScore.getWeight() != 0.0D) {
			String sql = "update testscore set weight=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getWeight());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getVitalCapacity() != 0.0D) {
			String sql = "update testscore set vitalCapacity=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getVitalCapacity());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getFiftyRun() != 0.0D) {
			String sql = "update testscore set fiftyRun=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getFiftyRun());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getJump() != 0.0D) {
			String sql = "update testscore set jump=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getJump());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getSitAndReach() != 0.0D) {
			String sql = "update testscore set sitAndReach=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getSitAndReach());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getEightHundredsRun() != null) {
			String sql = "update testscore set eightHundredsRun=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, testScore.getEightHundredsRun());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getOneThousandRun() != null) {
			String sql = "update testscore set oneThousandRun=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, testScore.getOneThousandRun());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getSitup() != 0) {
			String sql = "update testscore set situp=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getSitup());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (testScore.getPullup() != 0) {
			String sql = "update testscore set pullup=? where testTermId=? and stuNumber=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setDouble(1, testScore.getPullup());
				this.pstmt.setInt(2, testTermId);
				this.pstmt.setString(3, stuNumber);
				this.result *= this.pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		DatabaseConnection.closePreparedStatement(this.pstmt);
		DatabaseConnection.closeConnection(this.conn);
		return result;
	}

}
