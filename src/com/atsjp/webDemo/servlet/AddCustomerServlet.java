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
 * ������ӿͻ�����ز���
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
		request.setCharacterEncoding("UTF-8");// ���ý��뷽ʽ
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
	 * 1. Ajax�������û����Ƿ����
	 */
	protected void checkNameExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerName = request.getParameter("customerName");
		boolean exist = us.checkNameExist(customerName);
		if (exist) {
			response.getWriter().write("true"); // ����
		} else {
			response.getWriter().write("flase");// ������
		}
	}

	/*
	 * 
	 * 2. Ajax�������ֻ��Ƿ����
	 */
	protected void checkPhoneExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerPhone = request.getParameter("customerPhone");
		boolean exist = us.checkPhoneExist(customerPhone);
		if (exist) {
			response.getWriter().write("true");// ����
		} else {
			response.getWriter().write("flase");// ������
		}
	}

	/*
	 * 
	 * 3. ��ӿͻ�
	 */
	protected void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("CName");
		String password = request.getParameter("CPassword1");
		// �����Ա�
		String gender = "";
		try {
			gender = request.getParameter("CGender");
			if (gender.equals("1")) {
				gender = "��";
			} else {
				gender = "Ů";
			}
		} catch (Exception e) { // ����Ա��ȷ���Ա𣬿����ݲ������radio�Ҳ������쳣
			// e.printStackTrace();
			gender = "";
		}

		String birth = request.getParameter("CBirth");
		String majority = request.getParameter("CMajority");
		// ����jsp���صĿͻ���Ȥѡ��
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
		String id = UUIDutils.getUUID();// ��ȡ���UUID��Ϊ�ͻ���Ϣ��id
		Customer tempC = new Customer(id, name, password, gender, birth, majority, interest, email, phone);
		UserService us = new UserService();
		if (us.addCustomer(tempC)) {
			request.setAttribute("message", "��ӳɹ���");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "���ʧ�ܣ����޸��������ֻ�������ٴγ��ԣ�");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
