package com.sports.dao;

import java.util.List;

import com.sports.entity.TestTerm;

public interface ITestTermDao {
	public int doCreate(TestTerm testTerm) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	public int doUpdate(int id,TestTerm testTerm) throws Exception;
	
	public List<TestTerm> findAll() throws Exception;
	
	public List<TestTerm> findByGrade(String stuGradeNow) throws Exception;
	
	public List<TestTerm> findByKeyWord(String key) throws Exception;
	
	public TestTerm findById(int id) throws Exception;
	
	public TestTerm findNow() throws Exception;
	
	public int setNow(int id) throws Exception;//把isNow的值置为1。另外其他的学期置为0
	
	public int OpenBook() throws Exception;//打开当前测试学期的全校预约开关
	
	public int CloseBook() throws Exception;

	public TestTerm findByTestTermName(String testTermName) throws Exception;
}
