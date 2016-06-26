package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.ITeacherDao;
import com.sports.dao.Impl.TeacherDaoImpl;
import com.sports.entity.Teacher;
import com.sports.util.DatabaseConnection;

public class TeacherDaoProxy implements ITeacherDao {
	
	private DatabaseConnection dbc=null;
	private ITeacherDao dao=null;
	private int result=0;
	
	public TeacherDaoProxy() throws Exception{
		this.dbc=new DatabaseConnection();
		this.dao=new TeacherDaoImpl(this.dbc.getConnection());
	}
	
	@Override
	public int doCreate(Teacher teacher) throws Exception {
		
		try{
			result=this.dao.doCreate(teacher);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {
		
		try{
			result=this.dao.doDelete(id);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public int doUpdate(int id, Teacher teacher) throws Exception {
		
		try{
			result=this.dao.doUpdate(id, teacher);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public List<Teacher> findAll() throws Exception {
		
		List<Teacher> teacherList=new ArrayList<Teacher>();
		
		try{
			teacherList=this.dao.findAll();
		}catch(Exception e){
			throw e;
		}
		return teacherList;
	}

	@Override
	public Teacher findById(int id) throws Exception {
		
		Teacher teacher=null;
		
		try{
			teacher=this.dao.findById(id);
		}catch(Exception e){
			throw e;
		}
		return teacher;
	}

	@Override
	public Teacher findByTeaNumber(String teaNumber) throws Exception {
		
		Teacher teacher=null;
		
		try{
			teacher=this.dao.findByTeaNumber(teaNumber);
		}catch(Exception e){
			throw e;
		}
		return teacher;
	}

	@Override
	public Teacher findByTeaName(String teaName) throws Exception {
		
		Teacher teacher=null;
		
		try{
			teacher=this.dao.findByTeaName(teaName);
		}catch(Exception e){
			throw e;
		}
		return teacher;
	}

	@Override
	public int doChangePassword(String teaNumber, String teaPassword)
			throws Exception {
		try {
			result = this.dao.doChangePassword(teaNumber, teaPassword);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
