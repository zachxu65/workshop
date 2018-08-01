package com.yxu.mvcangular.service;

import java.util.List;

import com.yxu.mvcangular.model.User;

public interface UserService {

	com.yxu.mvcangular.model.User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(User user);

}
