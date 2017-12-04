package com.atsjp.webDemo.filter;

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

import com.atsjp.webDemo.entity.User;

/**
 * 
 * 过滤用户登录，以防未登录进入主页面
 */
@WebFilter("/manager/main.jsp")
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");// 在session中查找已存在的用户信息
		// 找到用户信息则已登录，否则返回登录界面，重新登录
		if (user == null) {
			req.getRequestDispatcher("/manager/login.jsp").forward(req, resp);// 请求转发，地址栏不会变化
		} else {
			chain.doFilter(request, response);// 请求放行
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
