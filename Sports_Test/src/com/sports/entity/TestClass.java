package com.sports.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TestClass implements Serializable {
	private int id;
	private String testClassName;
	private String testArea;
	private int testTermId;
    private String schoolArea;
    private int teaId;
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
	public int getTestTermId() {
		return testTermId;
	}
	public void setTestTermId(int testTermId) {
		this.testTermId = testTermId;
	}
	public String getSchoolArea() {
		return schoolArea;
	}
	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}
	public int getTeaId() {
		return teaId;
	}
	public void setTeaId(int teaId) {
		this.teaId = teaId;
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
	public String getTestArea() {
		return testArea;
	}
	public void setTestArea(String testArea) {
		this.testArea = testArea;
	}
    
}
