package com.sports.servlet;

import com.sports.dao.proxy.TestScoreDaoProxy;
import com.sports.entity.StudentView;
import com.sports.entity.TestScore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeaChangeScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Teacher/TeacherMemberGrade.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession();
		if ((ses.getAttribute("testTermId") != null)
				&& (ses.getAttribute("singleStudent") != null)) {
			int testTermId = Integer.parseInt(ses.getAttribute("testTermId")
					.toString());
			StudentView sv = (StudentView) ses.getAttribute("singleStudent");
			String stuNumber = sv.getStuNumber();

			TestScore ts = new TestScore();
			
			//防止没有成绩通过修改来增加成绩
			ts.setTestTermId(testTermId);
			ts.setTestClassId(Integer.valueOf(String.valueOf(ses.getAttribute("testClassId"))));
			ts.setStuNumber(stuNumber);

			String heightAndWeight = request.getParameter("heightAndWeight");
			int mark = heightAndWeight.indexOf("/");

			String height = "0";
			String weight = "0";
			if (mark != 0) {
				height = heightAndWeight.substring(0, mark - 1);
				weight = heightAndWeight.substring(mark + 1);
			}
			ts.setHeight(Double.parseDouble(height));
			ts.setWeight(Double.parseDouble(weight));
			if ((request.getParameter("vit") != "")
					&& (request.getParameter("vit") != null)) {
				ts.setVitalCapacity(Double.parseDouble(request
						.getParameter("vit")));
			} else {
				ts.setVitalCapacity(0.0D);
			}
			if ((request.getParameter("sit") != "")
					&& (request.getParameter("sit") != null)) {
				ts.setSitAndReach(Double.parseDouble(request
						.getParameter("sit")));
			} else {
				ts.setSitAndReach(0.0D);
			}
			if ((request.getParameter("jump") != "")
					&& (request.getParameter("jump") != null)) {
				ts.setJump(Double.parseDouble(request.getParameter("jump")));
			} else {
				ts.setJump(0.0D);
			}
			if ((request.getParameter("fifty") != "")
					&& (request.getParameter("fifty") != null)) {
				ts.setFiftyRun(Double.parseDouble(request.getParameter("fifty")));
			} else {
				ts.setFiftyRun(0.0D);
			}
			if ((request.getParameter("pullup") != "")
					&& (request.getParameter("pullup") != null)) {
				ts.setPullup(Integer.parseInt(request.getParameter("pullup")));
			} else {
				ts.setPullup(0);
			}
			if ((request.getParameter("situp") != "")
					&& (request.getParameter("situp") != null)) {
				ts.setSitup(Integer.parseInt(request.getParameter("situp")));
			} else {
				ts.setSitup(0);
			}
			if ((request.getParameter("eight") != "")
					&& (request.getParameter("eight") != null)) {
				ts.setEightHundredsRun(request.getParameter("eight"));
			} else {
				ts.setEightHundredsRun("");
			}
			if ((request.getParameter("one") != "")
					&& (request.getParameter("one") != null)) {
				ts.setOneThousandRun(request.getParameter("one"));
			} else {
				ts.setOneThousandRun("");
			}
			try {
				TestScoreDaoProxy tsdp1 = new TestScoreDaoProxy();
				int result = 0;
				if (tsdp1.findByTestTermIdAndStuNumber(testTermId, stuNumber) != null) {//如果有成绩记录 就执行更新操作
					TestScoreDaoProxy tsdp2 = new TestScoreDaoProxy();
					result = tsdp2.doUpdate(testTermId, stuNumber, ts);
				} else {//如果没有成绩记录  就执行插入操作
					TestScoreDaoProxy tsdp3 = new TestScoreDaoProxy();
					result = tsdp3.doCreate(ts);
				}
				if (result == 1) {
					pw.print("<script language='javascript'>alert('成绩修改成功');window.location.href='Teacher/TeacherClassMember.jsp';</script>");
				} else {
					pw.print("<script language='javascript'>alert('成绩修改失败');window.location.href='Teacher/TeacherClassMember.jsp';</script>");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("Teacher/TeacherIndex.jsp");
		}
	}
}
