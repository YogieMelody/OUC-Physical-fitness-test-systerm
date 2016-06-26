package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITestTermDao;
import com.sports.dao.Impl.TestTermDaoImpl;
import com.sports.entity.TestTerm;
import com.sports.util.DatabaseConnection;

public class TestTermDaoProxy implements ITestTermDao {

	private DatabaseConnection dbc = null;
	private ITestTermDao dao = null;

	public TestTermDaoProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new TestTermDaoImpl(this.dbc.getConnection());
	}

	@Override
	public int doCreate(TestTerm testTerm) throws Exception {
		int result = 0;

		try {
			result = this.dao.doCreate(testTerm);
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
	public int doUpdate(int id, TestTerm testTerm) throws Exception {
		int result = 0;

		try {
			result = this.dao.doUpdate(id, testTerm);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<TestTerm> findAll() throws Exception {
		List<TestTerm> testTermList = new ArrayList<TestTerm>();

		try {
			testTermList = this.dao.findAll();
		} catch (Exception e) {
			throw e;
		}
		return testTermList;
	}

	@Override
	public TestTerm findById(int id) throws Exception {
		TestTerm testTerm = new TestTerm();

		try {
			testTerm = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		}
		return testTerm;
	}

	@Override
	public TestTerm findNow() throws Exception {
		TestTerm testTerm = new TestTerm();

		try {
			testTerm = this.dao.findNow();
		} catch (Exception e) {
			throw e;
		}
		return testTerm;
	}

	@Override
	public int setNow(int id) throws Exception {
		int result = 0;

		try {
			result = this.dao.setNow(id);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int OpenBook() throws Exception {
		int result = 0;

		try {
			result = this.dao.OpenBook();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int CloseBook() throws Exception {
		int result = 0;

		try {
			result = this.dao.CloseBook();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<TestTerm> findByKeyWord(String key) throws Exception {
		List<TestTerm> testTermList = new ArrayList<TestTerm>();

		try {
			testTermList = this.dao.findByKeyWord(key);
		} catch (Exception e) {
			throw e;
		}
		return testTermList;
	}

	@Override
	public List<TestTerm> findByGrade(String stuGradeNow) throws Exception {
		
		List<TestTerm> testTermList = new ArrayList<TestTerm>();

		try {
			testTermList = this.dao.findByGrade(stuGradeNow);
		} catch (Exception e) {
			throw e;
		}
		return testTermList;
	}

	@Override
	public TestTerm findByTestTermName(String testTermName) throws Exception {
		TestTerm testTerm = new TestTerm();

		try {
			testTerm = this.dao.findByTestTermName(testTermName);
		} catch (Exception e) {
			throw e;
		}
		return testTerm;
	}

}
