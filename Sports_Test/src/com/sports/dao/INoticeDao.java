package com.sports.dao;

import java.util.List;

import com.sports.entity.Notice;

public interface INoticeDao {
	
	public int doCreate(Notice notice) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	public int doUpdate(int id,Notice notice) throws Exception;
	
	public List<Notice> findAll() throws Exception;
	
	public Notice findById(int id) throws Exception;
	
	public List<Notice> findByKeyWord(String key) throws Exception;
}
