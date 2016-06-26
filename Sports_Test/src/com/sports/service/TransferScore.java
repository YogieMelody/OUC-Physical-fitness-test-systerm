package com.sports.service;

import com.sports.entity.TestScore;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TransferScore {
	/**
	 * POI:解析Excel文件中的数据并把每行数据封装成一个实体
	 * 
	 * @param fis文件输入流
	 * @return List<EmployeeInfo> Excel中数据封装实体的集合
	 */
	public static List<TestScore> getAllByPoi(InputStream fis) {
		List<TestScore> tsList = new ArrayList<TestScore>();
		TestScore ts = null;
		try {
			// HSSF 07后 XSSF 07前
			// 创建excel工作簿
			HSSFWorkbook hwb = new HSSFWorkbook(fis);
			// 得到第一个工作表
			HSSFSheet sheet = hwb.getSheetAt(0);
			// HSSFRow row = null;
			HSSFRow row = null;
			// 日期格式化
			DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
				sheet = hwb.getSheetAt(i);
				for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {
					row = sheet.getRow(j);
					ts = new TestScore();
					// 0是序号
					if (getCellValue(row.getCell(0)) != null) {
						if (!"".equals(getCellValue(row.getCell(0)))) {
							ts.setId(Integer.valueOf(
									getCellValue(row.getCell(0))).intValue());
						}
					} else {
						ts.setId(1);
					}
					// 1是测试班级id
					if (getCellValue(row.getCell(1)) != null) {
						if (!"".equals(getCellValue(row.getCell(1)))) {
							ts.setTestClassId(Integer.valueOf(
									getCellValue(row.getCell(1))).intValue());
						}
					}
					// 2是测试学期id
					if (getCellValue(row.getCell(2)) != null) {
						if (!"".equals(getCellValue(row.getCell(2)))) {
							ts.setTestTermId(Integer.valueOf(
									getCellValue(row.getCell(2))).intValue());
						}
					}

					// 3是学生姓名 不要

					// 4是学号
					if (getCellValue(row.getCell(4)) != null) {
						if (!"".equals(getCellValue(row.getCell(4)))) {
							ts.setStuNumber(String.valueOf(getCellValue(row
									.getCell(4))));
						}
					}

					// 5是性别 后面用来判断存储的是800/1000米 以及 仰卧起坐/引体向上

					// 6是身高
					if (getCellValue(row.getCell(6)) != null) {
						if (!"".equals(getCellValue(row.getCell(6)))) {
							ts.setHeight(Double.valueOf(
									row.getCell(6).getNumericCellValue()));
						}
					}

					// 7是体重
					if (getCellValue(row.getCell(7)) != null) {
						if (!"".equals(getCellValue(row.getCell(7)))) {
							ts.setWeight(Double.valueOf(
									row.getCell(7).getNumericCellValue()));
						}
					}

					// 8是肺活量
					if (getCellValue(row.getCell(8)) != null) {
						if (!"".equals(getCellValue(row.getCell(8)))) {
							ts.setVitalCapacity(Double.valueOf(
									getCellValue(row.getCell(8))).doubleValue());
						}
					}

					// 9是50米
					if (getCellValue(row.getCell(9)) != null) {
						if (!"".equals(getCellValue(row.getCell(9)))) {
							ts.setFiftyRun(Double.valueOf(
									row.getCell(9).getNumericCellValue()));
						}
					}

					// 10是跳远
					if (getCellValue(row.getCell(10)) != null) {
						if (!"".equals(getCellValue(row.getCell(10)))) {
							ts.setJump(Double.valueOf(
									row.getCell(10).getNumericCellValue()));
						}
					}

					// 11是坐位体前屈
					if (getCellValue(row.getCell(11)) != null) {
						if (!"".equals(getCellValue(row.getCell(11)))) {
							ts.setSitAndReach(Double.valueOf(
									row.getCell(11).getNumericCellValue()));
						}
					}

					// 13是800/1000米
					if (getCellValue(row.getCell(5)).equals("女")) {
						if (getCellValue(row.getCell(13)) != null) {
							if (!"".equals(getCellValue(row.getCell(13)))) {
								ts.setEightHundredsRun(String
										.valueOf(row.getCell(13).getNumericCellValue()));
							}
						}
					} else {
						if (getCellValue(row.getCell(13)) != null) {
							if (!"".equals(getCellValue(row.getCell(13)))) {
								ts.setOneThousandRun(String
										.valueOf(row.getCell(13).getNumericCellValue()));
							}
						}
					}
					
					//获得小数
					// System.out.println(String
					// .valueOf(row.getCell(13).getNumericCellValue()));
					//获得整数
					//System.out.println(String.valueOf(getCellValue(row
					//		.getCell(13))));

					// 12是仰卧起坐/引体向上
					if (getCellValue(row.getCell(5)).equals("女")) {
						if (getCellValue(row.getCell(12)) != null) {
							if (!"".equals(getCellValue(row.getCell(12)))) {
								ts.setSitup(Integer.valueOf(
										getCellValue(row.getCell(12)))
										.intValue());
							}
						}
					} else {
						if (getCellValue(row.getCell(12)) != null) {
							if (!"".equals(getCellValue(row.getCell(12)))) {
								ts.setPullup(Integer.valueOf(
										getCellValue(row.getCell(12)))
										.intValue());
							}
						}
					}

					tsList.add(ts);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tsList;
	}

	private static String getCellValue(HSSFCell hssfCell) {
		String value = null;
		// 简单的查检列类型
		try {
			switch (hssfCell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:// 字符串
//				System.out.println("1");
				value = hssfCell.getRichStringCellValue().getString();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:// 数字
//				System.out.println("2");
				long dd = (long) hssfCell.getNumericCellValue();
				value = dd + "";
				break;
			case HSSFCell.CELL_TYPE_BLANK:
//				System.out.println("3");
				value = "";
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				value = String.valueOf(hssfCell.getCellFormula());
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:// boolean型值
				value = String.valueOf(hssfCell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = String.valueOf(hssfCell.getErrorCellValue());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			value = "";
			return value;
		}
		return value;
	}
}
