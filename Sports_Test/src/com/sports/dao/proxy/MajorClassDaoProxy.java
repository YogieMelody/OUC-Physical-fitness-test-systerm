package com.sports.dao.proxy;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.IMajorClassDao;
import com.sports.dao.Impl.MajorClassDaoImpl;
import com.sports.entity.MajorClass;
import com.sports.entity.MajorClassView;
import com.sports.util.DatabaseConnection;

public class MajorClassDaoProxy implements IMajorClassDao {

	private DatabaseConnection dbc = null;
	private IMajorClassDao dao = null;
	private int result = 0;

	public MajorClassDaoProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new MajorClassDaoImpl(this.dbc.getConnection());
	}

	@Override
	public int doCreate(MajorClass majorClass) throws Exception {

		try {
			result = this.dao.doCreate(majorClass);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {

		try {
			result = this.dao.doDelete(id);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int doUpdate(int id, MajorClass majorClass) throws Exception {
		
		try{
			result=this.dao.doUpdate(id, majorClass);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public List<MajorClass> findAll() throws Exception {
		
		List<MajorClass> majorClassList=new ArrayList<MajorClass>();
		
		try{
			majorClassList=this.dao.findAll();
		}catch(Exception e){
			throw e;
		}
		return majorClassList;
	}

	@Override
	public MajorClass findById(int id) throws Exception {
		
		MajorClass majorClass=null;
		
		try{
			majorClass=this.dao.findById(id);
		}catch(Exception e){
			throw e;
		}
		return majorClass;
	}

	@Override
	public MajorClassView findByIdV(int id) throws Exception {
		
		MajorClassView majorClassView=null;
		
		try{
			majorClassView=this.dao.findByIdV(id);
		}catch(Exception e){
			throw e;
		}
		return majorClassView;
	}

	@Override
	public List<MajorClassView> findAllV() throws Exception {
List<MajorClassView> majorClassViewList=new ArrayList<MajorClassView>();
		
		try{
			majorClassViewList=this.dao.findAllV();
		}catch(Exception e){
			throw e;
		}
		return majorClassViewList;
	}

	@Override
	public List<MajorClassView> findByDepNameV(String depName) throws Exception {
		List<MajorClassView> majorClassViewList=new ArrayList<MajorClassView>();
		
		try{
			majorClassViewList=this.dao.findByDepNameV(depName);
		}catch(Exception e){
			throw e;
		}
		return majorClassViewList;
	}

	@Override
	public int getIdByName(String majorClassName) throws Exception {
		int mcId=0;
		
		try{
			mcId=this.dao.getIdByName(majorClassName);
		}catch(Exception e){
			throw e;
		}
		return mcId;
	}

}
