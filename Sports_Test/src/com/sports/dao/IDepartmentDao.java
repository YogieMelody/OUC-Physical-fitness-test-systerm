package com.sports.dao;

import java.util.List;

import com.sports.entity.Department;

public interface IDepartmentDao {
	//增加一个部门
	public int doCreate(Department department) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	public int doUpdate(int id,Department department) throws Exception;
	
	public List<Department> findAll() throws Exception;
	
	public Department findById(int id) throws Exception;
	
	public int getIdByName(String departmentName) throws Exception;
}
