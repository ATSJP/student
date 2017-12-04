package com.atsjp.webDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.atsjp.webDemo.daoInter.CustomerDaoInter;
import com.atsjp.webDemo.entity.Customer;
import com.atsjp.webDemo.utils.JDBC;

public class CustomerDao implements CustomerDaoInter {
	private Connection conn = JDBC.getConnection();
	private PreparedStatement ps = null;
	private ResultSet res = null;

	/**
	 * 
	 * 增加customer对象
	 */
	@Override
	public boolean addCustomer(Customer customer) {
		try {
			String sql = "select * from customers where cname = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getCname());
			res = ps.executeQuery();
			if (res.next()) { // 存在相同用户名，
				return false;
			}
			sql = "select * from customers where cphone = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getCphone());
			res = ps.executeQuery();
			if (res.next()) {// 存在相同手机号码，
				return false;
			} else {
				String sql1 = "insert into customers values(?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql1);
				ps.setString(1, customer.getId());
				ps.setString(2, customer.getCname());
				ps.setString(3, customer.getCpassword());
				ps.setString(4, customer.getCgender());
				ps.setString(5, customer.getCbirth());
				ps.setString(6, customer.getCmajority());
				ps.setString(7, customer.getCinterest());
				ps.setString(8, customer.getCemail());
				ps.setString(9, customer.getCphone());
				ps.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 
	 * 删除customer对象
	 */
	@Override
	public boolean deleteCustomer(Customer customer) {
		String sql = "delete from customers where cname=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getCname());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * 
	 * 修改customer对象
	 */
	@Override
	public boolean modifyCustomer(Customer customer) {
		String sql = "update customers set cname=?,cpassword=?,cgender=?,cbirth=?,cmajority=?,cinterest=?,cemail=?,cphone=? where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getCname());
			ps.setString(2, customer.getCpassword());
			ps.setString(3, customer.getCgender());
			ps.setString(4, customer.getCbirth());
			ps.setString(5, customer.getCmajority());
			ps.setString(6, customer.getCinterest());
			ps.setString(7, customer.getCemail());
			ps.setString(8, customer.getCphone());
			ps.setString(9, customer.getId());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * 
	 * 根据返回的customer对象的cname或者cphone，查找customer对象
	 */
	@Override
	public Customer getCustomer(Customer customer) {
		Customer tempC = new Customer();
		String index = "";
		String sql = "";
		boolean flag = false;
		if (customer.getCname() != null && !customer.getCname().equals("")) {// 如果两者都有优先cname查找
			index = customer.getCname();
			flag = true; // 用于标记index被赋予了cname
		} else if (customer.getCphone() != null
				&& !customer.getCphone().equals("")) {
			index = customer.getCphone();
			flag = false;// 用于标记index被赋予了cphone
		}
		if (!index.equals("") && flag) {
			sql = "select * from customers where cname=?";
		} else if (!index.equals("") && !flag) {
			sql = "select * from customers where cphone=?";
		} else {
			return null;
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, index);
			res = ps.executeQuery();
			while (res.next()) {
				tempC = new Customer(res.getString(1), res.getString(2),
						res.getString(3), res.getString(4), res.getString(5),
						res.getString(6), res.getString(7), res.getString(8),
						res.getString(9));
			}
			return tempC;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * 
	 * 返回所有customer对象
	 */
	@Override
	public List<Customer> page(int page, int pageSize) {
		List<Customer> tempc = new LinkedList<Customer>();
		try {
			String sql = "select * from customers limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, page);
			ps.setInt(2, pageSize);
			// 3.执行SQL语句
			res = ps.executeQuery();
			// 4.访问结果集
			while (res.next()) {
				tempc.add(new Customer(res.getString(1), res.getString(2), res
						.getString(3), res.getString(4), res.getString(5), res
						.getString(6), res.getString(7), res.getString(8), res
						.getString(9)));
			}
			return tempc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 
	 * 返回customer总记录数
	 */
	@Override
	public int getCount() {
		int count = 0;
		String sql = "select count(*) from customers";
		try {
			ps = conn.prepareStatement(sql);
			res = ps.executeQuery();
			while (res.next()) {
				count = Integer.valueOf(res.getString(1));
			}
			return count;
		} catch (Exception e) {
			return 0;
		}
	}
}
