package com.sports.entity;

import java.io.Serializable;

public class Nation implements Serializable {
	private int id;
	private String nationName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
	}
	
}
