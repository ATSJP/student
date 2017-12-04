package com.atsjp.webDemo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.service.UserService;

/**
 * 
 * �޸�customer�ķ�װ����
 */
@WebServlet("/ModifyCustomerServlet")
public class ModifyCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyCustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("Id");
		String name = request.getParameter("CName");
		String password = request.getParameter("CPassword");
		String gender = request.getParameter("CGender");
		String birth = request.getParameter("CBirth");
		String majority = request.getParameter("CMajority");
		String interest = request.getParameter("CInterest");
		String email = request.getParameter("CEmail");
		String phone = request.getParameter("CPhone");
		Customer tempC = new Customer(id, name, password, gender, birth, majority, interest, email, phone);
		UserService us = new UserService();
		if (us.modifyCustomer(tempC)) {
			request.setAttribute("message", "�޸ĳɹ�!");
			request.setAttribute("ResultCName", tempC.getCname());
			request.getRequestDispatcher("./manager/modifyCustomerResult.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "�޸�ʧ�ܣ��볢�����������Ƿ��и��û������ٴγ��ԡ�");
			request.setAttribute("ResultCName", tempC.getCname());
			request.getRequestDispatcher("./manager/modifyCustomerResult.jsp").forward(request, response);
		}
	}

}
