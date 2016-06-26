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

import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.TestTerm;

public class MtestTermServlet extends HttpServlet {

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

		String path = "/BackStage/MtestTerm.jsp";
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

		String path = "/BackStage/MtestTerm.jsp";
		int result = 0;
		String inf = null;
		
		String doWhat = (String) request.getParameter("hid");
		// 判断是下方的form还是上方表格的form
		if ("".equals(doWhat) || doWhat == null) {// 上方
			String isUpdate=request.getParameter("update");
			if("".equals(isUpdate)||isUpdate==null){
				int deleteId=Integer.parseInt(request.getParameter("delete"));
				try{
					TestTermDaoProxy ttdp=new TestTermDaoProxy();
					result=ttdp.doDelete(deleteId);
					if(result==1){
						inf="数据删除成功";
					}else{
						inf="数据删除失败";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}else{
				int updateId=Integer.parseInt(request.getParameter("update"));
				try{
					TestTermDaoProxy ttdp=new TestTermDaoProxy();
					TestTerm tt=null;
					tt=ttdp.findById(updateId);
					if(tt==null){
						inf="没找到";
					}
					
					request.setAttribute("single", tt);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}else{//下方
			if (doWhat.equals("create")) {
				try {
					String p1 = request.getParameter("insertTestTermNameP1");
					String p2 = request.getParameter("insertTestTermNameP2");
					String p3 = request.getParameter("insertTestTermNameP3");
					String testTermName = "20" + p1 + "年" + p2 + "季学期体质测试（" + p3
							+ "）";
					TestTermDaoProxy ttdp = new TestTermDaoProxy();
					TestTerm isExist=ttdp.findByTestTermName(testTermName);
					if(isExist==null){
						TestTerm tt = new TestTerm();
						TestTermDaoProxy ttdp1=new TestTermDaoProxy();
						tt.setTestTermName(testTermName);
						result = ttdp1.doCreate(tt);
						if (result > 0) {
							inf = "添加测试学期成功";
						} else {
							inf = "增加测试学期失败";
						}
					}else{
						inf="测试学期已经存在，添加失败";
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("update")) {
				try {
					String p1 = request.getParameter("updateTestTermNameP1");
					String p2 = request.getParameter("updateTestTermNameP2");
					String p3 = request.getParameter("updateTestTermNameP3");
					
					String testTermName = "20" + p1 + "年" + p2 + "季学期体质测试（" + p3
							+ "）";
					TestTermDaoProxy ttdp = new TestTermDaoProxy();
					TestTerm tOld=ttdp.findById(Integer.parseInt(request
							.getParameter("updateTestTermId")));
					TestTerm tt = new TestTerm();
					tt.setTestTermName(testTermName);
					tt.setIsNow(tOld.getIsNow());
					tt.setIsOpen(tOld.getIsOpen());
					TestTermDaoProxy ttdp1=new TestTermDaoProxy();
					result = ttdp1.doUpdate(Integer.parseInt(request
							.getParameter("updateTestTermId")), tt);
					if (result > 0) {
						inf = "更新测试学期成功";
					} else {
						inf = "更新测试学期失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("set")) {
				try {
					TestTermDaoProxy ttdp = new TestTermDaoProxy();
					result = ttdp.setNow(Integer.parseInt(request
							.getParameter("setNowTestTerm")));
					if (result > 0) {
						inf = "设置当前测试学期成功";
					} else {
						inf = "设置当前测试学期失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(doWhat.equals("query")) {
				String key=request.getParameter("keyword");
				try{
					TestTermDaoProxy ttdp=new TestTermDaoProxy();
					List<TestTerm> testTermList=new ArrayList<TestTerm>();
					testTermList=ttdp.findByKeyWord(key);
					if(testTermList!=null){
						inf="查询到结果，信息如下";
						request.setAttribute("queryList", testTermList);
					}else{
						inf="没有查询到结果";
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
