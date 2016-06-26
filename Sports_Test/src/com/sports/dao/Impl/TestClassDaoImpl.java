package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITestClassDao;
import com.sports.dao.proxy.TeacherDaoProxy;
import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.Reserve;
import com.sports.entity.TestClass;
import com.sports.entity.TestClassView;
import com.sports.util.DatabaseConnection;

public class TestClassDaoImpl implements ITestClassDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt1 = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;
	private int result = 0;

	public TestClassDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int doCreate(TestClass testClass) throws Exception {
		String sql = "insert into testclass(testClassName,testArea,testTermId,schoolArea,teaId,limitNum,nowNum) values(?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testClass.getTestClassName());
			pstmt.setString(2, testClass.getTestArea());
			pstmt.setInt(3, testClass.getTestTermId());
			pstmt.setString(4, testClass.getSchoolArea());
			pstmt.setInt(5, testClass.getTeaId());
			pstmt.setInt(6, testClass.getLimitNum());
			pstmt.setInt(7, testClass.getNowNum());
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

		String sql = "delete from testClass where id=?";

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
	public int doUpdate(int id, TestClass testClass) throws Exception {

		String sql = "update testClass set testClassName=?,testArea=?,testTermId=?,schoolArea=?,teaId=?,limitNum=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testClass.getTestClassName());
			pstmt.setString(2, testClass.getTestArea());
			pstmt.setInt(3, testClass.getTestTermId());
			pstmt.setString(4, testClass.getSchoolArea());
			pstmt.setInt(5, testClass.getTeaId());
			pstmt.setInt(6, testClass.getLimitNum());
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
	public List<TestClass> findAll() throws Exception {

		List<TestClass> testClassList = new ArrayList<TestClass>();
		TestClass testClass = null;
		String sql = "select * from testClass";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClass = new TestClass();
				testClass.setId(rs.getInt(1));
				testClass.setTestClassName(rs.getString(2));
				testClass.setTestArea(rs.getString(3));
				testClass.setTestTermId(rs.getInt(4));
				testClass.setSchoolArea(rs.getString(5));
				testClass.setTeaId(rs.getInt(6));
				testClass.setLimitNum(rs.getInt(7));
				testClass.setNowNum(rs.getInt(8));
				testClassList.add(testClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassList;
	}

	@Override
	public List<TestClassView> findAllV() throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();
		TestClassView testClassView = null;
		String sql = "select * from testClass";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClassView = new TestClassView();
				testClassView.setId(rs.getInt(1));
				testClassView.setTestClassName(rs.getString(2));
				testClassView.setTestArea(rs.getString(3));
				// 获取测试学期的全称
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				String testTermName = ttdp.findById(rs.getInt(4))
						.getTestTermName();
				testClassView.setTermName(testTermName);
				// testClass.setTestTermId(rs.getInt(2));

				testClassView.setSchoolArea(rs.getString(5));

				// 获取教师姓名
				TeacherDaoProxy tdp = new TeacherDaoProxy();
				String teacherName = tdp.findById(rs.getInt(6)).getTeaName();
				testClassView.setTeaName(teacherName);
				// testClass.setTeaId();

				testClassView.setLimitNum(rs.getInt(7));
				testClassView.setNowNum(rs.getInt(8));
				testClassViewList.add(testClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
			;
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassViewList;
	}

	@Override
	public TestClass findById(int id) throws Exception {
		TestClass testClass = null;
		String sql = "select * from testClass where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClass = new TestClass();
				testClass.setId(rs.getInt(1));
				testClass.setTestClassName(rs.getString(2));
				testClass.setTestArea(rs.getString(3));
				testClass.setTestTermId(rs.getInt(4));
				testClass.setSchoolArea(rs.getString(5));
				testClass.setTeaId(rs.getInt(6));
				testClass.setLimitNum(rs.getInt(7));
				testClass.setNowNum(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClass;
	}

	@Override
	public TestClassView findByIdV(int id) throws Exception {
		TestClassView testClassView = null;

		String sql = "select * from testClass where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClassView = new TestClassView();
				testClassView.setId(rs.getInt(1));
				testClassView.setTestClassName(rs.getString(2));
				testClassView.setTestArea(rs.getString(3));

				// 获取测试学期的全称
				TestTermDaoProxy ttdp2 = new TestTermDaoProxy();
				String testTermName = ttdp2.findById(rs.getInt(4))
						.getTestTermName();
				testClassView.setTermName(testTermName);
				// testClass.setTestTermId(rs.getInt(2));

				testClassView.setSchoolArea(rs.getString(5));

				// 获取教师姓名
				TeacherDaoProxy tdp = new TeacherDaoProxy();
				String teacherName = tdp.findById(rs.getInt(6)).getTeaName();
				testClassView.setTeaName(teacherName);
				// testClass.setTeaId();

				testClassView.setLimitNum(rs.getInt(7));
				testClassView.setNowNum(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassView;
	}

	// 这个方法使用了事务，如果其中一条执行失败，就回滚一起失败
	@Override
	public int book(Reserve reserve) throws Exception {
		// 从预约记录中找到班级的Id
		TestClass tc = null;
		String sql = "select * from testClass where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reserve.getTestClassId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tc = new TestClass();
				tc.setId(rs.getInt(1));
				tc.setTestClassName(rs.getString(2));
				tc.setTestArea(rs.getString(3));
				tc.setTestTermId(rs.getInt(4));
				tc.setSchoolArea(rs.getString(5));
				tc.setTeaId(rs.getInt(6));
				tc.setLimitNum(rs.getInt(7));
				tc.setNowNum(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取已选人数和限定人数
		int nowNum = tc.getNowNum();
		int willBe = nowNum + 1;
		int limit = tc.getLimitNum();
		// 预定后预定人数+1
		// 并且插入一条数据到预约表中！！！
		if (nowNum < limit) {
			// 这样会加2。。好奇葩..我怀疑是因为公用了conn导致sql语句执行了两遍，所以直接在前面指定值
			// String sql1 = "update testClass set nowNum=nowNum+1 where id=?";
			String sql1 = "update testclass set nowNum=? where id=?";
			String sql2 = "insert into reserve(testTermId,testClassId,stuId) values (?,?,?)";

			try {
				// 设置事务不自动提交
				// 已选人数+1
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, willBe);
				pstmt1.setInt(2, tc.getId());
				conn.setAutoCommit(false);
				result = pstmt1.executeUpdate();
				// 添加信息到reserve表中
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, reserve.getTestTermId());
				pstmt2.setInt(2, reserve.getTestClassId());
				pstmt2.setInt(3, reserve.getStuId());
				result = result * pstmt2.executeUpdate();
				if (result == 1) {// 两个操作都成功则事务提交
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e) {
				// conn.rollback();
				e.printStackTrace();
			} finally {
				DatabaseConnection.closePreparedStatement(pstmt);
				DatabaseConnection.closePreparedStatement(pstmt1);
				DatabaseConnection.closePreparedStatement(pstmt2);
				DatabaseConnection.closeConnection(conn);
			}
		} else {
			// 用result=-1表示班级已满，无法继续预约
			result = -1;
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	// 和上面的方法相对应，取消预约
	public int cancle(Reserve reserve) throws Exception {

		// 从预约记录中找到班级的Id
		TestClass tc = null;
		String sql = "select * from testClass where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reserve.getTestClassId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tc = new TestClass();
				tc.setId(rs.getInt(1));
				tc.setTestClassName(rs.getString(2));
				tc.setTestArea(rs.getString(3));
				tc.setTestTermId(rs.getInt(4));
				tc.setSchoolArea(rs.getString(5));
				tc.setTeaId(rs.getInt(6));
				tc.setLimitNum(rs.getInt(7));
				tc.setNowNum(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取已选人数和限定人数
		int nowNum = tc.getNowNum();
		int willBe = nowNum - 1;
		// 取消预定后预定人数-1
		// 并且删除预约表中的数据
		String sql1 = "update testclass set nowNum=? where id=?";
		String sql2 = "delete from reserve where testTermId=? and stuId=?";
		try {
			// 已选人数-1
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, willBe);
			pstmt1.setInt(2, tc.getId());
			conn.setAutoCommit(false);
			result = pstmt1.executeUpdate();

			// 删除reserve表中的数据
			// 此处代码可能不规范，需要后期优化
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, reserve.getTestTermId());
			pstmt2.setInt(2, reserve.getStuId());

			result = result * pstmt2.executeUpdate();
			if (result == 1) {// 两个操作都成功则事务提交
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closePreparedStatement(pstmt1);
			DatabaseConnection.closePreparedStatement(pstmt2);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<TestClassView> findByTeaId(int id) throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();
		TestClassView testClassView = null;
		String sql = "select * from testClass where teaId=? order by testClassName desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClassView = new TestClassView();
				testClassView.setId(rs.getInt(1));
				testClassView.setTestClassName(rs.getString(2));
				testClassView.setTestArea(rs.getString(3));

				// 获取测试学期的全称
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				String testTermName = ttdp.findById(rs.getInt(4))
						.getTestTermName();
				testClassView.setTermName(testTermName);
				// testClass.setTestTermId(rs.getInt(2));

				testClassView.setSchoolArea(rs.getString(5));

				// 获取教师姓名
				TeacherDaoProxy tdp = new TeacherDaoProxy();
				String teacherName = tdp.findById(rs.getInt(6)).getTeaName();
				testClassView.setTeaName(teacherName);
				// testClass.setTeaId();

				testClassView.setLimitNum(rs.getInt(7));
				testClassView.setNowNum(rs.getInt(8));
				testClassViewList.add(testClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassViewList;
	}

	public List<TestClass> findByTeaIdC(int id) throws Exception {
		List<TestClass> testClassList = new ArrayList<TestClass>();
		TestClass testClass = null;
		String sql = "select * from testClass where teaId=? order by testClassName desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClass = new TestClass();
				testClass.setId(rs.getInt(1));
				testClass.setTestClassName(rs.getString(2));
				testClass.setTestArea(rs.getString(3));
				testClass.setTestTermId(rs.getInt(4));
				testClass.setSchoolArea(rs.getString(5));
				testClass.setTeaId(rs.getInt(6));
				testClass.setLimitNum(rs.getInt(7));
				testClass.setNowNum(rs.getInt(8));
				testClassList.add(testClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassList;
	}

	@Override
	public List<TestClass> findByPage(int begin, int number) throws Exception {

		List<TestClass> testClassList = new ArrayList<TestClass>();
		TestClass testClass = null;
		String sql = "select * from testClass limit begin,number";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClass = new TestClass();
				testClass.setId(rs.getInt(1));
				testClass.setTestClassName(rs.getString(2));
				testClass.setTestArea(rs.getString(3));
				testClass.setTestTermId(rs.getInt(4));
				testClass.setSchoolArea(rs.getString(5));
				testClass.setTeaId(rs.getInt(6));
				testClass.setLimitNum(rs.getInt(7));
				testClass.setNowNum(rs.getInt(8));
				testClassList.add(testClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassList;
	}

	@Override
	public List<TestClassView> findByFourCondition(String testClassName,
			String schoolArea, int teaId, int limitNum) throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();
		TestClassView testClassView = null;
		String sql = "select * from testClass where testClassName like ? and schoolArea like ? and teaId=? and limitNum<=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + testClassName + "%");
			pstmt.setString(2, "%" + schoolArea + "%");
			pstmt.setInt(3, teaId);
			pstmt.setInt(4, limitNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClassView = new TestClassView();
				testClassView.setId(rs.getInt(1));
				testClassView.setTestClassName(rs.getString(2));
				testClassView.setTestArea(rs.getString(3));
				// 获取测试学期的全称
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				String testTermName = ttdp.findById(rs.getInt(4))
						.getTestTermName();
				testClassView.setTermName(testTermName);
				// testClass.setTestTermId(rs.getInt(2));

				testClassView.setSchoolArea(rs.getString(5));

				// 获取教师姓名
				TeacherDaoProxy tdp = new TeacherDaoProxy();
				String teacherName = tdp.findById(rs.getInt(6)).getTeaName();
				testClassView.setTeaName(teacherName);
				// testClass.setTeaId();

				testClassView.setLimitNum(rs.getInt(7));
				testClassView.setNowNum(rs.getInt(8));
				testClassViewList.add(testClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassViewList;
	}

	@Override
	public List<TestClassView> findByTeaIdAndTestTermIdByLimit(int teaId,
			int testTermId, int begin, int pageNum) throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();
		TestClassView testClassView = null;
		String sql = "select * from testClass where teaId=? and testTermId=? limit ?,?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, teaId);
			pstmt.setInt(2, testTermId);
			pstmt.setInt(3, begin);
			pstmt.setInt(4, pageNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClassView = new TestClassView();
				testClassView.setId(rs.getInt(1));
				testClassView.setTestClassName(rs.getString(2));
				testClassView.setTestArea(rs.getString(3));

				// 获取测试学期的全称
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				String testTermName = ttdp.findById(rs.getInt(4))
						.getTestTermName();
				testClassView.setTermName(testTermName);
				// testClass.setTestTermId(rs.getInt(2));

				testClassView.setSchoolArea(rs.getString(5));

				// 获取教师姓名
				TeacherDaoProxy tdp = new TeacherDaoProxy();
				String teacherName = tdp.findById(rs.getInt(6)).getTeaName();
				testClassView.setTeaName(teacherName);
				// testClass.setTeaId();

				testClassView.setLimitNum(rs.getInt(7));
				testClassView.setNowNum(rs.getInt(8));
				testClassViewList.add(testClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassViewList;
	}

	@Override
	public List<TestClassView> findByThreeCondition(String testClassName,
			String schoolArea, int limitNum) throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();
		TestClassView testClassView = null;
		String sql = "select * from testClass where testClassName like ? and schoolArea like ? and limitNum<=?";
		// 拆分进行sql语句的拼接
		int a = testClassName.indexOf("年");
		int b = testClassName.indexOf("月");
		int c = testClassName.indexOf("日");
		String singleYear = testClassName.substring(0, a);
		String singleMonth = testClassName.substring(a + 1, b);
		String singleDay = testClassName.substring(b + 1, c);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + singleYear + "%" + "年" + "%" + singleMonth
					+ "%" + "月" + "%" + singleDay + "%" + "日 周" + "%");
			pstmt.setString(2, "%" + schoolArea + "%");
			pstmt.setInt(3, limitNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClassView = new TestClassView();
				testClassView.setId(rs.getInt(1));
				testClassView.setTestClassName(rs.getString(2));
				testClassView.setTestArea(rs.getString(3));

				// 获取测试学期的全称
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				String testTermName = ttdp.findById(rs.getInt(4))
						.getTestTermName();
				testClassView.setTermName(testTermName);
				// testClass.setTestTermId(rs.getInt(2));

				testClassView.setSchoolArea(rs.getString(5));

				// 获取教师姓名
				TeacherDaoProxy tdp = new TeacherDaoProxy();
				String teacherName = tdp.findById(rs.getInt(6)).getTeaName();
				testClassView.setTeaName(teacherName);
				// testClass.setTeaId();

				testClassView.setLimitNum(rs.getInt(7));
				testClassView.setNowNum(rs.getInt(8));
				testClassViewList.add(testClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassViewList;
	}

	@Override
	public TestClass findByTestClassNameAndSchoolAreaAndTeaId(
			String testClassName, String schoolArea, int teaId)
			throws Exception {
		TestClass testClass = null;
		String sql = "select * from testClass where testClassName=? and schoolArea=? and teaId=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testClassName);
			pstmt.setString(2, schoolArea);
			pstmt.setInt(3, teaId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClass = new TestClass();
				testClass.setId(rs.getInt(1));
				testClass.setTestClassName(rs.getString(2));
				testClass.setTestArea(rs.getString(3));
				testClass.setTestTermId(rs.getInt(4));
				testClass.setSchoolArea(rs.getString(5));
				testClass.setTeaId(rs.getInt(6));
				testClass.setLimitNum(rs.getInt(7));
				testClass.setNowNum(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClass;
	}

	// 提供分页查找
	@Override
	public List<TestClassView> findByNowTermAndSchoolArea(String schoolArea,
			int begin, int end) throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();
		TestClassView testClassView = null;

		// 找到当前测试学期的Id
		TestTermDaoProxy ttdp1 = new TestTermDaoProxy();
		int nowId = ttdp1.findNow().getId();

		String sql = "select * from testClass where testTermId=? and schoolArea=? order by id limit ?,?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nowId);
			pstmt.setString(2, schoolArea);
			pstmt.setInt(3, begin);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClassView = new TestClassView();
				testClassView.setId(rs.getInt(1));
				testClassView.setTestClassName(rs.getString(2));
				testClassView.setTestArea(rs.getString(3));

				// 获取测试学期的全称
				TestTermDaoProxy ttdp2 = new TestTermDaoProxy();
				String testTermName = ttdp2.findById(rs.getInt(4))
						.getTestTermName();
				testClassView.setTermName(testTermName);
				// testClass.setTestTermId(rs.getInt(2));

				testClassView.setSchoolArea(rs.getString(5));

				// 获取教师姓名
				TeacherDaoProxy tdp = new TeacherDaoProxy();
				String teacherName = tdp.findById(rs.getInt(6)).getTeaName();
				testClassView.setTeaName(teacherName);
				// testClass.setTeaId();

				testClassView.setLimitNum(rs.getInt(7));
				testClassView.setNowNum(rs.getInt(8));
				testClassViewList.add(testClassView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassViewList;
	}

	@Override
	public int findCount() throws Exception {

		int count = 0;
		String sql = "select count(*) from testclass";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return count;
	}

	@Override
	public int findCountByTeaIdAndTestTermID(int teaId, int testTermId) {
		int count = 0;
		String sql = "select count(*) from testclass where teaId=? and testTermId=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, teaId);
			pstmt.setInt(2, testTermId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return count;
	}

	@Override
	public List<TestClass> findByTestTermId(int testTermId) throws Exception {
		List<TestClass> testClassList = new ArrayList<TestClass>();
		TestClass testClass = null;
		String sql = "select * from testClass where testTermId=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, testTermId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				testClass = new TestClass();
				testClass.setId(rs.getInt(1));
				testClass.setTestClassName(rs.getString(2));
				testClass.setTestArea(rs.getString(3));
				testClass.setTestTermId(rs.getInt(4));
				testClass.setSchoolArea(rs.getString(5));
				testClass.setTeaId(rs.getInt(6));
				testClass.setLimitNum(rs.getInt(7));
				testClass.setNowNum(rs.getInt(8));
				testClassList.add(testClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return testClassList;
	}
}
