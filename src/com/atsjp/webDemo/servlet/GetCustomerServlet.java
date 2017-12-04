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

/**
 * 
 * 获得单个customer对象，并进行操作
 */
@WebServlet("/GetCustomerServlet")
public class GetCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Customer tempC = new Customer();
	private Customer ResultC = new Customer();
	private UserService us = new UserService();

	public GetCustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String index = request.getParameter("cindex");
		try {
			String encoding = request.getParameter("encoding");
			if (encoding.equals("ISO-8859-1")) {
				index = new String(index.trim().getBytes("ISO-8859-1"), "UTF-8"); // 解决url获取中文乱码问题
			}
		} catch (Exception e) {
			// 由于跳转过来的编码方式不确定，可能出现encoding无值
		}
		if (index != "" && index != null) {
			// 判断index是姓名还是手机号码
			boolean flag = false;
			for (int i = 0; i < index.length(); i++) {
				flag = Character.isDigit(index.charAt(i));
				if (!flag) {
					break;
				}
			}
			if (!flag) {
				tempC.setCname(index);
				tempC.setCphone(null);
			} else {
				tempC.setCname(null);
				tempC.setCphone(index);
			}
			// 取出customer返回给jsp
			ResultC = us.getCustomer(tempC);
			if (ResultC.getId() != null) {
				request.setAttribute("Customer", ResultC);
				// response.sendRedirect("./manager/getCustomer.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./manager/modifyCustomer.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("result", index);
				// response.sendRedirect("./manager/getCustomer.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./manager/modifyCustomer.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("result", index);
			// response.sendRedirect("./manager/getCustomer.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./manager/modifyCustomer.jsp");
			dispatcher.forward(request, response);
		}

	}
}
