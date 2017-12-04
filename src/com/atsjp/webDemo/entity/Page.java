package com.atsjp.webDemo.entity;

import com.atsjp.webDemo.dao.CustomerDao;

public class Page {
	private int Page = 0;// ��ҳ��
	private int TotalCount = 0;// �ܼ�¼��
	private int CurrentPage = 0;// ��ǰҳ
	private int PageSize = 7; // ÿҳ��ʾ����Ĭ��7

	public Page() {// ������ʱ�򣬳�ʼ��Page��TotalCount
		super();
	}

	public Page(int page, int currentPage, int pageSize, int totalCount) {
		super();
		Page = page;
		CurrentPage = currentPage;
		PageSize = pageSize;
		TotalCount = totalCount;
	}

	public boolean getNew() {// ��ȡ���µ����ݿ���Ϣ
		try {
			this.TotalCount = new CustomerDao().getCount();
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getPage() {
		return Page;
	}

	public void setPage(int page) {
		Page = page;
	}

	public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	@Override
	public String toString() {
		return "Page [Page=" + Page + ", CurrentPage=" + CurrentPage
				+ ", PageSize=" + PageSize + ", TotalCount=" + TotalCount + "]";
	}

}
