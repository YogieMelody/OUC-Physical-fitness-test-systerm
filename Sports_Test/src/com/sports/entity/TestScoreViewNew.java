package com.sports.entity;

import java.io.Serializable;

public class TestScoreViewNew implements Serializable {
	private int id;
	private String testClassName;
	private String testTermName;
	private String stuName;//!!!!!!!!!!!!!特别注意，这个为临时补救类
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
	public String getTestClassName() {
		return testClassName;
	}
	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}
	public String getTestTermName() {
		return testTermName;
	}
	public void setTestTermName(String testTermName) {
		this.testTermName = testTermName;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
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
