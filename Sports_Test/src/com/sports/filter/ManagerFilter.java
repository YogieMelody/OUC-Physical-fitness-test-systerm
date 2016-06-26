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
import javax.servlet.http.HttpSession;

import com.sports.entity.ManagerLogin;

/**
 * Servlet Filter implementation class ManagerFilter
 */
@WebFilter("/ManagerFilter")
public class ManagerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ManagerFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession ses=req.getSession();
		if(ses.getAttribute("manager")!=null){
			ManagerLogin managerLogin=(ManagerLogin)ses.getAttribute("manager");
			if(managerLogin.getManagerId()!=null){
				chain.doFilter(request, response);
			}else{
				request.getRequestDispatcher("/User/login.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/User/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
