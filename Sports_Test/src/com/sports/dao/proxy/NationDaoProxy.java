package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.INationDao;
import com.sports.dao.Impl.NationDaoImpl;
import com.sports.entity.Nation;
import com.sports.util.DatabaseConnection;

public class NationDaoProxy implements INationDao {

	private DatabaseConnection dbc=null;
	private INationDao dao=null;
	
	public NationDaoProxy() throws Exception{
		this.dbc=new DatabaseConnection();
		this.dao=new NationDaoImpl(this.dbc.getConnection());
	}
	@Override
	public int getIdByName(String name) throws Exception {
		
		int id=0;
		try{
			id=this.dao.getIdByName(name);
		}catch(Exception e){
			throw e;
		}
		return id;
	}

	@Override
	public String getNameById(int id) throws Exception {
		
		String name=null;
		try{
			name=this.dao.getNameById(id);
		}catch(Exception e){
			throw e;
		}
		return name;
	}
	@Override
	public List<Nation> findAll() throws Exception {
		
		List<Nation> nationList=new ArrayList<Nation>();
		
		try{
			nationList=this.dao.findAll();
		}catch(Exception e){
			throw e;
		}
		return nationList;
	}

}
