package com.sports.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sports.entity.Teacher;

/**
 * Servlet Filter implementation class TeaLoginFilter
 */
@WebFilter("/TeaLoginFilter")
public class TeaLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public TeaLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// 通过类型转换后才能进行后续的客户端跳转
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 定义未登录时跳转回的页面
		String fail = req.getContextPath() + "/User/login.jsp";

		HttpSession ses = req.getSession();
		if (ses.getAttribute("teacher") != null) {
			Teacher teacher = (Teacher) ses.getAttribute("teacher");
			if (teacher.getTeaNumber() != null) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(fail);
			}
		} else {
			resp.sendRedirect(fail);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
