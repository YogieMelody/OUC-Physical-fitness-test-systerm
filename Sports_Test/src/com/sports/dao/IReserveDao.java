package com.sports.dao;

import java.util.List;

import com.sports.entity.Reserve;

public interface IReserveDao {
	
	//预定时往reserve表中添加一条数据
	public int doCreate(Reserve reserve) throws Exception;
	
	//取消预定时从reserve表中移除相对应的数据
	public int doDelete(int id) throws Exception;
	
	//读出预定信息，此处只读出当前测试学期的预约信息，其他学期的不予显示，没有实际意义
	public Reserve findNowReserve(int testTermId,int stuId) throws Exception;
	
	public List<Reserve> findByTestTermIdAndTestClassId(int testTermId,int testClassId) throws Exception;
}
