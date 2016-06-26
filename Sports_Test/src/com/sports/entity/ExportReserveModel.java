package com.sports.entity;

public class ExportReserveModel {//为导出名单专门设置的model
	private int id;
	private int testClassId;
	private int testTermId;
	private String stuName;
	private String stuNumber;
	private String stuSex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestClassId() {
		return testClassId;
	}

	public void setTestClassId(int testClassId) {
		this.testClassId = testClassId;
	}

	public int getTestTermId() {
		return testTermId;
	}

	public void setTestTermId(int testTermId) {
		this.testTermId = testTermId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

}
