package com.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByUserCodeAndPassWord(String name,String password);
	
}
