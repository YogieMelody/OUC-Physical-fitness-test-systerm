package com.sports.servlet;

import com.sports.dao.proxy.ReserveDaoProxy;
import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.entity.Reserve;
import com.sports.entity.StudentView;
import com.sports.service.ExportOrder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeaLookTestStudentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Teacher/TeacherIndex.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession();
		int testTermId = Integer.parseInt(ses.getAttribute("testTermId")
				.toString());

		String exportStudent = request.getParameter("exportStudent");
		if ((exportStudent!="") && (exportStudent != null)) {
			int testClassId = Integer.parseInt(request
					.getParameter("exportStudent"));

			ExportOrder.exportTestOrder(testTermId, testClassId, response);
		} else {
			int testClassId = Integer.parseInt(request.getParameter("lookStudent"));

			ses.setAttribute("testClassId", Integer.valueOf(testClassId));
			if (ses.getAttribute("testTermId") != null) {
				if (testClassId != 0) {
					List<Reserve> rList = null;
					try {
						ReserveDaoProxy rdp = new ReserveDaoProxy();
						rList = rdp.findByTestTermIdAndTestClassId(testTermId,
								testClassId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						StudentDaoProxy sdp = null;
						StudentView sv = null;
						List<StudentView> svList = new ArrayList<StudentView>();
						for (int i = 0; i < rList.size(); i++) {
							sdp = new StudentDaoProxy();
							sv = sdp.findByIdV(((Reserve) rList.get(i))
									.getStuId());
							svList.add(sv);
						}
						ses.setAttribute("svList", svList);
						response.sendRedirect("Teacher/TeacherClassMember.jsp");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				response.sendRedirect("Teacher/TeacherClassMember.jsp");
			}
		}
	}
}
