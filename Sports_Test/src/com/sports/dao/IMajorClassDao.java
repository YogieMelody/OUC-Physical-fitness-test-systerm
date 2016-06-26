package com.sports.dao;

import java.util.List;

import com.sports.entity.MajorClass;
import com.sports.entity.MajorClassView;

public interface IMajorClassDao {
	
	public int doCreate(MajorClass majorClass) throws Exception;
	
	public int doDelete(int id) throws Exception;
	
	public int doUpdate(int id,MajorClass majorClass) throws Exception;
	
	public List<MajorClass> findAll() throws Exception;
	
	public List<MajorClassView> findAllV() throws Exception;
	
	public MajorClass findById(int id) throws Exception;
	
	public MajorClassView findByIdV(int id) throws Exception;
	
	public List<MajorClassView> findByDepNameV(String depName) throws Exception;
	
	public int getIdByName(String majorClassName) throws Exception;
}
