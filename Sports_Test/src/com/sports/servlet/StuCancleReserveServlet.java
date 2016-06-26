package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sports.dao.proxy.ReserveDaoProxy;
import com.sports.dao.proxy.TestClassDaoProxy;
import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.Reserve;
import com.sports.entity.StudentView;

public class StuCancleReserveServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("Student/index.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 先判定当前是否开放预约
		try {
			TestTermDaoProxy judge = new TestTermDaoProxy();
			int isOpen = judge.findNow().getIsOpen();
			if (isOpen == 0) {
				response.sendRedirect("Student/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		Reserve reserve = new Reserve();

		HttpSession ses = request.getSession();
		int stuId = 0;
		if (ses.getAttribute("student") != null) {
			stuId = ((StudentView) (ses.getAttribute("student"))).getId();
		} else {
			response.sendRedirect("User/login.jsp");
		}

		try {
			TestTermDaoProxy ttdp = new TestTermDaoProxy();
			int testTermId = ttdp.findNow().getId();

			ReserveDaoProxy rdp = new ReserveDaoProxy();
			reserve = rdp.findNowReserve(testTermId, stuId);

			TestClassDaoProxy tcdp = new TestClassDaoProxy();
			int result = tcdp.cancle(reserve);
			if (result == 1) {
				pw.print("<script language='javascript'>alert('取消预约成功，请重新选择预约班级');window.location.href='Student/Classchoose.jsp';</script>");
			} else {
				pw.print("<script language='javascript'>alert('取消预约失败');window.location.href='Student/OrderSearch.jsp';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
