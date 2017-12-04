package com.atsjp.webDemo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atsjp.webDemo.entity.User;
import com.atsjp.webDemo.service.UserService;

/**
 * 用于与管理员，相关操作的响应
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public UserServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String key = request.getParameter("method");
		if ("login".equals(key)) {
			toCilentUI(request, response);
		}
		if ("logout".equals(key)) {
			logout(request, response);
		}
	}

	/**
	 * 
	 * 1.用户登录
	 */
	protected void toCilentUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 获得请求中的值
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		// 将对应值封装到user对象中去
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		// 调用service层，检查用户登录信息
		if (us.checkUser(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // 保存用户信息到session中
			request.setAttribute("message", "登录成功,正在自动跳转...");
			// response.sendRedirect("./manager/main.jsp"); // 重定向
			request.getRequestDispatcher("./manager/main.jsp").forward(request, response);
		} else {
			// HttpSession session = request.getSession();
			request.setAttribute("message", "登录失败，您的用户名或者密码输入错误，请重新输入。");
			// response.sendRedirect("./manager/login.jsp"); // 重定向
			request.getRequestDispatcher("./manager/login.jsp").forward(request, response);
		}
	}

	/*
	 * 
	 * 2.用户退出
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获得session
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		// 销毁session
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/manager/login.jsp");
	}

}
