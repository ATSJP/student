package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.User;

public interface UserDaoInter {
	// ���ӹ���Ա
	public boolean addUser(User user);

	// ɾ������Ա
	public boolean deleteUser(User user);

	// �޸Ĺ���Ա
	public boolean modifyUser(User user);
	
	//���ҹ���Ա
	public User getUser(User user);
	
}
