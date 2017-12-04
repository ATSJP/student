package com.atsjp.webDemo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.service.UserService;

/*
 * 
 * 处理删除customer的信息
 */
@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public DeleteCustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cName = request.getParameter("cname");
		cName = new String(cName.trim().getBytes("ISO-8859-1"), "UTF-8"); // 解决url获取中文乱码问题
		Customer tempC = new Customer();
		tempC.setCname(cName);
		if (us.deleteCustomer(tempC)) {
			request.setAttribute("deleteResult", "删除成功！");
			request.getRequestDispatcher("./manager/getAllCustomer.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("deleteResult", "删除失败！");
			request.getRequestDispatcher("./manager/getAllCustomer.jsp")
					.forward(request, response);
		}

	}

}
