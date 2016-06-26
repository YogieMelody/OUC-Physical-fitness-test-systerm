package com.sports.dao.proxy;

import com.sports.dao.IUserDao;
import com.sports.dao.Impl.UserDaoImpl;
import com.sports.entity.ManagerLogin;
import com.sports.entity.Student;
import com.sports.entity.Teacher;
import com.sports.util.DatabaseConnection;

public class UserDaoProxy implements IUserDao {
	private DatabaseConnection dbc=null;
	private IUserDao dao=null;
	public UserDaoProxy(){
		try{
			this.dbc=new DatabaseConnection();//实例化数据库连接
		}catch(Exception e){
			e.printStackTrace();
		}
		this.dao=new UserDaoImpl(this.dbc.getConnection());
	}
	
	@Override
	public boolean findLoginStudent(Student student) throws Exception {
		boolean flag=false;
		try{
			flag=this.dao.findLoginStudent(student);//调用真实主题
		}catch(Exception e){ 
			throw e;                              //向上抛出异常
		}
		return flag;
	}

	@Override
	public boolean findLoginTeacher(Teacher teacher) throws Exception {
		boolean flag=false;
		try{
			flag=this.dao.findLoginTeacher(teacher);
		}catch(Exception e){
			throw e;
		}
		return flag;
	}

	@Override
	public boolean findLoginManager(ManagerLogin managerLogin) throws Exception {
		boolean flag=false;
		try{
			flag=this.dao.findLoginManager(managerLogin);
		}catch(Exception e){
			throw e;
		}
		return flag;
	}

}
