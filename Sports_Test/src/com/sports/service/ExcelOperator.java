package com.sports.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.entity.Student;
import com.sports.util.GetHash;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelOperator {
	
	public static void studentDbToExcel(String fileName){//如学生信息.xls
		FileSystemView fsv = FileSystemView.getFileSystemView();
		//fsv.getHomeDirectory();    //读取桌面路径的方法了，但是返回值是File
		String desktop=fsv.getHomeDirectory().getAbsolutePath();
		String path=desktop+"\\"+fileName;
		try{
			WritableWorkbook wwb=null;
			
			//创建可写入的工作簿
			File file=new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			//以fileName为文件名来创建一个Workbook
			wwb=Workbook.createWorkbook(file);
			
			WritableSheet ws=wwb.createSheet("Sheet1", 0);
			
			//查询数据库中的所有的数据
			StudentDaoProxy sdp=new StudentDaoProxy();
			List<Student> studentList=sdp.findAll();
			
			//要插入到的Excel表格的行号，默认从0开始
			Label labelId=new Label(0,0,"编号(id)");
			Label labelStuNumber=new Label(1,0,"学号(stuNumber)");
			Label labelStuName=new Label(2,0,"姓名(stuName)");
			Label labelStuSex=new Label(3,0,"性别(stuSex)");
			Label labelStuIdentification=new Label(4,0,"身份证(stuIdentification)");
			Label labelStuIsGat=new Label(5,0,"是否港澳生");
			Label labelStuPhone=new Label(6,0,"电话(stuPhone)");
			Label labelDepartmentId=new Label(7,0,"院系编号(departmentId)");
			Label labelMajorId=new Label(8,0,"班级编号(majorId)");
			Label labelGradeNow=new Label(9,0,"入学年份(gradeNow)");
			Label labelNationMark=new Label(10,0,"民族编号(nationMark)");
			Label labelBirthday=new Label(11,0,"出生日期(birthday)");
			Label labelAddress=new Label(12,0,"家庭地址(address)");
			Label labelPolitics=new Label(13,0,"政治面貌(politics)");
			
            ws.addCell(labelId);
            ws.addCell(labelStuNumber);
            ws.addCell(labelStuName);
            ws.addCell(labelStuSex);
            ws.addCell(labelStuIdentification);
            ws.addCell(labelStuIsGat);
            ws.addCell(labelStuPhone);
            ws.addCell(labelDepartmentId);
            ws.addCell(labelMajorId);
            ws.addCell(labelGradeNow);
            ws.addCell(labelNationMark);
            ws.addCell(labelBirthday);
            ws.addCell(labelAddress);
            ws.addCell(labelPolitics);
            
            //循环添加
            for(int i=0;i<studentList.size();i++){
            	//添加“”是为了把int型数据转换为String
            	//为了好看就所有的都加了
            	Label labelId_i=new Label(0,i+1,studentList.get(i).getId()+"");
            	Label labelStuNumber_i=new Label(1,i+1,studentList.get(i).getStuNumber()+"");
            	Label labelStuName_i=new Label(2,i+1,studentList.get(i).getStuName()+"");
            	Label labelStuSex_i=new Label(3,i+1,studentList.get(i).getStuSex()+"");
            	Label labelStuIdentification_i=new Label(4,i+1,studentList.get(i).getStuIdentification()+"");
            	Label labelStuIsGat_i=new Label(5,i+1,studentList.get(i).getStuIsGat()+"");
            	Label labelStuPhone_i=new Label(6,i+1,studentList.get(i).getStuPhone()+"");
            	Label labelDepartmentId_i=new Label(7,i+1,studentList.get(i).getDepartmentId()+"");
            	Label labelMajorId_i=new Label(8,i+1,studentList.get(i).getMajorId()+"");
            	Label labelGradeNow_i=new Label(9,i+1,studentList.get(i).getGradeNow()+"");
            	Label labelNationMark_i=new Label(10,i+1,studentList.get(i).getNationMark()+"");
            	Label labelBirthday_i=new Label(11,i+1,studentList.get(i).getBithday()+"");
            	Label labelAddress_i=new Label(12,i+1,studentList.get(i).getAddress()+"");
            	Label labelPolitics_i=new Label(13,i+1,studentList.get(i).getPolitics()+"");
            	
            	ws.addCell(labelId_i);
                ws.addCell(labelStuNumber_i);
                ws.addCell(labelStuName_i);
                ws.addCell(labelStuSex_i);
                ws.addCell(labelStuIdentification_i);
                ws.addCell(labelStuIsGat_i);
                ws.addCell(labelStuPhone_i);
                ws.addCell(labelDepartmentId_i);
                ws.addCell(labelMajorId_i);
                ws.addCell(labelGradeNow_i);
                ws.addCell(labelNationMark_i);
                ws.addCell(labelBirthday_i);
                ws.addCell(labelAddress_i);
                ws.addCell(labelPolitics_i);
            }
            
            //写进文档
            wwb.write();
            //关闭Excel工作簿对象
            wwb.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static List<Student> getAllByExcel(String fileName){
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		//fsv.getHomeDirectory();    //读取桌面路径的方法了，但是返回值是File
		String desktop=fsv.getHomeDirectory().getAbsolutePath();
		String path=desktop+"\\"+fileName;
		
		List<Student> studentList=new ArrayList<Student>();
		
        try {
            Workbook rwb=Workbook.getWorkbook(new File(path));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            Student student=null;
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                	//此处代码可以合并 减少一半量
                	//至于为什么没有合并，我也不知道
                	String id=rs.getCell(j++,i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String stuNumber=rs.getCell(j++, i).getContents();
                    String stuName=rs.getCell(j++, i).getContents();
                    String stuSex=rs.getCell(j++, i).getContents();
                    String stuIdentification=rs.getCell(j++, i).getContents();
                    String stuIsGat=rs.getCell(j++, i).getContents();
                    String stuPhone=rs.getCell(j++, i).getContents();
                    String departmentId=rs.getCell(j++, i).getContents();
                    String majorId=rs.getCell(j++, i).getContents();
                    String gradeNow=rs.getCell(j++, i).getContents();
                    String nationMark=rs.getCell(j++, i).getContents();
                    String birthday=rs.getCell(j++, i).getContents();
                    String address=rs.getCell(j++, i).getContents();
                    String politics=rs.getCell(j++, i).getContents();
                    
                    student=new Student();
                    student.setStuNumber(stuNumber);
                    
                    //把学号md5加密后存入数据库
                    String stuPassword=GetHash.getMD5(stuNumber);
                    student.setStuPassword(stuPassword);
                    
                    student.setStuName(stuName);
                    student.setStuSex(stuSex);
                    student.setStuIdentification(stuIdentification);
                    student.setStuIsGat(stuIsGat);
                    student.setStuPhone(stuPhone);
                    student.setDepartmentId(Integer.parseInt(departmentId));
                    student.setMajorId(Integer.parseInt(majorId));
                    student.setGradeNow(gradeNow);
                    student.setNationMark(Integer.parseInt(nationMark));
                    student.setBithday(birthday);
                    student.setAddress(address);
                    student.setPolitics(politics);
                    
                    studentList.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return studentList;
	}
	
	public static int studentExcelToDb(String fileName){
		
		int successCount=0;
		List<Student> studentList=getAllByExcel(fileName);
		
		try{
			StudentDaoProxy sdp=null;
			for(Student student:studentList){
				sdp=new StudentDaoProxy();
				successCount+=sdp.doCreate(student);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return successCount;
	}
	
	public static void main(String[] args) {
		//主函数用于测试
		String fileName="\\学生信息.xls";
		System.out.println(studentExcelToDb(fileName));
		
		//studentDbToExcel(fileName);
		
	}
}
