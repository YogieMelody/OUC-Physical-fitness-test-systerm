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

public class StuReserveServlet extends HttpServlet {

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
		//先判定当前是否开放预约
		try{
			TestTermDaoProxy judge=new TestTermDaoProxy();
			int isOpen=judge.findNow().getIsOpen();
			if(isOpen==0){
				response.sendRedirect("Student/index.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		PrintWriter pw = response.getWriter();
		//获取到要预约的测试班级的id
		int testClassId=Integer.parseInt(request.getParameter("reserve"));
		int testTermId=0;
		//获取当前测试学期的Id
		TestTermDaoProxy ttdp;
		try {
			ttdp = new TestTermDaoProxy();
			testTermId=ttdp.findNow().getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取学生Id
		HttpSession ses=request.getSession();
		int stuId=0;
		if(ses.getAttribute("student")!=null){
			stuId=((StudentView)(ses.getAttribute("student"))).getId();
		}else{
			response.sendRedirect("User/login.jsp");
		}
		
		//声明一个Reserve对象，准备执行book操作
		Reserve reserve=new Reserve();
		reserve.setStuId(stuId);
		reserve.setTestClassId(testClassId);
		reserve.setTestTermId(testTermId);
		
		//先判定该学生是否已经预约,再执行预约操作
		try{
			ReserveDaoProxy rdp=new ReserveDaoProxy();
			Reserve isExist=null;
			isExist=rdp.findNowReserve(testTermId, stuId);
			if(isExist!=null){//如果测试记录已经存在的话
				pw.print("<script language='javascript'>alert('您已经预约了班级,如果想预约新的班级请先取消之前的预约');window.location.href='Student/Classchoose.jsp';</script>");
			}else{
				try{
					TestClassDaoProxy tcdp=new TestClassDaoProxy();
					int result=tcdp.book(reserve);
								
					//在预约成功后插入一条空成绩数据到testscore表  暂时不这样处理
					
					if(result==1){
						pw.print("<script language='javascript'>alert('预约成功,请按时参加测试');window.location.href='Student/Classchoose.jsp';</script>");
					}else if(result==0){
						pw.print("<script language='javascript'>alert('预约失败');window.location.href='Student/Classchoose.jsp';</script>");
					}else if(result==-1){				
						pw.print("<script language='javascript'>alert('班级已满额,预约失败');window.location.href='Student/Classchoose.jsp';</script>");
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
