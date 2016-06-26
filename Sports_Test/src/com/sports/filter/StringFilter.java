package com.sports.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class StringFilter
 */
@WebFilter("/StringFilter")
public class StringFilter implements Filter {

	private String[] characterParams = null;
	private boolean OK = true;

	/**
	 * Default constructor.
	 */
	public StringFilter() {
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
		// HttpServletRequest servletrequest = (HttpServletRequest) request;
		HttpServletResponse servletresponse = (HttpServletResponse) response;
		boolean status = false;
		Enumeration<String> params = request.getParameterNames();
		String param = "";
		String paramValue = "";
		servletresponse.setContentType("text/html");
		servletresponse.setCharacterEncoding("utf-8");
		while (params.hasMoreElements()) {
			param = (String) params.nextElement();
			String[] values = request.getParameterValues(param);
			paramValue = "";
			if (OK) {// 过滤字符串为0个时 不对字符过滤
				for (int i = 0; i < values.length; i++)
					paramValue = paramValue + values[i];
				for (int i = 0; i < characterParams.length; i++)
					if (paramValue.indexOf(characterParams[i]) >= 0) {
						status = true;
						break;
					}
				if (status)
					break;
			}
		}
		// System.out.println(param+"="+paramValue+";");
		if (status) {
			PrintWriter out = servletresponse.getWriter();
			out.print("<script language='javascript'>alert(\"对不起！您输入内容含有非法字符。\");"
					// + servletrequest.getRequestURL()
					+ "window.history.go(-1);</script>");
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		FilterConfig f = null;
		f = fConfig;
		if(f.getInitParameter("characterParams")!=null){
			//System.out.println(f.getInitParameter("characterParams"));
			if (f.getInitParameter("characterParams").length() < 1){
				OK = false;
			}
			else{
				this.characterParams = f.getInitParameter("characterParams").split(",");
			}
		}
	}
}
