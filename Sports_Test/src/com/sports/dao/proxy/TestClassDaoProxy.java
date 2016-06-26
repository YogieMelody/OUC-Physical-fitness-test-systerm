package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITestClassDao;
import com.sports.dao.Impl.TestClassDaoImpl;
import com.sports.entity.Reserve;
import com.sports.entity.TestClass;
import com.sports.entity.TestClassView;
import com.sports.util.DatabaseConnection;

public class TestClassDaoProxy implements ITestClassDao {

	private DatabaseConnection dbc = null;
	private ITestClassDao dao = null;

	public TestClassDaoProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new TestClassDaoImpl(this.dbc.getConnection());
	}

	@Override
	public int doCreate(TestClass testClass) throws Exception {
		int result = 0;

		try {
			result = this.dao.doCreate(testClass);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {
		int result = 0;

		try {
			result = this.dao.doDelete(id);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int doUpdate(int id, TestClass testClass) throws Exception {
		int result = 0;

		try {
			result = this.dao.doUpdate(id, testClass);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<TestClass> findAll() throws Exception {
		List<TestClass> testClassList = new ArrayList<TestClass>();

		try {
			testClassList = this.dao.findAll();
		} catch (Exception e) {
			throw e;
		}
		return testClassList;
	}

	@Override
	public List<TestClassView> findAllV() throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();

		try {
			testClassViewList = this.dao.findAllV();
		} catch (Exception e) {
			throw e;
		}
		return testClassViewList;
	}

	@Override
	public TestClass findById(int id) throws Exception {
		TestClass testClass = new TestClass();

		try {
			testClass = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		}
		return testClass;
	}

	@Override
	public TestClassView findByIdV(int id) throws Exception {
		TestClassView tcv = new TestClassView();

		try {
			tcv = this.dao.findByIdV(id);
		} catch (Exception e) {
			throw e;
		}
		return tcv;
	}

	@Override
	public int book(Reserve reserve) throws Exception {
		int result = 0;

		try {
			result = this.dao.book(reserve);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int cancle(Reserve reserve) throws Exception {
		int result = 0;

		try {
			result = this.dao.cancle(reserve);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<TestClassView> findByTeaId(int id) throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();

		try {
			testClassViewList = this.dao.findByTeaId(id);
		} catch (Exception e) {
			throw e;
		}
		return testClassViewList;
	}

	@Override
	public List<TestClass> findByPage(int begin, int number) throws Exception {
		List<TestClass> testClassList = new ArrayList<TestClass>();

		try {
			testClassList = this.dao.findByPage(begin, number);
		} catch (Exception e) {
			throw e;
		}
		return testClassList;
	}

	@Override
	public List<TestClassView> findByFourCondition(String testClassName,
			String schoolArea, int teaId, int limitNum) throws Exception {
		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();

		try {
			testClassViewList = this.dao.findByFourCondition(testClassName,
					schoolArea, teaId, limitNum);
		} catch (Exception e) {
			throw e;
		}
		return testClassViewList;
	}

	@Override
	public List<TestClass> findByTeaIdC(int id) throws Exception {

		List<TestClass> testClassList = new ArrayList<TestClass>();

		try {
			testClassList = this.dao.findByTeaIdC(id);
		} catch (Exception e) {
			throw e;
		}
		return testClassList;
	}

	@Override
	public List<TestClassView> findByTeaIdAndTestTermIdByLimit(int teaId,
			int testTermId, int begin, int limit) throws Exception {

		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();

		try {
			testClassViewList = this.dao.findByTeaIdAndTestTermIdByLimit(teaId,
					testTermId, begin, limit);
		} catch (Exception e) {
			throw e;
		}
		return testClassViewList;
	}

	@Override
	public List<TestClassView> findByThreeCondition(String testClassName,
			String schoolArea, int limitNum) throws Exception {

		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();

		try {
			testClassViewList = this.dao.findByThreeCondition(testClassName,
					schoolArea, limitNum);
		} catch (Exception e) {
			throw e;
		}
		return testClassViewList;
	}

	@Override
	public TestClass findByTestClassNameAndSchoolAreaAndTeaId(
			String testClassName, String schoolArea, int teaId)
			throws Exception {

		TestClass testClass = new TestClass();

		try {
			testClass = this.dao.findByTestClassNameAndSchoolAreaAndTeaId(
					testClassName, schoolArea, teaId);
		} catch (Exception e) {
			throw e;
		}
		return testClass;
	}

	@Override
	public List<TestClassView> findByNowTermAndSchoolArea(String schoolArea,
			int begin, int end) throws Exception {

		List<TestClassView> testClassViewList = new ArrayList<TestClassView>();

		try {
			testClassViewList = this.dao.findByNowTermAndSchoolArea(schoolArea,
					begin, end);
		} catch (Exception e) {
			throw e;
		}
		return testClassViewList;
	}

	@Override
	public int findCount() throws Exception {

		int count = 0;

		try {
			count = this.dao.findCount();
		} catch (Exception e) {
			throw e;
		}
		return count;
	}

	@Override
	public int findCountByTeaIdAndTestTermID(int teaId, int testTermId)
			throws Exception {

		int count = 0;

		try {
			count = this.dao.findCountByTeaIdAndTestTermID(teaId, testTermId);
		} catch (Exception e) {
			throw e;
		}
		return count;
	}

	@Override
	public List<TestClass> findByTestTermId(int testTermId) throws Exception {
		List<TestClass> testClassList = new ArrayList<TestClass>();

		try {
			testClassList = this.dao.findByTestTermId(testTermId);
		} catch (Exception e) {
			throw e;
		}
		return testClassList;
	}
}
