package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sports.dao.proxy.TestScoreDaoProxy;
import com.sports.entity.StudentView;
import com.sports.entity.TestScore;
import com.sports.entity.TestScoreView;

public class StuLookScoreServlet extends HttpServlet {

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

		response.sendRedirect("Student/index.jsp");
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
		//获取学期Id
		int testTermId=Integer.parseInt(request.getParameter("ttId"));
		//获取学生学号
		HttpSession ses=request.getSession();
		String stuNumber=null;
		if(ses.getAttribute("student")!=null){
			stuNumber=((StudentView)ses.getAttribute("student")).getStuNumber();
		}else{
			response.sendRedirect("User/login.jsp");
		}
		
		
		try{
			TestScore ts=null;
			TestScoreDaoProxy tsdp=new TestScoreDaoProxy();
			ts=tsdp.findByTestTermIdAndStuNumberC(testTermId, stuNumber);
			if(ts!=null){
			//用response.sendRedirect必须用session，request会失效
			ses.setAttribute("ts", ts);
			response.sendRedirect("Student/GradeSearch2.jsp");
			}else{
				PrintWriter pw = response.getWriter();
				pw.print("<script language='javascript'>alert('该测试学期无成绩');window.location.href='Student/GradeSearch1.jsp';</script>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
