package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.dao.proxy.TeacherDaoProxy;
import com.sports.entity.ManagerLogin;
import com.sports.entity.Student;
import com.sports.entity.StudentView;
import com.sports.entity.Teacher;
import com.sports.factory.DaoFactory;
import com.sports.util.GetHash;

public class LoginServlet extends HttpServlet {

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

		// 点击注销按钮时，清空session，实现了注销功能
		HttpSession ses = request.getSession();
		ses.removeAttribute("student");
		ses.removeAttribute("teacher");
		ses.removeAttribute("rand");
		// 转回登录页面
		response.sendRedirect("User/login.jsp");
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

		PrintWriter pw = response.getWriter();

		String identity = request.getParameter("choose");

		// 接收验证码
		String vercode = request.getParameter("vercode").toUpperCase();
		String ver2 = request.getSession().getAttribute("rand").toString()
				.toUpperCase();
		if (identity.equals("学生")) {
			String stuNumber = request.getParameter("number");
			String stuPassword = GetHash.getMD5(request
					.getParameter("password"));// 将密码哈希后与数据库中的密码字段相比对
			// 判断验证码是否正确
			if (!(vercode.equals(ver2))) {
				pw.print("<script language='javascript'>alert('验证码输入有误');window.location.href='User/login.jsp';</script>");
			} else if (stuNumber == null || "".equals(stuNumber)) {
				pw.print("<script language='javascript'>alert('学号不能为空');window.location.href='User/login.jsp';</script>");
			} else if (stuPassword == null || "".equals(stuPassword)) {
				pw.print("<script language='javascript'>alert('密码不能为空');window.location.href='User/login.jsp';</script>");
			} else {
				Student student = new Student();
				student.setStuNumber(stuNumber);
				student.setStuPassword(stuPassword);
				try {
					if (DaoFactory.getIUserDaoInstance().findLoginStudent(
							student)) {
						// info.add("用户登录成功，欢迎"+student.getStuNumber()+"光临！");

						// 获取完全信息
						StudentDaoProxy sdp = new StudentDaoProxy();
						StudentView sv = sdp.findByStuNumberV(stuNumber);
						// 填充到session中
						HttpSession ses = request.getSession();
						// 设置session持续的最大时长
						ses.setMaxInactiveInterval(120 * 60);
						ses.setAttribute("student", sv);
						// 这是客户端跳转，可以改变url
						response.sendRedirect("Student/index.jsp");
					} else {
						pw.print("<script language='javascript'>alert('用户登录失败，错误的用户名或密码');window.location.href='User/login.jsp';</script>");
						// 这是服务器端跳转，url不会改变，个人觉得这样不好
						// request.getRequestDispatcher(fail).forward(request,
						// response);
						// response.sendRedirect(fail);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		else if (identity.equals("教师")) {
			String teaNumber = request.getParameter("number");
			String teaPassword = GetHash.getMD5(request
					.getParameter("password"));// 将密码哈希后与数据库中的密码字段相比对
			// 判断验证码是否正确
			if (!(vercode.equals(ver2))) {
				pw.print("<script language='javascript'>alert('验证码输入有误');window.location.href='User/login.jsp';</script>");
			} else if (teaNumber == null || "".equals(teaNumber)) {
				pw.print("<script language='javascript'>alert('学号不能为空');window.location.href='User/login.jsp';</script>");
			} else if (teaPassword == null || "".equals(teaPassword)) {
				pw.print("<script language='javascript'>alert('密码不能为空');window.location.href='User/login.jsp';</script>");
			} else {
				Teacher teacher = new Teacher();
				teacher.setTeaNumber(teaNumber);
				teacher.setTeaPassword(teaPassword);

				try {
					if (DaoFactory.getIUserDaoInstance().findLoginTeacher(
							teacher)) {
						// 获取完全信息
						TeacherDaoProxy tdp = new TeacherDaoProxy();
						Teacher t=tdp.findByTeaNumber(teaNumber);
						// 填充到session中
						HttpSession ses = request.getSession();
						// 设置session持续的最大时长
						ses.setMaxInactiveInterval(120 * 60);
						ses.setAttribute("teacher", t);
						// 这是客户端跳转，可以改变url
						response.sendRedirect("Teacher/TeacherIndex.jsp");
					} else {
						pw.print("<script language='javascript'>alert('用户登录失败，错误的用户名或密码');window.location.href='User/login.jsp';</script>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		else if (identity.equals("管理员")) {
			String managerId = request.getParameter("number");
			String managerPwd = GetHash
					.getMD5(request.getParameter("password"));// 将密码哈希后与数据库中的密码字段相比对
			if (managerId == null || "".equals(managerId)) {
				pw.print("<script language='javascript'>alert('用户名不能为空');window.location.href='BackStage/Mdepartment.jsp';</script>");
			}
			if (managerPwd == null || "".equals(managerPwd)) {
				pw.print("<script language='javascript'>alert('密码不能为空');window.location.href='BackStage/Mdepartment.jsp';</script>");
			}
			ManagerLogin managerLogin = new ManagerLogin();
			managerLogin.setManagerId(managerId);
			managerLogin.setManagerPwd(managerPwd);
			try {
				if (DaoFactory.getIUserDaoInstance().findLoginManager(
						managerLogin)) {
					request.getSession().setAttribute("manager", managerLogin);
					pw.print("<script language='javascript'>alert('登录成功');window.location.href='BackStage/Mdepartment.jsp';</script>");
				} else {
					pw.print("<script language='javascript'>alert('管理员登录失败，错误的用户名或密码');window.location.href='BackStage/Mlogin.jsp';</script>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
