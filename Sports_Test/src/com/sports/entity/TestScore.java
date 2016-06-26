package com.sports.entity;

import java.io.Serializable;
import java.sql.Date;

public class TestScore implements Serializable {
	//预约时学生信息才插入此表中前4列
	private int id;
	private int testClassId;
	private int testTermId;
	private String stuNumber;
	private double height;
	private double weight;
	private double vitalCapacity;
	private double fiftyRun;
	private double jump;
	private double sitAndReach; //坐位体前屈
	private String eightHundredsRun;
	private String oneThousandRun;
	private int situp;       //仰卧起坐
	private int pullup;      //引体向上
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
	public String getStuNumber() {
		return stuNumber;
	}
	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getVitalCapacity() {
		return vitalCapacity;
	}
	public void setVitalCapacity(double vitalCapacity) {
		this.vitalCapacity = vitalCapacity;
	}
	public double getFiftyRun() {
		return fiftyRun;
	}
	public void setFiftyRun(double fiftyRun) {
		this.fiftyRun = fiftyRun;
	}
	public double getJump() {
		return jump;
	}
	public void setJump(double jump) {
		this.jump = jump;
	}
	public double getSitAndReach() {
		return sitAndReach;
	}
	public void setSitAndReach(double sitAndReach) {
		this.sitAndReach = sitAndReach;
	}
	public String getEightHundredsRun() {
		return eightHundredsRun;
	}
	public void setEightHundredsRun(String eightHundredsRun) {
		this.eightHundredsRun = eightHundredsRun;
	}
	public String getOneThousandRun() {
		return oneThousandRun;
	}
	public void setOneThousandRun(String oneThousandRun) {
		this.oneThousandRun = oneThousandRun;
	}
	public int getSitup() {
		return situp;
	}
	public void setSitup(int situp) {
		this.situp = situp;
	}
	public int getPullup() {
		return pullup;
	}
	public void setPullup(int pullup) {
		this.pullup = pullup;
	}
}
