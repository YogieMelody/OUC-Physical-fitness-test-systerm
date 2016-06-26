package com.sports.dao;

import java.util.List;

import com.sports.entity.Reserve;
import com.sports.entity.TestClass;
import com.sports.entity.TestClassView;

public interface ITestClassDao {
	
	public int doCreate(TestClass testClass) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	public int doUpdate(int id,TestClass testClass) throws Exception;
	
	public List<TestClass> findAll() throws Exception;
	
	public List<TestClassView> findAllV() throws Exception;
	
	public TestClass findById(int id) throws Exception;
	
	//用于判定插入数据时是否已经存在
	public TestClass findByTestClassNameAndSchoolAreaAndTeaId(String testClassName,String schoolArea,int teaId) throws Exception;
	
	public TestClassView findByIdV(int id) throws Exception;
	
	//教师历年组织测试班级
	//返回具体值
	public List<TestClassView> findByTeaId(int id) throws Exception;
	
	//根据测试学期Id和教师Id进行分页查找
	public List<TestClassView> findByTeaIdAndTestTermIdByLimit(int teaId,int testTermId,int begin,int pageNum) throws Exception;
	
	public int findCountByTeaIdAndTestTermID(int teaId,int testTermId) throws Exception;
	//返回带id的值
	public List<TestClass> findByTeaIdC(int id) throws Exception;
	//根据条件进行查找
	public List<TestClassView> findByFourCondition(String testClassName,String schoolArea,int teaId,int limitNum) throws Exception;
	
	public List<TestClassView> findByThreeCondition(String testClassName,String schoolArea,int limitNum) throws Exception;
	
	//分页功能，采用SQL的limit语句实现，第一个参数为第一条读取的记录数，第二个参数为读取记录的条数
	public List<TestClass> findByPage(int begin,int number) throws Exception;
	
	//根据当前测试学期的Id和所选择的校区返回相应的所有测试班级
	public List<TestClassView> findByNowTermAndSchoolArea(String schoolArea,int begin,int end) throws Exception;
	
	//预约功能，预约成功时要在TestScore中插入数据！！！
	//可以用doUpdate替代，但此处尝试独立出来
	//参数决定使用Reserve
	public int book(Reserve reserve) throws Exception;
	
	public int cancle(Reserve reserve) throws Exception;
	
	public int findCount() throws Exception;
	
	public List<TestClass> findByTestTermId(int testTermId) throws Exception;
}
