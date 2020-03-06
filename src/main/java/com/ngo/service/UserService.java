package com.ngo.service;

import java.util.Set;

import com.ngo.model.User;

public interface UserService {
	public User getUserById(long id);
	public Set<User> getUsers();
	public void addUser(User u);
	public void updateUser(User u);
	public void deleteUser(long id);
}
