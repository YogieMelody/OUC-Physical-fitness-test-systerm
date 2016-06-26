package com.sports.dao;

import java.util.List;

import com.sports.entity.Nation;

public interface INationDao {
	
	public int getIdByName(String name) throws Exception;
	
	public String getNameById(int id) throws Exception;
	
	public List<Nation> findAll() throws Exception;
}
