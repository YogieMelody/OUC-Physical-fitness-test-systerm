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

import com.sports.dao.proxy.NoticeDaoProxy;
import com.sports.dao.proxy.TestTermDaoProxy;
import com.sports.entity.Notice;

public class MnoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "/BackStage/Mnotice.jsp";
		String login = "/BackStage/Mlogin.jsp";
		HttpSession ses = request.getSession();
		if (ses.getAttribute("manager") != null) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			request.getRequestDispatcher(login).forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "/BackStage/Mnotice.jsp";
		int result = 0;
		String inf = null;

		String doWhat = request.getParameter("hid");
		// 判断是下方的form还是上方表格的form
		if ("".equals(doWhat) || doWhat == null) {// 上方
			String isUpdate = request.getParameter("update");
			if ("".equals(isUpdate) || isUpdate == null) {
				int deleteId = Integer.parseInt(request.getParameter("delete"));
				try {
					NoticeDaoProxy ndp = new NoticeDaoProxy();
					result = ndp.doDelete(deleteId);
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
					NoticeDaoProxy ndp = new NoticeDaoProxy();
					Notice n = null;
					n = ndp.findById(updateId);
					request.setAttribute("single", n);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {// 下方
			if (doWhat.equals("create")) {
				try {
					String newTitle = request.getParameter("insertNoticeTitle");
					String newContent = request
							.getParameter("insertNoticeContent");
					Notice notice = new Notice();
					NoticeDaoProxy ndp = new NoticeDaoProxy();
					notice.setTitle(newTitle);
					notice.setContent(newContent);
					result = ndp.doCreate(notice);
					if (result > 0) {
						inf = "公告发布成功";
					} else {
						inf = "公告发布失败";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("query")) {
				String key = request.getParameter("keyword");
				try {
					NoticeDaoProxy ndp = new NoticeDaoProxy();
					List<Notice> noticeList = new ArrayList<Notice>();
					noticeList = ndp.findByKeyWord(key);
					if (noticeList != null) {
						inf = "查询到结果,公告信息如下";
						request.setAttribute("queryList", noticeList);
					} else {
						inf = "没有查询到结果";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (doWhat.equals("update")) {
				try{
					NoticeDaoProxy ndp=new NoticeDaoProxy();
					Notice notice=new Notice();
					notice.setTitle(request.getParameter("updateNoticeTitle"));
					notice.setTime(request.getParameter("updateNoticeTime"));
					notice.setContent(request.getParameter("updateNoticeContent"));
					result=ndp.doUpdate(Integer.parseInt(request.getParameter("updateNoticeId")), notice);
					if(result==1){
						inf="数据更新成功";
					}else{
						inf="数据更新失败";
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			else if (doWhat.equals("open")) {
				String openOrClose = request.getParameter("doOpen");
				if (openOrClose.equals("开放预约申请")) {
					try {
						TestTermDaoProxy ttdp = new TestTermDaoProxy();
						result = ttdp.OpenBook();
						if (result > 0) {
							inf = "开放成功";
						} else {
							inf = "开放失败,已经是开放状态";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						TestTermDaoProxy ttdp = new TestTermDaoProxy();
						result = ttdp.CloseBook();
						if (result > 0) {
							inf = "关闭成功";
						} else {
							inf = "关闭失败，已经是关闭状态";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		request.setAttribute("inf", inf);
		request.getRequestDispatcher(path).forward(request, response);
	}

}
