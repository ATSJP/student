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
 * ���������Ա����ز�������Ӧ
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
	 * 1.�û���¼
	 */
	protected void toCilentUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ��������е�ֵ
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		// ����Ӧֵ��װ��user������ȥ
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		// ����service�㣬����û���¼��Ϣ
		if (us.checkUser(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // �����û���Ϣ��session��
			request.setAttribute("message", "��¼�ɹ�,�����Զ���ת...");
			// response.sendRedirect("./manager/main.jsp"); // �ض���
			request.getRequestDispatcher("./manager/main.jsp").forward(request, response);
		} else {
			// HttpSession session = request.getSession();
			request.setAttribute("message", "��¼ʧ�ܣ������û���������������������������롣");
			// response.sendRedirect("./manager/login.jsp"); // �ض���
			request.getRequestDispatcher("./manager/login.jsp").forward(request, response);
		}
	}

	/*
	 * 
	 * 2.�û��˳�
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ���session
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		// ����session
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/manager/login.jsp");
	}

}
