package com.sports.factory;

import com.sports.dao.IUserDao;
import com.sports.dao.proxy.UserDaoProxy;

public class DaoFactory {
	public static IUserDao getIUserDaoInstance(){
		return new UserDaoProxy();
	}
	
}
