package com.sports.dao.proxy;

import java.util.List;

import com.sports.dao.IDepartmentDao;
import com.sports.dao.Impl.DepartmentDaoImpl;
import com.sports.entity.Department;
import com.sports.util.DatabaseConnection;

public class DepartmentDaoProxy implements IDepartmentDao {

	private DatabaseConnection dbc=null;
	private IDepartmentDao dao=null;
	
	public DepartmentDaoProxy() throws Exception{
		this.dbc=new DatabaseConnection();
		this.dao=new DepartmentDaoImpl(this.dbc.getConnection());
	}
	@Override
	public int doCreate(Department department) throws Exception {
		int result=0;
		try{
			result=this.dao.doCreate(department);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {
		int result=0;
		try{
			result=this.dao.doDelete(id);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public int doUpdate(int id, Department department) throws Exception {
		int result=0;
		try{
			result=this.dao.doUpdate(id, department);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public List<Department> findAll() throws Exception {
		List<Department> all=null;
		try{
			all=this.dao.findAll();
		}catch(Exception e){
			throw e;
		}
		return all;
	}

	@Override
	public Department findById(int id) throws Exception {
		Department department=null;
		try{
			department=this.dao.findById(id);
		}catch(Exception e){
			throw e;
		}
 		return department;
	}
	@Override
	public int getIdByName(String departmentName) throws Exception {
		
		int id=0;
		try{
			id=this.dao.getIdByName(departmentName);
		}catch(Exception e){
			throw e;
		}
		return id;
	}

}
