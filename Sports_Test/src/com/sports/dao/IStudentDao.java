package com.sports.dao;

import java.util.List;

import com.sports.entity.Student;
import com.sports.entity.StudentView;

public interface IStudentDao {
	
	public int doCreate(Student student) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	public int doUpdate(int id,Student student) throws Exception;
	
	public List<Student> findAll() throws Exception;
	
	public List<StudentView> findAllV() throws Exception;
	
	public Student findById(int id) throws Exception;
	
	public StudentView findByIdV(int id) throws Exception;
	
	public Student findByStuNumber(String stuNumber) throws Exception;
	
	public StudentView findByStuNumberV(String stuNumber) throws Exception;
	
	public List<Student> findByFourCondition(String stuNumber,String stuName,int stuMajId,String stuGradeNow) throws Exception;

    public List<Student> findByThreeCondition(String stuNumber,String stuName,String stuGradeNow) throws Exception;
    
    //修改密码,先根据学号定位到唯一的那个学生,然后执行修改密码的操作
    public int doChangePassword(String stuNumber,String stuPassword) throws Exception;
}
