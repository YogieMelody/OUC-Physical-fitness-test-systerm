package com.sports.entity;

import java.io.Serializable;
import java.sql.Date;

public class TestTerm implements Serializable {
	private int id;
	private String testTermName;
	private int isNow;
	private int isOpen;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestTermName() {
		return testTermName;
	}

	public void setTestTermName(String testTermName) {
		this.testTermName = testTermName;
	}

	public int getIsNow() {
		return isNow;
	}

	public void setIsNow(int isNow) {
		this.isNow = isNow;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

}
