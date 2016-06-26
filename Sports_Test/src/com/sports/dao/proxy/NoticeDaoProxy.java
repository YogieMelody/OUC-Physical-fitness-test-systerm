package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.INoticeDao;
import com.sports.dao.Impl.NoticeDaoImpl;
import com.sports.entity.Notice;
import com.sports.util.DatabaseConnection;

public class NoticeDaoProxy implements INoticeDao {
	
	private DatabaseConnection dbc=null;
	private INoticeDao dao=null;
	private int result=0;
	
	public NoticeDaoProxy() throws Exception{
		this.dbc=new DatabaseConnection();
		this.dao=new NoticeDaoImpl(this.dbc.getConnection());
	}
	@Override
	public int doCreate(Notice notice) throws Exception {
		
		try{
			result=this.dao.doCreate(notice);
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
	public int doUpdate(int id, Notice notice) throws Exception {
		
		try{
			result=this.dao.doUpdate(id, notice);
		}catch(Exception e){
			throw e;
		}
		return result;
	}

	@Override
	public List<Notice> findAll() throws Exception {
		
		List<Notice> noticeList=new ArrayList<Notice>();
		try{
			noticeList=this.dao.findAll();
		}catch(Exception e){
			throw e;
		}
		return noticeList;
	}

	@Override
	public Notice findById(int id) throws Exception {
		
		Notice notice=null;
		try{
			notice=this.dao.findById(id);
		}catch(Exception e){
			throw e;
		}
		return notice;
	}

	@Override
	public List<Notice> findByKeyWord(String key) throws Exception {
		
		List<Notice> noticeList=new ArrayList<Notice>();
		try{
			noticeList=this.dao.findByKeyWord(key);
		}catch(Exception e){
			throw e;
		}
		return noticeList;
	}

}
