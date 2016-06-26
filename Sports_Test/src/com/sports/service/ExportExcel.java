package com.sports.service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcel<T> {
	public void exportExcel(List<T> list, OutputStream out) {
		exportExcel("测试班级成员名单", null, list, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, List<T> list, OutputStream out) {
		exportExcel("测试班级成员名单", headers, list, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, List<T> list, OutputStream out,
			String pattern) {
		exportExcel("测试班级成员名单", headers, list, out, pattern);
	}

	public void exportExcel(String title, String[] headers, List<T> list,
			OutputStream out) {
		exportExcel(title, headers, list, out, "yyyy-MM-dd");
	}

	public void exportExcel(String title, String[] headers, List<T> list,
			OutputStream out, String pattern) {
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(title);

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 标题居中样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setAlignment((short) 2);

		sheet.setColumnWidth(0, 568);
		// 为方便老师，增加测试班级id和测试学期id，这两列有数值但设置为超窄，基本隐藏，为了录入数据又不占用空间
		sheet.setColumnWidth(1, 1);
		sheet.setColumnWidth(2, 1);
		sheet.setColumnWidth(3, 2048);
		sheet.setColumnWidth(4, 3072);
		sheet.setColumnWidth(5, 1280);

		for (int i = 6; i < 14; i++) {
			sheet.setColumnWidth(i, 1600);
		}

		// 立定 仰卧 800
		sheet.setColumnWidth(10, 2048);
		sheet.setColumnWidth(12, 2200);
		sheet.setColumnWidth(13, 2200);
		sheet.setDefaultRowHeightInPoints(16.0F);

		// 合并单元格作为标题
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 13);

		sheet.addMergedRegion(cra);

		Row rowTitle = sheet.createRow(0);

		Cell cell_1 = rowTitle.createCell(0);

		cell_1.setCellStyle(titleStyle);
		cell_1.setCellValue(title);

		HSSFRow row = sheet.createRow(1);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = list.iterator();
		int index = 1;
		while (it.hasNext()) {
			// 第一行为标题，第二行为表头，第三行才开始显示数据
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();

			// 14原本是fields.length ，为了生成网格线加了判断
			for (short i = 0; i < 14; i++) {
				if (i >= fields.length) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(cellStyle);
				} else {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(cellStyle);

					Field field = fields[i];
					String fieldName = field.getName();
					String getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					try {
						Class tCls = t.getClass();
						Method getMethod = tCls.getMethod(getMethodName,
								new Class[] {});
						Object value = getMethod.invoke(t, new Object[] {});
						String textValue = null;

						if ((value instanceof Boolean)) {
							boolean bValue = (Boolean) value;
							textValue = "男";
							if (!bValue) {
								textValue = "女";
							}
						} else if ((value instanceof Date)) {
							Date date = (Date) value;
							SimpleDateFormat sdf = new SimpleDateFormat(pattern);
							textValue = sdf.format(date);
						} else if ((value instanceof byte[])) {
							row.setHeightInPoints(60.0F);

							sheet.setColumnWidth(i, (short) 2856);

							byte[] bsValue = (byte[]) value;
							HSSFClientAnchor anchor = new HSSFClientAnchor(0,
									0, 1023, 255, (short) 6, index, (short) 6,
									index);
							anchor.setAnchorType(2);
						} else {
							textValue = value.toString();
						}

						if (textValue != null) {
							Pattern p = Pattern.compile("^//d+(//.//d+)?$");
							Matcher matcher = p.matcher(textValue);
							if (matcher.matches()) {
								cell.setCellValue(Double.parseDouble(textValue));
							} else {
								HSSFRichTextString richString = new HSSFRichTextString(
										textValue);
								HSSFFont font3 = workbook.createFont();

								richString.applyFont(font3);
								cell.setCellValue(richString);
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportScore(String title, String[] headers, List<T> list,
			OutputStream out, String pattern) {
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(title);

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);

		sheet.setColumnWidth(0, 768);
		sheet.setColumnWidth(1, 2560);
		sheet.setColumnWidth(2, 2560);
		sheet.setColumnWidth(3, 3072);
		sheet.setDefaultRowHeightInPoints(15.0F);

		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 13);

		sheet.addMergedRegion(cra);

		Row rowTitle = sheet.createRow(0);

		Cell cell_1 = rowTitle.createCell(0);

		cell_1.setCellStyle(cellStyle);
		cell_1.setCellValue(title);

		HSSFRow row = sheet.createRow(1);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		Iterator<T> it = list.iterator();
		int index = 1;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = it.next();

			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();

			for (short i = 0; i < fields.length; i++) {

				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(cellStyle);

				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				String textValue = null;
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[0]);
					Object value = getMethod.invoke(t, new Object[0]);

					
					if ((value instanceof Boolean)) {
						boolean bValue = ((Boolean) value).booleanValue();
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if ((value instanceof Date)) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if ((value instanceof byte[])) {
						row.setHeightInPoints(60.0F);

						sheet.setColumnWidth(i, (short) 2856);

						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
								1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
					} else {
						textValue = value.toString();
					}
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (NullPointerException e) {
					textValue="";
//					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportScore(String title, String[] headers, List<T> list,
			OutputStream out) {
		exportScore(title, headers, list, out, "yyyy-MM-dd");
	}
}
