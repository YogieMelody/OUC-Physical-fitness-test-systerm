package com.sports.entity;

import java.io.Serializable;

public class MajorClassView implements Serializable {
	private int id;
	//private int departmentId;
	private String departmentName;
	private String majorClassName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getMajorClassName() {
		return majorClassName;
	}
	public void setMajorClassName(String majorClassName) {
		this.majorClassName = majorClassName;
	}	
}
