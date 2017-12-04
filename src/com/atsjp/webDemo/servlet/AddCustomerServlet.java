package com.atsjp.webDemo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.service.UserService;
import com.atsjp.webDemo.utils.UUIDutils;

/**
 * 用于添加客户的相关操作
 */
@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public AddCustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 设置解码方式
		String key = request.getParameter("method");
		if ("checkNameExist".equals(key)) {
			checkNameExist(request, response);
		}
		if ("checkPhoneExist".equals(key)) {
			checkPhoneExist(request, response);
		}
		if ("addCustomer".equals(key)) {
			addCustomer(request, response);
		}
	}

	/*
	 * 
	 * 1. Ajax请求检查用户名是否可用
	 */
	protected void checkNameExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerName = request.getParameter("customerName");
		boolean exist = us.checkNameExist(customerName);
		if (exist) {
			response.getWriter().write("true"); // 存在
		} else {
			response.getWriter().write("flase");// 不存在
		}
	}

	/*
	 * 
	 * 2. Ajax请求检查手机是否可用
	 */
	protected void checkPhoneExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerPhone = request.getParameter("customerPhone");
		boolean exist = us.checkPhoneExist(customerPhone);
		if (exist) {
			response.getWriter().write("true");// 存在
		} else {
			response.getWriter().write("flase");// 不存在
		}
	}

	/*
	 * 
	 * 3. 添加客户
	 */
	protected void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("CName");
		String password = request.getParameter("CPassword1");
		// 处理性别
		String gender = "";
		try {
			gender = request.getParameter("CGender");
			if (gender.equals("1")) {
				gender = "男";
			} else {
				gender = "女";
			}
		} catch (Exception e) { // 管理员不确定性别，可以暂不填，处理radio找不到的异常
			// e.printStackTrace();
			gender = "";
		}

		String birth = request.getParameter("CBirth");
		String majority = request.getParameter("CMajority");
		// 处理jsp传回的客户兴趣选项
		String interest = request.getParameter("CInterest1");
		interest += ";" + request.getParameter("CInterest2");
		interest += ";" + request.getParameter("CInterest3");
		interest += ";" + request.getParameter("CInterest4");
		interest += ";" + request.getParameter("CInterest5");
		interest += ";" + request.getParameter("CInterest6");
		interest += ";" + request.getParameter("CInterest7");
		interest += ";" + request.getParameter("CInterest8") + ";";
		interest = interest.replaceAll("null;", "");
		String email = request.getParameter("CEmail");
		String phone = request.getParameter("CPhone");
		String id = UUIDutils.getUUID();// 获取随机UUID作为客户信息的id
		Customer tempC = new Customer(id, name, password, gender, birth, majority, interest, email, phone);
		UserService us = new UserService();
		if (us.addCustomer(tempC)) {
			request.setAttribute("message", "添加成功！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "添加失败！请修改姓名或手机号码后再次尝试！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
