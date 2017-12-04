package com.atsjp.webDemo.service;

import java.util.LinkedList;
import java.util.List;

import com.atsjp.webDemo.dao.CustomerDao;
import com.atsjp.webDemo.dao.Userdao;
import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.entity.User;

/*
 * 
 * �����servlet��װ������User�����customer����
 */
public class UserService {
	Userdao ud = new Userdao();
	CustomerDao cu = new CustomerDao();
	List<Customer> userlist = new LinkedList<Customer>();

	/*
	 * 
	 * ��֤����Ա��¼��Ϣ
	 */
	public boolean checkUser(User user) {
		User u = new User();
		u = ud.getUser(user); // ����servlet��װ�õ�user��Ϣ��ȥ���ݿ�ȡ����Ӧ��Ϣ��������ֵ������u������
		if (u != null) {
			if (user.getName().equals(u.getName())
					&& user.getPassword().equals(u.getPassword())) { // ȡ��servlet��װ�õ�user���������ݿ����ݺ˶�
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
	 * ����servlet���ص�Customer�������Customer�Ķ������ݿ���
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
	 * ɾ��cname��ӦCustomer����Ϣ
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
	 * ����servlet���ص�Customer����Ϣ����dao�����customer��Ϣ
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
	 * ����servlet���ص�page��pageSize������Customer��������ݿ��ҳ����
	 */
	public List<Customer> getAllCustomer(int page, int pageSize) {
		userlist = cu.page(page, pageSize);
		return userlist;
	}

	/*
	 * 
	 * ����servlet���ص�Customer��name����phoneֵ����dao����Ҳ��ҷ��ز�ѯ���
	 */
	public Customer getCustomer(Customer customer) {
		customer = cu.getCustomer(customer);
		return customer;
	}

	/*
	 * 
	 * ����servlet���ص�cname����Ϣ����dao��鿴customer��Ϣ�Ƿ����
	 */
	public boolean checkNameExist(String customerName) {
		Customer customer = new Customer();
		customer.setCname(customerName);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// �����Ѵ���
		} else {
			return false;// ����������
		}
	}

	/*
	 * 
	 * ����servlet���ص�cphone����Ϣ����dao��鿴customer��Ϣ�Ƿ����
	 */
	public boolean checkPhoneExist(String customerPhone) {
		Customer customer = new Customer();
		customer.setCphone(customerPhone);
		Customer tempc = cu.getCustomer(customer);
		if (tempc.getCname() != null && tempc.getCname() != "") {
			return true;// �ֻ������Ѵ���
		} else {
			return false;// �ֻ����벻����
		}
	}
}
