package com.atsjp.webDemo.service;

import java.util.LinkedList;
import java.util.List;

import com.atsjp.webDemo.dao.CustomerDao;
import com.atsjp.webDemo.dao.Userdao;
import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.entity.User;

/*
 * 
 * 处理从servlet封装过来的User对象和customer对象
 */
public class UserService {
	Userdao ud = new Userdao();
	CustomerDao cu = new CustomerDao();
	List<Customer> userlist = new LinkedList<Customer>();

	/*
	 * 
	 * 验证管理员登录信息
	 */
	public boolean checkUser(User user) {
		User u = new User();
		u = ud.getUser(user); // 根据servlet封装好的user信息，去数据库取出对应信息，将返回值保存在u对象中
		if (u != null) {
			if (user.getName().equals(u.getName())
					&& user.getPassword().equals(u.getPassword())) { // 取出servlet封装好的user对象与数据库数据核对
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * 
	 * 根据servlet返回的Customer对象，添加Customer的对象到数据库中
	 */
	public boolean addCustomer(Customer customer) {
		if (cu.addCustomer(customer)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 
	 * 删除cname对应Customer的信息
	 */
	public boolean deleteCustomer(Customer customer) {
		if (cu.deleteCustomer(customer)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 
	 * 根据servlet返回的Customer的信息，到dao层更新customer信息
	 */
	public boolean modifyCustomer(Customer customer) {
		if (cu.modifyCustomer(customer)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 
	 * 根据servlet返回的page、pageSize，进行Customer对象的数据库分页查找
	 */
	public List<Customer> getAllCustomer(int page, int pageSize) {
		userlist = cu.page(page, pageSize);
		return userlist;
	}

	/*
	 * 
	 * 根据servlet返回的Customer的name或者phone值，到dao层查找并且返回查询情况
	 */
	public Customer getCustomer(Customer customer) {
		customer = cu.getCustomer(customer);
		return customer;
	}

	/*
	 * 
	 * 根据servlet返回的cname的信息，到dao层查看customer信息是否存在
	 */
	public boolean checkNameExist(String customerName) {
		Customer customer = new Customer();
		customer.setCname(customerName);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// 姓名已存在
		} else {
			return false;// 姓名不存在
		}
	}

	/*
	 * 
	 * 根据servlet返回的cphone的信息，到dao层查看customer信息是否存在
	 */
	public boolean checkPhoneExist(String customerPhone) {
		Customer customer = new Customer();
		customer.setCphone(customerPhone);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// 手机号码已存在
		} else {
			return false;// 手机号码不存在
		}
	}
}
