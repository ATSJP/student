package com.atsjp.webDemo.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.entity.Page;
import com.atsjp.webDemo.service.UserService;

/**
 * 
 * �����ҳ��ȡcustomers����
 */
@WebServlet("/GetPageCustomerServlet")
public class GetPageCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Customer> tempCu = new LinkedList<Customer>();
	private Page pageService = new Page();
	private UserService us = new UserService();

	public GetPageCustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����ǰ̨���ص�����,first,down��up,last�ֱ��ʾ��ҳ����һҳ����һҳ,ĩҳ��0,1��2��3...��ʾ�ڼ�ҳ
		String page = "";
		try {
			page = request.getParameter("page");
			page = page.replaceAll(" ", "");// ȥ���ַ����еĿո�
		} catch (Exception e) {
			page = "0";
		}
		// ������ݿ��Ƿ��и��£����Ҹ���
		pageService.getNew();
		// ά����ǰҳ
		int pageSize = pageService.getPageSize();
		int currentPage = pageService.getCurrentPage();
		int totalCount = pageService.getTotalCount();
		if ("down".equals(page) || "up".equals(page) || "first".equals(page) || "last".equals(page)) {
			if ("up".equals(page)) {
				currentPage -= pageSize;
			}
			if ("down".equals(page)) {
				currentPage += pageSize;
			}
			if ("first".equals(page)) {
				currentPage = 0;
			}
			if ("last".equals(page)) {
				currentPage = (pageService.getPage() - 1) * pageSize;
			}
		} else {
			currentPage = (Integer.valueOf(page) - 1) * pageSize;
		}
		// ���м�飬�Է�currentPageԽ��
		if (currentPage < 0) {
			currentPage = 0;
		}

		if (currentPage >= totalCount) {
			currentPage = (pageService.getPage() - 1) * pageSize;
		}
		pageService.setCurrentPage(currentPage);
		// ���в���customer��Ϣ
		tempCu = us.getAllCustomer(currentPage, pageSize);
		// ���������߼������ϵ��ܼ�¼,��ҳ��,��һҳ����ҳ������ǰҳ,��ʼ��ӡ�ͽ�����ӡҳ
		int beginPage = currentPage / pageSize + 1 - 5;// ��ǰҳ��ǰ���ӡ5ҳ
		int endPage = currentPage / pageSize + 1 + 5;// ��ǰҳ�����ӡ5ҳ
		// ��ʼ�ͽ���ҳ����Խ��
		if (beginPage <= 0) {
			beginPage = 1;
		}
		if (endPage > pageService.getPage()) {
			endPage = pageService.getPage();
		}
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("lastPage", pageService.getPage());
		request.setAttribute("firstPage", 1);
		request.setAttribute("currentPage", currentPage / pageSize + 1);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("endPage", endPage);
		if (!tempCu.isEmpty()) {
			request.setAttribute("CustomerList", tempCu);
			request.getRequestDispatcher("./manager/getAllCustomer.jsp").forward(request, response);
		} else {
			request.setAttribute("CustomerList", null);
			request.getRequestDispatcher("./manager/getAllCustomer.jsp").forward(request, response);
		}
	}
}
