package com.sports.entity;

import java.io.Serializable;
import java.sql.Date;

public class Student implements Serializable {
	private int id;
	private String stuNumber;
	private String stuPassword;
	private String stuName;
	private String stuSex;
	private String stuIdentification;
	private String stuIsGat;
	private String stuPhone;
	private int departmentId;
	private int majorId;
	private String gradeNow;
	private int nationMark;
	private String bithday;//格式为2015/10/13
	private String address;
	private String politics;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStuNumber() {
		return stuNumber;
	}
	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}
	public String getStuPassword(){
		return stuPassword;
	}
	public void setStuPassword(String stuPassword){
		this.stuPassword=stuPassword;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuIdentification() {
		return stuIdentification;
	}
	public void setStuIdentification(String stuIdentification) {
		this.stuIdentification = stuIdentification;
	}
	public String getStuIsGat() {
		return stuIsGat;
	}
	public void setStuIsGat(String stuIsGat) {
		this.stuIsGat = stuIsGat;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public String getGradeNow() {
		return gradeNow;
	}
	public void setGradeNow(String gradeNow) {
		this.gradeNow = gradeNow;
	}
	public int getNationMark() {
		return nationMark;
	}
	public void setNationMark(int nationMark) {
		this.nationMark = nationMark;
	}
	public String getBithday() {
		return bithday;
	}
	public void setBithday(String bithday) {
		this.bithday = bithday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPolitics() {
		return politics;
	}
	public void setPolitics(String politics) {
		this.politics = politics;
	}
}
