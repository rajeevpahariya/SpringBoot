package com.spring.user.repo;

import org.springframework.data.repository.CrudRepository;

import com.spring.user.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}
