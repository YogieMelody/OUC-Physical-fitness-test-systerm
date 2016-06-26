package com.sports.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TestClassView implements Serializable {
	private int id;
	private String testClassName;
	private String testArea;
	//private int testTermId;
	private String termName;
	//private String testTime;和标题一致移除
    private String schoolArea;
    //private int teaId;
    private String teaName;
    private int limitNum;
    private int nowNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTestClassName() {
		return testClassName;
	}
	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}
	public String getTestArea() {
		return testArea;
	}
	public void setTestArea(String testArea) {
		this.testArea = testArea;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
//	public String getTestTime() {
//		return testTime;
//	}
//	public void setTestTime(String testTime) {
//		this.testTime = testTime;
//	}
	public String getSchoolArea() {
		return schoolArea;
	}
	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	public int getNowNum() {
		return nowNum;
	}
	public void setNowNum(int nowNum) {
		this.nowNum = nowNum;
	}
}
