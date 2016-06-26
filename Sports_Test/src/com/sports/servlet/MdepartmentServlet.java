package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sports.dao.proxy.DepartmentDaoProxy;
import com.sports.entity.Department;
import com.sports.entity.ManagerLogin;

public class MdepartmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession();// 获取Session
		if (ses.getAttribute("manager") != null) {// 判断是否登录成功，如果成功则进入后台管理
			request.getRequestDispatcher("/BackStage/Mdepartment.jsp").forward(
					request, response);
		} else {// 否则进入登录页
			request.getRequestDispatcher("/BackStage/Mlogin.jsp").forward(
					request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/BackStage/Mdepartment.jsp";
		int result = 0;
		String inf = null;
		// 判断是下方的form还是上方表格的form
		String doWhat = request.getParameter("hid");
		if ("".equals(doWhat) || doWhat == null) {// 上方
			String isUpdate = request.getParameter("update");
			if ("".equals(isUpdate) || isUpdate == null) {
				int deleteId = Integer.parseInt(request.getParameter("delete"));
				try {
					DepartmentDaoProxy ddp = new DepartmentDaoProxy();
					result = ddp.doDelete(deleteId);
					if (result == 1) {
						inf = "数据删除成功";
					} else {
						inf = "数据删除失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				int updateId = Integer.parseInt(request.getParameter("update"));
				try {
					DepartmentDaoProxy ddp = new DepartmentDaoProxy();
					Department d = null;
					d = ddp.findById(updateId);
					request.setAttribute("single", d);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {// 下方
			if (doWhat.equals("create")) {
				try {
					DepartmentDaoProxy ddp = new DepartmentDaoProxy();
					int isExist=ddp.getIdByName(request.getParameter("insertDepartmentName"));
					if(isExist==0){
						DepartmentDaoProxy ddp1=new DepartmentDaoProxy();
						Department dep = new Department();
						dep.setDepartmentName(request
								.getParameter("insertDepartmentName"));
						result = ddp1.doCreate(dep);
						if (result == 1) {
							inf = "数据增加成功";
						} else {
							inf = "数据增加失败";
						}
					}else{
						inf="该院系已经存在，增加失败";
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (doWhat.equals("update")) {
				try {
					DepartmentDaoProxy ddp = new DepartmentDaoProxy();
					Department dep = new Department();
					dep.setDepartmentName(request
							.getParameter("newDepartmentName"));
					result = ddp.doUpdate(Integer.parseInt(request
							.getParameter("updateDepartmentId")), dep);
					if (result == 1) {
						inf = "数据更新成功";
					} else {
						inf = "数据更新失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}/*
			 * else if(doWhat.equals("delete")){ try{ DepartmentDaoProxy ddp=new
			 * DepartmentDaoProxy();
			 * result=ddp.doDelete(Integer.parseInt(request
			 * .getParameter("deleteDepartmentId"))); if(result==1){
			 * inf="数据删除成功"; } }catch(Exception e){ e.printStackTrace(); } }
			 */
		}

		request.setAttribute("inf", inf);
		request.getRequestDispatcher(path).forward(request, response);
	}

}
