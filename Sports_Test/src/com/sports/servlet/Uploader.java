package com.sports.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.sports.dao.proxy.TestScoreDaoProxy;
import com.sports.entity.ManagerLogin;
import com.sports.entity.TestScore;
import com.sports.service.SingleFileUpload;
import com.sports.service.TransferScore;

public class Uploader extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "/BackStage/MtestScore.jsp";
		String login = "/User/login.jsp";
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
			throws ServletException, IOException {// 上传excel然后导入成绩
	// System.out.println("进入");
		String inf = "";

		SingleFileUpload upload = new SingleFileUpload();
		upload.parseRequest(request);
		File parent = new File("C:\\upload\\");

		try {
			inf=upload.upload(parent);
		} catch (org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException e) {
			// 文件大小超出最大值
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession ses = req.getSession();
		PrintWriter pw = response.getWriter();
		if (ses.getAttribute("manager") != null) {
			pw.print("<script language='javascript'>alert('"+inf+"');window.location.href='BackStage/MtestScore.jsp';</script>");
			pw.close();
		} else {
			pw.print("<script language='javascript'>alert('"+inf+"');window.location.href='Teacher/TeacherClassView.jsp';</script>");
			pw.close();
		}
	}
}
