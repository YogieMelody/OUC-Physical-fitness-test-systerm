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

import com.sports.dao.proxy.DepartmentDaoProxy;
import com.sports.dao.proxy.MajorClassDaoProxy;
import com.sports.entity.MajorClass;
import com.sports.entity.MajorClassView;

public class MmajorClassServlet extends HttpServlet {

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

		String path = "/BackStage/MmajorClass.jsp";
		String login = "/BackStage/Mlogin.jsp";
		HttpSession ses = request.getSession();
		if (ses.getAttribute("manager") != null) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			request.getRequestDispatcher(login).forward(request, response);
		}
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

		String path = "/BackStage/MmajorClass.jsp";
		int result = 0;
		String inf = null;

		String doWhat = request.getParameter("hid");

		if ("".equals(doWhat) || doWhat == null) {
			String isUpdate = request.getParameter("update");
			if ("".equals(isUpdate) || isUpdate == null) {
				int deleteId = Integer.parseInt(request.getParameter("delete"));
				try {
					MajorClassDaoProxy mcdp = new MajorClassDaoProxy();
					result = mcdp.doDelete(deleteId);
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
					MajorClassDaoProxy mcdp = new MajorClassDaoProxy();
					MajorClassView mcv = null;
					mcv = mcdp.findByIdV(updateId);
					if (mcv == null) {
						inf = "没找到";
					} else {
						inf = "请到下方表格进行修改";
					}
					request.setAttribute("single", mcv);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {// 下方
			if (doWhat.equals("create")) {
				try {
					// 获取要插入的部门id
					String departmentName = request
							.getParameter("departmentName");
					DepartmentDaoProxy ddp = new DepartmentDaoProxy();
					int depId = ddp.getIdByName(departmentName);

					String majorClassName = request
							.getParameter("majorClassName");
					
					//判定数据是否存在
					MajorClassDaoProxy mcdp = new MajorClassDaoProxy();
					int isExist=mcdp.getIdByName(majorClassName);
					if(isExist==0){//id为0说明没有找到，记录不存在，可以添加数据到数据库
						// 生成MajorClass对象
						MajorClass mc = new MajorClass();
						mc.setDepartmentId(depId);
						mc.setMajorClassName(majorClassName);
						//因为数据库连接的关闭，所以此处声明两个MajorClassDaoProxy的对象
						MajorClassDaoProxy mcdp2=new MajorClassDaoProxy();
						// 增加专业班级(如计算机3班)
						result = mcdp2.doCreate(mc);
						if (result > 0) {
							inf = "增加专业班级成功";
						} else {
							inf = "增加测试班级失败";
						}
					}else{
						inf="该班级已经存在,增加失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("update")) {
				try {
					// 获取要更新的部门id
					String departmentName = request
							.getParameter("updateDepartmentName");
					DepartmentDaoProxy ddp = new DepartmentDaoProxy();
					int depId = ddp.getIdByName(departmentName);

					String majorClassName = request
							.getParameter("updateMajorClassName");

					// 生成MajorClass对象
					MajorClass mc = new MajorClass();
					mc.setDepartmentId(depId);
					mc.setMajorClassName(majorClassName);

					// 增加专业班级(如计算机3班)
					MajorClassDaoProxy mcdp = new MajorClassDaoProxy();
					result = mcdp.doUpdate(Integer.parseInt(request
							.getParameter("updateMajorClassId")), mc);
					if (result > 0) {
						inf = "修改专业班级成功";
					} else {
						inf = "修改测试班级失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("query")) {
				String depName=request.getParameter("queryDepartmentName");
				List<MajorClassView> majorClassViewList=new ArrayList<MajorClassView>();
				try{
					MajorClassDaoProxy mcdp=new MajorClassDaoProxy();
					majorClassViewList=mcdp.findByDepNameV(depName);
					if(!majorClassViewList.isEmpty()){
						inf="所属院系为"+depName+"的专业班级有";
						HttpSession ses=request.getSession();
						ses.setAttribute("queryList", majorClassViewList);
					}else{
						inf="无结果";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("inf", inf);
		request.getRequestDispatcher(path).forward(request, response);
	}
}
