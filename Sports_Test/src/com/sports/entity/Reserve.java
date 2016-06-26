package com.sports.entity;

import java.io.Serializable;

public class Reserve implements Serializable {
	
	private int id;
	private int testTermId;
	private int testClassId;
	private int stuId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTestTermId() {
		return testTermId;
	}
	public void setTestTermId(int testTermId) {
		this.testTermId = testTermId;
	}
	public int getTestClassId() {
		return testClassId;
	}
	public void setTestClassId(int testClassId) {
		this.testClassId = testClassId;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
}
