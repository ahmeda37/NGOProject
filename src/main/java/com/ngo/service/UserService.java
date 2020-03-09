package com.ngo.service;

import java.util.Set;

import com.ngo.model.MyUser;

public interface UserService {
	public MyUser getUserById(long id);
	public MyUser getUserByEmail(String email);
	public Set<MyUser> getUsers();
	public void addUser(MyUser u);
	public void updateUser(MyUser u);
	public void deleteUser(long id);
}
