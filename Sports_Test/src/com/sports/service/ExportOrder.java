package com.sports.service;

import com.sports.dao.proxy.ReserveDaoProxy;
import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.dao.proxy.TestClassDaoProxy;
import com.sports.entity.ExportReserveModel;
import com.sports.entity.Reserve;
import com.sports.entity.Student;
import com.sports.entity.TestClassView;
import com.sports.util.ChangeISO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class ExportOrder {
	public static void exportTestOrder(int testTermId, int testClassId,
			HttpServletResponse response) {
		try {
			TestClassDaoProxy tcdp = new TestClassDaoProxy();
			TestClassView tcv = tcdp.findByIdV(testClassId);

			String testClassName = tcv.getTestClassName().substring(0, 18);

			String teaName = tcv.getTeaName();
			String testArea = tcv.getTestArea();
			String schoolArea = tcv.getSchoolArea();

			String fileName = testClassName + schoolArea + testArea + " "
					+ teaName + ".xls";
			// " 班级id为" + testClassId + " 学期id为" + testTermId +

			ReserveDaoProxy rdp = new ReserveDaoProxy();
			List<Reserve> rList = rdp.findByTestTermIdAndTestClassId(
					testTermId, testClassId);
			List<ExportReserveModel> studentList = new ArrayList<ExportReserveModel>();
			ExportReserveModel exm = null;
			StudentDaoProxy sdp = null;
			Student stu1 = null;
			for (int i = 0; i < rList.size(); i++) {
				sdp = new StudentDaoProxy();

				stu1 = sdp.findById(((Reserve) rList.get(i)).getStuId());

				exm = new ExportReserveModel();

				exm.setId(i + 1);
				exm.setTestClassId(testClassId);
				exm.setTestTermId(testTermId);
				exm.setStuName(stu1.getStuName());
				exm.setStuNumber(stu1.getStuNumber());
				exm.setStuSex(stu1.getStuSex());

				studentList.add(i, exm);
			}
			ExportExcel<ExportReserveModel> ex = new ExportExcel<ExportReserveModel>();
			String[] headers = { "序号", "班级id", "学期id", "姓名", "学号", "性别", "身高",
					"体重", "肺活量", "50米", "立定跳远", "体前屈", "仰卧/引体", "800/1000" };

			// 在服务器和本机运行需要更改盘符
			OutputStream out = new FileOutputStream("C://download//" + fileName);
			ex.exportExcel(fileName, headers, studentList, out);
			out.close();

			try {// 下载文件
				File file = new File("C://download//" + fileName);

				String filename = file.getName();

				InputStream fis = new BufferedInputStream(new FileInputStream(
						"C://download//" + fileName));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();

				response.addHeader("Content-Disposition",
						"attachment;filename=" + ChangeISO.parseGBK(filename));
				response.addHeader("Content-Length",
						String.valueOf(file.length()));
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
