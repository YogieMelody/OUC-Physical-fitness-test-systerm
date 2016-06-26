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
import com.sports.dao.proxy.NationDaoProxy;
import com.sports.dao.proxy.StudentDaoProxy;
import com.sports.dao.proxy.TestScoreDaoProxy;
import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.Student;
import com.sports.entity.TestScoreView;
import com.sports.entity.TestTerm;
import com.sports.service.ExcelOperator;
import com.sports.util.GetHash;

public class MstudentServlet extends HttpServlet {

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

		String path = "/BackStage/Mstudent.jsp";
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

		String path = "/BackStage/Mstudent.jsp";
		int result = 0;
		String inf = null;

		String doWhat = request.getParameter("hid");
		// 判断是下方的form还是上方显示数据列表的form
		if ("".equals(doWhat) || doWhat == null) {
			String isDetail = request.getParameter("detail");
			String isUpdate = request.getParameter("update");
			String isDelete = request.getParameter("delete");
			String isQueryTestTerm = request.getParameter("queryTestTerm");
			String isQueryTestClass = request.getParameter("queryTestClass");
			String isQueryStu=request.getParameter("queryStu");
			if (!("".equals(isDetail) || isDetail == null)) {
				// 通过前台传进的i获取学生的具体信息填充至表单，供管理员进行，修改和进一步的查询测试学期，测试班级（成绩）
				int stuId = Integer.parseInt(request.getParameter("detail"));
				try {
					StudentDaoProxy sdp = new StudentDaoProxy();
					Student s = null;
					s = sdp.findById(stuId);
					if (s == null) {
						inf = "没找到";
					} else {
						inf = "请到下方表格进行相应操作";
					}
					// 此处改成了session是为了表单中的内容不丢失
					// setTT作用是清空测试学期表单
					HttpSession ses = request.getSession();
					ses.setAttribute("singleStu", s);

					DepartmentDaoProxy ddpU = new DepartmentDaoProxy();
					String oldDepName = ddpU.findById(
							s.getDepartmentId()).getDepartmentName();
					MajorClassDaoProxy mcdpU = new MajorClassDaoProxy();
					String oldMajName = mcdpU.findById(s.getMajorId())
							.getMajorClassName();

					NationDaoProxy ndpU = new NationDaoProxy();
					String oldNat = ndpU.getNameById(s.getNationMark());

					String birthday = s.getBithday();
					// 分割字符串
					int lastIndex=birthday.lastIndexOf("-");
					String oldYear = birthday.substring(0, 4);
					String oldMonth = birthday.substring(5, lastIndex);
					String oldDay = birthday.substring(lastIndex+1);

					ses.setAttribute("oldDep", oldDepName);
					ses.setAttribute("oldMaj", oldMajName);
					ses.setAttribute("oldNat", oldNat);
					ses.setAttribute("oldYear", oldYear);
					ses.setAttribute("oldMonth", oldMonth);
					ses.setAttribute("oldDay", oldDay);

					HttpSession sesTT = request.getSession();
					sesTT.setAttribute("queryList1", null);
					sesTT.removeAttribute("queryListTestTerm");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (!("".equals(isUpdate) || isUpdate == null)) {
				int updateId = Integer.parseInt(request.getParameter("update"));
				String stuNumber = request.getParameter("updateStuNumber");
				String stuPassword = GetHash.getMD5(stuNumber);
				String stuName = request.getParameter("updateStuName");
				String stuSex = request.getParameter("updateStuSex");

				// 如果为null则不是港澳生
				// 如果非null则说明勾选，是港澳生
				String isGat = request.getParameter("updateIsGat");
				String stuIdentification = null;
				String stuIsGat = "否";
				if ("".equals(isGat) || isGat == null) {// 值为空说明没有勾选，非港澳生
					stuIdentification = request
							.getParameter("updateStuIdentification");
				} else {
					stuIsGat = "是";
					stuIdentification = request
							.getParameter("updateStuIdentification");
				}

				String stuPhone = request.getParameter("updateStuPhone");

				// 获取部门id
				int departmentId = Integer.parseInt(request.getParameter("updateStuDep"));

				// 获取专业id
				int majorClassId = Integer.parseInt(request.getParameter("updateStuMaj"));
				
				String stuGradeNow = request.getParameter("updateGradeNow");

				// 获取民族id
				int nationId = Integer.parseInt(request.getParameter("updateStuNation"));

				// 出生日期
				String stuYear = request.getParameter("updateYear");
				String stuMonth = request.getParameter("updateMonth");
				String stuDay = request.getParameter("updateDay");
				String stuBirthday = stuYear + "-" + stuMonth + "-" + stuDay;

				String stuAddress = request.getParameter("updateStuAdd");
				String stuPolitics = request.getParameter("updateStuPol");

				// 生成Student对象
				Student s = new Student();
				s.setStuNumber(stuNumber);
				s.setStuPassword(stuPassword);
				s.setStuName(stuName);
				s.setStuSex(stuSex);
				s.setStuIdentification(stuIdentification);
				s.setStuIsGat(stuIsGat);
				s.setStuPhone(stuPhone);
				s.setDepartmentId(departmentId);
				s.setMajorId(majorClassId);
				s.setGradeNow(stuGradeNow);
				s.setNationMark(nationId);
				s.setBithday(stuBirthday);
				s.setAddress(stuAddress);
				s.setPolitics(stuPolitics);

				// 修改学生信息
				try {
					StudentDaoProxy sdp = new StudentDaoProxy();
					result = sdp.doUpdate(updateId, s);
					if (result > 0) {
						inf = "学生信息修改成功";
						//把表单信息也更新
						HttpSession ses = request.getSession();
						ses.setAttribute("singleStu", s);

						DepartmentDaoProxy ddpU = new DepartmentDaoProxy();
						String oldDepName = ddpU.findById(
								s.getDepartmentId()).getDepartmentName();
						MajorClassDaoProxy mcdpU = new MajorClassDaoProxy();
						String oldMajName = mcdpU.findById(s.getMajorId())
								.getMajorClassName();

						NationDaoProxy ndpU = new NationDaoProxy();
						String oldNat = ndpU.getNameById(s.getNationMark());

						String birthday = s.getBithday();
						// 分割字符串
						int lastIndex=birthday.lastIndexOf("-");
						String oldYear = birthday.substring(0, 4);
						String oldMonth = birthday.substring(5, lastIndex);
						String oldDay = birthday.substring(lastIndex+1);

						ses.setAttribute("oldDep", oldDepName);
						ses.setAttribute("oldMaj", oldMajName);
						ses.setAttribute("oldNat", oldNat);
						ses.setAttribute("oldYear", oldYear);
						ses.setAttribute("oldMonth", oldMonth);
						ses.setAttribute("oldDay", oldDay);

						HttpSession sesTT = request.getSession();
						sesTT.setAttribute("queryList1", null);
					} else {
						inf = "学生信息修改失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (!("".equals(isDelete) || isDelete == null)) {
				int deleteId = Integer.parseInt(isDelete);
				try {
					StudentDaoProxy sdp = new StudentDaoProxy();
					result = sdp.doDelete(deleteId);
					if (result == 1) {
						inf = "数据删除成功";
					} else {
						inf = "数据删除失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (!("".equals(isQueryTestTerm) || isQueryTestTerm == null)) {
				
				int stuId=Integer.parseInt(request.getParameter("queryTestTerm"));
				String stuGradeNow=null;
				String stuNumber=null;
				try{
					StudentDaoProxy sdp=new StudentDaoProxy();
					TestTermDaoProxy ttdp=new TestTermDaoProxy();
					Student s=sdp.findById(stuId);
					stuGradeNow=s.getGradeNow();
					stuNumber=s.getStuNumber();
					List<TestTerm> testTermList=new ArrayList<TestTerm>();
					testTermList=ttdp.findByGrade(stuGradeNow);
					if(!testTermList.isEmpty()){
						inf="该学生参与过的测试学期如下";
						HttpSession sesTestTerm=request.getSession();
						sesTestTerm.setAttribute("queryListTestTerm", testTermList);
						sesTestTerm.setAttribute("queryListTestTermStuNumber", stuNumber);
					}else{
						inf="该学生没有参加过测试学期";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			} else if (!("".equals(isQueryTestClass) || isQueryTestClass == null)) {
				int testTermId=Integer.parseInt(request.getParameter("queryTestClass"));
				String stuNumber=request.getParameter("queryListTestTermStuNumber");
				try{
					TestScoreDaoProxy tsdp=new TestScoreDaoProxy();
					TestScoreView tsv=new TestScoreView();
					tsv=tsdp.findByTestTermIdAndStuNumber(testTermId, stuNumber);
					if(!(tsv==null)){
						inf="该学生在本学期参加的测试班级及成绩如下";
						request.setAttribute("score", tsv);
					}else{
						inf="无该学生相应信息";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			} else if(!("".equals(isQueryStu)||isQueryStu==null)){
				List<Student> studentList=new ArrayList<Student>();
				String stuNumber=request.getParameter("queryStuNumber");
				String stuName=request.getParameter("queryStuName");
				String stuMajId=request.getParameter("queryStuMaj");
				String stuGradeNow=request.getParameter("queryGradeNow");
				try{
					if(!("".equals(stuMajId)||stuMajId==null)){
						StudentDaoProxy sdp=new StudentDaoProxy();
						int majId=Integer.parseInt(stuMajId);
						studentList=sdp.findByFourCondition(stuNumber, stuName, majId, stuGradeNow);
					}else{
						StudentDaoProxy sdp=new StudentDaoProxy();
						studentList=sdp.findByThreeCondition(stuNumber, stuName, stuGradeNow);
					}
					if(!studentList.isEmpty()){
						inf="符合条件的学生如下";
						request.setAttribute("condition", studentList);
					}else{
						inf="没有符合条件的学生";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} else {
			if (doWhat.equals("ExcelAndDb")) {
				String fileName = request.getParameter("fileName");
				if ("".equals(fileName) || fileName == null) {
					ExcelOperator.studentDbToExcel("学生信息.xls");
				} else {
					int successCount = 0;
					successCount = ExcelOperator.studentExcelToDb(fileName);
					inf = "成功导入" + successCount + "条数据";
					request.setAttribute("inf", inf);
				}
			} else if (doWhat.equals("create")) {
				try{
					//通过前台表单获取要单条增加的学生的相关信息
					String stuNumber=request.getParameter("stuNumber");
					//将学生学号进行MD5加密再存入数据库
					String stuPassword=GetHash.getMD5(stuNumber);
					String stuName=request.getParameter("stuName");
					String stuSex=request.getParameter("stuSex");
					String stuIdentification=request.getParameter("stuIdentification");
					//判断是否选中
					String flag=request.getParameter("isGat");
					String stuIsGat="否";
					if(flag==null||"".equals(flag)){
						stuIsGat="是";
					}
					
					String stuPhone=request.getParameter("stuPhone");
					
					//获取院系编号
					int stuDepId=Integer.parseInt(request.getParameter("stuDep"));
					
					//获取专业编号,因为前台绑定的是id值,所以可以直接获取
					int stuMajId=Integer.parseInt(request.getParameter("stuMaj"));
					
					String stuGradeNow=request.getParameter("gradeNow");
					
					//获取民族编号
					int stuNatId=Integer.parseInt(request.getParameter("stuNation"));
					
					//获取学生出生日期
					String year=request.getParameter("year");
					String month=request.getParameter("month");
					String day=request.getParameter("day");
					String stuBir=year+"-"+month+"-"+day;
					
					String stuAdd=request.getParameter("stuAdd");
					String stuPol=request.getParameter("stuPol");
					
					//生成Student对象
					Student s=new Student();
					s.setStuNumber(stuNumber);
					s.setStuPassword(stuPassword);
					s.setStuName(stuName);
					s.setStuSex(stuSex);
					s.setStuIdentification(stuIdentification);
					s.setStuIsGat(stuIsGat);
					s.setStuPhone(stuPhone);
					s.setDepartmentId(stuDepId);
					s.setMajorId(stuMajId);
					s.setGradeNow(stuGradeNow);
					s.setNationMark(stuNatId);
					s.setBithday(stuBir);
					s.setAddress(stuAdd);
					s.setPolitics(stuPol);
					
					//单条增加学生信息
					StudentDaoProxy sdp=new StudentDaoProxy();
					Student isExist=sdp.findByStuNumber(stuNumber);
					if(isExist==null){
						StudentDaoProxy sdp1=new StudentDaoProxy();
						result=sdp1.doCreate(s);
						if(result>0){
							inf="添加学生成功";
						}else{
							inf="添加学生失败";
						}
					}else{
						inf="该学生已经存在，添加失败";
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
