package com.sports.dao;

import com.sports.entity.ManagerLogin;
import com.sports.entity.Student;
import com.sports.entity.Teacher;

public interface IUserDao {
	/**
	 * 用户登录验证
	 * @param user 传入VO对象
	 * @return 验证的操作结果
	 * @throw Exception
	 */
	public boolean findLoginStudent(Student student) throws Exception;
	
	public boolean findLoginTeacher(Teacher teacher) throws Exception;
	
	public boolean findLoginManager(ManagerLogin managerLogin) throws Exception;
}
