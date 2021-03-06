package com.sports.entity;

import java.io.Serializable;

public class MajorClass implements Serializable {
	private int id;
	private int departmentId;
	private String majorClassName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getMajorClassName() {
		return majorClassName;
	}
	public void setMajorClassName(String majorClassName) {
		this.majorClassName = majorClassName;
	}
}
