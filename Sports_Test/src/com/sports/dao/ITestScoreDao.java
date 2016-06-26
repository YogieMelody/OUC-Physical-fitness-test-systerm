package com.sports.dao;

import java.util.List;

import com.sports.entity.TestScore;
import com.sports.entity.TestScoreView;
import com.sports.entity.TestScoreViewNew;

public interface ITestScoreDao {
	//预定时插入一条数据
	public int doCreate(TestScore testScore) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	//根据测试学期id+学生学号或者测试班级id+学生学号定位到准确记录，这里id不方便获取，所以略做修改
	public int doUpdate(int testTermId,String stuNumber,TestScore testScore) throws Exception;
	
	public List<TestScore> findAll() throws Exception;
	
	//查找一个班级的全部成绩（对应教师的查看全部功能）临时增加实体！！！！
	public List<TestScoreViewNew> findByTestClassId(int testClassId) throws Exception;
	
	public TestScore findById(int id) throws Exception;
	
	//根据学生学号和测试学期id查找测试成绩
	//可以唯一锁定一条记录
	//作用可以用于查看预定记录，查看测试成绩，也可以用于判定是否能预定，若返回值不为空不为空则可以预定
	//所以这个方法有3个作用
	public TestScoreView findByTestTermIdAndStuNumber(int testTermId,String stuNumber) throws Exception;
	
	//C是为了区分上面的方法
	public TestScore findByTestTermIdAndStuNumberC(int testTermId,String stuNumber) throws Exception;
	
	public int doPartUpdate(int testTermId, String stuNumber, TestScore testScore) throws Exception;
	//导入导出Excel,肖年浩负责数据处理,相应的函数由其实现,应该注意excel和db数据的一致性
	//每条记录有学生学号，测试学期id(容易获取),测试班级id(获取方法暂定,可以通过查询的方式获取,不难实现)
	//因为不确定数据格式,函数暂时没有写,函数模仿com.sports.service下的ExcelOperater即可
}
