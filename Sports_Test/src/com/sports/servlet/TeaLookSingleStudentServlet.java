package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.dao.proxy.TestScoreDaoProxy;
import com.sports.entity.StudentView;
import com.sports.entity.TestScoreView;

public class TeaLookSingleStudentServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("Teacher/TeacherIndex.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("lookSingleStudent")!=null){
			String stuNumber=request.getParameter("lookSingleStudent");
			HttpSession ses=request.getSession();
			int testTermId=Integer.parseInt(ses.getAttribute("testTermId").toString());
			
			try {
				//查找这个学生成绩的信息
				TestScoreDaoProxy tsdp=new TestScoreDaoProxy();
				TestScoreView tsv=tsdp.findByTestTermIdAndStuNumber(testTermId, stuNumber);
				
				//查找这个学生的信息
				StudentDaoProxy sdp=new StudentDaoProxy();
				StudentView sv=sdp.findByStuNumberV(stuNumber);
				ses.setAttribute("singleStudent", sv);
				ses.setAttribute("singleScore", tsv);
				response.sendRedirect("Teacher/TeacherMemberGrade.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			response.sendRedirect("Teacher/TeacherClassMember.jsp");
		}
	}

}
