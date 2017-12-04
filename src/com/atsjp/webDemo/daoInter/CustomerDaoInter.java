package com.atsjp.webDemo.daoInter;

import java.util.List;

import com.atsjp.webDemo.entity.Customer;

public interface CustomerDaoInter {
	// 增加customer对象
	public boolean addCustomer(Customer customer);

	// 删除customer对象
	public boolean deleteCustomer(Customer customer);

	// 修改customer对象
	public boolean modifyCustomer(Customer customer);

	// 根据返回的customer对象的cname或者cphone，查找customer对象
	public Customer getCustomer(Customer customer);

	// 返回所有customer对象
	public List<Customer> page(int page, int pageSize);

	// 返回customer总记录数
	public int getCount();
}
