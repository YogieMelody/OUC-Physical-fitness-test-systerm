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

import com.sports.dao.proxy.TeacherDaoProxy;
import com.sports.dao.proxy.TestClassDaoProxy;
import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.Teacher;
import com.sports.entity.TestClass;
import com.sports.entity.TestClassView;

public class MtestClassServlet extends HttpServlet {

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
	//servlet中尽量使用局部变量
	//单线程不需要考虑并发问题
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "/BackStage/MtestClass.jsp";
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

		String path = "/BackStage/MtestClass.jsp";
		int result = 0;
		String inf = null;
		
		String doWhat = request.getParameter("hid");
		// 判断是下方的form还是上方显示数据列表的form
		if ("".equals(doWhat) || doWhat == null) {// 上方
			String isUpdate = request.getParameter("update");
			if ("".equals(isUpdate) || isUpdate == null) {
				int deleteId = Integer.parseInt(request.getParameter("delete"));
				try {
					TestClassDaoProxy tcdp = new TestClassDaoProxy();
					result = tcdp.doDelete(deleteId);
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
					TestClassDaoProxy tcdp = new TestClassDaoProxy();
					TestClass tc = null;
					tc = tcdp.findById(updateId);
					if (tc == null) {
						inf = "没找到";
					}else{
						inf="请到下方表格进行修改";
					}
					request.setAttribute("single", tc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {// 下方
			if (doWhat.equals("create")) {
				try {
					String p1 = request.getParameter("year");
					String p2 = request.getParameter("month");
					String p3 = request.getParameter("day");
					String p4 = request.getParameter("week");
					String p5 = request.getParameter("classTime");
					String p6 = request.getParameter("time");
					String testClassName = p1 + "年" + p2 + "月" + p3 + "日" + " "
							+ "周" + p4 + " " + p5 + "节" + " " + p6;
					String testArea=request.getParameter("testArea");
					String schoolArea = request.getParameter("schoolArea");
					String teaName = request.getParameter("teacher");
					int limit = Integer.parseInt(request.getParameter("limit"));

					// 根据教师姓名获取教师id用于插入数据库
					// 因为数据库中存储的是教师的id
					Teacher t = null;
					TeacherDaoProxy tdp = new TeacherDaoProxy();
					t = tdp.findByTeaName(teaName);
					int teaId = t.getId();

					// 获取当前测试学期的id
					TestTermDaoProxy ttdp = new TestTermDaoProxy();
					int testTermId = ttdp.findNow().getId();

					// 生成TestClass对象
					TestClass tc = new TestClass();
					tc.setTestClassName(testClassName);
					tc.setTestArea(testArea);
					tc.setTestTermId(testTermId);
					tc.setSchoolArea(schoolArea);
					tc.setTeaId(teaId);
					tc.setLimitNum(limit);
					tc.setNowNum(0);

					// 增加测试班级
					TestClassDaoProxy tcdp = new TestClassDaoProxy();
					TestClass isExist=tcdp.findByTestClassNameAndSchoolAreaAndTeaId(testClassName, schoolArea,teaId);
					if(isExist==null){
						TestClassDaoProxy tcdp1=new TestClassDaoProxy();
						result = tcdp1.doCreate(tc);
						if (result > 0) {
							inf = "添加测试班级成功";
						} else {
							inf = "添加测试班级失败";
						}
					}else{
						inf="测试班级已经存在，添加失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("update")) {
				try {
					String p1 = request.getParameter("updateYear");
					String p2 = request.getParameter("updateMonth");
					String p3 = request.getParameter("updateDay");
					String p4 = request.getParameter("updateWeek");
					String p5 = request.getParameter("updateClassTime");
					String p6 = request.getParameter("updateTime");
					String testClassName = p1 + "年" + p2 + "月" + p3 + "日" + " "
							+ "周" + p4 + " " + p5 + "节" + " " + p6;
					String testArea=request.getParameter("updateTestArea");
					String schoolArea = request.getParameter("updateSchoolArea");
					String teaName = request.getParameter("updateTeacher");
					int limit = Integer.parseInt(request.getParameter("updateLimit"));

					// 根据教师姓名获取教师id用于插入数据库
					// 因为数据库中存储的是教师的id
					Teacher t = null;
					TeacherDaoProxy tdp = new TeacherDaoProxy();
					t = tdp.findByTeaName(teaName);
					int teaId = t.getId();

					// 获取当前测试学期的id
					TestTermDaoProxy ttdp = new TestTermDaoProxy();
					int testTermId = ttdp.findNow().getId();

					// 生成TestClass对象
					TestClass tc = new TestClass();
					tc.setTestClassName(testClassName);
					tc.setTestArea(testArea);
					tc.setTestTermId(testTermId);
					tc.setSchoolArea(schoolArea);
					tc.setTeaId(teaId);
					tc.setLimitNum(limit);
					tc.setNowNum(0);

					// 增加测试班级
					TestClassDaoProxy tcdp = new TestClassDaoProxy();
					result = tcdp.doUpdate(Integer.parseInt(request.getParameter("updateTestClassId")), tc);
					if (result > 0) {
						inf = "修改测试班级成功";
					} else {
						inf = "修改测试班级失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("query")) {
				try {
					String p1 = request.getParameter("queryYear");
					String p2 = request.getParameter("queryMonth");
					String p3 = request.getParameter("queryDay");
					String p4 = request.getParameter("queryWeek");
					String p5 = request.getParameter("queryClassTime");
					String testClassName = p1 + "年" + p2 + "月" + p3 + "日" + " "
							+ "周" + p4 + " " + p5 + "节";
					String schoolArea = request.getParameter("querySchoolArea");
					String teaName = request.getParameter("queryTeacher");
					int limitNum = Integer.parseInt(request.getParameter("queryLimit"));

					// 根据教师姓名获取教师id用于比对数据库
					// 因为数据库中存储的是教师的id
					Teacher t = null;
					TeacherDaoProxy tdp = new TeacherDaoProxy();
					t = tdp.findByTeaName(teaName);
									
					//进行关键字查找
					TestClassDaoProxy tcdp=new TestClassDaoProxy();
					List<TestClassView> testClassViewList=new ArrayList<TestClassView>();
					int teaId=0;
					if(!(t==null)){
						teaId=t.getId();
						testClassViewList=tcdp.findByFourCondition(testClassName, schoolArea, teaId, limitNum);
					}else{
						testClassViewList=tcdp.findByThreeCondition(testClassName, schoolArea, limitNum);
					}
					
					if(!testClassViewList.isEmpty()){
						inf="查询到结果，信息如下";
						request.setAttribute("queryList", testClassViewList);
					}else{
						inf="没有查询到结果";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("inf", inf);
		request.getRequestDispatcher(path).forward(request, response);
	}

}
