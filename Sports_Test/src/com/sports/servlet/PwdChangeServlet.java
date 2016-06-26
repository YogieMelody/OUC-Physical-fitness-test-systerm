package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.dao.proxy.TeacherDaoProxy;
import com.sports.util.GetHash;

public class PwdChangeServlet extends HttpServlet {

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
		int result=0;
		//获取输出流
		PrintWriter pw = response.getWriter();
		//如果是学生修改密码
		if(!(request.getParameter("stuNumber")==null||"".equals(request.getParameter("stuNumber")))){
			String stuNumber=request.getParameter("stuNumber");
			String newPwd1=request.getParameter("newPwd1");
			String newPwd2=request.getParameter("newPwd2");
			if(newPwd1.equals(newPwd2)){
				try {
					String newPwd=GetHash.getMD5(newPwd1);
					StudentDaoProxy sdp=new StudentDaoProxy();
					result=sdp.doChangePassword(stuNumber, newPwd);
					if(result==1){
						pw.print("<script language='javascript'>alert('密码修改成功');window.location.href='Student/index.jsp';</script>");
					}else{
						pw.print("<script language='javascript'>alert('密码修改失败');window.location.href='Student/PasswordChange.jsp';</script>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				//实现弹窗+跳转
				pw.print("<script language='javascript'>alert('两次输入的密码不一致,密码修改失败');window.location.href='Student/PasswordChange.jsp';</script>");
			}
		}else{//如果是老师修改密码
			String teaNumber=request.getParameter("teaNumber");
			String newPwd1=request.getParameter("newPwd1");
			String newPwd2=request.getParameter("newPwd2");
			if(newPwd1.equals(newPwd2)){
				try {
					String newPwd=GetHash.getMD5(newPwd1);
					TeacherDaoProxy tdp=new TeacherDaoProxy();
					result=tdp.doChangePassword(teaNumber, newPwd);
					if(result==1){
						pw.print("<script language='javascript'>alert('密码修改成功');window.location.href='Teacher/TeacherIndex.jsp';</script>");
					}else{
						pw.print("<script language='javascript'>alert('密码修改失败');window.location.href='Teacher/PasswordChange.jsp';</script>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				//实现弹窗+跳转
				pw.print("<script language='javascript'>alert('两次输入的密码不一致,密码修改失败');window.location.href='Teacher/PasswordChange.jsp';</script>");
			}
		}
	}
}
