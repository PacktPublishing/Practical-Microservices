package com.practicalMicroservcies.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicalMicroservcies.entity.BankAccountDetail;
import com.practicalMicroservcies.entity.ObligationDetails;

public interface ObligationRepository extends JpaRepository<ObligationDetails, Integer> {
	
	ObligationDetails findByUserId(String userId);
}
