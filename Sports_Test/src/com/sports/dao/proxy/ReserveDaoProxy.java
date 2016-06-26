package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.IDepartmentDao;
import com.sports.dao.IReserveDao;
import com.sports.dao.Impl.DepartmentDaoImpl;
import com.sports.dao.Impl.ReserveDaoImpl;
import com.sports.entity.Reserve;
import com.sports.util.DatabaseConnection;

public class ReserveDaoProxy implements IReserveDao {

	private DatabaseConnection dbc=null;
	private IReserveDao dao=null;
	
	public ReserveDaoProxy() throws Exception{
		this.dbc=new DatabaseConnection();
		this.dao=new ReserveDaoImpl(this.dbc.getConnection());
	}
	@Override
	public int doCreate(Reserve reserve) throws Exception {
		
		int result=0;
		
		try{
			result=this.dao.doCreate(reserve);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {
		
		int result=0;
		
		try{
			result=this.dao.doDelete(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Reserve findNowReserve(int testTermId, int stuId) throws Exception {
		
		Reserve reserve=null;
		
		try{
			reserve=this.dao.findNowReserve(testTermId, stuId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return reserve;
	}
	@Override
	public List<Reserve> findByTestTermIdAndTestClassId(int testTermId,
			int testClassId) throws Exception {
		
		List<Reserve> rList=null;
		
		try{
			rList=this.dao.findByTestTermIdAndTestClassId(testTermId, testClassId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rList;
	}

}
