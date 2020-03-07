package com.ngo.service;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngo.model.User;
import com.ngo.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Set<User> getUsers() {
		// TODO Auto-generated method stub
		return new TreeSet<User>(repo.findAll());
	}

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		repo.save(u);
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		repo.save(u);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
