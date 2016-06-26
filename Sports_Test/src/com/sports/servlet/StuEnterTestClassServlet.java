package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.TestTerm;

public class StuEnterTestClassServlet extends HttpServlet {

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

		// 从首页获取校区和测试学期的Id，然后根据Id检验一下是否是当前测试学期
		// 如何是则进行页面跳转，进入Classchoose.jsp页面，测试班级为当前测试学期+前面所选择的校区条件下的全部，这部分有分页功能
		String schoolArea = request.getParameter("schoolArea");
		// 定义输出对象
		PrintWriter pw = response.getWriter();
		try {
			TestTermDaoProxy ttdp = new TestTermDaoProxy();
			TestTerm now = ttdp.findNow();
			int isOpen = now.getIsOpen();
			if (isOpen == 1) {// 说明当前测试学期的预约开关打开
				HttpSession ses = request.getSession();
				ses.setAttribute("schoolArea", schoolArea);
				response.sendRedirect("Student/Classchoose.jsp");
			} else {
				pw.print("<script language='javascript'>alert('当前预约系统尚未开放');window.location.href='Student/index.jsp';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
