package com.sports.entity;

import java.io.Serializable;

public class Teacher implements Serializable {
	private int id;
	private String teaNumber;
	private String teaPassword;
	private String teaName;
	private String teaSex;
	private String teaPosition;
	private String teaPhone;
	private String teaEmail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeaNumber() {
		return teaNumber;
	}
	public void setTeaNumber(String teaNumber) {
		this.teaNumber = teaNumber;
	}
	public String getTeaPassword() {
		return teaPassword;
	}
	public void setTeaPassword(String teaPassword) {
		this.teaPassword = teaPassword;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getTeaSex() {
		return teaSex;
	}
	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
	}
	public String getTeaPosition() {
		return teaPosition;
	}
	public void setTeaPosition(String teaPosition) {
		this.teaPosition = teaPosition;
	}
	public String getTeaPhone() {
		return teaPhone;
	}
	public void setTeaPhone(String teaPhone) {
		this.teaPhone = teaPhone;
	}
	public String getTeaEmail() {
		return teaEmail;
	}
	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}
}
