package com.sports.servlet;

import com.sports.dao.proxy.TestClassDaoProxy;
import com.sports.dao.proxy.TestScoreDaoProxy;
import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.TestClass;
import com.sports.entity.TestScore;
import com.sports.entity.TestTerm;
import com.sports.service.ExportExcel;
import com.sports.service.ExportOrder;
import com.sports.service.TransferScore;
import com.sports.util.ChangeISO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MtestScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/BackStage/Mstudent.jsp";
		String login = "/User/login.jsp";
		HttpSession ses = request.getSession();
		if (ses.getAttribute("manager") != null) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			request.getRequestDispatcher(login).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inf = null;
		String exportScore = request.getParameter("exportScore");
		if (exportScore != null) {// 为导出成绩操作
			try {
				TestScoreDaoProxy tsdp = new TestScoreDaoProxy();
				List<TestScore> tsList = tsdp.findAll();

				ExportExcel<TestScore> ex = new ExportExcel();
				String[] headers = { "编号", "测试班级", "测试学期", "学号", "身高", "体重",
						"肺活量", "50米", "跳远", "坐位体前屈", "800米", "1000米", "仰卧起坐",
						"引体向上" };
				String fileName = "学生成绩.xls";
				OutputStream out = new FileOutputStream("C://" + fileName);
				ex.exportScore(fileName, headers, tsList, out);
				out.close();
				try {
					File file = new File("C://" + fileName);

					String filename = file.getName();

					InputStream fis = new BufferedInputStream(
							new FileInputStream("C://" + filename));
					byte[] buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();

					response.reset();

					response.addHeader(
							"Content-Disposition",
							"attachment;filename="
									+ ChangeISO.parseGBK(fileName));
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
		} else if (request.getParameter("exportOrders") != null) {// 为导出所有测试名单操作
			try {
				TestTermDaoProxy ttdp = new TestTermDaoProxy();
				int testTermId = ttdp.findNow().getId();

				TestClassDaoProxy tcdp = new TestClassDaoProxy();
				List<TestClass> tcList = tcdp.findByTestTermId(testTermId);
				for (int i = 0; i < tcList.size(); i++) {
					ExportOrder.exportTestOrder(testTermId, tcList.get(i)
							.getId(), response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		request.getRequestDispatcher("/BackStage/MtestScore.jsp").forward(
//				request, response);
//		PrintWriter pw = response.getWriter();
//		pw.print("<script language='javascript'>window.location.href='BackStage/MtestScore.jsp';</script>");
//		pw.close();
	}
}
