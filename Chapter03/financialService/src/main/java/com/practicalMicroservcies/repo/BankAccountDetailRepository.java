package com.practicalMicroservcies.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicalMicroservcies.entity.BankAccountDetail;

public interface BankAccountDetailRepository extends JpaRepository<BankAccountDetail, Integer> {
	BankAccountDetail findByUserId(String userId);
}
