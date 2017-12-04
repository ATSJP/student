package com.atsjp.webDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.atsjp.webDemo.daoInter.UserDaoInter;
import com.atsjp.webDemo.entity.User;
import com.atsjp.webDemo.utils.JDBC;

/*
 * 
 * 对于user的增删改操作定义
 */
public class Userdao implements UserDaoInter {
	private Connection conn = JDBC.getConnection();
	private PreparedStatement ps = null;
	private ResultSet res = null;

	@Override
	public boolean addUser(User user) {
		try {
			String sql = "select * from users where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			res = ps.executeQuery();
			if (res.next()) { // 存在相同用户名，
				return false;
			} else {
				String sql1 = "insert into users values(?,?,?,?)";
				ps = conn.prepareStatement(sql1);
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				ps.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		try {
			String sql = "delete from users where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		try {
			String sql = "update users set password =? where username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public User getUser(User user) { // 根据service层传回的User对象的name值查找管理员信息
		// TODO Auto-generated method stub
		User tempUser = new User();
		try {
			String sql = "select * from users where username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			res = ps.executeQuery();
			if (res.next()) {
				String username = res.getString("username");
				String password = res.getString("password");
				tempUser.setName(username);
				tempUser.setPassword(password);
				return tempUser;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e1) {

			return null;
		}
	}
}
