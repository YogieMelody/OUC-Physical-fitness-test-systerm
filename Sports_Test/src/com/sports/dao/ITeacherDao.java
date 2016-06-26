package com.sports.dao;

import java.util.List;

import com.sports.entity.Teacher;

public interface ITeacherDao {
	
	public int doCreate(Teacher teacher) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	public int doUpdate(int id,Teacher teacher) throws Exception;
	
	public List<Teacher> findAll() throws Exception;
	
	public Teacher findById(int id) throws Exception;
	
	public Teacher findByTeaNumber(String teaNumber) throws Exception;
	
	public Teacher findByTeaName(String teaName) throws Exception;
	
	public int doChangePassword(String teaNumber,String teaPassword) throws Exception;
}
