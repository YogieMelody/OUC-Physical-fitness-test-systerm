package com.sports.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.sports.entity.TestTerm;
import com.sports.util.GetHash;

public class MteacherServlet extends HttpServlet {

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

		String path = "/BackStage/Mteacher.jsp";
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

		String path = "/BackStage/Mteacher.jsp";
		int result = 0;
		String inf = null;

		String doWhat = request.getParameter("hid");
		// 判断是下方的form还是显示数据列表的form
		if ("".equals(doWhat) || doWhat == null) {
			String isDetail=request.getParameter("detail");
			String isUpdate=request.getParameter("update");
			String isDelete=request.getParameter("delete");
			String isQueryOrganize=request.getParameter("queryOrganize");
			String isQueryTestClass=request.getParameter("queryTestClass");
			if(!("".equals(isDetail)||isDetail==null)){
				//通过前台传进的id获取教师的具体信息填充至表单，供管理员进行删除，修改和查询组织过的测试班级
				int teacherId = Integer.parseInt(request.getParameter("detail"));
				try {
					TeacherDaoProxy tdp = new TeacherDaoProxy();
					Teacher t = null;
					t = tdp.findById(teacherId);
					if (t == null) {
						inf = "没找到";
					} else {
						inf = "请到下方表格进行相应操作";
					}
					//此处改成了session是为了表单中的内容不丢失
					//setTT作用是清空测试学期表单
					HttpSession ses=request.getSession();
					ses.setAttribute("singleTea", t);
					HttpSession sesTT=request.getSession();
					sesTT.setAttribute("queryList1", null);
					//request.setAttribute("single", t);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(!("".equals(isUpdate)||isUpdate==null)){
				int updateId=Integer.parseInt(isUpdate);
				try{
					//通过前台表单获取要修改的教师的相关信息
					String teaNumber=request.getParameter("teaNumberDetail");
					String teaName=request.getParameter("teaNameDetail");
					String teaSex=request.getParameter("teaSexDetail");
					String teaPosition=request.getParameter("teaPositionDetail");
					String teaPhone=request.getParameter("teaPhoneDetail");
					String teaEmail=request.getParameter("teaEmailDetail");
					
					//生成Teacher对象
					Teacher t=new Teacher();
					t.setTeaNumber(teaNumber);
					t.setTeaName(teaName);
					t.setTeaSex(teaSex);
					t.setTeaPosition(teaPosition);
					t.setTeaPhone(teaPhone);
					t.setTeaEmail(teaEmail);
					
					//单条增加教师信息
					TeacherDaoProxy tdp=new TeacherDaoProxy();
					result=tdp.doUpdate(updateId, t);
					if(result>0){
						inf="教师信息更新成功";
					}else{                                                                           
						inf="教师信息更新失败";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(!("".equals(isDelete)||isDelete==null)){
				int deleteId=Integer.parseInt(isDelete);
				try{
					TeacherDaoProxy tdp=new TeacherDaoProxy();
					result=tdp.doDelete(deleteId);
					if(result==1){
						inf="数据删除成功";
					}else{
						inf="数据删除失败";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(!("".equals(isQueryOrganize)||isQueryOrganize==null)){
				int teaId=Integer.parseInt(isQueryOrganize);
				try{
					//此处需求分析要求列出老师参与的测试学期：解决方案如下
					//通过测试班级的teaId找出该老师参与的所有测试班级
					//再将每一个测试班级的testTermId添加到Set集合中
					//因为Set集合会自动过滤重复元素
					//这样Set集合中保存的是老师参与过的全部测试学期的Id
					//再通过TestTermDaoImlp的findById方法找到相应的测试学期
					//保存在List集合中
					
					//ITestClassDao增加方法findByTermIdAndTeaId()
					TestClassDaoProxy tcdp=new TestClassDaoProxy();
					List<TestClass> testClassList=new ArrayList<TestClass>();
					testClassList=tcdp.findByTeaIdC(teaId);
					
					Set<Integer> testTermIdList=new HashSet<Integer>();
					for(TestClass tcv:testClassList){
						testTermIdList.add(tcv.getTestTermId());
					}
					
					List<TestTerm> testTermList=new ArrayList<TestTerm>();
					TestTermDaoProxy ttdp=new TestTermDaoProxy();
					for(Integer i:testTermIdList){
						TestTerm tt=ttdp.findById(i);
						testTermList.add(tt);
					}
					//这个地方比较操蛋
					//为了防止表单被刷掉采用session 
					//为了防止表单信息交叉错乱 ，在前面点击详细时置空
					if(!testTermList.isEmpty()){
						inf="该老师参与过的测试学期如下";
						HttpSession sesTT=request.getSession();
						sesTT.setAttribute("queryList1", testTermList);
						//request.setAttribute("queryList1", testTermList);
					    //此处还要传送teaId才能进行下一步的查找
						//命名比较恶心主要为了区分
						sesTT.setAttribute("queryList1TeaId", teaId);
					}else{
						inf="该老师没有参加过测试学期";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(!("".equals(isQueryTestClass)||isQueryTestClass==null)){
				int testTermId=Integer.parseInt(request.getParameter("queryTestClass"));
				int teaId=Integer.parseInt(request.getParameter("queryList1TeaId"));
				//可以接受到值
				//System.out.println(testTermId);
				//System.out.println(teaId);
				try {
					TestClassDaoProxy tcdp0=new TestClassDaoProxy();
					int totalNum=tcdp0.findCountByTeaIdAndTestTermID(teaId, testTermId);
					TestClassDaoProxy tcdp=new TestClassDaoProxy();
					List<TestClassView> testClassViewList=new ArrayList<TestClassView>();
					testClassViewList=tcdp.findByTeaIdAndTestTermIdByLimit(teaId, testTermId,0,totalNum);
					if(!testClassViewList.isEmpty()){
						inf="该老师组织过的测试班级如下";
						request.setAttribute("queryList2", testClassViewList);
					}else{
						inf="该老师没有组织过测试班级";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}else{
			if(doWhat.equals("create")){
				try{
					//通过前台表单获取要增加的教师的相关信息
					String teaNumber=request.getParameter("teaNumber");
					//将教师工号进行MD5加密再存入数据库作为首次登陆密码，后续可进行修改 
					String teaPassword=GetHash.getMD5(teaNumber);
					String teaName=request.getParameter("teaName");
					String teaSex=request.getParameter("teaSex");
					String teaPosition=request.getParameter("teaPosition");
					String teaPhone=request.getParameter("teaPhone");
					String teaEmail=request.getParameter("teaEmail");
					
					//生成Teacher对象
					Teacher t=new Teacher();
					t.setTeaNumber(teaNumber);
					t.setTeaPassword(teaPassword);
					t.setTeaName(teaName);
					t.setTeaSex(teaSex);
					t.setTeaPosition(teaPosition);
					t.setTeaPhone(teaPhone);
					t.setTeaEmail(teaEmail);
					
					//单条增加教师信息
					TeacherDaoProxy tdp=new TeacherDaoProxy();
					Teacher isExist=tdp.findByTeaNumber(teaNumber);
					if(isExist==null){
						TeacherDaoProxy tdp1=new TeacherDaoProxy();
						result=tdp1.doCreate(t);
						if(result>0){
							inf="添加教师成功";
						}else{                                                                           
							inf="添加教师失败";
						}
					}else{
						inf="该教师已存在，添加失败";
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		//转回提交的页面
		request.setAttribute("inf", inf);
		request.getRequestDispatcher(path).forward(request, response);
	}
}
