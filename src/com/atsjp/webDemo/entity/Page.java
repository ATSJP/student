package com.atsjp.webDemo.entity;

import com.atsjp.webDemo.dao.CustomerDao;

public class Page {
	private int Page = 0;// 总页数
	private int TotalCount = 0;// 总记录数
	private int CurrentPage = 0;// 当前页
	private int PageSize = 7; // 每页显示数据默认7

	public Page() {// 创建的时候，初始化Page，TotalCount
		super();
	}

	public Page(int page, int currentPage, int pageSize, int totalCount) {
		super();
		Page = page;
		CurrentPage = currentPage;
		PageSize = pageSize;
		TotalCount = totalCount;
	}

	public boolean getNew() {// 获取最新的数据库信息
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
