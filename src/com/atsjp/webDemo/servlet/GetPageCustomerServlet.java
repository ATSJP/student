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
 * 处理分页获取customers队列
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
		// 处理从前台传回的数据,first,down，up,last分别表示首页，上一页，下一页,末页，0,1，2，3...表示第几页
		String page = "";
		try {
			page = request.getParameter("page");
			page = page.replaceAll(" ", "");// 去掉字符串中的空格
		} catch (Exception e) {
			page = "0";
		}
		// 检查数据库是否有更新，并且更新
		pageService.getNew();
		// 维护当前页
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
		// 进行检查，以防currentPage越界
		if (currentPage < 0) {
			currentPage = 0;
		}

		if (currentPage >= totalCount) {
			currentPage = (pageService.getPage() - 1) * pageSize;
		}
		pageService.setCurrentPage(currentPage);
		// 进行查找customer信息
		tempCu = us.getAllCustomer(currentPage, pageSize);
		// 传回真正逻辑意义上的总记录,总页数,第一页（首页），当前页,开始打印和结束打印页
		int beginPage = currentPage / pageSize + 1 - 5;// 当前页向前多打印5页
		int endPage = currentPage / pageSize + 1 + 5;// 当前页向后多打印5页
		// 开始和结束页不能越界
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
