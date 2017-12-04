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
 * �����û���¼���Է�δ��¼������ҳ��
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
		User user = (User) session.getAttribute("user");// ��session�в����Ѵ��ڵ��û���Ϣ
		// �ҵ��û���Ϣ���ѵ�¼�����򷵻ص�¼���棬���µ�¼
		if (user == null) {
			req.getRequestDispatcher("/manager/login.jsp").forward(req, resp);// ����ת������ַ������仯
		} else {
			chain.doFilter(request, response);// �������
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
