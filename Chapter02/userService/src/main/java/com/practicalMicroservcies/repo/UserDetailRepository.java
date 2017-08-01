package com.practicalMicroservcies.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicalMicroservcies.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
	
	UserDetail findByUserId(String userId);
}
