package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITestScoreDao;
import com.sports.dao.Impl.TestScoreDaoImpl;
import com.sports.entity.TestScore;
import com.sports.entity.TestScoreView;
import com.sports.entity.TestScoreViewNew;
import com.sports.util.DatabaseConnection;

public class TestScoreDaoProxy implements ITestScoreDao {
	private DatabaseConnection dbc = null;
	private ITestScoreDao dao = null;

	public TestScoreDaoProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new TestScoreDaoImpl(this.dbc.getConnection());
	}

	@Override
	public int doCreate(TestScore testScore) throws Exception {

		int result = 0;

		try {
			result = this.dao.doCreate(testScore);
		} catch (Exception e) {
			e.printStackTrace();
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

		int result = 0;

		try {
			result = this.dao.doUpdate(testTermId, stuNumber, testScore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TestScore> findAll() throws Exception {
		List<TestScore> tsList=new ArrayList<TestScore>();
		
		try{
			tsList=this.dao.findAll();
		}catch(Exception e){
			e.printStackTrace();
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

		try {
			tsv = this.dao.findByTestTermIdAndStuNumber(testTermId, stuNumber);
		} catch (Exception e) {
			throw e;
		}
		return tsv;
	}

	@Override
	public TestScore findByTestTermIdAndStuNumberC(int testTermId,
			String stuNumber) throws Exception {

		TestScore ts = null;

		try {
			ts = this.dao.findByTestTermIdAndStuNumberC(testTermId, stuNumber);
		} catch (Exception e) {
			throw e;
		}
		return ts;
	}

	@Override
	public List<TestScoreViewNew> findByTestClassId(int testClassId) throws Exception {

		List<TestScoreViewNew> tsvnList = null;

		try {
			tsvnList = this.dao.findByTestClassId(testClassId);
		} catch (Exception e) {

		}
		return tsvnList;
	}

	@Override
	public int doPartUpdate(int testTermId, String stuNumber,
			TestScore testScore) throws Exception {
		int result = 0;

		try {
			result = this.dao.doPartUpdate(testTermId, stuNumber, testScore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
